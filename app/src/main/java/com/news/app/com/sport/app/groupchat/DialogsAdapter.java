package com.news.app.com.sport.app.groupchat;

/**
 * Created by igorkhomenko on 9/12/14.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.news.app.R;
import com.quickblox.chat.model.QBDialog;
import com.quickblox.chat.model.QBDialogType;
import com.quickblox.users.model.QBUser;

import java.util.List;

public class DialogsAdapter extends BaseAdapter {
    private List<QBDialog> dataSource;
    private LayoutInflater inflater;

    public DialogsAdapter(List<QBDialog> dataSource, Activity ctx) {
        this.dataSource = dataSource;
        this.inflater = LayoutInflater.from(ctx);
    }

    public List<QBDialog> getDataSource() {
        return dataSource;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        // initIfNeed view
        //
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.lsv_group_chat, null);
            holder = new ViewHolder();
            holder.name = (TextView)convertView.findViewById(R.id.group_name);
            holder.mImageView=(ImageView)convertView.findViewById(R.id.img_cat);
            holder.noOfOccupant = (TextView)convertView.findViewById(R.id.active_user_no);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // set data
        //
        QBDialog dialog = dataSource.get(position);
        if(dialog.getType().equals(QBDialogType.PUBLIC_GROUP)){
            holder.name.setText(dialog.getName());
        }
        int i = dialog.getOccupants().size();
        holder.noOfOccupant.setText(i + " active users.");
        holder.mImageView.setImageResource(mThumbIds[position]);

        return convertView;
    }

    private static class ViewHolder{
        TextView name;
        TextView noOfOccupant;
        ImageView mImageView;
    }

    public static  Integer[] mThumbIds = {
            R.drawable.epl,
            R.drawable.la_liga,
            R.drawable.boxing,
            R.drawable.car_racing,
            R.drawable.horse_racing
    };
}
