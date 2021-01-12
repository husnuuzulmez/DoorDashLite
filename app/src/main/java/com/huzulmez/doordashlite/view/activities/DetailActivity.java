package com.huzulmez.doordashlite.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huzulmez.doordashlite.R;
import com.huzulmez.doordashlite.model.data.Store;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    private static final String EXTRA_STORE = "EXTRA_STORE";

    @BindView(R.id.image_detail) ImageView image;
    @BindView(R.id.txt_detail_name) TextView name;
    @BindView(R.id.txt_detail_desc ) TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent!=null && intent.getExtras()!=null){
            Bundle extras = intent.getExtras();
            Store store = extras.getParcelable(EXTRA_STORE);
            if (store!=null){
                name.setText(store.getName());
                desc.setText(store.getDescription());
                Glide.with(getApplicationContext()).load(store.getImage_url()).into(image);
            }
        }
    }

    public static void start(Context context, Store store){
        Intent intent = new Intent(context,DetailActivity.class);
        intent.putExtra(EXTRA_STORE , store);
        context.startActivity(intent);
    }
}