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

import com.calculadoradebuteco.model.CalcButeco;

public class BuddyActivity extends Activity implements OnClickListener {

	private Button buttonSave = null;
	private Button buttonClose = null;
	private EditText buddyName = null;
	private TextView buddyList = null;

	private int ID_ = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buddy);

		if (CalcButeco.getInstance().getBuddyList_DB() == null) {
			CalcButeco.getInstance().setBuddyList_DB(
					new Hashtable<String, Integer>());
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
			ClearFields();
			break;

		case R.id.button_buddy_close:
			showMsg("J�...");
			finish();
			break;

		default:
			break;
		}

	}

	private void ClearFields() {

		buddyName.setText("");
		buddyName.requestFocus();

	}

	private boolean writeBuddyInList() {

		String name = this.buddyName.getText().toString();

		if (CalcButeco.getInstance().getBuddyList_DB().containsKey(name)) {
			WarnAlredyExists();

		} else {

			if (name.trim().equals("") == false) {
				name = name.toUpperCase();
				ID_ = CalcButeco.getInstance().getBUDDY_ID_COUNT();
				CalcButeco.getInstance().getBuddyList_DB()
						.put(name, Integer.valueOf(ID_));
				CalcButeco.getInstance().inc_BUDDY_ID_COUNT();

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

		if (CalcButeco.getInstance().getBuddyList_DB() != null) {

			for (Enumeration<String> en = CalcButeco.getInstance()
					.getBuddyList_DB().keys(); en.hasMoreElements();) {
				String name = en.nextElement();
				int id = CalcButeco.getInstance().getBuddyList_DB().get(name);

				tmp.append(name);
				tmp.append("\t[" + id + "]");
				tmp.append("\n");
			}

			buddyList.setText(tmp.toString());
		}

	}

	private void showMsg(String text) {

		Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT)
				.show();

	}

}
