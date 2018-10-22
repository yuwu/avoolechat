package com.avoole.im.core.msg;

import com.avoole.im.core.YWEnum;

public class ImageMsg extends Msg
{
    protected int size;

    protected int width;

    protected int height;

    protected String mimeType;

    protected YWEnum.SendImageResolutionType sendImageResolutionType;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public YWEnum.SendImageResolutionType getSendImageResolutionType() {
        return sendImageResolutionType;
    }

    public void setSendImageResolutionType(YWEnum.SendImageResolutionType sendImageResolutionType) {
        this.sendImageResolutionType = sendImageResolutionType;
    }
}