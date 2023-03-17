package com.code.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//Group Table과 Mapping 됨
public class history_tb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hid;
    private Integer guid;
    private LocalDateTime enter_time;
    private LocalDateTime exit_time;
    private Character attendance_state;
    private Integer cid;
    private LocalDateTime create_date_time;


    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public Integer getGuid() {
        return guid;
    }

    public void setGuid(Integer guid) {
        this.guid = guid;
    }

    public LocalDateTime getEnter_time() {
        return enter_time;
    }

    public void setEnter_time(LocalDateTime enter_time) {
        this.enter_time = enter_time;
    }

    public LocalDateTime getExit_time() {
        return exit_time;
    }

    public void setExit_time(LocalDateTime exit_time) {
        this.exit_time = exit_time;
    }

    public Character getAttendance_state() {
        return attendance_state;
    }

    public void setAttendance_state(Character attendance_state) {
        this.attendance_state = attendance_state;
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