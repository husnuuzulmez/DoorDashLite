package com.huzulmez.doordashlite.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.huzulmez.doordashlite.R;
import com.huzulmez.doordashlite.model.data.Store;
import com.huzulmez.doordashlite.model.services.StoreService;
import com.huzulmez.doordashlite.view.adapters.StoreAdapter;
import com.huzulmez.doordashlite.viewmodel.MainViewModel;
import com.huzulmez.doordashlite.viewmodel.MainViewModelFactory;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
public class MainActivity extends AppCompatActivity implements StoreAdapter.StoreListener {

    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    StoreAdapter adapter;
    MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        MainViewModelFactory factory = new MainViewModelFactory(StoreService.getInstance());
        mViewModel = ViewModelProviders.of(this  , factory).get(MainViewModel.class);
        mViewModel.getStores().observe(this,new StoreObserver());
        try {
            mViewModel.loadStores();
        } catch (IOException e) {
            e.printStackTrace();
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        adapter = new StoreAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStoreClicked(Store store) {
        DetailActivity.start(this,store);
    }

    private class StoreObserver implements Observer<List<Store>> {
        @Override
        public void onChanged(List<Store> stores) {
            if (stores!=null){
                adapter.setStores(stores);
                adapter.notifyDataSetChanged();
            }
        }
    }
}