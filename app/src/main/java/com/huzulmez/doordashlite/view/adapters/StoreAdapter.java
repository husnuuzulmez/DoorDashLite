package com.huzulmez.doordashlite.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.huzulmez.doordashlite.R;
import com.huzulmez.doordashlite.model.data.Store;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {

    List<Store> mStores;
    StoreListener storeListener;

    public interface StoreListener{
        void onStoreClicked(Store store);
    }

    public StoreAdapter(StoreListener storeListener){
      mStores = new ArrayList<>();
      this.storeListener = storeListener;

    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new StoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {
        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return mStores.size();
    }

    public void setStores(List<Store> stores) {
        mStores=stores;
    }

    public class StoreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.txt_title) TextView store_name;
        @BindView(R.id.imageView) ImageView image;
        @BindView(R.id.txt_description) TextView store_description;

        public StoreViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(int position) {
            Store store = mStores.get(position);
            setName(store.getName());
            setDescription(store.getDescription());
            setImage(store.getImage_url());
            setClickListener(store);
        }

        private void setClickListener(Store store) {
            itemView.setTag(store);
            itemView.setOnClickListener(this);
        }

        private void setName(String name){
            store_name.setText(name);
        }

        private void setDescription(String description){
            store_description.setText(description);
        }

        private void setImage(String imageUrl) {
            Glide.with(itemView.getContext()).load(imageUrl).into(image);
        }

        @Override
        public void onClick(View view) {
            storeListener.onStoreClicked((Store) view.getTag());
        }
    }
}
