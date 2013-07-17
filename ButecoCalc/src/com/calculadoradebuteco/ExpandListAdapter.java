package com.calculadoradebuteco;

import java.util.ArrayList;
import java.util.Hashtable;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class ExpandListAdapter extends BaseExpandableListAdapter implements
		OnClickListener {

	private Context context;
	private ArrayList<ExpandListGroup> groups;
	private Hashtable<String, String> checkedItems = null;

	public ExpandListAdapter(Context context, ArrayList<ExpandListGroup> groups) {
		this.context = context;
		this.groups = groups;

		this.checkedItems = new Hashtable<String, String>(20);

	}

	public void addItem(ExpandListChild item, ExpandListGroup group) {
		if (!groups.contains(group)) {
			groups.add(group);
		}
		int index = groups.indexOf(group);
		ArrayList<ExpandListChild> ch = groups.get(index).getItems();
		ch.add(item);
		groups.get(index).setItems(ch);
	}

	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		ArrayList<ExpandListChild> chList = groups.get(groupPosition)
				.getItems();
		return chList.get(childPosition);
	}

	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View view, ViewGroup parent) {
		ExpandListChild child = (ExpandListChild) getChild(groupPosition,
				childPosition);
		if (view == null) {
			LayoutInflater infalInflater = (LayoutInflater) context
					.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			view = infalInflater.inflate(R.layout.adapter_item_buddy_child,
					null);
		}

		CheckBox tv = (CheckBox) view.findViewById(R.id.item_child_check);

		ExpandListGroup p = (ExpandListGroup) getGroup(groupPosition);
		String value = p.getName() + "::" + child.getName().toString();
		this.checkedItems.put(value, "");

		tv.setText(child.getName().toString());
		tv.setTag(value);
		tv.setOnClickListener(this);

		Log.i("ChildView", "= " + p.getName() + "::"
				+ child.getName().toString());

		return view;
	}

	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		ArrayList<ExpandListChild> chList = groups.get(groupPosition)
				.getItems();

		return chList.size();

	}

	public Object getGroup(int groupPosition) {
		return groups.get(groupPosition);
	}

	public int getGroupCount() {
		return groups.size();
	}

	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	public View getGroupView(int groupPosition, boolean isLastChild, View view,
			ViewGroup parent) {
		ExpandListGroup group = (ExpandListGroup) getGroup(groupPosition);
		if (view == null) {
			LayoutInflater inf = (LayoutInflater) context
					.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			view = inf.inflate(R.layout.adapter_item_buddy_parent, null);
		}
		TextView tv = (TextView) view.findViewById(R.id.item_text_group);
		tv.setText(group.getName());

		return view;
	}

	public boolean hasStableIds() {
		return true;
	}

	public boolean isChildSelectable(int arg0, int arg1) {
		return true;
	}

	@Override
	public void onClick(View arg0) {

		CheckBox cb = (CheckBox) arg0;
		String value = (String) cb.getTag();

		if (cb.isChecked()) {

			Log.i("ADAPTER", "" + value);
			this.checkedItems.put(value, value);

		} else {
			Log.i("ADAPTER", "vazio");
			this.checkedItems.put(value, "");
		}

	}

	public Hashtable<String, String> getCheckedItems() {
		return checkedItems;
	}

	public void setCheckedItems(Hashtable<String, String> checkedItems) {
		this.checkedItems = checkedItems;
	}

}
