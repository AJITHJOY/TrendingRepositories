package com.aj.trendingrepositories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aj.trendingrepositories.models.dbmodels.RepositoriesTable;
import com.aj.trendingrepositories.models.webmodels.Repositories;
import com.aj.trendingrepositories.viewmodels.RepositoriesViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Repositories> repositoriesList;
    private RepositoriesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(RepositoriesViewModel.class);

//        viewModel.getRepositoriesListObserver().observe(this, repositories -> {
//            if (repositories != null) {
//                repositoriesList = repositories;
//                Toast.makeText(MainActivity.this, "Writing" + repositoriesList.size(), Toast.LENGTH_SHORT).show();
//                viewModel.insert(repositoriesList);
//            } else {
//                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
//            }
//        });

        viewModel.getAllRepos().observe(this,
                repositoriesTables -> Toast.makeText(MainActivity.this, "Working fine  " + repositoriesTables.size(), Toast.LENGTH_SHORT).show());


//        viewModel.makeApiCall();



    }

}