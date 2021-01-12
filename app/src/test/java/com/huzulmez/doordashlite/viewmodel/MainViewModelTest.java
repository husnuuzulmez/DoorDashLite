package com.huzulmez.doordashlite.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.huzulmez.doordashlite.model.data.Store;
import com.huzulmez.doordashlite.model.services.StoreResponse;
import com.huzulmez.doordashlite.model.services.StoreService;
import com.huzulmez.doordashlite.view.activities.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainViewModelTest {

    MainViewModel testViewModel;
    MainViewModel.StoreCallBack storeCallBack;

    Context context;

    private MockWebServer server = new MockWebServer();

//    @Mock
//    StoreResponse storeResponse;
//
//    @Mock
//    Response<StoreResponse> response;

    @Mock
    Call<StoreResponse> call;


    List<Store> stores;

    @Mock
    Callback<StoreResponse> callback;


    @Mock
    Observer<List<Store>> observer;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        testViewModel = new MainViewModel(StoreService.getInstance());
        storeCallBack = mock(MainViewModel.StoreCallBack.class);
        server.start();
        //testViewModel.getStores().observe(observer);


    }

    @Test public void defaultMockResponse() {
        MockResponse response = new MockResponse();
        assertThat(headersToList(response)).containsExactly("Content-Length: 0");
        assertThat(response.getStatus()).isEqualTo("HTTP/1.1 200 OK");
    }


    private List<String> headersToList(MockResponse response) {
        Headers headers = response.getHeaders();
        int size = headers.size();
        List<String> headerList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            headerList.add(headers.name(i) + ": " + headers.value(i));
        }
        return headerList;
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void onCleared() {
    }

    @Test
    public void clear() {
    }

    @Test
    public void setTagIfAbsent() {
    }

    @Test
    public void getTag() {
    }

    @Test
    public void getStores() throws IOException {
        List<Store> stores = new ArrayList<>();
        stores.add(new Store("test_name1","test_description1","test_img_url1"));
        stores.add(new Store("test_name2","test_description2","test_img_url2"));
        StoreService.getInstance().getStoreApi().getAllStores().enqueue(new StoreCallBack()
        );
//        testViewModel.loadStores();
//        assertNotNull(testViewModel.getStores());



    }

    @Test
    public void loadStores() {
    }


    public class StoreCallBack implements Callback<StoreResponse> {

        @Override
        public void onResponse(Call<StoreResponse> call, Response<StoreResponse> response) {
          stores = response.body().getStores();
            Log.d("HUSNU" , stores.toString());
        }

        @Override
        public void onFailure(Call<StoreResponse> call, Throwable t) {

        }
    }
}