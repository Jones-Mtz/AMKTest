package com.amk.amktest;

import android.app.Application;

import com.amk.amktest.utils.SharedPreferencesManager;

/**
 * Created by Jones on 29/09/17.
 */

public class AMKTest extends Application {

    private SharedPreferencesManager sharedPreferencesManager;

    @Override
    public void onCreate() {
        super.onCreate();

        sharedPreferencesManager = new SharedPreferencesManager(this);

    }


    public SharedPreferencesManager getSharedPreferencesManager() {
        return sharedPreferencesManager;
    }
}
