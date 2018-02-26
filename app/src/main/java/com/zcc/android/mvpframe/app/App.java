package com.zcc.android.mvpframe.app;

import android.app.Application;

/**
 * @author ZCC
 * @date 2018/2/26
 * @description
 */
public class App extends Application {
    private static App mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }

    public static App getApp() {
        return mApp;
    }
}
