package com.lizhidu.lzopen.base;

import android.app.Application;

/**
 * Created by dulzh on 3/17/16.
 */
public class AppApplication extends Application {
    private static AppApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        this.sInstance = this;
    }

    public static AppApplication getsInstance(){
        return sInstance;
    }
}
