package com.vsga.app.finalprojectvsga.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.vsga.app.finalprojectvsga.R;

import java.io.File;

public class LoginDecisionerActivity extends AppCompatActivity {
    public static final String FILENAME = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login_loading);

        new Handler().postDelayed(() -> {
            if (isLogin()) {
                Intent intent = new Intent(LoginDecisionerActivity.this, LoginMainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(LoginDecisionerActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 500);
    }

    boolean isLogin() {
        File sdcard = getFilesDir();
        File file = new File(sdcard, FILENAME);
        return file.exists();
    }
}
