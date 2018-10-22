package com.avoole.im.conversation;

import com.avoole.im.BuildConfig;

public enum YWConversationType
{
    unknow(-1),

    P2P(1),

    Tribe(3),

    Custom(7),

    SHOP(16),

    HJTribe(15),

    CustomViewConversation(17);

    private final int value;

    private YWConversationType(int state)
    {
        this.value = state;
    }

    public static YWConversationType valueOf(int state)
    {
        switch (state) {
            case 1:
                return P2P;
            case 16:
                return SHOP;
            case 3:
                return Tribe;
            case 7:
                return Custom;
            case 15:
                return HJTribe;
            case 17:
                return CustomViewConversation;
            case 2:
            case 4:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
        }
        if (BuildConfig.DEBUG) {
            throw new RuntimeException("请实现会话类型的int值转枚举值");
        }
        return unknow;
    }

    public int getValue()
    {
        return this.value;
    }
}