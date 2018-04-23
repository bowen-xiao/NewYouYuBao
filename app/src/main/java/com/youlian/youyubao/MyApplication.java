package com.youlian.youyubao;

import android.app.Application;

public class MyApplication extends Application {
    static MyApplication mMyApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        mMyApplication = this;
    }

    public static MyApplication getInstance(){
        if(mMyApplication == null){
            mMyApplication = new MyApplication();
        }
        return mMyApplication;
    }
}
