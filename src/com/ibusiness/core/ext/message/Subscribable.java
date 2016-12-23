package com.ibusiness.core.ext.message;

public interface Subscribable<T> {
    void handleMessage(T message);

    String getTopic();
}
