package com.news.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.news.app.com.sport.app.adapter.Adapter_All_News_List;
import com.news.app.com.sport.app.adapter.PostAdapter;
import com.news.app.com.sport.app.model.ItemNewsList;
import com.news.app.com.sport.app.model.Pojo;
import com.news.app.com.sport.app.utilities.AlertDialogManager;
import com.news.app.com.sport.app.utilities.Constant;
import com.news.app.com.sport.app.utilities.DatabaseHandler;
import com.news.app.com.sport.app.utilities.ImageLoader;
import com.news.app.com.sport.app.utilities.JsonUtils;
import com.startapp.android.publish.StartAppAd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class News_Detail extends ActionBarActivity{

    private PopupWindow popWindow;

    int mDeviceHieght;

    int position;
    String[] allArraynews,allArraynewsCatName;
    String[] allArrayNewsCId,allArrayNewsCatId,allArrayNewsCatImage,allArrayNewsCatName,allArrayNewsHeading,allArrayNewsImage,allArrayNewsDes,allArrayNewsDate;
    ImageView vp_imageview;
    ViewPager viewpager;
    public ImageLoader imageLoader;
    int TOTAL_IMAGE;
    public DatabaseHandler db;
    private Menu menu;
    //private AdView mAdView;
    String newscid,newscat_id,newscatimage,newscatname,newsheading,newsimage,newsdes,newsdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsdetail);
        //mAdView = (AdView) findViewById(R.id.adView);
        //mAdView.loadAd(new AdRequest.Builder().build());

        //getActionBar().setDisplayHomeAsUpEnabled(true);
        /*getActionBar().setHomeButtonEnabled(true);
        getActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.header)));
        getActionBar().setStackedBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.header)));

*/

        final Button btnComment = (Button)findViewById(R.id.comment);
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShowPopup(btnComment);
            }
        });
        db = new DatabaseHandler(this);
        //setTitle(Constant.CATEGORY_TITLE);
        Intent i=getIntent();

        position=i.getIntExtra("POSITION", 0);
        allArrayNewsCId=i.getStringArrayExtra("CATEGORY_ITEM_CID");
        allArrayNewsCatName=i.getStringArrayExtra("CATEGORY_ITEM_NAME");
        allArrayNewsCatImage=i.getStringArrayExtra("CATEGORY_ITEM_IMAGE");
        allArrayNewsCatId=i.getStringArrayExtra("CATEGORY_ITEM_CAT_ID");
        allArrayNewsImage=i.getStringArrayExtra("CATEGORY_ITEM_NEWSIMAGE");
        allArrayNewsHeading=i.getStringArrayExtra("CATEGORY_ITEM_NEWSHEADING");
        allArrayNewsDes=i.getStringArrayExtra("CATEGORY_ITEM_NEWSDESCRI");
        allArrayNewsDate=i.getStringArrayExtra("CATEGORY_ITEM_NEWSDATE");


        //TOTAL_IMAGE=allArraynews.length-1;
        viewpager=(ViewPager)findViewById(R.id.news_slider);
        imageLoader=new ImageLoader(getApplicationContext());

        ImagePagerAdapter adapter = new ImagePagerAdapter();
        viewpager.setAdapter(adapter);

        boolean found = false;
        int j1=0;
        for(int i1=0;i1<allArrayNewsCatId.length;i1++)
        {
            if(allArrayNewsCatId[i1].contains(String.valueOf(position)))
            {
                found=true;
                j1=i1;
                break;
            }
        }
        if(found)
        {
            viewpager.setCurrentItem(j1);
        }

        viewpager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub

                position=viewpager.getCurrentItem();
                newscat_id=allArrayNewsCatId[position];

                List<Pojo> pojolist=db.getFavRow(newscat_id);
                if(pojolist.size()==0)
                {
                    menu.getItem(2).setIcon(getResources().getDrawable(R.drawable.ic_star_outline_white_48dp));
                }
                else
                {
                    if(pojolist.get(0).getCatId().equals(newscat_id))
                    {
                        menu.getItem(2).setIcon(getResources().getDrawable(R.drawable.ic_star_white_48dp));
                    }
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int position) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int position) {
                // TODO Auto-generated method stub

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.news_menu, menu);
        this.menu = menu;
        FirstFav();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        switch (menuItem.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.menu_back:
                position=viewpager.getCurrentItem();
                position--;
                if (position < 0) {
                    position = 0;
                }
                viewpager.setCurrentItem(position);
                return true;

            case R.id.menu_next:

                position=viewpager.getCurrentItem();
                position++;
                if (position == TOTAL_IMAGE) {
                    position = TOTAL_IMAGE;
                }
                viewpager.setCurrentItem(position);
                return true;

            case R.id.menu_fav:

                position=viewpager.getCurrentItem();
                newscat_id=allArrayNewsCatId[position];

                List<Pojo> pojolist=db.getFavRow(newscat_id);
                if(pojolist.size()==0)
                {
                    AddtoFav(position);//if size is zero i.e means that record not in database show add to favorite
                }
                else
                {
                    if(pojolist.get(0).getCatId().equals(newscat_id))
                    {
                        RemoveFav(position);
                    }
                }
                return true;

            case R.id.menu_share:

                position=viewpager.getCurrentItem();
                newsheading=allArrayNewsHeading[position];
                newsdes=allArrayNewsDes[position];
                String formattedString=android.text.Html.fromHtml(newsdes).toString();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, newsheading+"\n"+formattedString+"\n"+" I Would like to share this with you. Here You Can Download This Application from PlayStore "+"https://play.google.com/store/apps/details?id="+getPackageName());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }

    }
    public  String RemoveTag(String html){

        html=html.replaceAll("<br/>","");

        return html;
    }
    public void AddtoFav(int position)
    {
        newscat_id=allArrayNewsCatId[position];
        newscid=allArrayNewsCId[position];
        newscatname=allArrayNewsCatName[position];
        //newscatimage=allArrayNewsCatImage[position];
        newsheading=allArrayNewsHeading[position];
        newsimage=allArrayNewsImage[position];
        newsdes=allArrayNewsDes[position];
        newsdate=allArrayNewsDate[position];

        db.AddtoFavorite(new Pojo(newscat_id,newscid,newscatname,newsheading,newsimage,newsdes,newsdate));
        Toast.makeText(getApplicationContext(), "Added to Favorite", Toast.LENGTH_SHORT).show();
        menu.getItem(2).setIcon(getResources().getDrawable(R.drawable.ic_star_white_48dp));
    }

    //remove from favorite
    public void RemoveFav(int position)
    {
        newscat_id=allArrayNewsCatId[position];
        db.RemoveFav(new Pojo(newscat_id));
        Toast.makeText(getApplicationContext(), "Removed from Favorite", Toast.LENGTH_SHORT).show();
        menu.getItem(2).setIcon(getResources().getDrawable(R.drawable.ic_star_outline_white_48dp));

    }

    public void FirstFav()
    {
        int first=viewpager.getCurrentItem();
        String Image_id=allArrayNewsCatId[first];

        List<Pojo> pojolist=db.getFavRow(Image_id);
        if(pojolist.size()==0)
        {
            menu.getItem(2).setIcon(getResources().getDrawable(R.drawable.ic_star_outline_white_48dp));

        }
        else
        {
            if(pojolist.get(0).getCatId().equals(Image_id))
            {
                menu.getItem(2).setIcon(getResources().getDrawable(R.drawable.ic_star_white_48dp));

            }

        }
    }

    private class ImagePagerAdapter extends PagerAdapter {

        private LayoutInflater inflater;

        public ImagePagerAdapter() {
            // TODO Auto-generated constructor stub

            inflater = getLayoutInflater();
        }

        @Override
        public int getCount() {
            return allArrayNewsCatId.length;

        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            View imageLayout = inflater.inflate(R.layout.newpager_item, container, false);
            assert imageLayout != null;

            ImageView news_imageview=(ImageView)imageLayout.findViewById(R.id.image_news);
            TextView txt_newstitle=(TextView)imageLayout.findViewById(R.id.text_newstitle);
            TextView txt_newsdate=(TextView)imageLayout.findViewById(R.id.text_newsdate);
           // TextView txt_newsdes=(TextView)imageLayout.findViewById(R.id.text_newsdes);
            WebView webnewsdes=(WebView)imageLayout.findViewById(R.id.webView_newsdes);

            imageLoader.DisplayImage(Constant.SERVER_IMAGE_NEWSLISTDETAILS+allArrayNewsImage[position], news_imageview);

            txt_newstitle.setText(allArrayNewsHeading[position]);
            txt_newsdate.setText(allArrayNewsDate[position]);
            //txt_newsdes.setText(allArrayNewsDes[position]);
            webnewsdes.setBackgroundColor(Color.parseColor(getString(R.color.background_color)));
            webnewsdes.setFocusableInTouchMode(false);
            webnewsdes.setFocusable(false);
            webnewsdes.getSettings().setDefaultTextEncodingName("UTF-8");

            String mimeType = "text/html; charset=UTF-8";
            String encoding = "utf-8";
            String htmlText = allArrayNewsDes[position];

            String text = "<html><head>"
                    + "<style type=\"text/css\">body{color: #525252;}"
                    + "</style></head>"
                    + "<body>"
                    +  htmlText
                    + "</body></html>";

            webnewsdes.loadData(text, mimeType, encoding);

            container.addView(imageLayout, 0);
            return imageLayout;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }
    }
    @Override
    protected void onPause() {
        //mAdView.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //mAdView.resume();
    }

    @Override
    protected void onDestroy() {
        //mAdView.destroy();
        super.onDestroy();
    }

    // call this method when required to show popup
    public void onShowPopup(View v){

        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // inflate the custom popup layout
        final View inflatedView = layoutInflater.inflate(R.layout.fb_popup_layout, null,false);
        // find the ListView in the popup layout
        ListView listView = (ListView)inflatedView.findViewById(R.id.commentsListView);

        // get device size
        Display display = getWindowManager().getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);
        mDeviceHieght = size.y;


        // fill the data to the list items
        setSimpleList(listView);


        // set height depends on the device size
        popWindow = new PopupWindow(inflatedView, size.x - 50,mDeviceHieght - 50, true );
        // set a background drawable with rounders corners
        popWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.fb_popup_bg));
        // make it focusable to show the keyboard to enter in `EditText`
        popWindow.setFocusable(true);
        // make it outside touchable to dismiss the popup window
        popWindow.setOutsideTouchable(true);

        // show the popup at bottom of the screen and set some margin at bottom ie,
        popWindow.showAtLocation(v, Gravity.BOTTOM, 0,100);
    }

    void setSimpleList(ListView listView){

        ArrayList<String> contactsList = new ArrayList<String>();

        for (int index = 0; index < 10; index++) {
            contactsList.add("I am @ index " + index + " today " + Calendar.getInstance().getTime().toString());
        }

        listView.setAdapter(new ArrayAdapter<String>(News_Detail.this,
                R.layout.fb_comments_list_item, android.R.id.text1,contactsList));
    }

}
