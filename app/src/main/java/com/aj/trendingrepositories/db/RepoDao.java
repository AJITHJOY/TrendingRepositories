package com.aj.trendingrepositories.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.aj.trendingrepositories.models.dbmodels.RepositoriesTable;

import java.util.List;

@Dao
public interface RepoDao {

    @Insert
    void insertRepositoriesData(List<RepositoriesTable> repositoriesTableList);

    @Query("Delete from repositories")
    void deleteAll();

    @Query("Select * from repositories")
    LiveData<List<RepositoriesTable>> getAllRepos();
}
