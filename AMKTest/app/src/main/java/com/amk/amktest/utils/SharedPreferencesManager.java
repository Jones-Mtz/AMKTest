package com.amk.amktest.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by Jones on 29/09/17.
 */


public class SharedPreferencesManager {
    private static final String TAG = SharedPreferencesManager.class.getSimpleName();

    public static final String SESSION_LOGGED = "sessionlogged";
    private static SharedPreferences sharedPreferences;

    public SharedPreferencesManager(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setSession(){
        setValueBoolean(SESSION_LOGGED, true);
    }

    public boolean isSessionLogged(){
        return getValueBoolean(SESSION_LOGGED);
    }


    public void setValueBoolean(String key, boolean value) {
        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(key, value);
            editor.apply();
        } catch (Exception e) {
            Log.e(TAG, "Error on 'setValueString'" + e.getMessage());
        }
    }

    public boolean getValueBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public void removeKey(String key) {
        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(key);
            editor.apply();
        } catch (Exception e) {
            Log.i(TAG, "Error on 'removeKey': " + e.getMessage());
        }
    }

}