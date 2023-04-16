package com.code.Service;

import com.code.Entity.*;
import com.code.dto.GetJoinedGroupResponse;
import com.code.dto.GroupInfoResponse;
import com.code.dto.MainPageResponse;
import com.code.Repository.GroupRepository;
import com.code.Repository.UserRepository;
import com.code.dto.SignUpRequest;
import com.code.sessionAuthentication.SesseionManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.Bean;
//import org.apache.tomcat.util.json.JSONParser;
//import org.apache.tomcat.util.json.ParseException;
//import org.h2.util.json.JSONObject;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@org.springframework.stereotype.Service
public class AttenderService {

    private UserRepository userRepository;
    private GroupRepository groupRepository;

    //------------------------실행 테스트-----------------------
    public List<user_tb> getUserList() {
        return userRepository.findAll();
    }

    public String getUser(Integer userId) {
        return "{\n" +
                "\t\t\"id\": 1,\n" +
                "\t\t\"name\": \"김길동\",\n" +
                "\t\t\"age\": 16,\n" +
                "\t\t\"학교\": \"길동중\"\n" +
                "\t}";

    }
    //------------------------실행 테스트-----------------------

    //----------------------Project API------------------------
    //API1 : 사용자가 생성한 그룹 리스트 가져오기
    public List<group_tb> getGroupList(Integer uid) {
        return groupRepository.getGroupList(uid);
    }

    //API2 : 그룹 생성 & 초대코드 return
    public String createGroup(Integer uid, String groupTitle, String groupDetail) {

        String inviteCode = UUID.randomUUID().toString();; //랜덤 문자열 생성 : 초대코드
        groupRepository.createGroup(inviteCode, uid, groupTitle, groupDetail);   //그룹 생성
        return groupRepository.getInviteCode(); //초대코드 반환
    }

    //API3 : 접속한 그룹 정보 조회 & 출석 정보 포함
    public GroupInfoResponse getGroupInfo(Integer gid, Integer uid) {

        group_tb groupInfo = groupRepository.getGroupInfo(gid);
        var attendanceState = groupRepository.getAttendanceState(gid, uid);

        if(attendanceState.length == 0){
            return new GroupInfoResponse(groupInfo, "NO");
        }
        else{

            Object[] temp = (Object[])attendanceState[0];
            history_tb userHistory = groupRepository.getHistoryState((Integer)temp[1]);

            if(userHistory.getExit_time() == null){
                return new GroupInfoResponse(groupInfo, "Enter");
            }
            else{
                return new GroupInfoResponse(groupInfo, "Exit");
            }

        }
    }

    //API4 : 출석 코드 생성
    public void putAttendanceCode(Integer gid, LocalDateTime acceptStartTime, LocalDateTime acceptEndTime) {
        String attendanceCode = RandomStringUtils.randomAlphabetic(20); //랜덤 문자열 생성 : 출석코드

        groupRepository.insertAttendanceCode(attendanceCode, acceptStartTime, acceptEndTime);
        groupRepository.putGroupCid(gid);

    }

    //API5 : 출석 코드 조회
    public String getAttendanceCode(Integer gid) {
        return groupRepository.getAttendanceCode(gid);
    }

    //API6 : 자신이 참여한 그룹 리스트 조회
    public List<GetJoinedGroupResponse> getJoinedGroupList(Integer uid) {

        List<Object[]> joinedGroupList = groupRepository.getJoinedGroupList(uid);

        ArrayList<GetJoinedGroupResponse> result = new ArrayList<GetJoinedGroupResponse>();

        for(int i=0; i < joinedGroupList.size(); i++){
            Object[] temp = joinedGroupList.get(i);
            result.add(new GetJoinedGroupResponse(
                    (Integer)temp[0],
                    groupRepository.getJoinedGroupInfo((Integer)temp[2])
            ));
        }

        return result;
    }

