package com.youlian.youyubao.model;

import com.youlian.youyubao.bean.DataCallBack;

import rx.Subscription;

public interface MainModel {
    Subscription getData(String str, DataCallBack callBack);
}
