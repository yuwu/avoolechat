package com.avoole.im.channel.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IMsg
{
    public static final int MSG_DIRECTION_SEND = 0;
    public static final int MSG_DIRECTION_RECEIVE = 1;

    public long getMsgId();

    public long getTime();

    public String getAuthorId();

    public String getAuthorName();

    public int getSubType();

    public String getContent();

    public byte[] getBlob();

    public String getFrom();

    public int getSecurity();

    public List<String> getSecurityTips();

    public int getDirection();

    public int getAtFlag();

    public List<String> getAtUserList();

    public List<HashMap<String, String>> getAtMemberList();

    public boolean isAtMsgAck();

    public String getAtMsgAckUUid();

    public String getAtMsgAckUid();

    public boolean isAtMsgHasRead();

    public void setAtMsgHasRead(boolean paramBoolean);

    public void setMsgReadStatus(int paramInt);

    public int getMsgReadStatus();

    public Map<String, String> getMsgExInfo();

    public int getCustomMsgSubType();
}