package com.news.app.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.news.app.About_Us;
import com.news.app.com.sport.app.adapter.Adapter_All_News_List;
import com.news.app.com.sport.app.model.Pojo;
import com.news.app.com.sport.app.utilities.AlertDialogManager;
import com.news.app.com.sport.app.utilities.Constant;
import com.news.app.com.sport.app.utilities.DatabaseHandlerCache;
import com.news.app.com.sport.app.utilities.JsonUtils;
import com.news.app.News_Detail;
import com.news.app.News_Favorite;
import com.news.app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import eu.erikw.PullToRefreshListView;

/**
 * Created by simpumind on 4/24/15.
 */
public class PlaceholderFragment extends Fragment {

    PullToRefreshListView lsv_cat;
    List<Pojo> arrayOfNewsList;
    Adapter_All_News_List objAdapter;
    AlertDialogManager alert = new AlertDialogManager();
    ArrayList<String> allListnews,allListnewsCatName;
    ArrayList<String> allListNewsCId,allListNewsCatId,allListNewsCatImage,allListNewsCatName,allListNewsHeading,allListNewsImage,allListNewsDes,allListNewsDate;
    String[] allArraynews,allArraynewsCatName;
    String[] allArrayNewsCId,allArrayNewsCatId,allArrayNewsCatImage,allArrayNewsCatName,allArrayNewsHeading,allArrayNewsImage,allArrayNewsDes,allArrayNewsDate;
    private Pojo objAllBean;
    private int columnWidth;
    JsonUtils util;
    int textlength = 0;
    static ProgressBar pDialog;

    DatabaseHandlerCache db;

    private DatabaseHandlerCache.DatabaseManager dbManager;

