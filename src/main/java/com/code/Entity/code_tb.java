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
public class code_tb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;
    private Integer gid;
    private String attendance_code;
    private LocalDateTime accept_start_time;
    private LocalDateTime accept_end_time;
    private Character availability;
    private LocalDateTime create_date_time;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getAttendance_code() {
        return attendance_code;
    }

    public void setAttendance_code(String attendance_code) {
        this.attendance_code = attendance_code;
    }

    public LocalDateTime getAccept_start_time() {
        return accept_start_time;
    }

    public void setAccept_start_time(LocalDateTime accept_start_time) {
        this.accept_start_time = accept_start_time;
    }

    public LocalDateTime getAccept_end_time() {
        return accept_end_time;
    }

    public void setAccept_end_time(LocalDateTime accept_end_time) {
        this.accept_end_time = accept_end_time;
    }

    public Character getAvailability() {
        return availability;
    }

    public void setAvailability(Character availability) {
        this.availability = availability;
    }

    public LocalDateTime getCreate_date_time() {
        return create_date_time;
    }

    public void setCreate_date_time(LocalDateTime create_date_time) {
        this.create_date_time = create_date_time;
    }
}