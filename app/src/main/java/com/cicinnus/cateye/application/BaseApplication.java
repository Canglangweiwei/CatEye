package com.cicinnus.cateye.application;

import android.app.Application;

import com.amap.api.location.AMapLocationClient;
import com.cicinnus.cateye.base.BaseConstant;
import com.cicinnus.cateye.net.Api;
import com.cicinnus.cateye.net.OkHttpManager;
import com.cicinnus.cateye.net.RetrofitClient;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Administrator on 2017/1/18.
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        LeakCanary.install(this);
        // 初始化网络加载工具
        RetrofitClient.initClient_BaseUrl(OkHttpManager.getInstance(), Api.BASE_URL);
        // 初始化高德定位工具
        AMapLocationClient.setApiKey(BaseConstant.LOCATION_APPKEY);
    }

    public static BaseApplication getInstance() {
        return instance;
    }
}
