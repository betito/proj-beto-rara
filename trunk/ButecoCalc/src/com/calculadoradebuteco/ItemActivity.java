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

import com.calculadoradebuteco.core.DataItem;
import com.calculadoradebuteco.model.CalcButeco;

public class ItemActivity extends Activity implements OnClickListener {

	private Button buttonSave = null;
	private Button buttonCancel = null;

	private EditText ItemName = null;
	private EditText ItemPrice = null;
	private EditText ItemQuantity = null;
	private TextView itemList = null;

	private int ID_ = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);

		if (CalcButeco.getInstance().getItemListDB() == null) {
			CalcButeco.getInstance().setItemListDB(
					new Hashtable<String, DataItem>());
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
		ItemQuantity = (EditText) findViewById(R.id.edit_item_quant);
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
			ClearData();
			break;
		case R.id.button_item_cancel:
			showMsg("Já...");
			finish();
			break;

		default:
			break;
		}

	}

	private void ClearData() {

		ItemName.setText("");
		ItemPrice.setText("");
		ItemQuantity.setText("");
		ItemName.requestFocus();

	}

	private boolean writeBuddyInList() {

		String name = this.ItemName.getText().toString();
		String price = this.ItemPrice.getText().toString();
		String quant = this.ItemQuantity.getText().toString();

		if (CalcButeco.getInstance().getItemListDB().containsKey(name)) {
			WarnAlredyExists();

		} else {

			if ((price.trim().equals("") == false)
					&& (name.trim().equals("") == false)
					&& (quant.trim().equals("") == false)) {
				name = name.toUpperCase();
				ID_ = CalcButeco.getInstance().getITEM_ID_COUNT();
				DataItem ditem = new DataItem(ID_, name, price, quant);

				CalcButeco.getInstance().getItemListDB().put(name, ditem);

				CalcButeco.getInstance().inc_ITEM_ID_COUNT();

				updateDisplayItemList();

				return true;
			}
		}

		return false;

	}

	private void updateDisplayItemList() {

		StringBuilder tmp = new StringBuilder();

		for (Enumeration<String> en = CalcButeco.getInstance().getItemListDB()
				.keys(); en.hasMoreElements();) {

			String name = en.nextElement();
			DataItem ditem = CalcButeco.getInstance().getItemListDB().get(name);

			tmp.append(name);
			tmp.append("\t{" + ditem.getId() + "}");
			tmp.append("\t[" + ditem.getPrice() + "]");
			tmp.append("\t[" + ditem.getQuant() + "]");
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
