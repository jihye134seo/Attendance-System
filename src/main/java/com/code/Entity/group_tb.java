package com.code.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//Group Table과 Mapping 됨
public class group_tb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gid;
    private String group_title;
    private String group_detail;
    private Integer leader_uid;
    private String invite_code;
    private Character availability;
    private Integer cid;
    private LocalDateTime create_date_time;




    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getGroup_title() {
        return group_title;
    }

    public void setGroup_title(String group_title) {
        this.group_title = group_title;
    }

    public String getGroup_detail() {
        return group_detail;
    }

    public void setGroup_detail(String group_detail) {
        this.group_detail = group_detail;
    }

    public Integer getLeader_uid() {
        return leader_uid;
    }

    public void setLeader_uid(Integer leader_uid) {
        this.leader_uid = leader_uid;
    }

    public String getInvite_code() {
        return invite_code;
    }

    public void setInvite_code(String invite_code) {
        this.invite_code = invite_code;
    }

    public Character getAvailability() {
        return availability;
    }

    public void setAvailability(Character availability) {
        this.availability = availability;
    }


    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public LocalDateTime getCreate_date_time() {
        return create_date_time;
    }

    public void setCreate_date_time(LocalDateTime create_date_time) {
        this.create_date_time = create_date_time;
    }
}