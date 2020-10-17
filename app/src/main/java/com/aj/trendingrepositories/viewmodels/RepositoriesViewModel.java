package com.aj.trendingrepositories.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aj.trendingrepositories.models.webmodels.Repositories;
import com.aj.trendingrepositories.webservice.ApiInterface;
import com.aj.trendingrepositories.webservice.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoriesViewModel extends ViewModel {

    private MutableLiveData<List<Repositories>> repositoriesList;

    public RepositoriesViewModel() {
        repositoriesList = new MutableLiveData<>();
    }

    public MutableLiveData<List<Repositories>> getRepositoriesListObserver() {
        return repositoriesList;

    }

    public void makeApiCall() {
        ApiInterface apiInterface = RetroInstance.getRetroClient().create(ApiInterface.class);
        Call<List<Repositories>> call = apiInterface.getRepositoriesList();
        call.enqueue(new Callback<List<Repositories>>() {
            @Override
            public void onResponse(Call<List<Repositories>> call, Response<List<Repositories>> response) {
                repositoriesList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Repositories>> call, Throwable t) {
                repositoriesList.postValue(null);
            }
        });
    }

}