    // private AdView mAdView;
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    //public List<ItemAllNews> arrayOfAllnews;



    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);

        db = new DatabaseHandlerCache(getActivity());
        dbManager = DatabaseHandlerCache.DatabaseManager.INSTANCE;

        arrayOfNewsList=new ArrayList<Pojo>();
        arrayOfNewsList = db.getAllDataPremieeLeague();

        /*objAdapter = new Adapter_All_News_List(getActivity(), R.layout.lsv_item_news_list_horse,
                arrayOfNewsList, columnWidth);
        lsv_cat.setAdapter(objAdapter);*/

        allListnews=new ArrayList<String>();
        allListnewsCatName=new ArrayList<String>();
        allListNewsCId=new ArrayList<String>();
        allListNewsCatId=new ArrayList<String>();
        allListNewsCatImage=new ArrayList<String>();
        allListNewsCatName=new ArrayList<String>();
        allListNewsHeading=new ArrayList<String>();
        allListNewsImage=new ArrayList<String>();
        allListNewsDes=new ArrayList<String>();
        allListNewsDate=new ArrayList<String>();

        allArraynews=new String[allListnews.size()];
        allArraynewsCatName=new String[allListnewsCatName.size()];
        allArrayNewsCId=new String[allListNewsCId.size()];
        allArrayNewsCatId=new String[allListNewsCatId.size()];
        allArrayNewsCatImage=new String[allListNewsCatImage.size()];
        allArrayNewsCatName=new String[allListNewsCatName.size()];
        allArrayNewsHeading=new String[allListNewsHeading.size()];
        allArrayNewsImage=new String[allListNewsImage.size()];
        allArrayNewsDes=new String[allListNewsDes.size()];
        allArrayNewsDate=new String[allListNewsDate.size()];

        util=new JsonUtils(getActivity());


        networkCall();
        lsv_cat = (PullToRefreshListView)rootView.findViewById(R.id.lsv_latest);
        lsv_cat.setOnRefreshListener(new PullToRefreshListView.OnRefreshListener() {

            @Override
            public void onRefresh() {
                // Your code to refresh the list contents

                // ...

                // Make sure you call listView.onRefreshComplete()
                // when the loading is done. This can be done from here or any
                // other place, like on a broadcast receive from your loading
                // service or the onPostExecute of your AsyncTask.
                arrayOfNewsList.clear();
                networkCall();
                lsv_cat.onRefreshComplete();
            }
        });



        lsv_cat.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                // TODO Auto-generated method stub

                objAllBean=arrayOfNewsList.get(position);
                int pos=Integer.parseInt(objAllBean.getCatId());

                Intent intplay=new Intent(getActivity(),News_Detail.class);
                intplay.putExtra("POSITION", pos);
                intplay.putExtra("CATEGORY_ITEM_CID", allArrayNewsCId);
                intplay.putExtra("CATEGORY_ITEM_NAME", allArrayNewsCatName);
                intplay.putExtra("CATEGORY_ITEM_IMAGE", allArrayNewsCatImage);
                intplay.putExtra("CATEGORY_ITEM_CAT_ID", allArrayNewsCatId);
                intplay.putExtra("CATEGORY_ITEM_NEWSIMAGE", allArrayNewsImage);
                intplay.putExtra("CATEGORY_ITEM_NEWSHEADING", allArrayNewsHeading);
                intplay.putExtra("CATEGORY_ITEM_NEWSDESCRI", allArrayNewsDes);
                intplay.putExtra("CATEGORY_ITEM_NEWSDATE", allArrayNewsDate);

                startActivity(intplay);
            }
        });
        pDialog = (ProgressBar)rootView.findViewById(R.id.progressBar);
        return rootView;
    }

    private void networkCall() {
        if (JsonUtils.isNetworkAvailable(getActivity())) {
            new MyTask().execute(Constant.CATEGORY_ITEM_URL+7);
        } else {
            showToast("No Network Connection!!!");
            alert.showAlertDialog(getActivity(), "Internet Connection Error",
                    "Please connect to working Internet connection", false);
        }
    }

    private	class MyTask extends AsyncTask<String, Void, String> {



        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            ;
        }

        @Override
        protected String doInBackground(String... params) {
            return JsonUtils.getJSONString(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);


            if (null != pDialog && pDialog.isShown()) {
                pDialog.setVisibility(View.GONE);
            }

            if (null == result || result.length() == 0) {
                showToast("Server Connection Error");
                alert.showAlertDialog(getActivity(), "Server Connection Error",
                        "May Server Under Maintaines Or Low Network", false);

            } else {

                try {
                    JSONObject mainJson = new JSONObject(result);
                    JSONArray jsonArray = mainJson.getJSONArray(Constant.CATEGORY_ARRAY_NAME);
                    JSONObject objJson = null;
                    db = new DatabaseHandlerCache(getActivity());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        objJson = jsonArray.getJSONObject(i);

                        //ItemNewsList objItem = new ItemNewsList();
                        Pojo p = new Pojo();

                        p.setCId(objJson.getString(Constant.CATEGORY_ITEM_CID));
                        p.setCategoryName(objJson.getString(Constant.CATEGORY_ITEM_NAME));
                        p.setCatId(objJson.getString(Constant.CATEGORY_ITEM_CAT_ID));
                        p.setNewsImage(objJson.getString(Constant.CATEGORY_ITEM_NEWSIMAGE));
                        p.setNewsHeading(objJson.getString(Constant.CATEGORY_ITEM_NEWSHEADING));
                        p.setNewsDesc(objJson.getString(Constant.CATEGORY_ITEM_NEWSDESCRI));
                        p.setNewsDate(objJson.getString(Constant.CATEGORY_ITEM_NEWSDATE));

                        db.AddtoPremiereLeague(new Pojo(p.getCatId(), p.getCId(), p.getCategoryName(),
                                p.getNewsHeading(), p.getNewsImage(), p.getNewsDesc(), p.getNewsDate()));

                        arrayOfNewsList = db.getAllDataPremieeLeague();


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for(int j=0;j<arrayOfNewsList.size();j++)
                {

                    objAllBean=arrayOfNewsList.get(j);

                    allListNewsCatId.add(objAllBean.getCatId());
                    allArrayNewsCatId=allListNewsCatId.toArray(allArrayNewsCatId);

                    allListNewsCatName.add(objAllBean.getCategoryName());
                    allArrayNewsCatName=allListNewsCatName.toArray(allArrayNewsCatName);

                    allListNewsCId.add(String.valueOf(objAllBean.getCId()));
                    allArrayNewsCId=allListNewsCId.toArray(allArrayNewsCId);

                    allListNewsImage.add(String.valueOf(objAllBean.getNewsImage()));
                    allArrayNewsImage=allListNewsImage.toArray(allArrayNewsImage);


                    allListNewsHeading.add(String.valueOf(objAllBean.getNewsHeading()));
                    allArrayNewsHeading=allListNewsHeading.toArray(allArrayNewsHeading);

                    allListNewsDes.add(String.valueOf(objAllBean.getNewsDesc()));
                    allArrayNewsDes=allListNewsDes.toArray(allArrayNewsDes);

                    allListNewsDate.add(String.valueOf(objAllBean.getNewsDate()));
                    allArrayNewsDate=allListNewsDate.toArray(allArrayNewsDate);

                }

                setAdapterToListview();
            }

        }
    }

    public void setAdapterToListview() {
        if(!arrayOfNewsList.isEmpty()) {
            objAdapter = new Adapter_All_News_List(getActivity(), R.layout.lsv_item_news_list,
                    arrayOfNewsList, columnWidth);
            lsv_cat.setAdapter(objAdapter);
        }else{
            lsv_cat.setAdapter(null);
        }
    }

    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        // TODO Auto-generated method stub

        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.menu_search, menu);


        final SearchView searchView = (SearchView) menu.findItem(R.id.search)
                .getActionView();

        final MenuItem searchMenuItem = menu.findItem(R.id.search);
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if(!hasFocus) {
                    searchMenuItem.collapseActionView();
                    searchView.setQuery("", false);
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {

                textlength=newText.length();
                arrayOfNewsList.clear();

                for(int i=0;i< allArrayNewsHeading.length;i++)
                {
                    if(textlength <= allArrayNewsHeading[i].length())
                    {
                        if(newText.toString().equalsIgnoreCase((String) allArrayNewsHeading[i].subSequence(0, textlength)))
                        {


                            Pojo objItem = new Pojo();

                            objItem.setCategoryName(allArrayNewsCatName[i]);
                            objItem.setCatId(allArrayNewsCatId[i]);
                            objItem.setCId(allArrayNewsCId[i]);
                            objItem.setNewsDate(allArrayNewsDate[i]);
                            objItem.setNewsDesc(allArrayNewsDes[i]);
                            objItem.setNewsHeading(allArrayNewsHeading[i]);
                            objItem.setNewsImage(allArrayNewsImage[i]);

                            arrayOfNewsList.add(objItem);

                        }
                    }
                }

                setAdapterToListview();
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
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case android.R.id.home:
                getActivity().onBackPressed();
                return true;

            case R.id.refresh:
                getActivity().finish();
                startActivity(getActivity().getIntent());
                getActivity().overridePendingTransition(R.anim.open_next, R.anim.close_next);

                return true;

            case R.id.menu_favorite:
                startActivity(new Intent(getActivity(), News_Favorite.class));
                return true;

            case R.id.menu_about:
                Intent about = new Intent(getActivity(), About_Us.class);
                startActivity(about);
                return true;

            case R.id.menu_overflow:
                return true;

            case R.id.menu_moreapp:
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(getString(R.string.play_more_apps))));
                return true;

            case R.id.menu_rateapp:
                final String appName = getActivity().getPackageName();
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + appName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id="
                                    + appName)));
                }
                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void onDestroy() {
        // Log.e("OnDestroy", "called");
        if (!dbManager.isDatabaseClosed())
            dbManager.closeDatabase();
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        // Log.e("OnPaused", "called");
        if (!dbManager.isDatabaseClosed())
            dbManager.closeDatabase();
    }

    @Override
    public void onResume() {
        super.onResume();

        arrayOfNewsList = db.getAllDataPremieeLeague();
        objAdapter = new Adapter_All_News_List(getActivity(), R.layout.lsv_item_news_list,
                arrayOfNewsList, columnWidth);
        lsv_cat.setAdapter(objAdapter);
    }
}
