package com.calculadoradebuteco;

import java.util.Enumeration;
import java.util.Hashtable;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ItemActivity extends Activity implements OnClickListener {

	private Button buttonSave = null;
	private Button buttonCancel = null;

	private EditText ItemName = null;
	private EditText ItemPrice = null;
	private TextView itemList = null;
	private Hashtable<String, Integer> ItemList_DB = null;
	private Hashtable<String, Float> ItemPriceList_DB = null;
	private int ID_ = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);

		if (this.ItemList_DB == null) {
			this.ItemList_DB = new Hashtable<String, Integer>();
			this.ItemPriceList_DB = new Hashtable<String, Float>();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_item, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();

		buttonSave = (Button) findViewById(R.id.button_item_save);
		buttonCancel = (Button) findViewById(R.id.button_item_cancel);
		ItemName = (EditText) findViewById(R.id.edit_item_name);
		ItemPrice = (EditText) findViewById(R.id.edit_item_price);
		itemList = (TextView) findViewById(R.id.text_item_list);

		buttonSave.setOnClickListener(this);
		buttonCancel.setOnClickListener(this);
		
		updateDisplayItemList();

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button_item_save:
			showMsg("Salvar...");
			writeBuddyInList();
			break;
		case R.id.button_item_cancel:
			showMsg("Já...");
			break;

		default:
			break;
		}

	}

	private boolean writeBuddyInList() {

		String name = this.ItemName.getText().toString();
		String price = this.ItemPrice.getText().toString();

		if (this.ItemList_DB.containsKey(name)) {
			WarnAlredyExists();

		} else {

			if ((price.trim().equals("") == false)
					&& (name.trim().equals("") == false)) {
				name = name.toUpperCase();
				this.ItemList_DB.put(name, new Integer(ID_));
				this.ItemPriceList_DB.put(name,
						new Float(Float.parseFloat(price)));
				ID_++;

				updateDisplayItemList();

				return true;
			}
		}

		return false;

	}

	private void updateDisplayItemList() {

		StringBuilder tmp = new StringBuilder();

		for (Enumeration<String> en = this.ItemList_DB.keys(); en
				.hasMoreElements();) {
			String name = en.nextElement();

			tmp.append(name);
			tmp.append("\t[" + this.ItemPriceList_DB.get(name) + "]");
			tmp.append("\t[" + this.ItemList_DB.get(name) + "]");
			tmp.append("\n");
		}

		itemList.setText(tmp.toString());

	}

	private void WarnAlredyExists() {
		Toast.makeText(getApplicationContext(), "Este item já tá na lista...",
				Toast.LENGTH_SHORT).show();

	}

	private void showMsg(String text) {

		Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT)
				.show();

	}

}
