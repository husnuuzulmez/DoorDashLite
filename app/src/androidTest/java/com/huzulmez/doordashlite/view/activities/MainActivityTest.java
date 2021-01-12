package com.huzulmez.doordashlite.view.activities;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.huzulmez.doordashlite.R;
import com.huzulmez.doordashlite.model.data.Store;
import com.huzulmez.doordashlite.view.adapters.StoreAdapter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    public static final String STORE_NAME = "Chili's Grill & Bar";
    public static final String STORE_DESC = "Dessert, Burgers, Chicken, Tex-Mex, Dinner, Bar and Grill, Mexican, Takeout, Pickup";
    public static final String STORE_IMG_URL = "https://cdn.doordash.com/media/restaurant/cover/ff546db378a071126fab96dc1c39704fee8b14bb_chilis_id_peppers_cmyk_1.jpg";

    private MainActivity activity;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class, true, true);

    @Before
    public void setUp(){
        activity = mActivityRule.getActivity();
    }


    @Test
    public void ensureRecyclerViewIsPresent() {
        View viewById = activity.findViewById(R.id.recyclerView);
        assertThat(viewById,notNullValue());
        assertThat(viewById, instanceOf(RecyclerView.class));
        RecyclerView recyclerView = (RecyclerView) viewById;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        assertThat(adapter, instanceOf(StoreAdapter.class));
    }

    @Test
    public void ensureRecyclerViewHasItemsFromNetwork() throws InterruptedException {
        RecyclerView recyclerView = activity.findViewById(R.id.recyclerView);
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        CountDownLatch signal = new CountDownLatch(5);
        signal.countDown();
        signal.await(5, TimeUnit.SECONDS);
        assertThat(adapter.getItemCount(), greaterThan(5));
    }

    @Test
    public void checkDetailActivityHasStoreData() throws InterruptedException {
        activity.onStoreClicked(new Store(STORE_NAME,STORE_DESC,STORE_IMG_URL));
        CountDownLatch signal = new CountDownLatch(5);
        signal.countDown();
        signal.await(5, TimeUnit.SECONDS);
    }

}