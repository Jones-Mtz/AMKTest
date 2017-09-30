package com.amk.amktest.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Jones on 29/09/17.
 */

public class Utils {

    public static boolean isOnline(Context context) {
        ConnectivityManager conMgr;
        conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

}
