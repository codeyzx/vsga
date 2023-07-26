package com.vsga.app.finalprojectvsga.restapi.request;

import com.vsga.app.finalprojectvsga.restapi.utils.Credentials;
import com.vsga.app.finalprojectvsga.restapi.utils.EmployeApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private static final Retrofit.Builder retrofitBuilderEmploye = new Retrofit.Builder().baseUrl(Credentials.BASE_URL).addConverterFactory(GsonConverterFactory.create());
    private static final Retrofit retrofitEmploye = retrofitBuilderEmploye.build();
    private static final EmployeApi employeApi = retrofitEmploye.create(EmployeApi.class);


    public static EmployeApi getEmployes() {
        return employeApi;
    }
}
