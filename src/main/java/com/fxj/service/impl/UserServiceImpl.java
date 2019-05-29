package com.fxj.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fxj.api.WechatApi;
import com.fxj.component.UserLocation;
import com.fxj.constant.WechatLongConstant;
import com.fxj.constant.WechatTokenConstant;
import com.fxj.constant.WorkingState;
import com.fxj.dto.TextRespMsg;
import com.fxj.mapper.UserDatailMapper;
import com.fxj.mapper.UserMapper;
import com.fxj.po.User;
import com.fxj.po.UserDatail;
import com.fxj.service.UserService;
import com.fxj.vo.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.soap.Text;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private WechatApi wechatApi;

    @Autowired
    private UserLocation userLocation;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserDatailMapper userDatailMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Transactional
    @Override
    public Object subscribe(JSONObject reqMsg) {
        JSONObject userInfo = wechatApi.getUserInfo(WechatTokenConstant.accessToken, reqMsg.getString("FromUserName"), WechatLongConstant.ZH_CN);
        String openid = userInfo.getString("openid");
        String nickname = userInfo.getString("nickname");
        Byte sex = userInfo.getByte("sex");
        String headimgurl = userInfo.getString("headimgurl");
        User user = new User(openid, nickname, null, sex, headimgurl, WorkingState.OFF.getValue());
        userMapper.insert(user);
        UserDatail userDatail = new UserDatail();
        userDatail.setOpenid(openid);
        userDatailMapper.insert(userDatail);
        return new TextRespMsg(reqMsg, "恭喜\"" + nickname + "\"成为我公司一员，加油工作吧！");
    }

    @Transactional
    @Override
    public Object unsubscribe(JSONObject reqMsg) {
        String openid = reqMsg.getString("FromUserName");
        userMapper.deleteByPrimaryKey(openid);
        userDatailMapper.deleteByPrimaryKey(openid);
        return new TextRespMsg(reqMsg, "祝您日后发展更加蒸蒸日上");
    }

    @Override
    public Object putLocation(JSONObject reqMsg) {
        String openId = reqMsg.getString("FromUserName");
        Double latitude = reqMsg.getDouble("Latitude");
        Double longitude = reqMsg.getDouble("Longitude");
        Location location = new Location(latitude, longitude);
        userLocation.put(openId,location);
        logger.info("set user position: {}, {}", openId, location);
        return "success";
    }
}
