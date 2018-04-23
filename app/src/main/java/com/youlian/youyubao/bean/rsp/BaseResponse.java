package com.youlian.youyubao.bean.rsp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youlian.youyubao.util.GzipUtil;
import com.youlian.youyubao.util.RSAUtils;
import com.youlian.youyubao.util.ToolLog;
import com.youlian.youyubao.util.UtilBytes;

import java.lang.reflect.Type;

public abstract class BaseResponse<T> {

    /**
     * code : 0231
     * msg : 账户未注册
     * time : 1523928233278
     * state : null
     * data : null
     */

    private String code;
    private String msg;
    private String time;
    private String state;
    private String data;

    private T myData;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public T getMyData() {
        //解密
        decodeData();
//        Type gsonType = new TypeToken<BaseResponse<T>>() {}.getType();
        Type gsonType = new TypeToken<T>(){}.getType();
        myData = new Gson().fromJson(data,gsonType);
        return myData;
    }

    public void setMyData(T myData) {
        this.myData = myData;
    }

    //对返回的数据进行解密操作
    private void decodeData(){
        try {
            if(state!=null&&state.length()>0){
                byte[] dataValue1=null;
                if(state.length()>1){
                    String isZip=state.substring(1,2);
                    if("1".equals(isZip)){//需要解压
                        dataValue1= UtilBytes.hexStringToBytes(data);
                        dataValue1= GzipUtil.unGZip(dataValue1);
                    }
                }
                if("1".equals(state.substring(0,1))){//解密
                    dataValue1 = RSAUtils.decrypt(RSAUtils.getKeyByFile(),dataValue1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ToolLog.e(this.getClass().getSimpleName(),"decode err",e);
        }
    }
}
