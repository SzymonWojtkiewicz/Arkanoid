package com.arkanoid.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ArkanoidServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);
}
