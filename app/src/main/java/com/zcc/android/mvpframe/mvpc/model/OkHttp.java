package com.zcc.android.mvpframe.mvpc.model;

import okhttp3.OkHttpClient;

/**
 * @author ZCC
 * @date 2017/12/29
 * @description 单例获取OkHttpClient
 */

public class OkHttp {

    private static OkHttpClient instance;


    public static OkHttpClient getInstance() {
        if (instance == null) {
            synchronized (OkHttp.class) {
                if (instance == null) {
                    instance = new OkHttpClient();
                }
            }
        }
        return instance;
    }

}
