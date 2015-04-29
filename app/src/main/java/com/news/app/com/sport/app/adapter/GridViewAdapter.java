package com.news.app.com.sport.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.news.app.R;
import com.news.app.com.sport.app.model.ItemAllNews;

import java.util.ArrayList;

/**
 * Created by simpumind on 4/15/15.
 */
public class GridViewAdapter extends ArrayAdapter<ItemAllNews> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<ItemAllNews> data = new ArrayList<ItemAllNews>();

    public GridViewAdapter(Context context, int layoutResourceId, ArrayList<ItemAllNews> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageTitle=(TextView)row.findViewById(R.id.txt_allnews_categty);
            holder.image=(ImageView)row.findViewById(R.id.img_cat);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }


        ItemAllNews item = data.get(position);
        holder.imageTitle.setText(item.getCategoryName());
        holder.image.setImageBitmap(item.getImage());
        return row;
    }

    static class ViewHolder {
        TextView imageTitle;
        ImageView image;
    }
}
