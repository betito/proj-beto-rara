package com.calculadoradebuteco.adapter;

import java.util.List;

import com.calculadoradebuteco.R;
import com.calculadoradebuteco.model.ItensListSelect;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListItemBuddyAdapter extends BaseAdapter {

	private final LayoutInflater inflater;
	private transient List<ItensListSelect> list;

	public ListItemBuddyAdapter(Context context, List<ItensListSelect> list) {

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public ItensListSelect getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, final View convertView, ViewGroup parent) {

		View view = convertView;

		if (convertView == null) {

			view = inflater.inflate(R.layout.item_list_item_buddy, parent,
					false);
		}

		ItensListSelect item = getItem(position);

		TextView itemName = (TextView) view
				.findViewById(R.id.item_list_item_buddy_desc);

		itemName.setText(item.getItem());

		int bgColor;
		if (item.isSelected()) {
			bgColor = Color.parseColor("#00775F");
		} else {
			bgColor = Color.parseColor("#00779F");
		}

		view.setBackgroundColor(bgColor);

		return view;
	}

	public void setList(List<ItensListSelect> list) {
		this.list = list;
	}

	public List<ItensListSelect> getList() {
		return list;
	}

}
