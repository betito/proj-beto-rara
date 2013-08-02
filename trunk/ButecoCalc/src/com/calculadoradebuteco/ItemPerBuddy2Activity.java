package com.calculadoradebuteco;

import java.util.ArrayList;
import java.util.List;

import com.calculadoradebuteco.adapter.ListItemBuddyAdapter;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ItemPerBuddy2Activity extends Activity implements OnItemClickListener {

	private ListView buddiesListView;

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
		
		dialog.setContentView(R.layout.dialog_item_buddy);
		
		TextView desc = (TextView) dialog.findViewById(R.id.dialog_item_buddy_desc);
		desc.setText(String.format(getText(R.string.dialog_item_buddy_desc_text).toString(), id));
		
		ListView listView = (ListView) dialog.findViewById(R.id.dialog_item_buddy_list);
		
		List<String> listItens = prepareListOfBuddies("Item ");
		
		listView.setAdapter(new ListItemBuddyAdapter(this, listItens));
		
		dialog.show();
	}

}
