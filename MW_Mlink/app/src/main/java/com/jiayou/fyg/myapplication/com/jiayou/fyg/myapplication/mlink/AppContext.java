package com.jiayou.fyg.myapplication.com.jiayou.fyg.myapplication.mlink;

import android.app.Application;
import android.content.Context;

/**
 * Created by fyg on 16-11-14.
 */
public class AppContext extends Application {


    private static Context mContext;
    private static AppContext mInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mInstance = this;


    }

    public static synchronized Context getContext() {
        return mContext;
    }

    public static synchronized AppContext getInstance() {
        return mInstance;
    }



}
