package com.calculadoradebuteco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.widget.TextView;

public class SplashActivity extends Activity implements Runnable {

	private final int DELAY = 3000;
	private TextView appName = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		Handler h = new Handler();
		h.postDelayed(this, DELAY);
		
//		Typeface typeface = Typeface.createFromAsset(getAssets(), "robto/Roboto-Regular.ttf");
		this.appName = (TextView) findViewById(R.id.splash_appname);
//		this.appName.setTypeface(typeface);

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
