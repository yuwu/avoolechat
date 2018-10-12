package com.avoole.im.channel.message;

import java.io.Serializable;

public class YWEnum
{
    public static enum SendImageResolutionType implements Serializable
    {
        BIG_IMAGE,
        ORIGINAL_IMAGE;
    }

    public static enum ShowImageResolutionType
    {
        BIG_IMAGE,

        ORIGINAL_IMAGE,

        THUMNAIL_IMAGE;
    }
}