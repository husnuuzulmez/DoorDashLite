package com.huzulmez.doordashlite.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huzulmez.doordashlite.model.data.Store;
import com.huzulmez.doordashlite.model.services.StoreResponse;
import com.huzulmez.doordashlite.model.services.StoreService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<Store>> stores;

    private StoreService mStoreService;

    public MainViewModel(StoreService storeService){
        this.mStoreService = storeService;
        this.stores = new MutableLiveData<>();
    }

    public MutableLiveData<List<Store>> getStores() {
        return stores;
    }

    public void loadStores(){
        Call<StoreResponse> call =  mStoreService.getStoreApi().getAllStores();
        call.enqueue(new StoreCallBack());
    }


    public class StoreCallBack implements Callback<StoreResponse> {
        @Override
        public void onResponse(Call<StoreResponse> call, Response<StoreResponse> response) {
            StoreResponse storeResponse = response.body();
            if (storeResponse!=null){
               setStores(storeResponse.getStores());
            }
        }

        @Override
        public void onFailure(Call<StoreResponse> call, Throwable t) {
                setStores(new ArrayList<Store>());
        }
    }

    private void setStores(List<Store> stores) {
        this.stores.postValue(stores);
    }
}
