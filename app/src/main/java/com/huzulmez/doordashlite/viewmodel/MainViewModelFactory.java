package com.huzulmez.doordashlite.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.huzulmez.doordashlite.model.services.StoreService;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    private StoreService mStoreService;

    public MainViewModelFactory(StoreService storeService) {
        mStoreService = storeService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(mStoreService);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
