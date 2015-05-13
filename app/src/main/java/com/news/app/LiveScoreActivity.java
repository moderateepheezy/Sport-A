package com.news.app;

import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TableLayout;

import com.news.app.com.sport.app.adapter.LiveScoreAdapter;

import java.util.ArrayList;
import java.util.List;


public class LiveScoreActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_score);

        List<String> headers = new ArrayList<>();
        headers.add("England");
        headers.add("La liga");
        headers.add("Italy");

        Resources res = getResources();
        ListView listView = (ListView)findViewById(R.id.lsv_livescore);
        listView.setAdapter(new LiveScoreAdapter(this, res, headers));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_live_score, menu);
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
