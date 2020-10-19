package com.aj.trendingrepositories.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.aj.trendingrepositories.R;
import com.aj.trendingrepositories.databinding.ActivityMainBinding;
import com.aj.trendingrepositories.db.tables.RepositoriesTable;
import com.aj.trendingrepositories.models.webmodels.Repositories;
import com.aj.trendingrepositories.ui.adapters.RepositoriesRecyclerAdapter;
import com.aj.trendingrepositories.utils.NetworkUtils;
import com.aj.trendingrepositories.viewmodels.RepositoriesViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Repositories> repositoriesList;
    private RepositoriesViewModel viewModel;
    private ActivityMainBinding binding;
    private RepositoriesRecyclerAdapter repositoriesRecyclerAdapter;
    private Context context = this;
    private List<RepositoriesTable> repositoriesTableList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();


        viewModel.getRepositoriesListObserver().observe(this, repositories -> {
            if (repositories != null) {
                repositoriesList = repositories;
                viewModel.insert(repositoriesList);
                binding.swiperefresh.setRefreshing(false);
                Toast.makeText(MainActivity.this, "Downloaded", Toast.LENGTH_SHORT).show();

//                Toast.makeText(MainActivity.this, "Writing" + repositoriesList.size(), Toast.LENGTH_SHORT).show();
//                viewModel.insert(repositoriesList);
            } else {
                binding.swiperefresh.setRefreshing(false);
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });


        viewModel.getAllRepos().observe(this, repositoriesTableList -> {
            if (repositoriesTableList.size() > 0) {
                binding.swiperefresh.setRefreshing(false);
                Toast.makeText(context, "" + repositoriesTableList.size(), Toast.LENGTH_SHORT).show();

                repositoriesRecyclerAdapter = new RepositoriesRecyclerAdapter(repositoriesTableList, context);
                binding.rvRepositories.setAdapter(repositoriesRecyclerAdapter);
                repositoriesRecyclerAdapter.notifyDataSetChanged();
            } else {
                binding.swiperefresh.setRefreshing(false);
                Toast.makeText(context, "No Data", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void init() {
        setProgressView();
        setRecyclerView();
        binding.btnTryAgain.setOnClickListener(view -> callWebservice());
        viewModel = ViewModelProviders.of(this).get(RepositoriesViewModel.class);
        callWebservice();
    }

    private void setProgressView() {
        binding.swiperefresh.setColorSchemeColors(getResources().getColor(R.color.design_default_color_primary_dark));
        binding.swiperefresh.setRefreshing(true);
        binding.swiperefresh.setOnRefreshListener(this::callWebservice);
    }

    private void setRecyclerView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        binding.rvRepositories.setLayoutManager(mLayoutManager);
        binding.rvRepositories.setItemAnimator(new DefaultItemAnimator());
        repositoriesRecyclerAdapter = new RepositoriesRecyclerAdapter(repositoriesTableList, context);
    }

    private void callWebservice() {
        binding.swiperefresh.setRefreshing(false);
        if (NetworkUtils.isNetworkAvailable(context)) {
            binding.llNoInternet.setVisibility(View.GONE);
            binding.swiperefresh.setVisibility(View.VISIBLE);
            viewModel.makeApiCall();
        } else {
            binding.llNoInternet.setVisibility(View.VISIBLE);
            binding.swiperefresh.setVisibility(View.GONE);
        }
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
                repositoriesRecyclerAdapter.getFilter().filter(s);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}