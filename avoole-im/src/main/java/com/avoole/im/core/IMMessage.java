package com.avoole.im.core;

import com.avoole.common.Applog;
import com.avoole.im.core.msg.Msg;

public class IMMessage {

    private Conversation conversation;
    private Msg msg;

    public IMMessage(Conversation conversation) {
       this.conversation = conversation;
    }

    public IMMessage(Msg msg) {
        this.conversation = new Conversation();
        this.msg = msg;
        this.conversation.fromMsg(msg);
    }

    public Msg getMsg(){
        return msg;
    }

    public void setMsg(Msg msg){
        this.msg = msg;
    }

    public Conversation getConversation(){
        return conversation;
    }

    public String getSender() {
        if (this.msg != null) {
            String identifer = IMManager.getInstance().getIdentifier();
            return identifer.equals(this.msg.getFrom()) ? this.msg.getFrom() : this.msg.getTo();
        } else {
            Applog.i("imsdk.IMMessage", "msg is null");
            return null;
        }
    }

    public String getPeer(){
        if(this.msg != null){
            String identifer = IMManager.getInstance().getIdentifier();
            return identifer.equals(this.msg.getFrom()) ? this.msg.getTo() : this.msg.getFrom();
        }else {
            Applog.i("imsdk.IMMessage","msg is null");
            return null;
        }
    }

    public MessageType getType() {
        if(this.msg == null){
            return MessageType.kInvalid;
        }
        return MessageType.swigToEnum(msg.getType());
    }

    public boolean isSelf() {
        if(msg == null){
            return false;
        }
        String identifer = IMManager.getInstance().getIdentifier();
        return identifer.equals(this.msg.getFrom());
    }
}
