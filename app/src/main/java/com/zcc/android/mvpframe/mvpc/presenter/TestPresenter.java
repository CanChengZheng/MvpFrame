package com.zcc.android.mvpframe.mvpc.presenter;

import com.zcc.android.mvpframe.mvpc.contract.TestContract;
import com.zcc.android.mvpframe.mvpc.model.ICallBack;

/**
 * @author ZCC
 * @date 2017/12/29
 * @description
 */

public class TestPresenter extends TestContract.Presenter {
    @Override
    public void request(String url) {
        getModel().get(url, getTAG(), new ICallBack() {
            @Override
            public void onSuccess(String s) {
                if (getView() != null) {
                    getView().request(s);
                }
            }

            @Override
            public void onFailure(String s) {
                if (getView()!= null) {
                    getView().onFailure(s);
                }
            }
        });
    }
}
