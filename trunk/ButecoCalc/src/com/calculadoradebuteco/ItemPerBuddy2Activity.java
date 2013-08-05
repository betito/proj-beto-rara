package com.calculadoradebuteco;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.calculadoradebuteco.adapter.ListItemBuddyAdapter;
import com.calculadoradebuteco.model.CalcButeco;
import com.calculadoradebuteco.model.ItensListSelect;

public class ItemPerBuddy2Activity extends Activity implements OnClickListener,
		OnItemClickListener {

	private ListView buddiesListView;

	protected transient ListItemBuddyAdapter itemBuddyListAdapter;

	private Button btn_clear = null;
	private Button btn_close = null;

	private TextView currentBuddyName = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_item_per_buddy2);
	}

	@Override
	protected void onResume() {
		super.onResume();

		// fm = getSupportFragmentManager();

		List<String> listOfBuddies = prepareListOfBuddies();

		buddiesListView = (ListView) findViewById(R.id.itembuddy_list_of_buddies);

		final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_list_item_1, listOfBuddies);

		buddiesListView.setAdapter(arrayAdapter);

		buddiesListView.setOnItemClickListener(this);

		btn_clear = (Button) findViewById(R.id.btn_clear);
		btn_close = (Button) findViewById(R.id.btn_save);

		btn_clear.setOnClickListener(this);
		btn_close.setOnClickListener(this);

	}

	private List<String> prepareListOfBuddies() {

		List<String> list = new ArrayList<String>();
		Hashtable<String, Integer> names = CalcButeco.getInstance()
				.getBuddyList_DB();

		for (Enumeration<String> en = names.keys(); en.hasMoreElements();) {
			String name = en.nextElement();
			list.add(name);
		}

		return list;
	}

	/**
	 * Prepare list of itens
	 * 
	 * @param iniString
	 * @return
	 */
	private List<ItensListSelect> prepareListOfItem() {

		List<ItensListSelect> list = new ArrayList<ItensListSelect>();

		ItensListSelect item;

		Hashtable<String, Integer> names = CalcButeco.getInstance()
				.getItemIDs();

		for (Enumeration<String> en = names.keys(); en.hasMoreElements();) {
			String iniString = en.nextElement();

			item = new ItensListSelect(iniString, false);

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
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		Button btnClear = null;
		Button btnSaveClose = null;

		// open dialog
		final Dialog dialog = new Dialog(this);

		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		dialog.setContentView(R.layout.dialog_item_buddy);

		btnClear = (Button) dialog.findViewById(R.id.btn_dialog_clear);
		btnSaveClose = (Button) dialog.findViewById(R.id.btn_dialog_save_close);

		this.currentBuddyName = (TextView) view;

		TextView desc = (TextView) dialog
				.findViewById(R.id.dialog_item_buddy_desc);
		desc.setText(String.format(
				getText(R.string.dialog_item_buddy_desc_text).toString(),
				currentBuddyName.getText().toString()));

		ListView listView = (ListView) dialog
				.findViewById(R.id.dialog_item_buddy_list);

		listView.setTextFilterEnabled(true);

		List<ItensListSelect> listItens = prepareListOfItem();

		itemBuddyListAdapter = new ListItemBuddyAdapter(this, listItens);

		listView.setAdapter(itemBuddyListAdapter);

		listView.setOnItemClickListener(new ItemBuddyItemClickListener());

		btnClear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i("DIALOG", "Desmarcar tudo all..");
			}
		});

		btnSaveClose.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		dialog.show();

	}

	private class ItemBuddyItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {

			Log.e("CALC", "ItemBuddyItemClickListener::onItemClick");

			List<ItensListSelect> listItens = itemBuddyListAdapter.getList();

			final ItensListSelect item = new ItensListSelect(listItens.get(
					position).getItem(),
					(listItens.get(position).isSelected() ? false : true));

			listItens.set(position, item);

			Log.i("ITEM", " " + item.getItem());
			Log.i("BUDDY", " " + currentBuddyName.getText().toString());

			if (listItens.get(position).isSelected()) {
				checkMatrix(item.getItem(), currentBuddyName.getText()
						.toString());
			} else {
				unCheckMatrix(item.getItem(), currentBuddyName.getText()
						.toString());
			}

			itemBuddyListAdapter.setList(listItens);

			itemBuddyListAdapter.notifyDataSetChanged();
		}

		private void checkMatrix(String item, String bud) {

			int posBuddy = CalcButeco.getInstance().getBuddyList_DB().get(bud);
			int posItem = CalcButeco.getInstance().getItemIDs().get(item);

			CalcButeco.getInstance().setMatrix();

			CalcButeco.getInstance().checkMatrix(posItem, posBuddy);

		}

		private void unCheckMatrix(String item, String bud) {

			int posBuddy = CalcButeco.getInstance().getBuddyList_DB().get(bud);
			int posItem = CalcButeco.getInstance().getItemIDs().get(item);

			CalcButeco.getInstance().setMatrix();

			CalcButeco.getInstance().uncheckMatrix(posItem, posBuddy);

		}

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.btn_clear:
			Clear();
			break;
		case R.id.btn_save:
			SaveAndClose();
			break;

		}

	}

	private void SaveAndClose() {

		Log.i("BOTAO", "Salvar e Fechar.");
		finish();
		//

	}

	private void Clear() {

		Log.i("BOTAO", "Desmarcar todos.");

	}
}
