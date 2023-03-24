package com.code.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//Group Table과 Mapping 됨
public interface history_tb {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer hid;
//    private Integer guid;
//    private LocalDateTime enter_time;
//    private LocalDateTime exit_time;
//    private Character attendance_state;
//    private Integer cid;
//    private LocalDateTime create_date_time;


    public Integer getHid();

    public void setHid(Integer hid);

    public Integer getGuid();

    public void setGuid(Integer guid);

    public LocalDateTime getEnter_time();

    public void setEnter_time(LocalDateTime enter_time);

    public LocalDateTime getExit_time();

    public void setExit_time(LocalDateTime exit_time);

    public Character getAttendance_state();

    public void setAttendance_state(Character attendance_state);

    public Integer getCid();


    public void setCid(Integer cid);

    public LocalDateTime getCreate_date_time();

    public void setCreate_date_time(LocalDateTime create_date_time);
}