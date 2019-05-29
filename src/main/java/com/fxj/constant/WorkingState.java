package com.fxj.constant;

public enum WorkingState {
    WORK(1),OFF(2);

    private byte value;

    private WorkingState(int i) {
        value = (byte) i;
    }

    public byte getValue(){
        return value;
    }
}
