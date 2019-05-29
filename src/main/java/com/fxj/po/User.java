package com.fxj.po;

public class User {
    private String openid;

    private String nickname;

    private String realName;

    private Byte gender;

    private String avatarUrl;

    private Byte status;

    public User(String openid, String nickname, String realName, Byte gender, String avatarUrl, Byte status) {
        this.openid = openid;
        this.nickname = nickname;
        this.realName = realName;
        this.gender = gender;
        this.avatarUrl = avatarUrl;
        this.status = status;
    }

    public User() {
        super();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}