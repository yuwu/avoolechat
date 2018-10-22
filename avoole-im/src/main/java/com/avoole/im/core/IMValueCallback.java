package com.avoole.im.core;

public interface IMValueCallback<T> {
    void onError(int code, String error);

    void onSuccess(T var1);
}

