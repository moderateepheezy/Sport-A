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
        return POSTS.length;
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
            convertView=mLayoutInflater.inflate(R.layout.lsv_item_news_list, parent, false);
            mVHolder=new ViewHolder();
            mVHolder.mImageView=(ImageView)convertView.findViewById(R.id.img_newslist);
            mVHolder.mPost=(TextView)convertView.findViewById(R.id.txt_newslistheading);
            mVHolder.mLastSeen=(TextView)convertView.findViewById(R.id.txt_newslistdate);
            convertView.setTag(mVHolder);
        }else{
            mVHolder=(ViewHolder)convertView.getTag();
        }
        mVHolder.mImageView.setImageResource(mThumbIds[position]);
        mVHolder.mPost.setText(POSTS[position]);
        mVHolder.mLastSeen.setText(TIME[position]);

        return convertView;
    }
    static class ViewHolder{
        ImageView mImageView;
        TextView mPost;
        TextView mLastSeen;
        TextView mUsername;
    }


    public static String[] POSTS = new String[] {"by adding an ability which allows the user" +
            " picking up a file to be sent along with the e-mail message as an attachment. The " +
            "email form will have an additional field with which the user can select a file " +
            "on his computer, like in the following screenshot",
            "Workflow is similar to the sample application described in the tutorial Sending e-mail with JSP, Servlet and " +
                    "JavaMail, plus with some changes for handling file upload and attaching the file to the e-mail message, as follows:",
            "Frustration arise the moment you realise that you " +
                    "cannot use a custom font by setting TextView's " +
                    "and EditText's android:typeface XML-attribute. " +
                    "AnyTextView is here to relieve your pain",
            "Form validation and feedback library for Android." +
                    " Provides .setText for more than just TextView" +
                    " and EditText widgets. Provides easy means to validate" +
                    " with dependencies."
    };


    public static final String[] TIME = new String[]{
      "11:00pm", "12:45am", "02:43pm", "03:45pm"
    };

    public static  Integer[] mThumbIds = {
            R.drawable.image_1,
            R.drawable.image_2,
            R.drawable.image_3,
            R.drawable.image_4
    };
}
