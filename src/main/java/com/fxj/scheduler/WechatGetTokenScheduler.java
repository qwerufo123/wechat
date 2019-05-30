package com.fxj.scheduler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fxj.api.WechatApi;
import com.fxj.constant.WechatTokenConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class WechatGetTokenScheduler {

    @Value("${wechat.gettoken.granttype}")
    private String granttype;

    @Value("${wechat.appID}")
    private String appID;

    @Value("${wechat.appsecret}")
    private String appsecret;

    @Autowired
    private WechatApi wechatApi;

    /**
     * 定时刷新token
     */
    @Scheduled(fixedRate = 90 * 60 * 1000)
    private void gettoken(){
            JSONObject accessToken = wechatApi.getAccessToken(granttype, appID, appsecret);
            String access_token = accessToken.getString("access_token");
            WechatTokenConstant.accessToken = access_token;
    }

}
