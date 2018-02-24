package com.zcc.android.mvpframe.mvpc.presenter;

import android.util.Log;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ZCC
 * @date 2017/12/22
 * @description Presenter 工厂
 */

public class PresenterFactory {

    /**
     * 根据注解中的Presenter生成对应的Presenter
     *
     * @param object 声明注解的类
     * @return 已生成的Presenter的list，用list是因为可能存在声明多个Presenter
     */
    public static Set<BasePresenter> getPresenter(Object object) {
        Set<BasePresenter> list = new HashSet<>();
        // 通过获取注解获取所需的Presenter
        CreatePresenter annotation = object.getClass().getAnnotation(CreatePresenter.class);
        if (annotation == null) {
            return list;
        }
        Class<? extends BasePresenter>[] array = annotation.value();
        for (Class<? extends BasePresenter> e : array) {
            BasePresenter presenter = getPresenter(e);
            Log.d(object.getClass().getSimpleName(), presenter.getClass().getSimpleName() + " -- 成功创建");
            list.add(presenter);
        }
        return list;
    }

    private static BasePresenter getPresenter(Class<? extends BasePresenter> clazz) {
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