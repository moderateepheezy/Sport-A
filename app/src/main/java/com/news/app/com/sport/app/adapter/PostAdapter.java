package com.news.app.com.sport.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.news.app.R;

/**
 * Created by simpumind on 4/8/15.
 */
public class PostAdapter extends BaseAdapter{

    private LayoutInflater   mLayoutInflater;

    public PostAdapter(Context context){
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return GROUP_NAME.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder mVHolder;
        if(convertView == null){
            convertView=mLayoutInflater.inflate(R.layout.lsv_chat_group, parent, false);
            mVHolder=new ViewHolder();
            mVHolder.mImageView=(ImageView)convertView.findViewById(R.id.img_cat);
            mVHolder.mGroupName=(TextView)convertView.findViewById(R.id.group_name);
            convertView.setTag(mVHolder);
        }else{
            mVHolder=(ViewHolder)convertView.getTag();
        }
        mVHolder.mImageView.setImageResource(mThumbIds[position]);
        mVHolder.mGroupName.setText(GROUP_NAME[position]);

        return convertView;
    }
    static class ViewHolder{
        ImageView mImageView;
        TextView mGroupName;
    }


    public static String[] GROUP_NAME = new String[] {
            "English Premiere League", "La-Liga", "Boxing", "Car Racing", "Horse Racing"
    };


    /*public static final String[] TIME = new String[]{
      "11:00pm", "12:45am", "02:43pm", "03:45pm"
    }*/;

    public static  Integer[] mThumbIds = {
            R.drawable.epl,
            R.drawable.la_liga,
            R.drawable.boxing,
            R.drawable.car_racing,
            R.drawable.horse_racing
    };
}
