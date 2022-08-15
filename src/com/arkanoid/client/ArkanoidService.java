package com.arkanoid.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ArkanoidService")
public interface ArkanoidService extends RemoteService {
    // Sample interface method of remote interface
   // String getMessage(String msg);

    /**
     * Utility/Convenience class.
     * Use ArkanoidService.App.getInstance() to access static instance of ArkanoidServiceAsync
     */
    public static class App {
       // private static ArkanoidServiceAsync ourInstance = GWT.create(ArkanoidService.class);

        //public static synchronized ArkanoidServiceAsync getInstance() {
       //     return ourInstance;
       // }
    }
}
