package com.huzulmez.doordashlite.view.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huzulmez.doordashlite.R;
import com.huzulmez.doordashlite.model.data.Store;
import com.huzulmez.doordashlite.viewmodel.DetailsViewModel;
import com.huzulmez.doordashlite.viewmodel.DetailViewModelFactory;
import com.huzulmez.doordashlite.viewmodel.MainViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    private static final String EXTRA_STORE = "EXTRA_STORE";

    @BindView(R.id.image_detail) ImageView image;
    @BindView(R.id.txt_detail_name) TextView name;
    @BindView(R.id.txt_detail_desc ) TextView desc;

    DetailsViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Store store = getIntent().getParcelableExtra(EXTRA_STORE);
        DetailViewModelFactory factory = new DetailViewModelFactory(store);
        mViewModel = ViewModelProviders.of(this  , factory).get(DetailsViewModel.class);
        mViewModel.getStore().observe(this,new DetailActivity.StoreObserver());
        mViewModel.loadMovieData();
    }

    private class StoreObserver implements Observer<Store> {
        @Override
        public void onChanged(@Nullable Store store) {
            if (store == null) return;
            name.setText(store.getName());
            desc.setText(store.getDescription());
            Glide.with(getApplicationContext()).load(store.getImage_url()).into(image);
        }
    }

    public static void start(Context context, Store store){
        Intent intent = new Intent(context,DetailActivity.class);
        intent.putExtra(EXTRA_STORE , store);
        context.startActivity(intent);
    }
}