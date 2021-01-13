package com.huzulmez.doordashlite.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huzulmez.doordashlite.model.data.Store;

public class DetailsViewModel extends ViewModel {

    private MutableLiveData<Store> storeLiveData = new MutableLiveData<>();
    private Store mStore;

    public DetailsViewModel(Store store) {
        mStore = store;
    }

    public void loadMovieData() {
        storeLiveData.postValue(mStore);
    }

    public MutableLiveData<Store> getStore() {
        return storeLiveData;
    }
}
