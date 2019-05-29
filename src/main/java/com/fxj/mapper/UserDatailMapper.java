package com.fxj.mapper;

import com.fxj.po.UserDatail;

public interface UserDatailMapper {
    int deleteByPrimaryKey(String openid);

    int insert(UserDatail record);

    int insertSelective(UserDatail record);

    UserDatail selectByPrimaryKey(String openid);

    int updateByPrimaryKeySelective(UserDatail record);

    int updateByPrimaryKey(UserDatail record);
}