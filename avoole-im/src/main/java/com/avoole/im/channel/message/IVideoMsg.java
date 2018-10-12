package com.avoole.im.channel.message;

public interface IVideoMsg extends IMsg
{
    public long getID();

    public int getSize();

    public String getResource();

    public String getPic();

    public String getService();

    public int getDuration();

    public int getPicW();

    public int getPicH();

    public String getDegradeText();

    public String getMimeType();
}
