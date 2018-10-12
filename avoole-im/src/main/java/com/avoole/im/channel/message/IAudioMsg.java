package com.avoole.im.channel.message;

public interface IAudioMsg extends IMsg
{
    public int getFileSize();

    public int getPlayTime();

    public String getContent();

    public String getMimeType();
}