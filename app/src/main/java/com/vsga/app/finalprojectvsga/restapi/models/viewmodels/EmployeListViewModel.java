package com.vsga.app.finalprojectvsga.restapi.models.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.vsga.app.finalprojectvsga.restapi.models.EmployeModel;
import com.vsga.app.finalprojectvsga.restapi.repositories.EmployeRepository;

import java.util.HashMap;
import java.util.List;

public class EmployeListViewModel extends ViewModel {
    private final EmployeRepository employeRepository;

    public EmployeListViewModel() {
        employeRepository = EmployeRepository.getInstance();
    }

    public LiveData<List<EmployeModel>> getEmployes() {
        return employeRepository.getEmployes();
    }

    public void deleteEmploye(int id) {
        employeRepository.deleteEmploye(id);

    }

    public void addEmploye(HashMap<String, String> employee) {
        employeRepository.addEmploye(employee);

    }

    public void updateEmploye(HashMap<String, String> employee, int id) {
        employeRepository.updateEmploye(employee, id);

    }

    public void getEmploye() {
        employeRepository.getEmploye();
    }
}
