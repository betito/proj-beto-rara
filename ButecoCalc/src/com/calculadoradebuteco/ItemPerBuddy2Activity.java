package com.calculadoradebuteco;

import java.util.ArrayList;
import java.util.List;

import com.calculadoradebuteco.adapter.ListItemBuddyAdapter;
import com.calculadoradebuteco.model.ItensListSelect;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ItemPerBuddy2Activity extends Activity implements OnItemClickListener {

	private ListView buddiesListView;
	
	protected transient ListItemBuddyAdapter itemBuddyListAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_item_per_buddy2);
	}

	@Override
	protected void onResume() {
		super.onResume();

//		fm = getSupportFragmentManager();

		List<String> listOfBuddies = prepareListOfBuddies("Jovem ");

		buddiesListView = (ListView) findViewById(R.id.itembuddy_list_of_buddies);

		final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfBuddies);

		buddiesListView.setAdapter(arrayAdapter);

		buddiesListView.setOnItemClickListener(this);

	}

	private List<String> prepareListOfBuddies(final String iniString) {

		List<String> list = new ArrayList<String>();

		for (int i = 0; i < 10; i++) {
			list.add(iniString + i);
		}
		
		return list;
	}
	
	/**
	 * Prepare list of itens
	 * @param iniString
	 * @return
	 */
	private List<ItensListSelect> prepareListOfItens(final String iniString) {

		List<ItensListSelect> list = new ArrayList<ItensListSelect>();
		
		ItensListSelect item;
		
		for (int i = 0; i < 10; i++) {
			item = new ItensListSelect(iniString + i, false);
			
			list.add(item);
		}
		
		return list;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_item_per_buddy2, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}
	
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		// open dialog
		Dialog dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		dialog.setContentView(R.layout.dialog_item_buddy);
		
		TextView desc = (TextView) dialog.findViewById(R.id.dialog_item_buddy_desc);
		desc.setText(String.format(getText(R.string.dialog_item_buddy_desc_text).toString(), id));
		
		ListView listView = (ListView) dialog.findViewById(R.id.dialog_item_buddy_list);
		listView.setTextFilterEnabled(true);
		
		List<ItensListSelect> listItens = prepareListOfItens("Item ");
		
		itemBuddyListAdapter = new ListItemBuddyAdapter(this, listItens);
		
		listView.setAdapter(itemBuddyListAdapter);
		
		listView.setOnItemClickListener(new ItemBuddyItemClickListener());
		
		dialog.show();
	}
	
	private class ItemBuddyItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			
			Log.e("CALC", "ItemBuddyItemClickListener::onItemClick");
			
			List<ItensListSelect> listItens = itemBuddyListAdapter.getList();
			
			final ItensListSelect item = new ItensListSelect(listItens.get(position).getItem(), (listItens.get(position).isSelected() ? false : true));
			
			listItens.set(position, item); 
			
			itemBuddyListAdapter.setList(listItens);
			
			itemBuddyListAdapter.notifyDataSetChanged();
		}
		
	}

}
