package com.huzulmez.doordashlite.model.services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.huzulmez.doordashlite.model.data.Store;

import java.util.List;

public class StoreResponse {

    @Expose
    @SerializedName("stores")
    private List<Store> stores;

    public List<Store> getStores() {
        return stores;
    }
}
