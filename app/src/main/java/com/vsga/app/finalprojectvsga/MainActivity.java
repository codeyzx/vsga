package com.vsga.app.finalprojectvsga;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.vsga.app.finalprojectvsga.catatan.CatatanActivity;
import com.vsga.app.finalprojectvsga.inputnama.InputActivity;
import com.vsga.app.finalprojectvsga.kalkulator.KalkulatorActivity;
import com.vsga.app.finalprojectvsga.listview.ListViewActivity;
import com.vsga.app.finalprojectvsga.login.LoginDecisionerActivity;
import com.vsga.app.finalprojectvsga.restapi.EmployeListActivity;
import com.vsga.app.finalprojectvsga.sqlite.SQLiteActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    protected AppCompatButton btn_input, btn_kalkulator, btn_listview, btn_catatan, btn_login, btn_sqlite, btn_restapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).hide();

        btn_input = findViewById(R.id.btn_input);
        btn_kalkulator = findViewById(R.id.btn_kalkulator);
        btn_listview = findViewById(R.id.btn_listview);
        btn_catatan = findViewById(R.id.btn_catatan);
        btn_login = findViewById(R.id.btn_login);
        btn_sqlite = findViewById(R.id.btn_sqlite);
        btn_restapi = findViewById(R.id.btn_restapi);

        btn_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InputActivity.class);
                startActivity(intent);
            }
        });

        btn_kalkulator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, KalkulatorActivity.class);
                startActivity(intent);
            }
        });

        btn_listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
                startActivity(intent);
            }
        });

        btn_catatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CatatanActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginDecisionerActivity.class);
                startActivity(intent);
            }
        });

        btn_sqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SQLiteActivity.class);
                startActivity(intent);
            }
        });

        btn_restapi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EmployeListActivity.class);
                startActivity(intent);
            }
        });
    }
}