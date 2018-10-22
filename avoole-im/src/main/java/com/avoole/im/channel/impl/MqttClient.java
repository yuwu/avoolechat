package com.avoole.im.channel.impl;

import android.content.Context;

import com.avoole.im.channel.Client;
import com.avoole.im.core.Message;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class MqttClient implements Client {

    private Context context;
    private String server;
    private int port;
    private String clientId;
    private String name;
    private String password;
    private int keepAliveInterval;
    private int connectionTimeout;
    private boolean automaticReconnect = true;

    private MqttAndroidClient mqttClient;

    public MqttClient() {
    }

    @Override
    public Client setContext(Context context) {
        this.context = context;
        return this;
    }

    @Override
    public Client setServer(String server) {
        this.server = server;
        return this;
    }

    @Override
    public Client setPort(int port) {
        this.port = port;
        return this;
    }

    @Override
    public Client setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    @Override
    public Client setUserName(String name) {
        return null;
    }

    @Override
    public Client setPassword(String password) {
        return null;
    }

    @Override
    public Client setKeepAliveInterval(int keepAliveInterval) {
        return null;
    }

    @Override
    public Client setConnectionTimeout(int connectionTimeout) {
        return null;
    }

    @Override
    public Client setAutomaticReconnect(boolean automaticReconnect) {
        return null;
    }

    @Override
    public void connect(){
        if(mqttClient == null){
            mqttClient = new MqttAndroidClient(context, server, clientId);
        }
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setCleanSession(false);

        try {
            IMqttToken token = mqttClient.connect(mqttConnectOptions);
        }catch (Exception ex){

        }
    }

    @Override
    public void connect(String server, int port) {

    }

    @Override
    public void disconnect() {

    }

    @Override
    public void send(Message msg) {

    }

    @Override
    public boolean isConnected() {
        return false;
    }
}
