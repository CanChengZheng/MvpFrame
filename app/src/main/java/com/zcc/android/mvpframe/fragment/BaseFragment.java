package com.zcc.android.mvpframe.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zcc.android.mvpframe.mvpc.contract.IBaseView;
import com.zcc.android.mvpframe.mvpc.presenter.BasePresenter;
import com.zcc.android.mvpframe.mvpc.presenter.PresenterFactory;
import com.zcc.android.mvpframe.util.ToastUtil;
import com.zcc.android.mvpframe.widget.CustomDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment 基类
 * Created by ZCC on 2017/11/4.
 * 1. 封装Presenter
 * 1. 需要时重写createPresenterByName()方法来创建Presenter
 * 2. 通过getPresenter(Class)来获取Presenter
 * 2. 封装CustomDialog
 * 1. 需要显示时，根据需要调用showLoadingDialog() / showErrorDialog()
 * 2. 关闭调用disMissDialog()进行关闭  （onDestroyView()也会调用此方法）
 */
public abstract class BaseFragment extends Fragment implements IBaseView {

    protected final String TAG = this.getClass().getSimpleName();

    private CustomDialog dialog;

    /**
     * 使用List来保存Presenter
     * 解决一个界面多个Presenter的情况
     */
    private List<BasePresenter> mPresenterList = new ArrayList<>();

    Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(bindLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        initPresenter();
        initCreate();
        return view;
    }

    /**
     * 获取绑定的布局
     */
    protected abstract int bindLayout();

    /**
     * 初始化Presenter
     */
    private void initPresenter() {
        createPresenter(createPresenterByClazz());
        attachView();
    }

    /**
     * 创建Presenter
     *
     * @param presenterClazz Presetener的Class
     *                       不定长参数，创建多个Presenter，则传入多个Class
     */
    private void createPresenter(Class<? extends BasePresenter>[] presenterClazz) {
        if (presenterClazz == null) return;
        for (Class<? extends BasePresenter> clazz : presenterClazz) {
            BasePresenter presenter = PresenterFactory.getPresenter(clazz);
            mPresenterList.add(presenter);
            Log.d(TAG, presenter.getClass().getSimpleName() + " -- 成功创建");
        }
    }

    /**
     * 返回要创建的Presenter Class
     *
     * @return 返回要创建的Presenter Class
     */
    protected Class<? extends BasePresenter>[] createPresenterByClazz() {
        return null;
    }

    /**
     * Presenter绑定V层
     */
    private void attachView() {
        // 绑定View
        for (int i = 0; i < mPresenterList.size(); i++) {
            Log.d(TAG, mPresenterList.get(i).getClass().getSimpleName() + " -- 绑定当前View");
            mPresenterList.get(i).attachView(this);
        }
    }


    /**
     * 获取对应的Presenter
     *
     * @param clazz 对应的Presenter类
     * @param <T>
     * @return 对应的Presenter
     */
    @SuppressWarnings("unchecked")
    public <T extends BasePresenter> T getPresenter(Class<? extends BasePresenter> clazz) {
        for (int i = 0; i < mPresenterList.size(); i++) {
            if (mPresenterList.get(i).getClass() == clazz) {
                Log.d(TAG, mPresenterList.get(i).getClass().getSimpleName() + " -- 成功获取");
                return (T) mPresenterList.get(i);
            }
        }
        Log.d(TAG, clazz.getSimpleName() + " -- 找不到类");
        return null;
    }


    /**
     * 初始化布局和数据
     */
    protected abstract void initCreate();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        detachView();
        disMissDialog();
    }

    /**
     * 显示加载对话框
     */
    public void showLoadingDialog() {
        showCustomDialog(CustomDialog.LOADING);
    }

    /**
     *
     */
    public void showErrorDialog() {
        showCustomDialog(CustomDialog.ERROR);
    }

    /**
     * 显示对话框
     *
     * @param layoutId 对话框的类型
     */
    private void showCustomDialog(int layoutId) {
        // 考虑多次调用的情况，先将以显示的dialog关闭
        if (dialog != null) {
            disMissDialog();
        }
        dialog = new CustomDialog(getContext(), layoutId);
        dialog.show();
        Log.d(TAG, "显示Dialog");
    }

    /**
     * 若dialog显示，则将其关闭
     */
    public void disMissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            Log.d(TAG, "关闭Dialog");
        }
    }

    /**
     * 解绑View
     */
    private void detachView() {
        for (BasePresenter e : mPresenterList) {
            Log.d(TAG, e.getClass().getSimpleName() + " -- 解除绑定");
            e.detachView();
        }
        mPresenterList.clear();
    }

    @Override
    public void resultFailure(String s) {
        disMissDialog();
        ToastUtil.showThreadToast(getContext(), s);
    }
}
