package com.youlian.youyubao.presenter;

import com.youlian.youyubao.bean.DataCallBack;
import com.youlian.youyubao.model.MainModel;
import com.youlian.youyubao.model.imp.MainModelImpl;
import com.youlian.youyubao.ui.MainView;

public class MainPresenter {

    MainView mainView;
    MainModel mainModel;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
        mainModel = new MainModelImpl();
    }

    public void getData(){
        String userName = mainView.getUserName();
        mainView.showLoading();
        mainModel.getData(userName,new DataCallBack<String>(){

            @Override
            public void onSuccess(String str) {
                mainView.showResult(str);
            }

            @Override
            public void onFail(String message) {
                mainView.showErr(message);
            }
        });
    }
}
