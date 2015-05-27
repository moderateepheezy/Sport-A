/*
package com.news.app;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.ads.AdView;
import com.startapp.android.publish.StartAppAd;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerCallbacks {

*/
/*
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     * *//*



    private com.news.app.Fragments.NavigationDrawerFragment mNavigationDrawerFragment;
    public static Toolbar mToolbar;

    private AdView mAdView;
    private StartAppAd startAppAd = new StartAppAd(this);
    private ActionBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StartAppAd.init(this, getString(R.string.startapp_dev_id), getString(R.string.startapp_app_id));
        setContentView(R.layout.activity_main_activity2);
        this.invalidateOptionsMenu();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new News_All())
                    .commit();
        }

        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.header)));
        bar.setStackedBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.header)));
        bar.setHomeButtonEnabled(true);
        getSupportActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        //mToolbar.inflateMenu(R.menu.main);
        //mToolbar.setEnabled(false);
       // mToolbar.
        //setSupportActionBar(mToolbar);

        mNavigationDrawerFragment = (com.news.app.Fragments.NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        //Toast.makeText(this, "Menu item selected -> " + position, Toast.LENGTH_SHORT).show();
        Fragment fragment = null;
        switch (position){
            case 0:
               // Intent a = new Intent(this, MainActivity.class);
               // startActivity(a);
                break;
            case 1:
                fragment = new DialogActivity();
                break;
            case 2:
                Intent i = new Intent(this, NewsActivity.class);
                startActivity(i);
                break;
            case 3:
                Intent x = new Intent(this, Subscribe.class);
                startActivity(x);
                break;
        }
        if(fragment != null){
            FragmentManager fm = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment);
            fragmentTransaction.commit();
        }
    }


    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.menu_search, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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

                return super.onOptionsItemSelected(item);
        }
    }

}
*/
