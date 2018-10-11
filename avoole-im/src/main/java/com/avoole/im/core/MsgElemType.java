package com.avoole.im.core;

/**
 * Created by wuyu on 18/9/28.
 */

public enum MsgElemType {
    kInvalid(0),
    Text,
    Pic,
    Ptt,
    Custom,
    File,
    System,
    GroupTips,
    Face,
    Location,
    GroupReport,
    FriendChange,
    ProfileChange,
    Video,
    UGC;

    private final int swigValue;

    public final int swigValue() {
        return this.swigValue;
    }

    public static MsgElemType swigToEnum(int var0) {
        MsgElemType[] var1 = (MsgElemType[])MsgElemType.class.getEnumConstants();
        if(var0 < var1.length && var0 >= 0 && var1[var0].swigValue == var0) {
            return var1[var0];
        } else {
            int var2 = (var1 = var1).length;

            for(int var3 = 0; var3 < var2; ++var3) {
                MsgElemType var4;
                if((var4 = var1[var3]).swigValue == var0) {
                    return var4;
                }
            }

            throw new IllegalArgumentException("No enum " + MsgElemType.class + " with value " + var0);
        }
    }

    private MsgElemType() {
        this.swigValue = MsgElemType.aa.a++;
    }

    private MsgElemType(int var3) {
        this.swigValue = var3;
        MsgElemType.aa.a = var3 + 1;
    }

    private MsgElemType(MsgElemType var3) {
        this.swigValue = var3.swigValue;
        MsgElemType.aa.a = this.swigValue + 1;
    }

    private static class aa {
        private static int a = 0;
    }
}
