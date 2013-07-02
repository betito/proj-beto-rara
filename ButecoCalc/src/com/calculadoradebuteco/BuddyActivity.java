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

public class BuddyActivity extends Activity implements OnClickListener {

	private Button buttonSave = null;
	private Button buttonClose = null;
	private EditText buddyName = null;
	private TextView buddyList = null;

	private Hashtable<String, Integer> BuddyList_DB = null;
	private int ID_ = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buddy);

		if (this.BuddyList_DB == null) {
			this.BuddyList_DB = new Hashtable<String, Integer>();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_buddy, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();

		buttonSave = (Button) findViewById(R.id.button_buddy_save);
		buttonClose = (Button) findViewById(R.id.button_buddy_close);
		buddyName = (EditText) findViewById(R.id.edit_buddy_name);
		buddyList = (TextView) findViewById(R.id.text_buddy_list);

		buttonSave.setOnClickListener(this);
		buttonClose.setOnClickListener(this);
		
		updateDisplayBuddyList();

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button_buddy_save:
			showMsg("Salvar...");
			writeBuddyInList();
			break;

		case R.id.button_buddy_close:
			showMsg("J�...");
			finish();
			break;

		default:
			break;
		}

	}

	private boolean writeBuddyInList() {

		String name = this.buddyName.getText().toString();

		if (this.BuddyList_DB.containsKey(name)) {
			WarnAlredyExists();

		} else {

			if (name.trim().equals("") == false) {
				name = name.toUpperCase();
				this.BuddyList_DB.put(name, new Integer(ID_));
				ID_++;

				updateDisplayBuddyList();

				return true;
			}
		}

		return false;

	}

	private void WarnAlredyExists() {
		Toast.makeText(getApplicationContext(), "Este cabra j� t� na lista...",
				Toast.LENGTH_SHORT).show();

	}

	private void updateDisplayBuddyList() {

		StringBuilder tmp = new StringBuilder();

		for (Enumeration<String> en = this.BuddyList_DB.keys(); en
				.hasMoreElements();) {
			String name = en.nextElement();

			tmp.append(name);
			tmp.append("\t[" + this.BuddyList_DB.get(name) + "]");
			tmp.append("\n");
		}

		buddyList.setText(tmp.toString());


	}

	private void showMsg(String text) {

		Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT)
				.show();

	}

}
