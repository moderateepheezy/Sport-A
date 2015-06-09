package com.news.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdView;
import com.news.app.com.sport.app.NotificationView;
import com.news.app.com.sport.app.groupchat.ApplicationSingleton;
import com.news.app.com.sport.app.groupchat.DataHolder;
import com.news.app.com.sport.app.groupchat.DialogsActivity;
import com.news.app.com.sport.app.groupchat.LoginActivity;
import com.news.app.com.sport.app.model.ItemAllNews;
import com.news.app.com.sport.app.utilities.Constant;
import com.news.app.com.sport.app.utilities.ImageLoader;
import com.quickblox.auth.QBAuth;
import com.quickblox.auth.model.QBSession;
import com.quickblox.chat.QBChatService;
import com.quickblox.core.QBEntityCallbackImpl;
import com.quickblox.core.QBSettings;
import com.quickblox.users.model.QBUser;
import com.startapp.android.publish.StartAppAd;

import org.jivesoftware.smack.SmackException;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActiviy extends ActionBarActivity {

    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private NavigationAdapter mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    private AdView mAdView;
    private StartAppAd startAppAd = new StartAppAd(this);
    private ActionBar bar;

    private NotificationManager mNotificationManager;
    private int notificationID = 100;
    private int numMessages = 0;

    public static final String APP_ID = "22950";
    public static final String AUTH_KEY = "TRfNfhHpURK8sO3";
    public static final String AUTH_SECRET = "AJJkDmt7XTJ2Ccj";

    static final int AUTO_PRESENCE_INTERVAL_IN_SECONDS = 30;

    private QBChatService chatService;

    //private ProgressBar progressBar;

    private Context context;

    final QBUser user = new QBUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activiy);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new News_All())
                    .commit();
        }



        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        QBChatService.setDebugEnabled(true);
        QBSettings.getInstance().fastConfigInit(APP_ID, AUTH_KEY, AUTH_SECRET);
        if (!QBChatService.isInitialized()) {
            QBChatService.init(this);
        }
        chatService = QBChatService.getInstance();

        //Declare the timer
        Timer t = new Timer();
//Set the schedule function and rate
        t.scheduleAtFixedRate(new TimerTask() {

                                  @Override
                                  public void run() {
                                      runOnUiThread(new Runnable() {

                                          @Override
                                          public void run() {
                                              displayNotification();
                                          }

                                      });
                                  }

                              },
//Set how long before to start calling the TimerTask (in milliseconds)
                0,
//Set the amount of time between each execution (in milliseconds)
                50000);

    }




    protected void displayNotification() {
        Log.i("Start", "notification");

      /* Invoking the default notification service */
        NotificationCompat.Builder  mBuilder =
                new NotificationCompat.Builder(this);

        mBuilder.setContentTitle("SportApp News");
        mBuilder.setContentText("There are new sport update on SportApp. Click now!");
        mBuilder.setTicker("New Message Alert!");
        mBuilder.setSmallIcon(R.drawable.ic_logo_ap);

      /* Increase notification number every time a new notification arrives */
        mBuilder.setNumber(++numMessages);

      /* Creates an explicit intent for an Activity in your app */
        Intent resultIntent = new Intent(this, NewsActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(NotificationView.class);

      /* Adds the Intent that starts the Activity to the top of the stack */
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder.setContentIntent(resultPendingIntent);

        mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

      /* notificationID allows you to update the notification later on. */
        mNotificationManager.notify(notificationID, mBuilder.build());
    }

    private void addDrawerItems() {
        final List<NavigationItem> navigationItems = getMenu();
        mAdapter = new NavigationAdapter(this, android.R.layout.simple_list_item_1, navigationItems);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
                        Intent o = new Intent(getApplicationContext(), DialogsActivity.class);
                        startActivity(o);
                        break;
                    case 3:
                        Intent x = new Intent(getApplicationContext(), Subscribe.class);
                        startActivity(x);
                        break;
                    case 4:
                        Intent l = new Intent(getApplicationContext(), LiveScoreActivity.class);
                        startActivity(l);
                        break;
                    case 5:
                        Intent r = new Intent(getApplicationContext(), FAQS.class);
                        startActivity(r);
                        break;
                }
                if(fragment != null){
                    FragmentManager fm = getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.container, fragment);
                    fragmentTransaction.commit();
                }
                mDrawerLayout.closeDrawers();
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
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
        items.add(new NavigationItem("Sport ChatActivity", R.drawable.nav_chat));
        items.add(new NavigationItem("Subscribe", R.drawable.nav2));
        items.add(new NavigationItem("Live Scores", R.drawable.scores));
        items.add(new NavigationItem("Game", R.drawable.games));
        items.add(new NavigationItem("FAQS", R.drawable.faqi));
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
