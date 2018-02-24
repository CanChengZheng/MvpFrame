package com.zcc.android.mvpframe.activity;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.zcc.android.mvpframe.R;
import com.zcc.android.mvpframe.mvpc.contract.LoginContract;
import com.zcc.android.mvpframe.mvpc.presenter.CreatePresenter;
import com.zcc.android.mvpframe.mvpc.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.OnClick;

@CreatePresenter({LoginPresenter.class})
public class MainActivity extends BaseActivity implements LoginContract.View{

    @BindView(R.id.et_main_activity_userid)
    EditText mEtUserId;
    @BindView(R.id.et_main_activity_pwd)
    EditText mEtPwd;
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
        LoginPresenter presenter = getPresenter(LoginPresenter.class);
        presenter.loginResult(mEtUserId.getText().toString(), mEtPwd.getText().toString());
    }

    @Override
    public void loginResult(String s) {
        Log.d(TAG,  "loginResult：" + s);
    }
}
