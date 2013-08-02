package com.calculadoradebuteco.adapter;

import java.util.List;

import com.calculadoradebuteco.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListItemBuddyAdapter extends BaseAdapter {

	private final LayoutInflater inflater;
	private transient List<String> list;
	
	public ListItemBuddyAdapter(Context context, List<String> list) {
		
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		this.list = list;
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public String getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, final View convertView, ViewGroup parent) {
		
		View view = convertView;
		
		if(convertView == null) {
			
			view = inflater.inflate(R.layout.item_list_item_buddy, parent, false);
		}
		
		TextView itemName = (TextView) view.findViewById(R.id.item_list_item_buddy_desc);
		
		itemName.setText(getItem(position));
		
		return view;
	}

}
