package com.huzulmez.doordashlite.view.adapters;

import com.huzulmez.doordashlite.model.data.Store;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class StoreAdapterTest {

    StoreAdapter storeAdapter;

    @Mock
    StoreAdapter.StoreListener storeListener;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        storeAdapter = new StoreAdapter(storeListener);
    }

    @Test
    public void checkItemCount(){
        Store store1= new Store("st1_name","st1_desc","st1_url" );
        Store store2= new Store("st2_name","st2_desc","st2_url" );
        List<Store> stores = new ArrayList();
        stores.add(store1);
        stores.add(store2);
        storeAdapter.setStores(stores);
        Assert.assertEquals(2,storeAdapter.getItemCount());
    }

}