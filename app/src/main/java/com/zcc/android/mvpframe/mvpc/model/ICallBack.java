package com.zcc.android.mvpframe.mvpc.model;

/**
 * Created by DeMon on 2017/11/6.
 * model接口，请求结果回调
 */
public interface ICallBack {
    /**
     * 请求成功的回调
     */
    void onSuccess(String s);

    /**
     * 请求失败的回调，一般是网络或服务器问题、或者界面关闭导致的网络请求被取消
     * @param s
     */
    void onFailure(String s);
}
