package com.vsga.app.finalprojectvsga.restapi.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutor {
    private static AppExecutor instance;
    private final ScheduledExecutorService networkIO = Executors.newScheduledThreadPool(3);

    public static AppExecutor getInstance() {
        if (instance == null) {
            instance = new AppExecutor();
        }
        return instance;
    }

    public ScheduledExecutorService getNetworkIO() {
        return networkIO;
    }
}
