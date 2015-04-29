package com.news.app;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SearchView;

import com.news.app.com.sport.app.adapter.Adapter_Latest;
import com.news.app.com.sport.app.adapter.GridViewAdapter;
import com.news.app.com.sport.app.model.ItemAllNews;
import com.news.app.com.sport.app.model.ItemLatest;
import com.news.app.com.sport.app.utilities.AlertDialogManager;
import com.news.app.com.sport.app.utilities.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class News_All extends Fragment {

    private GridViewAdapter gridAdapter;
    GridView lsv_allnews;

    List<ItemLatest> arrayOfLatestnews;
    String[] allArrayNewsCId, allArrayNewsCatId,
            allArrayNewsCatName, allArrayNewsHeading, allArrayNewsImage,
            allArrayNewsDes, allArrayNewsDate;
    JsonUtils util;
    int textlength = 0;

    public News_All() {
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View rootView = inflater.inflate(R.layout.news_all, container, false);
		lsv_allnews=(GridView)rootView.findViewById(R.id.gridView1);

        gridAdapter = new GridViewAdapter(getActivity(), R.layout.lsv_item_category, getData());
        lsv_allnews.setAdapter(gridAdapter);

        lsv_allnews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Fragment fr = new Fragment();
                ItemAllNews item = (ItemAllNews) parent.getItemAtPosition(position);
                // switch (item.getTitle().g)
                if(item.getCategoryName().equals("Yello Wall")) {
                    //Create intent
                    /*Intent intent = new Intent(getActivity(), WallActivity.class);

                    //Start details activity
                    startActivity(intent);*/
                    fr = new WallActivity();
                }
                if(item.getCategoryName().equals("Sport Chat")) {
                    //Create intent
                    ///Intent intent = new Intent(getActivity(), ChatActivity.class);

                    //Start details activity
                   // startActivity(intent);
                    fr = new ChatActivity();
                }




                if(item.getCategoryName().equals("Live Score")) {
                    //Create intent
                   // Intent intent = new Intent(getActivity(), LiveScoreActivity.class);

                    //Start details activity
                   // startActivity(intent);
                }

                if(item.getCategoryName().equals("FAQS")) {
                    //Create intent
                  //  Intent intent = new Intent(getActivity(), FaqsActivity.class);

                    //Start details activity
                  //  startActivity(intent);
                }

                if(item.getCategoryName().equals("News")) {
                    //Create intent
                    Intent intent = new Intent(getActivity(), NewsActivity.class);

                    //Start details activity
                   startActivity(intent);
                }
                if(item.getCategoryName().equals("Subscribe")) {
                    //Create intent
                    Intent intent = new Intent(getActivity(), Subscribe.class);

                    //Start details activity
                    startActivity(intent);
                }

                android.support.v4.app.FragmentManager fm = getFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.container, fr);
                fragmentTransaction.commit();
            }
        });

        return rootView;
    }

    private ArrayList<ItemAllNews> getData() {
        final ArrayList<ItemAllNews> imageItems = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        String[] x = getResources().getStringArray(R.array.image_names);
        for (int i = 0; i < imgs.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            imageItems.add(new ItemAllNews(bitmap,x[i]));
        }
        return imageItems;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search, menu);

        final SearchView searchView = (SearchView) menu.findItem(R.id.search)
                .getActionView();

        final MenuItem searchMenuItem = menu.findItem(R.id.search);
        searchView
                .setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        // TODO Auto-generated method stub
                        if (!hasFocus) {
                            searchMenuItem.collapseActionView();
                            searchView.setQuery("", false);
                        }
                    }
                });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {

                textlength = newText.length();
                arrayOfLatestnews.clear();

                for (int i = 0; i < allArrayNewsHeading.length; i++) {
                    if (textlength <= allArrayNewsHeading[i].length()) {
                        if (newText.toString().equalsIgnoreCase(
                                (String) allArrayNewsHeading[i].subSequence(0,
                                        textlength))) {

                            ItemLatest objItem = new ItemLatest();

                            // objItem.setCategoryImage((allArrayNewsCatImage[i]));
                            objItem.setCategoryName((allArrayNewsCatName[i]));
                            objItem.setCatId(allArrayNewsCatId[i]);
                            objItem.setCId(allArrayNewsCId[i]);
                            objItem.setNewsDate(allArrayNewsDate[i]);
                            objItem.setNewsDescription(allArrayNewsDes[i]);
                            objItem.setNewsHeading(allArrayNewsHeading[i]);
                            objItem.setNewsImage(allArrayNewsImage[i]);
                            arrayOfLatestnews.add(objItem);

                        }
                    }
                }

                //setAdapterToListview();
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                // Do something
                return true;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.refresh:
                startActivity(getActivity().getIntent());
                getActivity().overridePendingTransition(R.anim.open_next, R.anim.close_next);

                return true;

            case R.id.menu_about:

                Intent about=new Intent(getActivity(), About_Us.class);
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

                final String appName = getActivity().getPackageName();//your application package name i.e play store application url
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
