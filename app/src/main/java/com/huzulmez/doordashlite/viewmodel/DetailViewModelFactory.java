package com.huzulmez.doordashlite.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.huzulmez.doordashlite.model.data.Store;

public class DetailViewModelFactory implements ViewModelProvider.Factory{

    private final Store mStore;

    public DetailViewModelFactory(Store store) {
        mStore = store;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DetailsViewModel.class)) {
            return (T) new DetailsViewModel(mStore);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
