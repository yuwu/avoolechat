package com.avoole.im.channel.message;

public interface IGeoMsg extends IMsg
{
    public double getLongitude();

    public double getLatitude();

    public String getContent();
}