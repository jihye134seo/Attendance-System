package com.code.Controller;

import com.code.Entity.User;
import com.code.Entity.Group;
import com.code.Entity.GroupApi;
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
        String json = "{name : 'icecream'}";
        return json;
    }
    @GetMapping(value = "/api/user")
    public List<User> getUserList() {
        return service.getUserList();
    }
    @GetMapping(value = "/api/user/{userId}")
    public String getUser(@PathVariable String userId) {
        return service.getUser(Integer.parseInt(userId));
    }
    //------------------------실행 테스트-----------------------


    //----------------------Project API------------------------
    //API1 : 사용자가 생성한 그룹 리스트 가져오기
    @GetMapping(value = "/api/user/{uid}/groups/created")
    public List<Group> getGroupList(@PathVariable String uid) {
        return service.getGroupList(Integer.parseInt(uid));
    }

    //API2 + API3 : 그룹 생성 & 초대코드 return
    @ResponseBody
    @PostMapping(value = "/api/group")
    public String createGroup(@RequestBody GroupApi groupApi) {
        return service.createGroup(groupApi.getUid(), groupApi.getGroup_title(), groupApi.getGroup_detail());
    }
    //----------------------Project API------------------------


}
