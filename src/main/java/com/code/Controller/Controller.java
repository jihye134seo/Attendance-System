package com.code.Controller;

import com.code.Entity.*;
import com.code.Service.Service;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class Controller {

    private final Service service;

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
        return service.getGroupList(Integer.parseInt(uid));
    }

    //API2 : 그룹 생성 & 초대코드 return
    @PostMapping(value = "/api/group")
    public String createGroup(@RequestBody CreateGroupRequest createGroupRequest) {
        return service.createGroup(createGroupRequest.getUid(), createGroupRequest.getGroup_title(), createGroupRequest.getGroup_detail());
    }

    //API3 : 접속한 그룹 정보 조회 & 출석 정보 포함
    @GetMapping(value = "/api/group/{gid}/{uid}")
    public GroupInfoResponse getGroupInfo(@PathVariable String gid, @PathVariable String uid) {
        return service.getGroupInfo(Integer.parseInt(gid), Integer.parseInt(uid));
    }

    //API4 : 출석 코드 생성
    @PutMapping(value = "/api/attendance")
    public void putAttendanceCode(@RequestBody PutAttendanceCodeRequest putAttendanceCodeRequest) {
        service.putAttendanceCode(putAttendanceCodeRequest.getGid(), putAttendanceCodeRequest.getAcceptStartTime(), putAttendanceCodeRequest.getAcceptEndTime());
    }

    //API5 : 출석 코드 조회
    @GetMapping(value = "/api/group/{gid}/attendance")
    public String getAttendanceCode(@PathVariable String gid) {
        return service.getAttendanceCode(Integer.parseInt(gid));
    }

    //API6 : 자신이 참여한 그룹 리스트 조회
    @GetMapping(value = "/api/user/{uid}/groups/joined")
    public List<API6Response> getJoinedGroupList(@PathVariable String uid) {
        return service.getJoinedGroupList(Integer.parseInt(uid));
    }














    //API8 : 사용자의 출석 상태 Insert
    @PostMapping(value = "/api/user/attendance")
    public void insertUserAttendance(@RequestBody AttendanceInsertApi attendanceInsertApi) {
        service.insertUserAttendance(attendanceInsertApi.getGuid(), attendanceInsertApi.getEnter_time(), attendanceInsertApi.getAttendance_code());
    }


    //API9 : 사용자의 출석 상태 Update
    @PatchMapping(value = "/api/user/attendance")
    public void updateUserAttendance(@RequestBody AttendanceUpdateApi attendanceUpdateApi) {
        service.updateUserAttendance(attendanceUpdateApi.getGuid(), attendanceUpdateApi.getExit_time());
    }


    //API10 : 그룹 참가
    @ResponseBody
    @PostMapping(value = "/api/group/join")
    public void insertGroupUser(@RequestBody InsertUserGroupApi insertUserGroupApi) {
        service.insertGroupUser(insertUserGroupApi.getUid(), insertUserGroupApi.getInvite_code());
    }



    //----------------------Project API------------------------










}
