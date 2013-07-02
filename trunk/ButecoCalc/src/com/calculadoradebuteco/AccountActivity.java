package com.calculadoradebuteco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AccountActivity extends Activity implements OnClickListener {

	private Button buttonAddItem = null;
	private Button buttonAddBuddy = null;

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

		buttonAddItem.setOnClickListener(this);
		buttonAddBuddy.setOnClickListener(this);

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
		}

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
