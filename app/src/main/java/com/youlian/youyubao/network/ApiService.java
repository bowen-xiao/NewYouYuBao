package com.youlian.youyubao.network;

import java.util.Map;

import rx.Observable;

public class ApiService {

    private static RxNetWorkService mApiService;

    public static void getApiInstance(){
        if(mApiService == null)
            mApiService = DataEngine2.getServiceApiByClass(RxNetWorkService.class);
    }

    /**
     * @param params
     * @return
     */
    public  static  Observable<String>  getData(Map<String,Object> params) {
        getApiInstance();
        Observable<String> movieBeanObservable = mApiService.getBaiDuInfo(params);
        return movieBeanObservable;
    }

}
