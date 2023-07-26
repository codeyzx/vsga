package com.vsga.app.finalprojectvsga.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.vsga.app.finalprojectvsga.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoginMainActivity extends AppCompatActivity {

    public static final String FILENAME = "login";
    EditText editUsernamMain, editPasswordMain, editEmailMain, editNamaMain, editAsalMain, editAlamatMain;
    Button btnSimpan;
    TextView textViewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Halaman Depan");
        setContentView(R.layout.activity_login_register);

        editUsernamMain = findViewById(R.id.editUsernameReg);
        textViewPassword = findViewById(R.id.textViewPassword);
        editPasswordMain = findViewById(R.id.editPasswordeReg);
        editEmailMain = findViewById(R.id.editEmailReg);
        editNamaMain = findViewById(R.id.editNamaReg);
        editAsalMain = findViewById(R.id.editAsalReg);
        editAlamatMain = findViewById(R.id.editAlamatReg);
        btnSimpan = findViewById(R.id.btnSimpanReg);


        btnSimpan.setVisibility(View.GONE);
        editUsernamMain.setEnabled(false);
        editPasswordMain.setVisibility(View.GONE);
        textViewPassword.setVisibility(View.GONE);
        editEmailMain.setEnabled(false);
        editNamaMain.setEnabled(false);
        editAsalMain.setEnabled(false);
        editAlamatMain.setEnabled(false);

        bacaFileLogin();
    }

    void bacaFileLogin() {
        File sdcard = getFilesDir();
        File file = new File(sdcard, FILENAME);
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
            bacaDataUser(dataUser[0]);
        }
    }

    void bacaDataUser(String s) {
        File sdcard = getFilesDir();
        File file = new File(sdcard, s);
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

            editUsernamMain.setText(dataUser[0]);
            editEmailMain.setText(dataUser[2]);
            editNamaMain.setText(dataUser[3]);
            editAsalMain.setText(dataUser[4]);
            editAlamatMain.setText(dataUser[5]);
        } else {
            Toast.makeText(this, "Data tidak ditemukan!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            tampilkanDialogKonfrimasiLogout();
        }
        return super.onOptionsItemSelected(item);
    }

    void tampilkanDialogKonfrimasiLogout() {
        new AlertDialog.Builder(this)
                .setTitle("Logout?")
                .setMessage("Apakah Anda yakin ingin Logout?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hapusFile();
                        Intent intent = new Intent(LoginMainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton(android.R.string.no, null).show();
    }

    void hapusFile() {
        File file = new File(getFilesDir(), FILENAME);
        if (file.exists()) {
            file.delete();
        }
    }
}
