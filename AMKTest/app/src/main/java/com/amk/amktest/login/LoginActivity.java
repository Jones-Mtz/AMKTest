package com.amk.amktest.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.opengl.EGLDisplay;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amk.amktest.AMKTest;
import com.amk.amktest.MainActivity;
import com.amk.amktest.R;
import com.amk.amktest.SplashActivity;
import com.amk.amktest.utils.DialogUtils;
import com.amk.amktest.utils.SharedPreferencesManager;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout tilLoginUser;
    private TextInputLayout tilLoginPassword;
    private TextInputEditText tvLoginUser;
    private TextInputEditText tvLoginPassword;

    private DialogUtils dialogUtils;
    private SharedPreferencesManager sharedPreferencesManager;

    public LoginActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dialogUtils = new DialogUtils(LoginActivity.this);

        tilLoginUser = (TextInputLayout) findViewById(R.id.til_login_user_name);
        tvLoginUser = (TextInputEditText) findViewById(R.id.tv_login_user_name);
        tilLoginPassword = (TextInputLayout) findViewById(R.id.til_login_password);
        tvLoginPassword = (TextInputEditText) findViewById(R.id.tv_login_password);


        sharedPreferencesManager = ((AMKTest) getApplication()).getSharedPreferencesManager();

        Button btnLogin = (Button) findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(view -> {
            dialogUtils.showDialog();
            if (validations()) {
                new Handler().postDelayed(
                        () -> {
                            sharedPreferencesManager.setSession();
                            dialogUtils.dismissDialog();
                            Intent animationActivity = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(animationActivity);
                            finish();
                        }, 2000);
            } else {
                dialogUtils.dismissDialog();
            }
        });
    }

    private boolean validations() {
        boolean isValid = true;
        Pattern patron = Pattern.compile("^(?=.*[A-Z])[A-Za-z\\d]{8,}$");
        tilLoginUser.setError(null);
        tilLoginPassword.setError(null);

        if (tvLoginUser.getText().toString().isEmpty()) {
            tilLoginUser.setError(getString(R.string.empty_error));
            isValid = false;

        } else if (!patron.matcher(tvLoginUser.getText().toString()).matches()) {
            tilLoginUser.setError(getString(R.string.wron_format));
            isValid = false;

        }
        if (tvLoginPassword.getText().toString().isEmpty()) {
            tilLoginPassword.setError(getString(R.string.empty_error));
            isValid = false;
        } else if (!patron.matcher(tvLoginPassword.getText().toString()).matches()) {
            tilLoginPassword.setError(getString(R.string.wron_format));
            isValid = false;

        }

        return isValid;
    }
}
