package com.vsga.app.finalprojectvsga.inputnama;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.vsga.app.finalprojectvsga.R;

public class InputActivity extends AppCompatActivity {
    Button btnOk;
    EditText editNama;
    TextView txtHasil;
    TextView txtNiceTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Input Nama");
        setContentView(R.layout.activity_input_nama);

        btnOk = findViewById(R.id.preview);
        editNama = findViewById(R.id.input_name);
        txtHasil = findViewById(R.id.myName);
        txtNiceTo = findViewById(R.id.meetYou);

        btnOk.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                txtHasil.setText(editNama.getText().toString());
                txtNiceTo.setText("Nice to meet you!");
            }
        });
    }
}
