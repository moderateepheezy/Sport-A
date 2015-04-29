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
import com.news.app.com.sport.app.model.Pojo;
import com.news.app.com.sport.app.utilities.Constant;
import com.news.app.com.sport.app.utilities.ImageLoader;

import java.util.List;

public class Adapter_Favorite  extends ArrayAdapter<Pojo> {


	private Activity activity;
	private List<Pojo> itemsfavoritelatest;
	private Pojo objFavoriteBeanlatest;
	private int row;
	public ImageLoader imageLoader;
	 

	public Adapter_Favorite(Activity act, int resource, List<Pojo> arrayList) {
		super(act, resource, arrayList);
		this.activity = act;
		this.row = resource;
		this.itemsfavoritelatest = arrayList;
		imageLoader=new ImageLoader(activity);
		

	}
 
	@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			View view = convertView;
			ViewHolder holder;
			if (view == null) {
				LayoutInflater inflater = (LayoutInflater) activity
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = inflater.inflate(row, null);

				holder = new ViewHolder();
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}

			if ((itemsfavoritelatest == null) || ((position + 1) > itemsfavoritelatest.size()))
				return view;

			objFavoriteBeanlatest = itemsfavoritelatest.get(position);

			holder.txt_newsheadinglatest=(TextView)view.findViewById(R.id.txt_newslistheadingfav);
			holder.txt_newsdatelatest=(TextView)view.findViewById(R.id.txt_newslistdatefav);
			
			holder.img_newslatest=(ImageView)view.findViewById(R.id.img_newslistfav);
			
			holder.txt_newsheadinglatest.setText(objFavoriteBeanlatest.getNewsHeading().toString());
			holder.txt_newsdatelatest.setText(objFavoriteBeanlatest.getNewsDate().toString());

			imageLoader.DisplayImage(Constant.SERVER_IMAGE_NEWSLIST_THUMBS+objFavoriteBeanlatest.getNewsImage().toString(), holder.img_newslatest);
			
			return view;
			 
		}

		public class ViewHolder {
		 
			public ImageView img_newslatest;
			public TextView txt_newsheadinglatest;
			public TextView txt_newsdatelatest;
			 
		}
}
