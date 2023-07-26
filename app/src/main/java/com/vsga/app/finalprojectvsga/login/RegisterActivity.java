package com.vsga.app.finalprojectvsga.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vsga.app.finalprojectvsga.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {

    EditText editUsernamReg, editPasswordReg, editEmailReg, editNamaReg, editAsalReg, editAlamatReg;
    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Register");
        setContentView(R.layout.activity_login_register);

        editUsernamReg = findViewById(R.id.editUsernameReg);
        editPasswordReg = findViewById(R.id.editPasswordeReg);
        editEmailReg = findViewById(R.id.editEmailReg);
        editNamaReg = findViewById(R.id.editNamaReg);
        editAsalReg = findViewById(R.id.editAsalReg);
        editAlamatReg = findViewById(R.id.editAlamatReg);
        btnSimpan = findViewById(R.id.btnSimpanReg);

        btnSimpan.setOnClickListener((v) -> {
            if (isValidation()) {
                simpanFileData();
            } else {
                Toast.makeText(this, "Mohon Lengkapi Seluruh Data!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    boolean isValidation() {
        return !editUsernamReg.getText().toString().equals("") && !editPasswordReg.getText().toString().equals("") && !editEmailReg.getText().toString().equals("") && !editNamaReg.getText().toString().equals("") && !editAsalReg.getText().toString().equals("") && !editAlamatReg.getText().toString().equals("");
    }

    void simpanFileData() {
        String isiFile = editUsernamReg.getText().toString() + ";" + editPasswordReg.getText().toString() + ";" + editEmailReg.getText().toString() + ";" + editNamaReg.getText().toString() + ";" + editAsalReg.getText().toString() + ";" + editAlamatReg.getText().toString();
        File file = new File(getFilesDir(), editUsernamReg.getText().toString());
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show();
        onBackPressed();

    }
}
