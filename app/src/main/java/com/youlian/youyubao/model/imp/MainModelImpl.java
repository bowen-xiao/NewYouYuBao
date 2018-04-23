package com.youlian.youyubao.model.imp;

import com.youlian.youyubao.bean.DataCallBack;
import com.youlian.youyubao.model.MainModel;
import com.youlian.youyubao.network.ApiService;
import com.youlian.youyubao.util.ToolLog;

import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainModelImpl implements MainModel {
    @Override
    public Subscription getData(String str, final DataCallBack callBack) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("data",str);
        Observable<String> observable = ApiService.getData(params);
        Subscription subscribe = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        ToolLog.i("MovieModelImpl-------->>onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onNext(String result) {
                        callBack.onSuccess(result);
                    }
                });
        return subscribe;
    }
}
