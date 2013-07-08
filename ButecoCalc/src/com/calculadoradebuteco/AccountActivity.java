package com.calculadoradebuteco;

import java.util.Enumeration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.calculadoradebuteco.core.DataItem;
import com.calculadoradebuteco.model.CalcButeco;

public class AccountActivity extends Activity implements OnClickListener {

	private Button buttonAddItem = null;
	private Button buttonAddBuddy = null;
	private Button buttonCheck = null;
	private Button buttonItemPerBuddy = null;
	private TextView label = null;

	private StringBuilder itemList = null;

	private final int ITEM_ACT_RES_CODE_1 = 0x01;
	private final int ITEM_ACT_RES_CODE_2 = 0x02;

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
		buttonCheck = (Button) findViewById(R.id.button_check);
		buttonItemPerBuddy = (Button) findViewById(R.id.button_item_per_buddy);
		label = (TextView) findViewById(R.id.account_label);

		buttonAddItem.setOnClickListener(this);
		buttonAddBuddy.setOnClickListener(this);
		buttonCheck.setOnClickListener(this);
		buttonItemPerBuddy.setOnClickListener(this);

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
		}
	}

	private void openItemPerBuddyActivity() {

		Intent intent = new Intent(getApplicationContext(),
				ItemPerBuddyActivity.class);
		startActivity(intent);

	}

	private void openBuddyActivity() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(getApplicationContext(), BuddyActivity.class);
		startActivityForResult(intent, ITEM_ACT_RES_CODE_1);

	}

	private void openItemActivity() {
		// TODO Auto-generated method stub
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
	}

}
