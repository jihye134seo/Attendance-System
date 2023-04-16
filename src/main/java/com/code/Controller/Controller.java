package com.code.Controller;

import com.code.Entity.*;
import com.code.dto.*;
import com.code.Service.AttenderService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class Controller {

    private final AttenderService attenderService;

    //------------------------실행 테스트-----------------------
    @GetMapping(value = "api/test")
    public String test() {
        return "{name : 'icecream'}";
    }
    //------------------------실행 테스트-----------------------

    //----------------------Project API------------------------
    //API1 : 사용자가 생성한 그룹 리스트 가져오기
    @GetMapping(value = "/api/user/{uid}/groups/created")
    public List<group_tb> getGroupList(@PathVariable String uid) {
        return attenderService.getGroupList(Integer.parseInt(uid));
    }

    //API2 : 그룹 생성 & 초대코드 return
    @PostMapping(value = "/api/group")
    public String createGroup(@RequestBody CreateGroupRequest createGroupRequest) {
        return attenderService.createGroup(createGroupRequest.getUid(), createGroupRequest.getGroup_title(), createGroupRequest.getGroup_detail());
    }

    //API3 : 접속한 그룹 정보 조회 & 출석 정보 포함
    @GetMapping(value = "/api/group/{gid}/{uid}")
    public GroupInfoResponse getGroupInfo(@PathVariable String gid, @PathVariable String uid) {
        return attenderService.getGroupInfo(Integer.parseInt(gid), Integer.parseInt(uid));
    }

    //API4 : 출석 코드 생성
    @PutMapping(value = "/api/attendance")
    public void putAttendanceCode(@RequestBody PutAttendanceCodeRequest putAttendanceCodeRequest) {
        attenderService.putAttendanceCode(putAttendanceCodeRequest.getGid(), putAttendanceCodeRequest.getAcceptStartTime(), putAttendanceCodeRequest.getAcceptEndTime());
    }

    //API5 : 출석 코드 조회
    @GetMapping(value = "/api/group/{gid}/attendance")
    public String getAttendanceCode(@PathVariable String gid) {
        return attenderService.getAttendanceCode(Integer.parseInt(gid));
    }

    //API6 : 자신이 참여한 그룹 리스트 조회
    @GetMapping(value = "/api/user/{uid}/groups/joined")
    public List<GetJoinedGroupResponse> getJoinedGroupList(@PathVariable String uid) {
        return attenderService.getJoinedGroupList(Integer.parseInt(uid));
    }

    //API7 : 메인페이지 - 전체 회원수, 그룹수, 오늘 출석한 사람 수
    @GetMapping(value = "/api/main")
    public MainPageResponse getMainPageInfo() {
        return attenderService.getMainPageInfo();
    }

    //API8 : 사용자의 출석 상태 Insert
    @PostMapping(value = "/api/user/attendance")
    public String insertUserAttendance(@RequestBody InsertUserAttendanceRequest insertUserAttendanceRequest) {
        return attenderService.insertUserAttendance(insertUserAttendanceRequest.getGuid(), insertUserAttendanceRequest.getEnter_time(), insertUserAttendanceRequest.getAttendance_code());
    }

    //API9 : 사용자의 출석 상태 Update
    @PatchMapping(value = "/api/user/attendance")
    public void updateUserAttendance(@RequestBody UpdateUserAttendanceRequest updateUserAttendanceRequest) {
        attenderService.updateUserAttendance(updateUserAttendanceRequest.getGuid(), updateUserAttendanceRequest.getExit_time());
    }

    //API10 : 그룹 참가
    @PostMapping(value = "/api/group/join")
    public String insertGroupUser(@RequestBody InsertGroupUserRequest insertGroupUserRequest) {
        return attenderService.insertGroupUser(insertGroupUserRequest.getUid(), insertGroupUserRequest.getInvite_code());
    }

    //API11 : 그룹의 회원수
    @GetMapping(value = "/api/group/{gid}/count")
    public Integer getGroupUserCount(@PathVariable String gid) {
        return attenderService.getGroupUserCount(Integer.parseInt(gid));
    }


    /////////////////////////////////////////////////////////////////
    //////////////////      Authentication      /////////////////////
    /////////////////////////////////////////////////////////////////

    // 1. 회원가입
    @PostMapping(value = "/api/user/signup")
    public String signUp(@RequestBody SignUpRequest signUpRequest) {
        return attenderService.signUp(signUpRequest);
    }



    // 2. 로그인



    // 3. 로그아웃







    //API12 : 로그인
    @GetMapping(value = "/api/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return attenderService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }

    ///////////////////////////////////////////////////////////////








    //----------------------Project API------------------------










}
