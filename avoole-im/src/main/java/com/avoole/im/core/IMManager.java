package com.avoole.im.core;

import android.content.Context;

import com.avoole.im.channel.Client;
import com.avoole.im.channel.impl.MqttClient;

import java.util.ArrayList;
import java.util.List;

public class IMManager {
    private String identification = "";
    private List<MessageListener> msglisteners = new ArrayList<>();

    private static IMManager INSTANCE;
    private Client client;

    private Context context;

    private IMManager(String identification) {
        this.identification = identification;
    }

    public static void init(Context context, String identification) {
        if(INSTANCE == null){
            INSTANCE = new IMManager(identification);
            INSTANCE.context = context;
        }
    }

    public static IMManager getInstance() {
        return INSTANCE;
    }

    public Client getClient() {
        if(client == null){
            client = new MqttClient();
            client.setContext(context);
        }
        return client;
    }

    public Conversation getConversation(ConversationType type, String peer) {
        Conversation conversation = new Conversation(this.identification);
        conversation.setType(type);
        conversation.setPeer(peer);
        return null;
    }

    public void addMessageListener(MessageListener listener) {
        this.msglisteners.add(listener);
    }

    public void removeMessageListener(MessageListener listener) {
        this.msglisteners.remove(listener);
    }
    public String getIdentifier() {
        return this.identification;
    }
}
