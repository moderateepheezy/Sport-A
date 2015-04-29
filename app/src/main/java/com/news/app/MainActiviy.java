package com.news.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdView;
import com.news.app.com.sport.app.model.ItemAllNews;
import com.news.app.com.sport.app.utilities.Constant;
import com.news.app.com.sport.app.utilities.ImageLoader;
import com.startapp.android.publish.StartAppAd;

import java.util.ArrayList;
import java.util.List;


public class MainActiviy extends ActionBarActivity {

    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private NavigationAdapter mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    private AdView mAdView;
    private StartAppAd startAppAd = new StartAppAd(this);
    private ActionBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StartAppAd.init(this, getString(R.string.startapp_dev_id), getString(R.string.startapp_app_id));
        setContentView(R.layout.activity_main_activiy);

        /*bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.header)));
        bar.setStackedBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.header)));
        bar.setHomeButtonEnabled(true);
        getSupportActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));*/

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new News_All())
                    .commit();
        }


        mDrawerList = (ListView)findViewById(R.id.navList);mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void addDrawerItems() {
        final List<NavigationItem> navigationItems = getMenu();
        mAdapter = new NavigationAdapter(this, android.R.layout.simple_list_item_1, navigationItems);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActiviy.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
                Fragment fragment = null;
                switch (position){
                    case 0:
                        fragment = new News_All();
                        break;
                    case 1:
                        Intent i = new Intent(getApplicationContext(), NewsActivity.class);
                        startActivity(i);
                        break;
                    case 2:
                        fragment = new ChatActivity();
                        break;
                    case 3:
                        Intent x = new Intent(getApplicationContext(), Subscribe.class);
                        startActivity(x);
                        break;
                    case 4:
                        Intent l = new Intent(getApplicationContext(), LiveScoreActivity.class);
                        startActivity(l);
                        break;
                }
                if(fragment != null){
                    FragmentManager fm = getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.container, fragment);
                    fragmentTransaction.commit();
                }
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public List<NavigationItem> getMenu() {
        List<NavigationItem> items = new ArrayList<NavigationItem>();
        items.add(new NavigationItem("Home", R.drawable.nav1));
        items.add(new NavigationItem("Sport News", R.drawable.nav_news));
        items.add(new NavigationItem("Sport Chat", R.drawable.nav_chat));
        items.add(new NavigationItem("Subscribe", R.drawable.nav2));
        items.add(new NavigationItem("Live Scores", R.drawable.score));
        items.add(new NavigationItem("FAQS", R.drawable.faqs));
        return items;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.menu_search, menu);
            return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.refresh:
                finish();
                startActivity(getIntent());
                overridePendingTransition(R.anim.open_next, R.anim.close_next);

                return true;

            case R.id.menu_about:

                Intent about = new Intent(getApplicationContext(), About_Us.class);
                startActivity(about);

                return true;

            case R.id.menu_overflow:
                return true;

            case R.id.menu_moreapp:

                startActivity(new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(getString(R.string.play_more_apps))));

                return true;

            case R.id.menu_rateapp:

                final String appName = getPackageName();//your application package name i.e play store application url
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id="
                                    + appName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id="
                                    + appName)));
                }
                return true;

            default:
                // Activate the navigation drawer toggle
                if (mDrawerToggle.onOptionsItemSelected(item)) {
                    return true;
                }

        }
        return super.onOptionsItemSelected(item);
    }


    public class NavigationAdapter extends ArrayAdapter<NavigationItem> {

        private Activity activity;
        private List<NavigationItem> itemsAllnews;
        private ItemAllNews objAllBean;
        private int row;
        public ImageLoader imageLoader;


        public NavigationAdapter(Activity act, int resource, List<NavigationItem> arrayList) {
            super(act, resource, arrayList);
            this.activity = act;
            this.row = resource;
            this.itemsAllnews = arrayList;
            imageLoader=new ImageLoader(activity);

        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ViewHolder holder;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) activity
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.item_row, null);

                holder = new ViewHolder();
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            holder.txt=(TextView)view.findViewById(R.id.rowText);
            holder.img_cat=(ImageView)view.findViewById(R.id.rowIcon);

            NavigationItem nav = getItem(position);
            holder.txt.setText(nav.getText().toString());
            holder.img_cat.setImageResource(nav.getDrawable());

            return view;

        }

        public class ViewHolder {

            public TextView txt;
            public ImageView img_cat;
        }

    }
}
