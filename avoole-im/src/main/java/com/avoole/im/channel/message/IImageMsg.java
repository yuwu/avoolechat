package com.avoole.im.channel.message;

public interface IImageMsg extends IMsg
{
    public String getImagePreUrl();

    public int getFileSize();

    public int getWidth();

    public int getHeight();

    public String getContent();

    public String getMimeType();

    public YWEnum.SendImageResolutionType getSendImageResolutionType();

    public void setSubType(int paramInt);
}