package com.news.app.com.sport.app.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.news.app.R;
import com.news.app.com.sport.app.model.Child;
import com.news.app.com.sport.app.model.Parent;

public class ExpandableAdapter extends BaseExpandableListAdapter{

	private Context _context;
	//private ArrayList<Parent> _listDataHeader;//header title
	/*public ExpandableAdapter(Context _context, ArrayList<Parent> _listDataHeader) {
		this._context = _context;
		this._listDataHeader = _listDataHeader;
	}*/
    private ArrayList<String> parents, child;
    private LayoutInflater inflater;
    private Activity activity;
    ArrayList<Object> childItems;

    public ExpandableAdapter(ArrayList<String > parents, ArrayList<Object> children) {
        this.parents = parents;
        this.childItems = children;
    }

    public void setInflater(LayoutInflater inflater, Activity activity) {
        this.inflater = inflater;
        this.activity = activity;
    }

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		//ArrayList<Child> _listDataChild = _listDataHeader.get(groupPosition).getChildren();
		//return _listDataChild.get(childPosition);r
        return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		// TODO Auto-generated method stub
		//final Child child = (Child) getChild(groupPosition, childPosition);
		 child  = (ArrayList<String>) childItems.get(groupPosition);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.childitem, null);
        }
 
        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.childText);
 
        txtListChild.setText(child.get(childPosition));
        return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		//ArrayList<Child> _listDataChild = _listDataHeader.get(groupPosition).getChildren();
		//return _listDataChild.size();
        return ((ArrayList<String>) childItems.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	
	public int getGroupCount() {		// TODO Auto-generated method stub
		return parents.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//Parent header = (Parent) getGroup(groupPosition);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.parentitem, null);
        }


        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.parentText);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(parents.get(groupPosition));
        return convertView;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}
	
}

