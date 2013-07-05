package com.calculadoradebuteco;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class QuantityAdapter extends BaseAdapter {

	private Context context = null;
	private List<String> ItemList = null;
	
	
	public QuantityAdapter(Context context, List<String> itemList) {
		super();
		this.context = context;
		ItemList = itemList;
	}

	@Override
	public int getCount() {
		
		return ItemList.size();
	}

	@Override
	public Object getItem(int pos) {
		return ItemList.get(pos);
	}

	@Override
	public long getItemId(int pos) {

		return pos;
	}

	@Override
	public View getView(int pos, View view, ViewGroup parent) {

		String stritem = ItemList.get(pos);
		
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View v = inflater.inflate(R.layout.adapter_quantity, null);
		
		TextView itemname = (TextView) v.findViewById(R.id.quantity_item_name);
		itemname.setText(stritem);
		
//		TextView itemtotal = (TextView) v.findViewById(R.id.quantity_item_price_total);
//		itemtotal.setText("R$ ...");
		
		EditText edittext = (EditText) v.findViewById(R.id.quantity_item_value);
		
		return v;
	}
	
	
	/*private class ItemEditBox {
		TextView textView;
		EditText editText;
		TextView textView;
	}*/

}
