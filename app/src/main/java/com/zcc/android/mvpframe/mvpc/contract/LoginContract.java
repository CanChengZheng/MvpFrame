package com.zcc.android.mvpframe.mvpc.contract;

import com.zcc.android.mvpframe.mvpc.presenter.BasePresenter;

/**
 * @author ZCC
 * @date 2017/12/29
 * @description
 */

public interface LoginContract {
    interface View extends IBaseView {
        void loginResult(String s);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void loginResult(String userId, String pwd);
    }
}
