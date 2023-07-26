package com.vsga.app.finalprojectvsga.restapi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.vsga.app.finalprojectvsga.R;
import com.vsga.app.finalprojectvsga.restapi.models.viewmodels.EmployeListViewModel;

import java.util.HashMap;
import java.util.Objects;

public class UpdateEmployee extends AppCompatActivity implements View.OnClickListener {
    //Dibawah ini merupakan perintah untuk mendefinikan View
    private TextView editTextName;
    private TextView editTextDesg;
    private TextView editTextSal;

    private Button buttonAdd;
    private EmployeListViewModel employeListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Get the data from the intent
        String name = getIntent().getStringExtra("name");
        String position = getIntent().getStringExtra("position");
        int salary = getIntent().getIntExtra("salary", 0);
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Update Employee");
        setContentView(R.layout.activity_update_employee);

        employeListViewModel = new ViewModelProvider(this).get(EmployeListViewModel.class);

        //Inisialisasi dari View
        editTextName = findViewById(R.id.editTextName);
        editTextDesg = findViewById(R.id.editTextDesg);
        editTextSal = findViewById(R.id.editTextSalary);

        editTextName.append(name);
        editTextDesg.append(position);
        editTextSal.append(String.valueOf(salary));

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
                loading = ProgressDialog.show(UpdateEmployee.this, "Update...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(UpdateEmployee.this, s, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(UpdateEmployee.this, EmployeListActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            protected String doInBackground(Void... v) {
                int id = Integer.parseInt(getIntent().getStringExtra("id"));
                HashMap<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("position", desg);
                params.put("salary", sal);
                employeListViewModel.updateEmploye(params, id);
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
