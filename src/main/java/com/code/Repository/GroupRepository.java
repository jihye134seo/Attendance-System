package com.code.Repository;

import com.code.Entity.Group;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Transactional
@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    @Query(value = "SELECT * FROM attendance_web_db.group g where g.master_uid = :uid", nativeQuery = true)
    public List<Group> getGroupList(@Param("uid") Integer uid);

    @Modifying
    @Query(value = "INSERT INTO attendance_web_db.group " +
            "(`invite_code`, `group_title`, `group_detail`, `create_date`, `master_uid`, `head_count`) " +
            "VALUES (:invite_code, :groupTitle, :groupDetail, now(), :uid, 1)", nativeQuery = true)
    void createList(String invite_code, Integer uid, String groupTitle, String groupDetail);

    @Query(value = "SELECT g.invite_code FROM attendance_web_db.group g WHERE g.gid = (SELECT LAST_INSERT_ID())", nativeQuery = true)
    String getInviteCode();
}
