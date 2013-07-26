package com.calculadoradebuteco;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AboutActivity extends Activity implements OnClickListener {

	private TextView Devel = null;
	private LinearLayout aboutScreen = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);

	}

	@Override
	protected void onResume() {
		super.onResume();

		this.Devel = (TextView) findViewById(R.id.about_txt_devel);
		this.aboutScreen = (LinearLayout) findViewById(R.id.about_screen);
		
		this.Devel.setText(Html.fromHtml("Raoni Novellino<br/>" +
				"Roberto Oliveira<br/>" +
				"<i>Development</i><br/><br/>" +
				"Marcelo Vitor Oliveira<br/>" +
				"<i>UX/UI design</i>"));
		
		this.aboutScreen.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_about, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View arg0) {

		finish();

	}

}
