package com.calculadoradebuteco;

import java.util.Enumeration;
import java.util.Hashtable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.calculadoradebuteco.core.CalcularConta;
import com.calculadoradebuteco.core.DataItem;
import com.calculadoradebuteco.model.CalcButeco;

public class AccountActivity extends Activity implements OnClickListener {

	private Button buttonAddItem = null;
	private Button buttonAddBuddy = null;
	private Button buttonCheck = null;
	private Button buttonItemPerBuddy = null;
	private Button buttonAbout = null;
	private TextView label = null;

	private StringBuilder itemList = null;

	private final int ITEM_ACT_RES_CODE_1 = 0x01;
	private final int ITEM_ACT_RES_CODE_2 = 0x02;
	private final int ITEM_ACT_RES_CODE_3 = 0x03;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);

	}

	@Override
	protected void onResume() {
		super.onResume();

		buttonAddItem = (Button) findViewById(R.id.button_item);
		buttonAddBuddy = (Button) findViewById(R.id.button_buddy);
		buttonItemPerBuddy = (Button) findViewById(R.id.button_item_per_buddy);
		buttonCheck = (Button) findViewById(R.id.button_check);
		buttonAbout = (Button) findViewById(R.id.button_about);
		label = (TextView) findViewById(R.id.account_label);

		buttonAddItem.setOnClickListener(this);
		buttonAddBuddy.setOnClickListener(this);
		buttonItemPerBuddy.setOnClickListener(this);
		buttonCheck.setOnClickListener(this);
		buttonAbout.setOnClickListener(this);

		if (itemList == null) {

			itemList = new StringBuilder();

		}

		updateAccountList();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_account, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		int viewId = v.getId();

		switch (viewId) {
		case R.id.button_buddy:
			openBuddyActivity();
			break;
		case R.id.button_item:
			openItemActivity();
			break;
		case R.id.button_item_per_buddy:
			openItemPerBuddyActivity();
			break;
		case R.id.button_check:
			showCheck();
			break;
		case R.id.button_about:
			openAboutActivity();
			break;
		}
	}

	private void openAboutActivity() {
		Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
		startActivity(intent);

	}

	private void openItemPerBuddyActivity() {

		if ((CalcButeco.getInstance().getItemListDB() != null)
				&& (CalcButeco.getInstance().getBuddyList_DB() != null)) {

			Intent intent = new Intent(getApplicationContext(),
					ItemPerBuddyActivity.class);
			startActivityForResult(intent, ITEM_ACT_RES_CODE_3);
		} else {
			Toast.makeText(
					getApplicationContext(),
					"Faltam dados.\n… preciso inserir os Camaradas e os Itens ;)!!",
					Toast.LENGTH_LONG).show();
		}

	}

	private void openBuddyActivity() {
		Intent intent = new Intent(getApplicationContext(), BuddyActivity.class);
		startActivityForResult(intent, ITEM_ACT_RES_CODE_1);

	}

	private void openItemActivity() {
		Intent intent = new Intent(getApplicationContext(), ItemActivity.class);
		startActivityForResult(intent, ITEM_ACT_RES_CODE_2);

	}

	private void updateDisplayItemList() {

		StringBuilder tmp = new StringBuilder();

		if (CalcButeco.getInstance().getItemListDB() != null) {

			for (Enumeration<String> en = CalcButeco.getInstance()
					.getItemListDB().keys(); en.hasMoreElements();) {
				String name = en.nextElement();
				DataItem ditem = CalcButeco.getInstance().getItemListDB()
						.get(name);

				tmp.append(name);
				tmp.append("\t{" + ditem.getId() + "}");
				tmp.append("\t[" + ditem.getPrice() + "]");
				tmp.append("\t[" + ditem.getQuant() + "]");
				tmp.append("\n");
			}
		}

		label.setText(label.getText()
				+ "\n\n======================\n\nItems \n---------------\n"
				+ tmp.toString());

	}

	private void updateDisplayBuddyList() {

		StringBuilder tmp = new StringBuilder();

		if (CalcButeco.getInstance().getBuddyList_DB() != null) {

			for (Enumeration<String> en = CalcButeco.getInstance()
					.getBuddyList_DB().keys(); en.hasMoreElements();) {
				String name = en.nextElement();
				int id = CalcButeco.getInstance().getBuddyList_DB().get(name);

				tmp.append(name);
				tmp.append("\t[" + id + "]");
				tmp.append("\n");
			}
		}

		label.setText("Buddies\n---------------\n" + tmp.toString());

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		updateAccountList();

	}

	private void updateAccountList() {
		updateDisplayBuddyList();
		updateDisplayItemList();
		showCheck();
	}

	private void showCheck() {

		Hashtable<String, Integer> buddyList_DB = null;
		Hashtable<String, Integer> ItemIDs = null;
		Hashtable<String, DataItem> itemList_DB = null;
		Hashtable<String, Float> BuddyMoney = null;
		StringBuilder output = null;

		if (CalcButeco.getInstance().getMatrix() != null) {
			Log.i("MATRIX", "OK");
			int matrix[][] = CalcButeco.getInstance().getMatrix();
			ItemIDs = CalcButeco.getInstance().getItemIDs();
			buddyList_DB = CalcButeco.getInstance().getBuddyList_DB();
			itemList_DB = CalcButeco.getInstance().getItemListDB();

			CalcularConta cc = new CalcularConta(buddyList_DB, ItemIDs,
					itemList_DB, matrix);

			BuddyMoney = cc.calculateIndividualBuddyAccountWithService();

			output = new StringBuilder();
			output.append("\n");

			for (Enumeration<String> enj = buddyList_DB.keys(); enj
					.hasMoreElements();) {

				String buddystr = enj.nextElement();
				Float val = BuddyMoney.get(buddystr);

				output.append(buddystr + " = " + val.toString() + "\n");

			}

			label.setText("\n\n\nValor justo COM 10% \n==================\n"
					+ output.toString());

		}

	}
}
