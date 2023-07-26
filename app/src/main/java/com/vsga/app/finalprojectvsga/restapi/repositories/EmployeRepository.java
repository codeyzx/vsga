package com.vsga.app.finalprojectvsga.restapi.repositories;


import androidx.lifecycle.LiveData;

import com.vsga.app.finalprojectvsga.restapi.models.EmployeModel;
import com.vsga.app.finalprojectvsga.restapi.request.EmployeApiClient;

import java.util.HashMap;
import java.util.List;

public class EmployeRepository {
    private static EmployeRepository instance;
    private final EmployeApiClient employeApiClient;


    private EmployeRepository() {
        employeApiClient = EmployeApiClient.getInstance();
    }

    public static EmployeRepository getInstance() {
        if (instance == null) {
            instance = new EmployeRepository();
        }

        return instance;
    }

    public LiveData<List<EmployeModel>> getEmployes() {

        return employeApiClient.getEmployes();
    }

    public void getEmploye() {
        employeApiClient.getEmploye();
    }

    public void deleteEmploye(int id) {
        employeApiClient.deleteEmploye(id);
    }

    public void addEmploye(HashMap<String, String> employee) {
        employeApiClient.addEmploye(employee);
    }

    public void updateEmploye(HashMap<String, String> employee, int id) {
        employeApiClient.updateEmploye(employee, id);
    }

}
