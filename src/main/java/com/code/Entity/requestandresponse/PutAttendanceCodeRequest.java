package com.code.Entity.requestandresponse;

import java.time.LocalDateTime;


public class PutAttendanceCodeRequest {


    private Integer gid;
    private LocalDateTime acceptStartTime;
    private LocalDateTime acceptEndTime;


    public Integer getGid() {
        return gid;
    }
    public void setGid(Integer GID) {
        this.gid = GID;
    }


    public LocalDateTime getAcceptStartTime() {
        return acceptStartTime;
    }
    public void setAcceptStartTime(LocalDateTime acceptStartTime) {
        this.acceptStartTime = acceptStartTime;
    }


    public LocalDateTime getAcceptEndTime() {
        return acceptEndTime;
    }

}
