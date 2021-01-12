package com.huzulmez.doordashlite.view.activities;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.huzulmez.doordashlite.R;
import com.huzulmez.doordashlite.model.data.Store;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.huzulmez.doordashlite.view.activities.MainActivityTest.STORE_DESC;
import static com.huzulmez.doordashlite.view.activities.MainActivityTest.STORE_IMG_URL;
import static com.huzulmez.doordashlite.view.activities.MainActivityTest.STORE_NAME;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    private static final String EXTRA_STORE = "EXTRA_STORE";
    private DetailActivity detailActivity;

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityRule =
            new ActivityTestRule<>(MainActivity.class, true, true);


    @Rule
    public ActivityTestRule<DetailActivity> mDetailActivityRule  = new  ActivityTestRule<DetailActivity>(DetailActivity.class)
    {
        @Override
        protected Intent getActivityIntent() {
            Context context = InstrumentationRegistry.getTargetContext();
            Intent intent = new Intent(context,DetailActivity.class);
            intent.putExtra(EXTRA_STORE , new Store(STORE_NAME,STORE_DESC,STORE_IMG_URL));
            return intent;
        }
    };

    @Before
    public void setUp(){
        detailActivity = mDetailActivityRule.getActivity();
    }


    @Test
    public void ensureDetailContentIsEqualToIntentExtras(){
        View name_viewById = detailActivity.findViewById(R.id.txt_detail_name);
        View desc_viewById = detailActivity.findViewById(R.id.txt_detail_desc);
        assertThat(name_viewById,notNullValue());
        assertThat(name_viewById, instanceOf(TextView.class));
        assertThat(desc_viewById,notNullValue());
        assertThat(desc_viewById, instanceOf(TextView.class));
        Assert.assertEquals( ((TextView)name_viewById).getText() , STORE_NAME);
        Assert.assertEquals( ((TextView)desc_viewById).getText() , STORE_DESC);
    }

}