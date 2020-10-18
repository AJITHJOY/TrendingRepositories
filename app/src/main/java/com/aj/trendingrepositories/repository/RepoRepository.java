package com.aj.trendingrepositories.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.aj.trendingrepositories.db.RepoDao;
import com.aj.trendingrepositories.db.RepoDb;
import com.aj.trendingrepositories.db.tables.RepositoriesTable;
import com.aj.trendingrepositories.models.webmodels.Repositories;

import java.util.ArrayList;
import java.util.List;

public class RepoRepository {

    private RepoDb repoDb;

    private LiveData<List<RepositoriesTable>> getAllRepos;

    public RepoRepository(Application application) {
        repoDb = RepoDb.getInstance(application);
        getAllRepos = repoDb.RepoDao().getAllRepos();
    }

    public void insert(List<Repositories> repositoriesList) {
        new InsertAllRepositoriesTask(repoDb, repositoriesList).execute();
    }

    public LiveData<List<RepositoriesTable>> getGetAllRepos() {
        return getAllRepos;
    }

    class InsertAllRepositoriesTask extends AsyncTask<Void, Void, Void> {

        private RepoDao repoDao;
        private List<Repositories> repositoriesList;
        private List<RepositoriesTable> repositoriesTableList = new ArrayList<>();

        InsertAllRepositoriesTask(RepoDb repoDb, List<Repositories> repositoriesDataList) {
            repoDao = repoDb.RepoDao();
            repositoriesList = repositoriesDataList;
        }

        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 0; i < repositoriesList.size(); i++) {
                RepositoriesTable repositoriesTable = new RepositoriesTable(
                        repositoriesList.get(i).getAuthor(),
                        repositoriesList.get(i).getName(),
                        repositoriesList.get(i).getAvatar(),
                        repositoriesList.get(i).getUrl(),
                        repositoriesList.get(i).getDescription(),
                        repositoriesList.get(i).getLanguage(),
                        repositoriesList.get(i).getLanguageColor(),
                        repositoriesList.get(i).getStars(),
                        repositoriesList.get(i).getForks(),
                        repositoriesList.get(i).getCurrentPeriodStars());
                repositoriesTableList.add(repositoriesTable);
            }
            repoDao.insertRepositoriesData(repositoriesTableList);
            return null;
        }

    }


}
