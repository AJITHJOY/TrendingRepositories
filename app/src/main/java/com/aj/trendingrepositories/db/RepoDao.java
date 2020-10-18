package com.aj.trendingrepositories.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.aj.trendingrepositories.db.tables.RepositoriesTable;

import java.util.List;

@Dao
public interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRepositoriesData(List<RepositoriesTable> repositoriesTableList);

    @Query("Delete from repositories")
    void deleteAll();

    @Query("Select * from repositories")
    LiveData<List<RepositoriesTable>> getAllRepos();
}
