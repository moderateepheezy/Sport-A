package com.news.app.com.sport.app.adapter;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.news.app.R;
import com.news.app.com.sport.app.model.LiveScore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simpumind on 4/8/15.
 */
public class LiveScoreAdapter extends BaseAdapter {

    private LayoutInflater   mLayoutInflater;

    private int resource;
    public List<String> headers = new ArrayList<>();
    public List<LiveScore> tableItems3 = new ArrayList<>();
    public List<LiveScore> tableItems2 = new ArrayList<>();
    public List<LiveScore> tableItems1 = new ArrayList<>();

    String tempValue;

    public Resources res;

    public ArrayList<String> table1 = new ArrayList<>();
    public Context context;


    public LiveScoreAdapter(Context context, Resources resLocal, List<String> headers) {
        this.context = context;
        this.headers = headers;
        this.res = resLocal;

        mLayoutInflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return headers.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        ViewHolder holder;

        //headers.add("England");
        tableItems1.add(new LiveScore(0, "England ", "70'", "Man Blue", "Chealsea", "13 Apr", "2", "4"));
        tableItems1.add(new LiveScore(0, "England Premier", "12'", "liverpool", "barca", "12 Apr", "7", "2"));

        //headers.add("Laliga");
        tableItems1.add(new LiveScore(1, "LA-LIGA ", "FT", "Real-Madrid", "Barcelona", "13 Apr", "5", "3"));

        //headers.add("Italy");
        tableItems1.add(new LiveScore(2, "England Premier", "HT'", "Southampthon", "Everton", "12 Apr", "6", "4"));
        tableItems1.add(new LiveScore(2, "England ", "70'", "Arsenal", "Chealsea", "13 Apr", "4", "2"));
        tableItems1.add(new LiveScore(2, "England ", "70'", "Man U", "Chealsea", "13 Apr", "6", "1"));

        if (convertView == null) {

            vi = mLayoutInflater.inflate(R.layout.livescore_items, null);
            holder = new ViewHolder();

            // final View tableHeader = mLayoutInflater.inflate(R.layout.item_rows, null);
            holder.league = (TextView) vi.findViewById(R.id.league_name);
            holder.localTeam = (TextView) vi.findViewById(R.id.local_team);
            holder.localTeamScore = (TextView) vi.findViewById(R.id.local_team_score);
            holder.visitorTeam = (TextView) vi.findViewById(R.id.visitor_team);
            holder.visitorTeamScore = (TextView) vi.findViewById(R.id.visitor_team_score);
            holder.date = (TextView) vi.findViewById(R.id.date);
            holder.time = (TextView) vi.findViewById(R.id.time);

            vi.setTag(holder);
        }
        else {
            holder = (ViewHolder) vi.getTag();
        }
            int pos = 0;
        String h = headers.get(position);
           // for (String header : headers) {
                holder.league.setText(h);
                for (LiveScore liveScore : tableItems1) {
                    if (liveScore.getLeagueId() == pos) {
                        holder.localTeam.setText(liveScore.getLocalTeam());
                        holder.localTeamScore.setText(liveScore.getLocal_score());
                        holder.visitorTeam.setText(liveScore.getVisitorTeam());
                        holder. visitorTeamScore.setText(liveScore.getVisitor_score());
                        holder. date.setText(liveScore.getDate());
                        holder. time.setText(liveScore.getMatchTime());
                    }
                }
                ++pos;
           // }
        return vi;
    }

    public static class ViewHolder{

        public TextView localTeam;
        public TextView localTeamScore;
        public TextView date;
        public TextView visitorTeam;
        public TextView visitorTeamScore;
        public TextView league;
        public TextView time;
    }
}