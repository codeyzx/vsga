package com.vsga.app.finalprojectvsga.restapi.utils;

import com.vsga.app.finalprojectvsga.restapi.models.EmployeModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmployeApi {
    @GET("employe")
    Call<List<EmployeModel>> getEmployes();

    @DELETE("employe/{id}")
    Call<Map<String, String>> deleteEmployes(@Path("id") int id);

    @FormUrlEncoded

    @POST("employe/")
    Call<Map> addEmployes(@FieldMap Map<String, String> employee);

    @FormUrlEncoded

    @PUT("employe/{id}")
    Call<Map> updateEmployes(@Path("id") int id, @FieldMap Map<String, String> employee);
}
