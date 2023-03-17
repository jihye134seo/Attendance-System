package com.code.Repository;

import com.code.Entity.group_tb;
import com.code.Entity.history_tb;
import com.code.Entity.user_and_history_tb;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;


@Transactional
@Repository
public interface GroupRepository extends JpaRepository<group_tb, Integer> {


    //------------------------------API1 : 사용자가 생성한 그룹 리스트 가져오기------------------------------
    @Query(value = "SELECT * FROM attender.group_tb g where g.leader_uid = :uid", nativeQuery = true)
    List<group_tb> getGroupList(@Param("uid") Integer uid);

    //---------------------------------API2 : 그룹 생성 & 초대코드 return ---------------------------------
    @Modifying
    @Query(value = "INSERT INTO attender.group_tb " +
            "(`group_title`, `group_detail`, `leader_uid`, `invite_code`, `availability`, `create_date_time`) " +
            "VALUES (:groupTitle, :groupDetail, :uid, :invite_code, 'Y', now())", nativeQuery = true)
    void createGroup(String invite_code, Integer uid, String groupTitle, String groupDetail);
    @Query(value = "SELECT g.invite_code FROM attender.group_tb g WHERE g.gid = (SELECT LAST_INSERT_ID())", nativeQuery = true)
    String getInviteCode();

    //-----------------------------API3 : 접속한 그룹 정보 조회 & 출석 정보 포함-----------------------------
    @Query(value = "SELECT * FROM attender.group_tb g where g.gid = :gid", nativeQuery = true)
    group_tb getGroupInfo(Integer gid);

    @Query(value = "SELECT * FROM attender.user_and_history_tb uh WHERE uh.guid = (SELECT gu.guid FROM attender.group_and_user_tb gu WHERE (gu.gid = :gid AND gu.uid = :uid))", nativeQuery = true)
    user_and_history_tb getAttendanceState(Integer gid, Integer uid);

    @Query(value = "SELECT * FROM attender.history_tb h where h.hid = :hid", nativeQuery = true)
    history_tb getHistoryState(Integer hid);

    //-----------------------------API4 : 출석 코드 생성-----------------------------
    @Modifying
    @Query(value = "INSERT INTO attender.code_tb " +
            "(attendance_code, accept_start_time, accept_end_time, create_date_time) " +
            "VALUES (:attendanceCode, :acceptStartTime, :acceptEndTime, now())", nativeQuery = true)
    void insertAttendanceCode(String attendanceCode, LocalDateTime acceptStartTime, LocalDateTime acceptEndTime);

    @Modifying
    @Query(value = "UPDATE attender.group_tb SET cid = (SELECT LAST_INSERT_ID()) WHERE gid = :gid", nativeQuery = true)
    void putGroupCid(Integer gid);

    //-----------------------------API5 : 출석 코드 조회-----------------------------
    //API5 : 출석 코드 조회
    @Query(value = "SELECT c.attendance_code FROM attender.code_tb c WHERE c.cid = (SELECT g.cid FROM attender.group_tb g where g.gid = :gid)", nativeQuery = true)
    String getAttendanceCode(Integer gid);















    //API6 : 자신이 참여한 그룹 리스트 조회
    @Query(value = "SELECT gu.guid, g.*" +
                    "FROM attendance_web_db.group g " +
                    "INNER JOIN attendance_web_db.group_user gu " +
                    "ON g.gid = gu.gid " +
                    "WHERE gu.uid = :userId ", nativeQuery = true)
    List<Object[]> getJoinedGroupList(Integer userId);





    //API8 : 사용자의 출석 상태 Insert
    @Modifying
    @Query(value = "INSERT INTO attendance_web_db.history " +
            "(`guid`, `enter_time`, `generate_time`, `attendance_code`) " +
            "VALUES (:guid, :enterTime, now(), :attendanceCode)", nativeQuery = true)
    void insertUserAttendance(String guid, LocalDateTime enterTime, String attendanceCode);
    @Modifying
    @Query(value = "UPDATE `attendance_web_db`.`group_user` SET `hid` = (SELECT LAST_INSERT_ID()) WHERE (`guid` = :guid)", nativeQuery = true)
    void updateHid(String guid);

    //API9 : 사용자의 출석 상태 Update
    @Modifying
    @Query(value = "UPDATE `attendance_web_db`.`history` SET `exit_time` = :exitTime, `attendance_state` = 'P' WHERE `hid` = (select hid from attendance_web_db.group_user where guid = :guid )", nativeQuery = true)
    void updateUserAttendance(String guid, LocalDateTime exitTime);



    //API10 : 그룹 참가
    @Modifying
    @Query(value = "INSERT INTO attendance_web_db.group_user " +
            "(`uid`, `gid`, `present_state`) " +
            "VALUES (:uid, (select gid from attendance_web_db.group g where g.invite_code = :userCode), 'Y')", nativeQuery = true)
    void insertGroupUser(Integer uid, String userCode);

}
