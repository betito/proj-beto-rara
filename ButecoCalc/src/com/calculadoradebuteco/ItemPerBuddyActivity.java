package com.calculadoradebuteco;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.calculadoradebuteco.core.DataItem;
import com.calculadoradebuteco.model.CalcButeco;

public class ItemPerBuddyActivity extends Activity implements OnClickListener {

	TextView itemInfo = null;
	Button btnSave = null;
	private ExpandListAdapter ExpAdapter;
	private ArrayList<ExpandListGroup> ExpListItems;
	private ExpandableListView ExpandList;

	Hashtable<String, Integer> Buddy = null;
	Hashtable<String, Integer> Item = null;
	Hashtable<String, DataItem> ItemD = null;
	int X_ = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_per_buddy);
		// getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_item_per_buddy, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();

		ExpandList = (ExpandableListView) findViewById(R.id.expList);
		ExpListItems = SetStandardGroups();
		ExpAdapter = new ExpandListAdapter(ItemPerBuddyActivity.this,
				ExpListItems);
		ExpandList.setAdapter(ExpAdapter);

		// ExpandList.setOnChildClickListener(this);

//		ExpandList.setOnChildClickListener(new OnChildClickListener() {
//
//			public boolean onChildClick(ExpandableListView parent, View v,
//					int groupPosition, int childPosition, long id) {
//				final String selected = (String) ExpAdapter.getChild(
//						groupPosition, childPosition);
//				Toast.makeText(getApplicationContext(), selected,
//						Toast.LENGTH_LONG).show();
//
//				return true;
//			}
//		});

		this.Buddy = CalcButeco.getInstance().getBuddyList_DB();
		this.Item = CalcButeco.getInstance().getItemIDs();
		
		CalcButeco.getInstance().setMatrix();

		itemInfo = (TextView) findViewById(R.id.text_information);
		btnSave = (Button) findViewById(R.id.btn_save);

		btnSave.setOnClickListener(this);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	public ArrayList<ExpandListGroup> SetStandardGroups() {

		ArrayList<ExpandListGroup> list = new ArrayList<ExpandListGroup>();

		if (CalcButeco.getInstance().getBuddyList_DB() != null) {

			String[] listOfBuddies = getListInString(CalcButeco.getInstance()
					.getBuddyList_DB());
			String[] listOfItems = getListInString(CalcButeco.getInstance()
					.getItemListDB());

			for (int i = 0; i < listOfBuddies.length; i++) {

				ArrayList<ExpandListChild> list2 = new ArrayList<ExpandListChild>();
				ExpandListGroup parent = new ExpandListGroup();
				parent.setName(listOfBuddies[i]);

				// Log.i("PARENT", listOfBuddies[i]);

				for (int j = 0; j < listOfItems.length; j++) {

					ExpandListChild child = new ExpandListChild();
					child.setName(listOfItems[j]);
					child.setTag(null);
					list2.add(child);
					// Log.i("CHILD", listOfItems[j]);

				}
				parent.setItems(list2);
				list.add(parent);
				// Log.i("LOOP", "" + i);

				// printList(list);
			}

			return list;
		}

		return null;

	}

	private void printList(ArrayList<ExpandListGroup> list) {
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			ExpandListGroup expandList = (ExpandListGroup) iterator.next();

			Log.i("RETURN 1", expandList.getName());

			ArrayList<ExpandListChild> listc = expandList.getItems();
			for (Iterator iterator2 = listc.iterator(); iterator2.hasNext();) {
				ExpandListChild expandListChild = (ExpandListChild) iterator2
						.next();

				Log.i("RETURN 2", expandListChild.getName());

			}

		}

	}

	private String[] getListInString(final Hashtable hashtable) {

		StringBuilder list = new StringBuilder();
		if (hashtable != null) {
			for (Enumeration<Object> enx = hashtable.keys(); enx
					.hasMoreElements();) {
				String x = (String) enx.nextElement();
				list.append(x + "::");
			}
		} else {
			list.append("vazio");
		}
		return list.toString().split("::");
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();

		switch (id) {
		case R.id.btn_save:
			saveMatrix();
			break;
		}

	}

	private void saveMatrix() {

		String dbuddy = null;
		String ditem = null;

		Hashtable<String, String> checkedvalues = this.ExpAdapter
				.getCheckedItems();

		for (Enumeration<String> en = checkedvalues.keys(); en
				.hasMoreElements();) {

			String item = (String) en.nextElement();
			String value = checkedvalues.get(item);
			Log.i("SAVE 1", "> " + item + " = " + value);
			
			if (!(value.equals(""))) {
				
				String data[] = value.split("::");
				dbuddy = data[0];
				ditem = data[1];
				
				int posbuddy = Integer.valueOf(this.Buddy.get(dbuddy));
				int positem = Integer.valueOf(this.Item.get(ditem));
				CalcButeco.getInstance().checkMatrix(positem, posbuddy);
				
			}
		}
		
		Toast.makeText(getApplicationContext(), "Salvo na matrix",
				Toast.LENGTH_LONG).show();
		
		finish();
		

	}
}
