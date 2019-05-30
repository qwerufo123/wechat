package com.fxj.po;

import java.util.Date;

public class CheckRecord {
    private Integer id;

    private String openid;

    private Byte type;

    private Date time;

    public CheckRecord( String openid, Byte type) {
        this.openid = openid;
        this.type = type;
        this.time = new Date();
    }

    public CheckRecord() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}