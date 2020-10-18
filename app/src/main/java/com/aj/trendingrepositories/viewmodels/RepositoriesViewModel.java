package com.aj.trendingrepositories.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.aj.trendingrepositories.db.tables.RepositoriesTable;
import com.aj.trendingrepositories.models.webmodels.Repositories;
import com.aj.trendingrepositories.repository.RepoRepository;
import com.aj.trendingrepositories.webservice.ApiInterface;
import com.aj.trendingrepositories.webservice.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoriesViewModel extends AndroidViewModel {

    private MutableLiveData<List<Repositories>> repositoriesList;
    private RepoRepository repoRepository;
    public LiveData<List<RepositoriesTable>> getAllRepos;

    public RepositoriesViewModel(@NonNull Application application) {
        super(application);
        repositoriesList = new MutableLiveData<>();
        repoRepository = new RepoRepository(application);
        getAllRepos = repoRepository.getGetAllRepos();
    }

    public LiveData<List<Repositories>> getRepositoriesListObserver() {
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

    public void insert(List<Repositories> repositoriesList) {
        repoRepository.insert(repositoriesList);
    }

    public LiveData<List<RepositoriesTable>> getAllRepos() {
        return getAllRepos;
    }

}

