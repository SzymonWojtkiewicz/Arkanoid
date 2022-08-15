package com.arkanoid.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.arkanoid.client.ArkanoidService;

public class ArkanoidServiceImpl extends RemoteServiceServlet implements ArkanoidService {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
}