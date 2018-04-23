package com.youlian.youyubao.ui;

public interface MainView {

    String getUserName();

    void showLoading();

    void showResult(String str);

    void showErr(String msg);
}
