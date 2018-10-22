package com.avoole.im.channel;

public interface ConnectListener {

    public void onSuccess();

    public void onFailure(Throwable exception);
}
