package com.news.app;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.news.app.com.sport.app.adapter.ExpandableAdapter;
import com.news.app.com.sport.app.model.Child;
import com.news.app.com.sport.app.model.Parent;

import java.util.ArrayList;


public class FAQS extends ActionBarActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    static ArrayList<Parent> listDataHeader;
    static ArrayList<Child> listDataChild;
    private ArrayList<String> parentItems = new ArrayList<String>();
    private ArrayList<Object> childItems = new ArrayList<Object>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);

        expListView = (ExpandableListView)findViewById(R.id.faq_list);
        //expListView.setAdapter(ge);

        setGroupParents();
        setChildData();

        ExpandableAdapter adapter = new ExpandableAdapter(parentItems, childItems);
        adapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), this);
        expListView.setAdapter(adapter);

    }

    public void setGroupParents() {
        parentItems.add("How can i subscribe to the channel");
        parentItems.add("I am having trouble logging out");
        parentItems.add( "How can i end a session");
        parentItems.add("The timeline changes, Why?");
    }

    public void setChildData() {
        ArrayList<String> child = new ArrayList<String>();
        child.add("Email with attachment");
        childItems.add(child);
        child = new ArrayList<String>();
        child.add("Do not get frustrated");
        childItems.add(child);
        child = new ArrayList<String>();
        child.add("Validating feedback in Android");
        childItems.add(child);
        child = new ArrayList<String>();
        child.add("Check your phone manual to know why");
        childItems.add(child);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_faq, menu);
        return true;
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
}
