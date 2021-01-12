package com.huzulmez.doordashlite.model.services;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class StoreService {
    private static final String URL = "https://api.doordash.com/";

    private static StoreService instance;
    private StoreApi mMStoreApi;

    public static StoreService getInstance(){
        if (instance==null) {
            instance = new StoreService();
        }
        return instance;
    }

    private StoreService(){
        Retrofit mRetrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(URL).build();
        mMStoreApi = mRetrofit.create(StoreApi.class);
    }

    public StoreApi getStoreApi() {
        return mMStoreApi;
    }

    public interface StoreApi{
        @GET("v1/store_feed/?lat=37.422740&lng=-122.139956&offset=0&limit=50")
        Call<StoreResponse> getAllStores();
    }



}
