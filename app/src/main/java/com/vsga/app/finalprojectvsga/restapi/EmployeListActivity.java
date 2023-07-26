package com.vsga.app.finalprojectvsga.restapi;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vsga.app.finalprojectvsga.R;
import com.vsga.app.finalprojectvsga.restapi.adapters.EmployeRecycleView;
import com.vsga.app.finalprojectvsga.restapi.adapters.OnEmployeListener;
import com.vsga.app.finalprojectvsga.restapi.models.EmployeModel;
import com.vsga.app.finalprojectvsga.restapi.models.viewmodels.EmployeListViewModel;

public class EmployeListActivity extends AppCompatActivity implements OnEmployeListener {
    AlertDialog.Builder dialog;
    private EmployeListViewModel employeListViewModel;
    private RecyclerView recyclerView;
    private EmployeRecycleView recycleViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employe_list);

        getSupportActionBar().setTitle("Employee");
        recyclerView = findViewById(R.id.recycleViews);
        employeListViewModel = new ViewModelProvider(this).get(EmployeListViewModel.class);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployeListActivity.this, RESTAPIActivity.class);
                startActivity(intent);
            }
        });
        ObservasingAnyChangesEmploye();

        employeListViewModel.getEmploye();

        configureRecycleView();

    }


    private void configureRecycleView() {
        recycleViewAdapter = new EmployeRecycleView(this);
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void ObservasingAnyChangesEmploye() {
        employeListViewModel.getEmployes().observe(this, employeModels -> {
            if (employeModels != null) {
                for (EmployeModel model : employeModels) {
                    recycleViewAdapter.setEmploye(employeModels);
                }
            }
        });
    }

    @Override
    public void onEmployeClick() {
    }

    @Override
    public void onEmployeLongClick(int position) {
        final EmployeModel employe = recycleViewAdapter.getSelectedEmploye(position);

        final CharSequence[] dialogitem = {"Edit", "Delete"};
        dialog = new AlertDialog.Builder(EmployeListActivity.this);
        dialog.setCancelable(true);
        dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Intent intent = new Intent(EmployeListActivity.this, UpdateEmployee.class);
                        intent.putExtra("id", employe.getId());
                        intent.putExtra("name", employe.getName());
                        intent.putExtra("position", employe.getPosition());
                        intent.putExtra("salary", employe.getSalary());
                        startActivity(intent);
                        break;
                    case 1:
                        employeListViewModel.deleteEmploye(Integer.parseInt(employe.getId()));
                        break;
                }
            }
        }).show();
    }
}


