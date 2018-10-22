package com.avoole.im.core;

/**
 * Created by wuyu on 18/9/28.
 */

public enum MessageType {
    kInvalid(0),
    Text,
    Image,
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
    Sound,
    UGC;

    private final int swigValue;

    public final int swigValue() {
        return this.swigValue;
    }

    public static MessageType swigToEnum(int var0) {
        MessageType[] var1 = (MessageType[])MessageType.class.getEnumConstants();
        if(var0 < var1.length && var0 >= 0 && var1[var0].swigValue == var0) {
            return var1[var0];
        } else {
            int var2 = (var1 = var1).length;
            for(int var3 = 0; var3 < var2; ++var3) {
                MessageType var4;
                if((var4 = var1[var3]).swigValue == var0) {
                    return var4;
                }
            }
            throw new IllegalArgumentException("No enum " + MessageType.class + " with value " + var0);
        }
    }

    private MessageType() {
        this.swigValue = MessageType.aa.a++;
    }

    private MessageType(int var3) {
        this.swigValue = var3;
        MessageType.aa.a = var3 + 1;
    }

    private MessageType(MessageType var3) {
        this.swigValue = var3.swigValue;
        MessageType.aa.a = this.swigValue + 1;
    }

    private static class aa {
        private static int a = 0;
    }
}
