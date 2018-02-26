package com.zcc.android.mvpframe.mvpc.view;

/**
 * @author ZCC
 * @date 2017/12/29
 * @description 让所有View接口必须实现，这个接口可以什么都不做，只是用于约束类型
 */

public interface IBaseView {
    /**
     * 处理请求失败的情况
     *
     * @param s 返回的失败字符串，根据实际情况进行处理
     */
    void onFailure(String s);
}
