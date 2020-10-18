package com.aj.trendingrepositories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.aj.trendingrepositories.models.dbmodels.RepositoriesTable;
import com.aj.trendingrepositories.models.webmodels.Repositories;
import com.aj.trendingrepositories.repository.RepoRepository;
import com.aj.trendingrepositories.viewmodels.RepositoriesViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Repositories> repositoriesList;
    private RepositoriesViewModel viewModel;
    private ProgressDialog progressDialog;
    private Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(RepositoriesViewModel.class);

        viewModel.makeApiCall();

        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading..");
        progressDialog.show();


        viewModel.getRepositoriesListObserver().observe(this, repositories -> {
            if (repositories != null) {
                repositoriesList = repositories;
                Toast.makeText(MainActivity.this, "Writing" + repositoriesList.size(), Toast.LENGTH_SHORT).show();
//                viewModel.insert(repositoriesList);
                progressDialog.dismiss();
            } else {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

//        viewModel.getAllRepos().observe(this,
//                repositoriesTables -> Toast.makeText(MainActivity.this, "Working fine  " + repositoriesTables.size(), Toast.LENGTH_SHORT).show());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.my_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_icon);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search here");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Toast.makeText(context, "searching", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}