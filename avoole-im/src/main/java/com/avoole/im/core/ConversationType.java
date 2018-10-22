package com.avoole.im.core;

public enum ConversationType {
    Invalid(0),
    C2C(1),
    Group(2),
    System(3);

    private final int swigValue;

    public final int swigValue() {
        return this.swigValue;
    }

    public static ConversationType swigToEnum(int var0) {
        ConversationType[] var1 = (ConversationType[])ConversationType.class.getEnumConstants();
        if (var0 < var1.length && var0 >= 0 && var1[var0].swigValue == var0) {
            return var1[var0];
        } else {
            int var2 = (var1 = var1).length;

            for(int var3 = 0; var3 < var2; ++var3) {
                ConversationType var4;
                if ((var4 = var1[var3]).swigValue == var0) {
                    return var4;
                }
            }

            throw new IllegalArgumentException("No enum " + ConversationType.class + " with value " + var0);
        }
    }

    private ConversationType() {
        this.swigValue = ConversationType.aa.a++;
    }

    private ConversationType(int var3) {
        this.swigValue = var3;
        ConversationType.aa.a = var3 + 1;
    }

    private ConversationType(ConversationType var3) {
        this.swigValue = var3.swigValue;
        ConversationType.aa.a = this.swigValue + 1;
    }

    private static class aa {
        private static int a = 0;
    }
}
