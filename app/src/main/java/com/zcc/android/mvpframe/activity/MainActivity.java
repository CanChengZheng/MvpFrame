package com.zcc.android.mvpframe.activity;

import android.util.Log;
import android.widget.Button;

import com.zcc.android.mvpframe.R;
import com.zcc.android.mvpframe.mvpc.contract.TestContract;
import com.zcc.android.mvpframe.mvpc.presenter.CreatePresenter;
import com.zcc.android.mvpframe.mvpc.presenter.TestPresenter;

import butterknife.BindView;
import butterknife.OnClick;

@CreatePresenter({TestPresenter.class})
public class MainActivity extends BaseActivity implements TestContract.View{

    @BindView(R.id.btn_main_activity_login)
    Button mBtnLogin;

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.btn_main_activity_login)
    public void onViewClicked() {
        TestPresenter presenter = getPresenter(TestPresenter.class);
        presenter.request("https://www.baidu.com/");
    }

    @Override
    public void request(String s) {
        Log.d(TAG,  "request：" + s);
    }
}
