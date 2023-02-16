package com.code.Service;

import com.code.Entity.User;
import com.code.Repository.GroupRepository;
import com.code.Entity.Group;
import com.code.Repository.UserRepository;
import lombok.AllArgsConstructor;

import org.apache.commons.lang3.RandomStringUtils;


import java.util.List;

@AllArgsConstructor
@org.springframework.stereotype.Service
public class Service {

    private UserRepository userRepository;
    private GroupRepository groupRepository;

    //------------------------실행 테스트-----------------------
    public List<User> getUserList() {
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
    public List<Group> getGroupList(Integer userId) {
        return groupRepository.getGroupList(userId);
    }

    //API2 + API3 : 그룹 생성 & 초대코드 return
    public String createGroup(Integer uid, String groupTitle, String groupDetail) {

        String inviteCode = RandomStringUtils.randomAlphabetic(10); //랜덤 문자열 생성 : 초대코드
        groupRepository.createList(inviteCode, uid, groupTitle, groupDetail);   //그룹 생성
        return groupRepository.getInviteCode(); //초대코드 반환
    }
    //----------------------Project API------------------------
}
