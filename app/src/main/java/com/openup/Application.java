package com.openup;

import android.content.Context;

public class Application extends android.app.Application{


    static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        Application.context = getApplicationContext();
        //Firebase.setAndroidContext(this);

    }

    public static Context getAppContext() {
        return Application.context;
    }
}
