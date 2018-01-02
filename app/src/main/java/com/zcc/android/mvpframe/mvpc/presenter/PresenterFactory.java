package com.zcc.android.mvpframe.mvpc.presenter;

/**
 * @author ZCC
 * @date 2017/12/22
 * @description Presenter 工厂
 */

public class PresenterFactory {

    public static BasePresenter getPresenter(Class<? extends BasePresenter> clazz) {
        BasePresenter presenter = null;
        try {
            presenter = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return presenter;
    }

}