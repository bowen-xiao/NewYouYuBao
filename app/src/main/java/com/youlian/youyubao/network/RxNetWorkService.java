package com.youlian.youyubao.network;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by bowen.xiao on 2018.04.16
 * 通用的网络请求框架,用户模块
 */
public interface RxNetWorkService {

    //访问百度后面的空不能省略
    @FormUrlEncoded
    @POST(" ")
    Observable<String> getBaiDuInfo(@FieldMap Map<String, Object> filedMap);

    //key	是	string	信鸽推送KEY
    @FormUrlEncoded
    @POST("based/push/index")
    Observable<String> upLoadPushID(@FieldMap Map<String, Object> filedMap);

    //http://api.hannengclub.com/user/login/get
    @FormUrlEncoded
    @POST("user/login/third")
    Observable<String> thirdLogin(@FieldMap Map<String, Object> param);

}
