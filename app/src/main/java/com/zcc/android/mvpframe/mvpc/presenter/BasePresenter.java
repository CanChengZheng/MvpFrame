package com.zcc.android.mvpframe.mvpc.presenter;

import android.util.Log;

import com.zcc.android.mvpframe.mvpc.model.Model;
import com.zcc.android.mvpframe.mvpc.view.IBaseView;

/**
 * Presenter 基类
 *
 * @author ZCC
 * @date 2017/12/29
 * @description Presenter基类
 */

public abstract class BasePresenter<V extends IBaseView> {

    protected final String TAG = this.getClass().getSimpleName();

    private V mView;
    private Model mModel = Model.getInstance();

    /**
     * 绑定V层
     *
     * @param view V层
     */
    public void attachView(V view) {
        mView = view;
    }

    /**
     * 解绑V层
     */
    public void detachView() {
        interruptRequest();
        mView = null;
    }

    /**
     * 获取V层
     */
    public V getView() {
        return mView;
    }

    /**
     * 获取Model
     */
    public Model getModel() {
        return mModel;
    }

    /**
     * 获取View的完整名称
     */
    public String getTAG() {
        return mView.getClass().getName() + ":" + TAG;
    }

    /**
     * 取消网络请求
     */
    private void interruptRequest() {
        Log.d(TAG, "取消可能存在的网络请求 -- TAG -- " + getTAG());
        mModel.interruptRequest(getTAG());
    }

}
