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
public class user_and_history_tb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uhid;
    private Integer hid;
    private Integer guid;


    public Integer getUhid() {
        return uhid;
    }

    public void setUhid(Integer uhid) {
        this.uhid = uhid;
    }

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
}