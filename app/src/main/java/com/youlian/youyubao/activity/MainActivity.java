package com.youlian.youyubao.activity;

import android.view.View;
import android.widget.TextView;

import com.youlian.youyubao.R;
import com.youlian.youyubao.presenter.MainPresenter;
import com.youlian.youyubao.ui.MainView;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainView {

    @BindView(R.id.main_tv_hello)
    TextView mTvHello;

    private MainPresenter mLoginPresenter;

    @Override
    protected String getPageTitle() {
        return "首页";
    }

    @Override
    public int getView() {
        mLoginPresenter = new MainPresenter(this);
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        mTvHello.setText("what");
        mLoginPresenter.getData();
        mHeaderLine.setVisibility(View.VISIBLE);
    }

    @Override
    public String getUserName() {
        return "bowen";
    }

    @Override
    public void showLoading() {
        mLoaddingText.setText("加载中");
        mLoaddingRoot.setVisibility(View.VISIBLE);
    }

    @Override
    public void showResult(String result) {
        mLoaddingRoot.setVisibility(View.GONE);
        //mTvHello.setText(result);
    }

    @Override
    public void showErr(String msg) {
        mLoaddingRoot.setVisibility(View.GONE);
        //mTvHello.setText(msg + " :: click retry");
    }

    @OnClick({
            R.id.main_tv_hello
    })
    public void onClick(View view){
//            if(mTvHello.getText().toString().contains(" :: click retry")){
//                mLoginPresenter.getData();
//            }
    }
}
