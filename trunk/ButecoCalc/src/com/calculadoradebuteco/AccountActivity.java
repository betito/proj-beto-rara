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
	private Button buttonItemPerBuddy2 = null;
	private Button buttonAbout = null;
	private Button buttonExit = null;

	private TextView labelBuddies = null;
	private TextView labelItems = null;
	private TextView labelIndividual = null;

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
		buttonItemPerBuddy2 = (Button) findViewById(R.id.button_item_per_buddy2);
		buttonCheck = (Button) findViewById(R.id.button_check);
		buttonAbout = (Button) findViewById(R.id.button_about);
		buttonExit = (Button) findViewById(R.id.button_exit);
		labelBuddies = (TextView) findViewById(R.id.account_buddies);
		labelItems = (TextView) findViewById(R.id.account_items);
		labelIndividual = (TextView) findViewById(R.id.account_individual);

		buttonAddItem.setOnClickListener(this);
		buttonAddBuddy.setOnClickListener(this);
		buttonItemPerBuddy.setOnClickListener(this);
		buttonItemPerBuddy2.setOnClickListener(this);
		buttonCheck.setOnClickListener(this);
		buttonAbout.setOnClickListener(this);
		buttonExit.setOnClickListener(this);

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

		case R.id.button_item_per_buddy2:
			openItemPerBuddy2Activity();
			break;

		case R.id.button_check:
			showCheck();
			break;
		case R.id.button_about:
			openAboutActivity();
			break;
		case R.id.button_exit:
			finish();
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

	private void openItemPerBuddy2Activity() {

		if ((CalcButeco.getInstance().getItemListDB() != null)
				&& (CalcButeco.getInstance().getBuddyList_DB() != null)) {

			Intent intent = new Intent(getApplicationContext(),
					ItemPerBuddy2Activity.class);
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

		if (labelItems == null) {
			this.labelItems = (TextView) findViewById(R.id.account_items);
		}

		if (CalcButeco.getInstance().getItemListDB() != null) {

			for (Enumeration<String> en = CalcButeco.getInstance()
					.getItemListDB().keys(); en.hasMoreElements();) {
				String name = en.nextElement();
				DataItem ditem = CalcButeco.getInstance().getItemListDB()
						.get(name);

				tmp.append(name);
				// tmp.append("\t{" + ditem.getId() + "}");
				tmp.append("\t[" + ditem.getPrice() + "]");
				tmp.append("\t[" + ditem.getQuant() + "]");
				tmp.append("\n");
			}
		}

		labelItems.setText("Items\t[PreÁo Un.]\t[Quant.]\n---------------\n"
				+ tmp.toString());

	}

	private void updateDisplayBuddyList() {

		StringBuilder tmp = new StringBuilder();

		if (labelBuddies == null) {
			this.labelBuddies = (TextView) findViewById(R.id.account_buddies);
		}

		if (CalcButeco.getInstance().getBuddyList_DB() != null) {

			for (Enumeration<String> en = CalcButeco.getInstance()
					.getBuddyList_DB().keys(); en.hasMoreElements();) {
				String name = en.nextElement();

				tmp.append(name);
				// tmp.append("\t{" +
				// CalcButeco.getInstance().getBuddyList_DB().get(name) + "}");
				tmp.append("\n");
			}
		}

		labelBuddies.setText("Buddies\n---------------\n" + tmp.toString());

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		updateAccountList();

	}

	private void updateAccountList() {
		updateDisplayBuddyList();
		updateDisplayItemList();
	}

	private void showCheck() {

		Hashtable<String, Integer> buddyList_DB = null;
		Hashtable<String, Integer> ItemIDs = null;
		Hashtable<String, DataItem> itemList_DB = null;
		Hashtable<String, Float> BuddyMoney = null;
		StringBuilder output = null;

		if (labelIndividual == null) {
			this.labelIndividual = (TextView) findViewById(R.id.account_individual);
		}

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

				output.append(buddystr + "\t=\t" + String.format("%.2f", val)
						+ "\n");

			}

			updateAccountList();

			labelIndividual
					.setText("\n\nValor justo COM 10% \n==================\n\n"
							+ "Total Geral\t=\t"
							+ String.format("%.2f",
									cc.calculateTotalWithService())
							+ "\n-----------" + output.toString());

		}

	}
}
