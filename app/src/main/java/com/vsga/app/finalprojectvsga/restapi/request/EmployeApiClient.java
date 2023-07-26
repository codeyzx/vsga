package com.vsga.app.finalprojectvsga.restapi.request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vsga.app.finalprojectvsga.restapi.models.EmployeModel;
import com.vsga.app.finalprojectvsga.restapi.utils.AppExecutor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeApiClient {
    private static EmployeApiClient instance;
    // live data
    private final MutableLiveData<List<EmployeModel>> employeLiveData;
    // global variable for runnable
    private PopularRunnable popularRunnable;

    private EmployeApiClient() {
        employeLiveData = new MutableLiveData<>();
    }

    public static EmployeApiClient getInstance() {
        if (instance == null) {
            instance = new EmployeApiClient();
        }
        return instance;
    }

    public LiveData<List<EmployeModel>> getEmployes() {
        return employeLiveData;
    }

    public void getEmploye() {
        if (popularRunnable != null) {
            popularRunnable = null;
        }

        popularRunnable = new PopularRunnable();

        final Future handler = AppExecutor.getInstance().getNetworkIO().submit(popularRunnable);

        AppExecutor.getInstance().getNetworkIO().schedule(() -> {
            // canceling retrofit call
            handler.cancel(true);
        }, 3000, TimeUnit.MILLISECONDS);

    }


    public void deleteEmploye(int id) {
        Call<Map<String, String>> response =
                ApiService.getEmployes().deleteEmployes(id);

        response.enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if (popularRunnable != null) {
                    popularRunnable = null;
                }

                popularRunnable = new PopularRunnable();

                final Future handler = AppExecutor.getInstance().getNetworkIO().submit(popularRunnable);

                AppExecutor.getInstance().getNetworkIO().schedule(() -> {
                    // canceling retrofit call
                    handler.cancel(true);
                }, 3000, TimeUnit.MILLISECONDS);

            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {

                // Handle network failures or errors
                System.out.println("Error: " + t.getMessage());
            }


        });
    }


    public void addEmploye(HashMap<String, String> employee) {
        Call<Map> response =
                ApiService.getEmployes().addEmployes(employee);

        response.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                if (popularRunnable != null) {
                    popularRunnable = null;
                }

                popularRunnable = new PopularRunnable();

                final Future handler = AppExecutor.getInstance().getNetworkIO().submit(popularRunnable);

                AppExecutor.getInstance().getNetworkIO().schedule(() -> {
                    // canceling retrofit call
                    handler.cancel(true);
                }, 3000, TimeUnit.MILLISECONDS);

            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {

                // Handle network failures or errors
                System.out.println("Error: " + t.getMessage());
            }


        });
    }

    public void updateEmploye(HashMap<String, String> employee, int id) {
        Call<Map> response =
                ApiService.getEmployes().updateEmployes(id, employee);

        response.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                if (popularRunnable != null) {
                    popularRunnable = null;
                }

                popularRunnable = new PopularRunnable();

                final Future handler = AppExecutor.getInstance().getNetworkIO().submit(popularRunnable);

                AppExecutor.getInstance().getNetworkIO().schedule(() -> {
                    // canceling retrofit call
                    handler.cancel(true);
                }, 3000, TimeUnit.MILLISECONDS);

            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {

                // Handle network failures or errors
                System.out.println("Error: " + t.getMessage());
            }


        });
    }

    private class PopularRunnable implements Runnable {

        boolean cancelRequest;

        public PopularRunnable() {
            cancelRequest = false;
        }

        @Override
        public void run() {
            // getting request
            Call<List<EmployeModel>> response = getEmployes();

            // Enqueue the call to execute it asynchronously
            response.enqueue(new Callback<List<EmployeModel>>() {
                @Override
                public void onResponse(Call<List<EmployeModel>> call, Response<List<EmployeModel>> response) {

                    if (response.isSuccessful()) {

                        if (response.code() == 200) {
                            assert response.body() != null;
                            List<EmployeModel> employeModelList = response.body();
                            employeLiveData.postValue(employeModelList);
                        } else {
                            assert response.errorBody() != null;
                            System.out.println(response.errorBody());
                            employeLiveData.postValue(null);
                        }
                    } else {
                        System.out.println("request isnt successful");
                    }
                }

                @Override
                public void onFailure(Call<List<EmployeModel>> call, Throwable t) {
                    System.out.println("Error: " + t.getMessage());
                }


            });
        }

        private Call<List<EmployeModel>> getEmployes() {
            return ApiService.getEmployes().getEmployes();
        }
    }
}