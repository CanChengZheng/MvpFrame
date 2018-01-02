package com.zcc.android.mvpframe.mvpc.presenter;

import com.zcc.android.mvpframe.data.Api;
import com.zcc.android.mvpframe.data.Constant;
import com.zcc.android.mvpframe.mvpc.contract.LoginContract;
import com.zcc.android.mvpframe.mvpc.model.ICallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZCC
 * @date 2017/12/29
 * @description
 */

public class LoginPresenter extends LoginContract.Presenter {
    @Override
    public void loginResult(String userId, String pwd) {
        Map<String,Object> map = new HashMap<>();
        map.put(Constant.LOGIN_ADMIN_NAME, userId);
        map.put(Constant.LOGIN_ADMIN_PASSWORD, pwd);
        getModel().post(Api.LOGIN_URL, map, getTAG(), new ICallBack() {
            @Override
            public void resultSuccess(String s) {
                if (getView() != null) {
                    getView().loginResult(s);
                }
            }

            @Override
            public void resultFailure(String s) {
                if (getView()!= null) {
                    getView().resultFailure(s);
                }
            }
        });
        interruptRequest();
    }
}
