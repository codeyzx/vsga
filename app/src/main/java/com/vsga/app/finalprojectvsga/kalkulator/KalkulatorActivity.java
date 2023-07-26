package com.vsga.app.finalprojectvsga.kalkulator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vsga.app.finalprojectvsga.R;


public class KalkulatorActivity extends AppCompatActivity {
    EditText angka_pertama, angka_kedua;
    Button tambah, kurang, kali, bagi, bersihkan;
    TextView hasil, operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Kalkulator");
        setContentView(R.layout.activity_kalkulator);

        angka_pertama = findViewById(R.id.first_number);
        angka_kedua = findViewById(R.id.second_number);
        tambah = findViewById(R.id.plus);
        kurang = findViewById(R.id.minus);
        kali = findViewById(R.id.multiplier);
        bagi = findViewById(R.id.divider);
        bersihkan = findViewById(R.id.clear);
        hasil = findViewById(R.id.hasil);
        operator = findViewById(R.id.operators);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((angka_pertama.getText().length() > 0) && (angka_kedua.getText().length() > 0)) {
                    double angka1 = Double.parseDouble(angka_pertama.getText().toString());
                    double angka2 = Double.parseDouble(angka_kedua.getText().toString());
                    double result = angka1 + angka2;
                    operator.setText("+");
                    hasil.setText(Double.toString(result));
                } else {
                    Toast toast = Toast.makeText(KalkulatorActivity.this,
                            "Mohon masukkan Angka pertama & Kedua", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        kurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((angka_pertama.getText().length() > 0) && (angka_kedua.getText().length() > 0)) {
                    double angka1 = Double.parseDouble(angka_pertama.getText().toString());
                    double angka2 = Double.parseDouble(angka_kedua.getText().toString());
                    double result = angka1 - angka2;
                    operator.setText("-");
                    hasil.setText(Double.toString(result));
                } else {
                    Toast toast = Toast.makeText(KalkulatorActivity.this, "Mohon masukkan Angka pertama & Kedua", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        kali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((angka_pertama.getText().length() > 0) && (angka_kedua.getText().length() > 0)) {
                    double angka1 = Double.parseDouble(angka_pertama.getText().toString());
                    double angka2 = Double.parseDouble(angka_kedua.getText().toString());
                    double result = angka1 * angka2;
                    operator.setText("X");
                    hasil.setText(Double.toString(result));
                } else {
                    Toast toast = Toast.makeText(KalkulatorActivity.this, "Mohon masukkan Angka pertama & Kedua", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        bagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((angka_pertama.getText().length() > 0) && (angka_kedua.getText().length() > 0)) {
                    double angka1 = Double.parseDouble(angka_pertama.getText().toString());
                    double angka2 = Double.parseDouble(angka_kedua.getText().toString());
                    double result = angka1 / angka2;
                    operator.setText("/");
                    hasil.setText(Double.toString(result));
                } else {
                    Toast toast = Toast.makeText(KalkulatorActivity.this, "Mohon masukkan Angka pertama & Kedua", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        bersihkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                angka_pertama.setText("");
                angka_kedua.setText("");
                hasil.setText("0");
                operator.setText("...");
                angka_pertama.requestFocus();
            }
        });
    }
}
