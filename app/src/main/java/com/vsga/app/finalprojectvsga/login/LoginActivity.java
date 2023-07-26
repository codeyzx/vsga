package com.vsga.app.finalprojectvsga.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vsga.app.finalprojectvsga.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    public static final String FILENAME = "login";

    EditText editUsername, editPassword;
    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login_main);

        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.action_login);
        btnRegister = findViewById(R.id.action_register);

        btnLogin.setOnClickListener((v) -> login());

        btnRegister.setOnClickListener((v) -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

    }

    void simpanFileLogin() {
        String isiFile = editUsername.getText().toString() + ";" + editPassword.getText().toString();
        File file = new File(getFilesDir(), FILENAME);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    void login() {
        if (editUsername.getText().toString().equals("") || editUsername.getText().toString() == null || editPassword.getText().toString().equals("") || editPassword.getText().toString() == null) {
            Toast.makeText(this, "Mohon Isi Username dan Password Anda", Toast.LENGTH_SHORT).show();
        } else {
            File sdcard = getFilesDir();
            File file = new File(sdcard, editUsername.getText().toString());
            if (file.exists()) {
                StringBuilder text = new StringBuilder();
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line = br.readLine();
                    while (line != null) {
                        text.append(line);
                        line = br.readLine();
                    }
                    br.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String data = text.toString();
                String[] dataUser = data.split(";");

                if (dataUser[1].equals(editPassword.getText().toString())) {
                    simpanFileLogin();
                    Intent intent = new Intent(this, LoginMainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Password tidak sesuai!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(LoginActivity.this, "Username tidak ditemukan!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
