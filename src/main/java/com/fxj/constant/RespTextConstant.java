package com.fxj.constant;

import org.aspectj.apache.bcel.generic.RET;

import java.io.*;

public class RespTextConstant {

    public static final String NOADDRESS = "未获取位置";
    public static final String NOSCOPE = "请前往公司范围内再打卡";
    public static final String CHECKIN = "打卡成功，祝新的一天工作愉快";
    public static final String CHECKOUT = "打卡成功，祝下班愉快";
    public static final String SUBSCRIBE = "恭喜%s成为我公司一员，加油工作吧！\r\n您可以输入\"介绍\"获取介绍列表信息";
    public static final String UNSUBSCRIBE = "祝您日后发展更加蒸蒸日上";

    /**
     * 详细列表
     */
    public static String FIRSTLIST;

    static{
        try {
            File file = new File("other/text");
            BufferedReader fileReader = null;

            fileReader = new BufferedReader(new FileReader(new File(file,"firstList.txt")));

            StringBuffer stringBuffer = new StringBuffer();
            String s = null;
            while ((s = fileReader.readLine())!=null){
              stringBuffer.append(s + "\r\n");
            }
            FIRSTLIST = stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能介绍
     */
    public static String TECHNIQUE;

    static{
        try {
            File file = new File("other/text");
            BufferedReader fileReader = null;

            fileReader = new BufferedReader(new FileReader(new File(file,"technique.txt")));

            StringBuffer stringBuffer = new StringBuffer();
            String s = null;
            while ((s = fileReader.readLine())!=null){
                stringBuffer.append(s + "\r\n");
            }
            TECHNIQUE = stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final String ERMEDIAID = "8l8Mt4EMh4rT7zJ5wbQEFCeN-uqYU4gBkOmRuwyB1Ak";
    public static final String FLOWCHARTMEDIAID = "8l8Mt4EMh4rT7zJ5wbQEFOt_7o3PyL7y7HvaUFUF3O4";

    public static final String APIURL = "https://docs.qq.com/doc/DZUxpdlZUWVB2Y3ds";
    public static final String DEPLOYURL = "https://docs.qq.com/doc/DZW9OQlRKRnlEWnpp";

}
