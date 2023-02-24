package com.code.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AttendanceCodeApi {

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer GID) {
        this.gid = GID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer gid;

    public LocalDateTime getAcceptStartTime() {
        return acceptStartTime;
    }

    public void setAcceptStartTime(LocalDateTime acceptStartTime) {
        this.acceptStartTime = acceptStartTime;
    }

    public Date getAcceptEndTime() {
        return acceptEndTime;
    }

    public void setAcceptEndTime(Date acceptEndTime) {
        this.acceptEndTime = acceptEndTime;
    }

    private LocalDateTime acceptStartTime;
    private Date acceptEndTime;


}
