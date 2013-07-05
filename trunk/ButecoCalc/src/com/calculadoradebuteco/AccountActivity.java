	package com.calculadoradebuteco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class AccountActivity extends Activity implements OnClickListener {

	private Button buttonAddItem = null;
	private Button buttonAddBuddy = null;
	private Button buttonCheck = null;
	private Button buttonItemQuantity = null;
	private Button buttonItemPerBuddy = null;
	
	private StringBuilder itemList = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);

	}

	@Override
	protected void onResume() {
		super.onResume();

		if (itemList == null){
			
			itemList = new StringBuilder();
			
		}
		
		buttonAddItem = (Button) findViewById(R.id.button_item);
		buttonAddBuddy = (Button) findViewById(R.id.button_buddy);
		buttonCheck = (Button) findViewById(R.id.button_check);
		buttonItemQuantity = (Button) findViewById(R.id.button_quantity);
		buttonItemPerBuddy = (Button) findViewById(R.id.button_item_per_buddy);

		buttonAddItem.setOnClickListener(this);
		buttonAddBuddy.setOnClickListener(this);
		buttonCheck.setOnClickListener(this);
		buttonItemPerBuddy.setOnClickListener(this);
		buttonItemQuantity.setOnClickListener(this);

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
		case R.id.button_check:
			openCheckActivity();
			break;
		case R.id.button_quantity:
			openQuantityActivity();
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

	private void openQuantityActivity() {
		Intent intent = new Intent(getApplicationContext(),
				ItemQuantityActivity.class);
		startActivity(intent);

	}

	private String openCheckActivity() {

		Toast.makeText(getApplicationContext(), "Abrir Quantidade",
				Toast.LENGTH_LONG).show();

		return null;

	}

	private void openBuddyActivity() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(getApplicationContext(), BuddyActivity.class);
		startActivity(intent);

	}

	private void openItemActivity() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(getApplicationContext(), ItemActivity.class);
		startActivity(intent);

	}

}
