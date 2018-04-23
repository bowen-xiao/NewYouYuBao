package com.youlian.youyubao.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.youlian.youyubao.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public abstract class BaseActivity extends AppCompatActivity {
    //全局上下文对象
    Activity mContext;

    @BindView(R.id.ll_common_left)
    LinearLayout mLeftRoot;
    @BindView(R.id.tv_common_title)
    protected TextView mTvTitle;

    @BindView(R.id.tv_head_title_right_text)
    protected TextView     mTvRight;
    @BindView(R.id.ll_common_right)
    LinearLayout mRightRoot;

    //标题里面的内容
    @BindView(R.id.ll_common_header_root)
    LinearLayout mTitleRoot;

    //加载页面
    @BindView(R.id.ll_page_loadding_root)
    LinearLayout mLoaddingRoot;

    //加载页面
    @BindView(R.id.tv_common_loadding_status)
    TextView mLoaddingText;

    //加载页面
    @BindView(R.id.header_underline)
    View mHeaderLine;

    Bundle mIsRestartData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        onCreateBefore();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        setChildView();
        ButterKnife.bind(this);
        initData();
        initPageTitle();
        setTitleBar();
    }


    /**
     * 设置页面的标题
     */
    private void initPageTitle() {
        String title = getPageTitle();
        if(title != null){
            mTvTitle.setText(title);
        }
    }

    //获取页面标题
    protected abstract String getPageTitle();

    //子页面的根布局
    FrameLayout mContentView;
    //设置子页面的内容
    private void setChildView() {
        View childView = View.inflate(mContext, getView(), null);
        mContentView = (FrameLayout) findViewById(R.id.ff_common_content);
        mContentView.addView(childView);
    }

    /**
     * 创建之前的一些操作
     */
    public void onCreateBefore() {
        //		锁定屏幕的方向
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 设置键盘不要破坏布局
        //		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //		getWindow().setFormat(PixelFormat.TRANSLUCENT);  adjustPan
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        mContext = this;
        //不是启动页面
        if(!(this instanceof SplashActivity)){
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        }
    }

    //设置状态栏颜色
    private void setTitleBar(){
        if((this instanceof SplashActivity)){
          return;
        }
        // 4.4及以上版本开启
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);

        // 自定义颜色
        tintManager.setTintColor(Color.parseColor("#37ACFE"));

    }


    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    //所以子类必须有自己的页面
    public abstract int getView();

    //去加载数据
    protected abstract void initData();


    //左右点击事件处理
    @OnClick({
            R.id.ll_common_left
            ,R.id.ll_common_right
    })
    public void handClick(View view) {
        switch (view.getId()) {
            case R.id.ll_common_left:
                leftClick();
                break;
            case R.id.ll_common_right:
                rightClick();
                break;
        }
    }


    //右上角的点击事件处理
    protected void rightClick(){finish();}

    //默认操作是关闭
    protected void leftClick() {
        finish();
    }

    /** 隐藏已经打开的软键盘 **/
    public void hideSoftInput(){
        try {
            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(getCurrentFocus()
                                    .getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onPause() {
        hideSoftInput();
        super.onPause();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransitionEnter();
    }

    /**
     * Overrides the pending Activity transition by performing the "Enter" animation.
     */
    protected void overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    /**
     * Overrides the pending Activity transition by performing the "Exit" animation.
     */
    protected void overridePendingTransitionExit() {
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransitionExit();
    }
}
