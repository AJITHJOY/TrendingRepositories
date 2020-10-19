package com.aj.trendingrepositories.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.aj.trendingrepositories.db.tables.RepositoriesTable;

@Database(entities = {RepositoriesTable.class}, version = 1,exportSchema = false)
public abstract class RepoDb extends RoomDatabase {

    private static final String DB_NAME = "RepoDb";

    public abstract RepoDao RepoDao();

    private static volatile RepoDb INSTANCE;

    public static RepoDb getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (RepoDb.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, RepoDb.class, DB_NAME)
                            .addCallback(callback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsyncTask(INSTANCE);
        }
    };


    private static class PopulateAsyncTask extends AsyncTask<Void, Void, Void> {

        private RepoDao repoDao;

        public PopulateAsyncTask(RepoDb repoDb) {
            repoDao = repoDb.RepoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            repoDao.deleteAll();
            return null;
        }
    }
}
