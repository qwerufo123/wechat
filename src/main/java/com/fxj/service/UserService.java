package com.fxj.service;

import com.alibaba.fastjson.JSONObject;

public interface UserService {

    /**
     * 关注后获取用户信息
     * @param reqMsg
     * @return
     */
    Object subscribe(JSONObject reqMsg);

    /**
     * 取关后删除用户信息
     * @param reqMsg
     * @return
     */
    Object unsubscribe(JSONObject reqMsg);

    /**
     * 储存地址
     * @param reqMsg
     * @return
     */
    Object putLocation(JSONObject reqMsg);
}
