package com.avoole.im.channel;

import android.content.Context;

import com.avoole.im.core.Message;

public interface Client {

    Client setContext(Context context);

    Client setServer(String server);

    Client setPort(int port);

    Client setClientId(String clientId);

    Client setUserName(String name);

    Client setPassword(String password);

    Client setKeepAliveInterval(int keepAliveInterval);

    Client setConnectionTimeout(int connectionTimeout);

    Client setAutomaticReconnect(boolean automaticReconnect);

    void connect();

    void connect(String server, int port);

    void disconnect();

    void send(Message msg);

    boolean isConnected();
}