    //API7 : 메인페이지 - 전체 회원수, 그룹수, 오늘 출석한 사람 수
    public MainPageResponse getMainPageInfo() {
        return new MainPageResponse(
                groupRepository.getAllMemberConut(),
                groupRepository.getAllGroupCount(),
                groupRepository.getTodayAttendCount()
        );
    }

    //API8 : 사용자의 출석 상태 Insert
    public String insertUserAttendance(String guid, LocalDateTime enterTime, String attendanceCode) {

        //0. cid와 일치하는지 유효성 검사 코드를 작성한다.

        Integer groupCid = groupRepository.getGroupCurrentCid(guid);
        Integer foundCid = groupRepository.getFoundCid(attendanceCode);

        if(groupCid.compareTo(foundCid) == 0) { //올바른 코드가 입력되었다면

//            groupRepository.transactionStart();

            //1. history_tb에 출석정보 추가
            groupRepository.insertUserAttendanceToHistory(guid, enterTime, groupCid);

            //2. user_and_history_tb에 출석 정보 추가
            groupRepository.insertUserAttendanceToUAH(guid);

//            groupRepository.commit();

            return "OK";
        }
        else{
            return "FAIL"; // 올바르지 않은 코드가 입력되었다면.
        }

    }

    //API9 : 사용자의 출석 상태 Update
    public void updateUserAttendance(String guid, LocalDateTime exitTime) {
        groupRepository.updateUserAttendance(guid, exitTime);
    }

    //API10 : 그룹 참가
    public String insertGroupUser(Integer uid, String userCode) {

        //0. 사용자가 입력한 초대 코드가 유효한 코드인지 확인한다.
        //1. 사용자가 입력한 초대 코드가 유효하지 않다면 FAIL
        Integer foundGid = groupRepository.getGroupInviteCode(userCode);

        if(foundGid != null) { //올바른 코드가 입력되었다면

            //1.5 이미 사용자가 그룹에 속한 사람이라면
            if(groupRepository.getIsUserInGroup(uid, foundGid) != null){
                return "Already Exist";
            }
            else{
                //2. 사용자가 입력한 초대 코드가 유효하다면 GROUP USER에 INSERT
                groupRepository.insertGroupUser(uid, foundGid);

                return "OK";
            }
        }
        else{
            //1. 사용자가 입력한 초대 코드가 유효하지 않다면 FAIL
            return "FAIL"; // 올바르지 않은 코드가 입력되었다면.
        }
    }



    //API11 : 그룹의 회원수
    public Integer getGroupUserCount(Integer gid) {
        return groupRepository.getGroupUserCount(gid);
    }







    /////////////////////////////////////////////////////////////////
    //////////////////      Authentication      /////////////////////
    /////////////////////////////////////////////////////////////////

    // 1. 회원가입
    public String signUp(SignUpRequest signUpRequest) {

        try{
            userRepository.save(user_tb.builder()
                    .password(signUpRequest.getPassword())
                    .real_name(signUpRequest.getReal_name())
                    .email_address(signUpRequest.getEmail())
                    .nick_name(signUpRequest.getNick_name())
                    .role('U')
                    .create_date_time(LocalDateTime.now())
                    .build());


            return "200 OK";

        }
        catch(Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }

    }




    SesseionManager sessionManager;

    // 2. 로그인
    public String signIn(String email, String password, HttpServletResponse response) {

        var temp = userRepository.checkSignInValidation(email, password);


        try{
            if(temp != null){
                sessionManager.createSession(userRepository.findById(temp), response);
                return "200 OK";

            }
            else{
                return "Not a Member";
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }



    }


    // 3. 로그아웃
    public String signOut(HttpServletResponse response, HttpServletRequest request) {

        try{
            sessionManager.expire(request);
            return "200 OK";
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
    }



    //4. 로그인 체크
    public String test(HttpServletRequest request) {
        Optional<user_tb> member = (Optional<user_tb>) sessionManager.getSession(request);

        if (member == null) {
            return "home";
        }

        return "loginHome";

    }


    ///////////////////////////////////////////////////////////////


    //----------------------Project API------------------------
}
