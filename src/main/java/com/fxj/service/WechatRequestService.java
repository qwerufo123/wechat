package com.fxj.service;

import com.alibaba.fastjson.JSONObject;

public interface WechatRequestService {

    /**
     * 请求头处理
     * @param reqMsg
     * @return
     */
    Object handle(JSONObject reqMsg);
}
