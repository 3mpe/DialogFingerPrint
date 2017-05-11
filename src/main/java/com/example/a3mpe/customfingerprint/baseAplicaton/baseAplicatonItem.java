package com.example.a3mpe.customfingerprint.baseAplicaton;

import android.app.Application;
import android.content.Context;

public class baseAplicatonItem extends Application {

    private static baseAplicatonItem instance;

    public static baseAplicatonItem getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public static Context getContext() {
        return instance;
    }

}
