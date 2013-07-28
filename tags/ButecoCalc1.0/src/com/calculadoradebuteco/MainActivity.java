package com.calculadoradebuteco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button buttonAddAccount = null;
	private Button buttonExit = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onResume() {
		super.onResume();

		buttonAddAccount = (Button) findViewById(R.id.button_add_account);
		buttonExit = (Button) findViewById(R.id.button_exit);

		buttonAddAccount.setOnClickListener(this);
		buttonExit.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button_add_account:
			openAccountActivity();
			break;
		case R.id.button_exit:
			ExitApp();
			break;

		default:
			break;
		}

	}

	private void ExitApp() {

		finish();
		System.exit(0);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private void openAccountActivity() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(getApplicationContext(),
				AccountActivity.class);
		startActivity(intent);

	}

}
