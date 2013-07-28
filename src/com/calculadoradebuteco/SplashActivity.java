package com.calculadoradebuteco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

public class SplashActivity extends Activity implements Runnable {

	private final int DELAY = 3000;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		Handler h = new Handler();
		h.postDelayed(this, DELAY);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_splash, menu);
		return true;
	}

	@Override
	public void run() {

		Intent intent = new Intent(getApplicationContext(),
				AccountActivity.class);
		startActivity(intent);

		finish();

	}
}
