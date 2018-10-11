package com.avoole.im.core;

/**
 * Created by wuyu on 18/9/28.
 */


public enum MsgStatus {
    Sending(1),
    SendSucc(2),
    SendFail(3),
    HasDeleted(4),
    LocalImported(5),
    HasRevoked(6);

    private final int swigValue;

    public final int swigValue() {
        return this.swigValue;
    }

    public static MsgStatus swigToEnum(int var0) {
        MsgStatus[] var1 = (MsgStatus[])MsgStatus.class.getEnumConstants();
        if(var0 < var1.length && var0 >= 0 && var1[var0].swigValue == var0) {
            return var1[var0];
        } else {
            int var2 = (var1 = var1).length;

            for(int var3 = 0; var3 < var2; ++var3) {
                MsgStatus var4;
                if((var4 = var1[var3]).swigValue == var0) {
                    return var4;
                }
            }

            throw new IllegalArgumentException("No enum " + MsgStatus.class + " with value " + var0);
        }
    }

    private MsgStatus() {
        this.swigValue = MsgStatus.aa.a++;
    }

    private MsgStatus(int var3) {
        this.swigValue = var3;
        MsgStatus.aa.a = var3 + 1;
    }

    private MsgStatus(MsgStatus var3) {
        this.swigValue = var3.swigValue;
        MsgStatus.aa.a = this.swigValue + 1;
    }

    private static class aa {
        private static int a = 0;
    }
}

