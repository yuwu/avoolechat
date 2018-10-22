package com.avoole.im.core.msg;

import com.avoole.common.Applog;
import com.avoole.im.core.IMManager;

import java.util.Map;

public abstract class Msg {

    protected String id;

    protected Object payload;

    protected String from;

    protected String to;

    protected int status;

    protected long lifetime;

    protected long time;

    protected int type;

    protected int readStatus;

    protected Map<String, String> msgExInfo;

    protected int conversationType;

    public int getConversationType() {
        return conversationType;
    }

    public void setConversationType(int conversationType) {
        this.conversationType = conversationType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getLifetime() {
        return lifetime;
    }

    public void setLifetime(long lifetime) {
        this.lifetime = lifetime;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(int readStatus) {
        this.readStatus = readStatus;
    }

    public Map<String, String> getMsgExInfo() {
        return msgExInfo;
    }

    public void setMsgExInfo(Map<String, String> msgExInfo) {
        this.msgExInfo = msgExInfo;
    }

    public String getPeer(){
        String identifer = IMManager.getInstance().getIdentifier();
        return identifer.equals(getFrom()) ? getTo() : getFrom();
    }

}
