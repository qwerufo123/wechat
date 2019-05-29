package com.fxj.constant;

public enum SexEnum {
    BOY(1),GIRL(2),UNDEFINED(3);


    private int value;

    private SexEnum(int i) {
        value = i;
    }

    public int getValue() {
        return value;
    }


//    public static final int BOY = 1;
//    public static final int GIRL = 2;
//    public static final int UNDEFINED = 3;
}
