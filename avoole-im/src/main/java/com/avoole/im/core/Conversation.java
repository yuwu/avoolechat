package com.avoole.im.core;

import android.support.annotation.NonNull;

import com.avoole.im.channel.Client;
import com.avoole.im.core.msg.Msg;

public class Conversation {
    private String identifer = "";
    private String peer = "";
    private ConversationType type;
    private Client client;

    public Conversation() {
        this.type = ConversationType.Invalid;
        this.identifer = IMManager.getInstance().getIdentifier();
        this.client = IMManager.getInstance().getClient();
    }

    public Conversation(String peer) {
        this.type = ConversationType.Invalid;
        this.identifer = IMManager.getInstance().getIdentifier();
        this.peer = peer;
    }

    protected void setIdentifer(String identifer) {
        this.identifer = identifer;
    }

    protected String getIdentifer() {
        return this.identifer;
    }

    protected boolean valid() {
        return this.type != ConversationType.Invalid;
    }

    public String getPeer() {
        return this.peer;
    }

    public void setPeer(String peer) {
        this.peer = peer;
    }

    public ConversationType getType() {
        return this.type;
    }

    public void fromMsg(Msg msg) {
        this.type = ConversationType.Invalid;
        if (msg != null) {
            this.type = ConversationType.swigToEnum(msg.getConversationType());
            this.setPeer(msg.getPeer());
        }
    }

    public void setType(ConversationType var1) {
        if (var1 != null) {
            this.type = var1;
        }
    }

    public void sendMessage(@NonNull IMMessage message, @NonNull IMValueCallback<IMMessage> callback) {
        client.sendMessage(message, callback);
    }
}
