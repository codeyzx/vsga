package com.vsga.app.finalprojectvsga.restapi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.vsga.app.finalprojectvsga.R;
import com.vsga.app.finalprojectvsga.restapi.models.viewmodels.EmployeListViewModel;

import java.util.HashMap;

public class RESTAPIActivity extends AppCompatActivity implements View.OnClickListener {
    //Dibawah ini merupakan perintah untuk mendefinikan View
    private EditText editTextName;
    private EditText editTextDesg;
    private EditText editTextSal;

    private Button buttonAdd;
    private EmployeListViewModel employeListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Employee");
        setContentView(R.layout.activity_restapi_main);


        employeListViewModel = new ViewModelProvider(this).get(EmployeListViewModel.class);

        //Inisialisasi dari View
        editTextName = findViewById(R.id.editTextName);
        editTextDesg = findViewById(R.id.editTextDesg);
        editTextSal = findViewById(R.id.editTextSalary);

        buttonAdd = findViewById(R.id.buttonAdd);

        //Setting listeners to button
        buttonAdd.setOnClickListener(this);
    }


    //Dibawah ini merupakan perintah untuk Menambahkan Pegawai (CREATE)
    private void addEmployee() {

        final String name = editTextName.getText().toString().trim();
        final String desg = editTextDesg.getText().toString().trim();
        final String sal = editTextSal.getText().toString().trim();

        class AddEmployee extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(RESTAPIActivity.this, "Menambahkan...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(RESTAPIActivity.this, s, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RESTAPIActivity.this, EmployeListActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("position", desg);
                params.put("salary", sal);
                employeListViewModel.addEmploye(params);
                return "";
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if (v == buttonAdd) {
            addEmployee();
        }
    }

}
