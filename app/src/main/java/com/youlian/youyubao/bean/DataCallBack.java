package com.youlian.youyubao.bean;

public interface DataCallBack <T> {
    void onSuccess(String str);
    void onFail(T message);
}
