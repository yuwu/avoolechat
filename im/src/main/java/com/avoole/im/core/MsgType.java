package com.avoole.im.core;

/**
 * Created by wuyu on 18/9/28.
 */

public enum MsgType {
    Invalid(1),
    C2C(2),
    Group(3),
    System(4);

    private final int swigValue;

    public final int swigValue() {
        return this.swigValue;
    }

    public static MsgType swigToEnum(int var0) {
        MsgType[] var1 = (MsgType[])MsgType.class.getEnumConstants();
        if(var0 < var1.length && var0 >= 0 && var1[var0].swigValue == var0) {
            return var1[var0];
        } else {
            int var2 = (var1 = var1).length;

            for(int var3 = 0; var3 < var2; ++var3) {
                MsgType var4;
                if((var4 = var1[var3]).swigValue == var0) {
                    return var4;
                }
            }

            throw new IllegalArgumentException("No enum " + MsgType.class + " with value " + var0);
        }
    }

    private MsgType() {
        this.swigValue = MsgType.aa.a++;
    }

    private MsgType(int var3) {
        this.swigValue = var3;
        MsgType.aa.a = var3 + 1;
    }

    private MsgType(MsgType var3) {
        this.swigValue = var3.swigValue;
        MsgType.aa.a = this.swigValue + 1;
    }

    private static class aa {
        private static int a = 0;
    }
}
