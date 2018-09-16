package br.com.patrocine.patrocine.app;

import android.app.Application;

public class MyApplication extends Application {

    public static final String TAG = MyApplication.class.getSimpleName();

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

}
