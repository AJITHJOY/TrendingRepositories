package com.aj.trendingrepositories.webservice;

import com.aj.trendingrepositories.models.webmodels.Repositories;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("repositories")
    Call<List<Repositories>> getRepositoriesList();

}
