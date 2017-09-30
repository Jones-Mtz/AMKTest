package com.amk.amktest.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;

import com.amk.amktest.R;

/**
 * Created by Jones on 29/09/17.
 */

public class DialogUtils {
    Context context;
    Dialog progressDialog = null;


    public DialogUtils(Context context) {
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    public void showDialog(){
        if (progressDialog!=null){
            progressDialog.show();
            progressDialog.setContentView(R.layout.dialog_progress);
        }
    }

    public void dismissDialog(){
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
}
