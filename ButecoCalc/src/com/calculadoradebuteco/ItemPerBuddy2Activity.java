package com.calculadoradebuteco;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ItemPerBuddy2Activity extends FragmentActivity implements
		OnItemClickListener {

	private ArrayList<String> listOfBuddies = null;
	private ListView buddiesListView;
	private FragmentManager fm = null;

	private ArrayAdapter arrayAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_per_buddy2);
	}

	@Override
	protected void onResume() {
		super.onResume();

		fm = getSupportFragmentManager();

		prepareListOfBuddies();

		buddiesListView = (ListView) findViewById(R.id.itembuddy_list_of_buddies);

		arrayAdapter = new ArrayAdapter(this,
				android.R.layout.simple_list_item_1, listOfBuddies);

		buddiesListView.setAdapter(arrayAdapter);

		buddiesListView.setOnItemClickListener(this);

	}

	private void prepareListOfBuddies() {

		if (listOfBuddies == null) {

			listOfBuddies = new ArrayList<String>();

			for (int i = 0; i < 10; i++) {

				listOfBuddies.add("Jovem " + i);

			}

		}

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
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		Log.i("CLICK ARG2", "" + arg2);


	}

}
