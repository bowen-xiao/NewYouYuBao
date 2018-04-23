package com.youlian.youyubao.activity;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.youlian.youyubao.R;
import com.youlian.youyubao.util.ToolImage;

import butterknife.BindView;

/**
 * 启动页面
 */
public class SplashActivity extends BaseActivity {

    //需要显示的view
    @BindView(R.id.splash_main_image)
    ImageView mImage;

    @Override
    public void onCreateBefore() {
        super.onCreateBefore();
        //设置主题
        setTheme(R.style.AppTheme_NoActionBar);
    }

    @Override
    protected String getPageTitle() {
        return null;
    }

    @Override
    public int getView() {
        //需要全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {
        //不显示标题行
        mTitleRoot.setVisibility(View.GONE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                jumpToHome();
            }
        },3000L);

        ToolImage.displayLocalPic(mContext,mImage,R.mipmap.flash,R.mipmap.flash);
        //开始做动画
        mContentView.startAnimation(getAlphaInAnimation(300));
    }

    //需要去做一个动画
    private Animation getAlphaInAnimation(int time) {
        Animation ani = new AlphaAnimation(0.5f, 1.0f);
        ani.setDuration(time);
        ani.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }
        });
        return ani;
    }

    //去主页面
    private void jumpToHome() {
        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
        finish();
    }


    /**
     * Overrides the pending Activity transition by performing the "Enter" animation.
     */
    protected void overridePendingTransitionEnter() {
        overridePendingTransition(0, 0);
    }

    /**
     * Overrides the pending Activity transition by performing the "Exit" animation.
     */
    protected void overridePendingTransitionExit() {
        overridePendingTransition(0, 0);
    }
}
