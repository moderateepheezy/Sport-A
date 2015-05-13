package com.news.app;

import java.util.ArrayList;
import java.util.Locale;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.simpumind.restclient.RestClient;
import com.news.app.com.sport.app.adapter.Adapter_All_News;
import com.news.app.com.sport.app.utilities.AlertDialogManager;
import com.news.app.com.sport.app.utilities.Constant;
import com.news.app.com.sport.app.model.ItemAllNews;

import com.news.app.Fragments.BoxingFragment;
import com.news.app.Fragments.CarRace;
import com.news.app.Fragments.HorseRace;
import com.news.app.Fragments.LALIGA;
import com.news.app.Fragments.PlaceholderFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class NewsActivity extends ActionBarActivity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    //GridView lsv_allnews;


    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    Toolbar mToolbar;

    String[] title ={"EPL", "LALIGA", "CAR RACE","BOXING", "HORSE RACE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);


/*
        if (JsonUtils.isNetworkAvailable(this)) {
            new MyTask().execute();
        } else {
            showToast("No Network Connection!!!");
            alert.showAlertDialog(this, "Internet Connection Error",
                    "Please connect to working Internet connection", false);
        }*/

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        getSupportActionBar().setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));

        //      mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
//        setSupportActionBar(mToolbar);


        /*actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.header)));
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.header)));
        actionBar.setHomeButtonEnabled(true);
        getSupportActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
*/

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
                int len = title.length;
                if(position < 0 || position >= len){
                    return;
                }
                String tit = title[position];
                actionBar.setTitle(tit);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.


            actionBar.addTab(
                    actionBar.newTab()
                            .setIcon(mSectionsPagerAdapter.getIcon(i))
                            .setTabListener(this));
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return new PlaceholderFragment();
                case 1:
                    return new LALIGA();
                case 2:
                    return new CarRace();
                case 3:
                    return new BoxingFragment();
                case 4:
                    return new HorseRace();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            int Catid;
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section5).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
                case 3:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 4:
                    return getString(R.string.title_section4).toUpperCase(l);
            }
            return getString(R.string.title_section1).toUpperCase(l);
        }

        public int getIcon(int position) {
            switch (position) {
                case 0:
                    return R.drawable.epl;
                case 1:
                    return R.drawable.la_liga;
                case 2:
                    return R.drawable.car_racing;
                case 3:
                    return R.drawable.boxing;
                case 4:
                    return R.drawable.horse_racing;
            }
            return R.drawable.la_liga;
        }
    }
}
