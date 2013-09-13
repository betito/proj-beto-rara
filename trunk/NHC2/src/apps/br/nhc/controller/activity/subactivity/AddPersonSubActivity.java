package apps.br.nhc.controller.activity.subactivity;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import apps.br.nhc.R;
import apps.br.nhc.controller.NhcBO;
import apps.br.nhc.controller.activity.MainActivity;
import apps.br.nhc.controller.util.Utils;
import apps.br.nhc.model.domain.PersonItem;

public class AddPersonSubActivity extends SubActivity implements
		OnClickListener {

	private EditText edtName;
	private Button butCancel;
	private Button butSave;

	public AddPersonSubActivity(Activity activity, final View viewRootSub) {
		super(activity, viewRootSub);

		edtName = (EditText) activity.findViewById(R.id.add_person_edit_name);
		butCancel = (Button) activity
				.findViewById(R.id.add_person_button_cancel);
		butSave = (Button) activity.findViewById(R.id.add_person_button_save);

		butCancel.setOnClickListener(this);
		butSave.setOnClickListener(this);

		focusOn(edtName);

	}

	private void focusOn(EditText edt) {

		edt.requestFocus();

		InputMethodManager keyboard = (InputMethodManager) activity
				.getApplicationContext().getSystemService(
						Context.INPUT_METHOD_SERVICE);
		keyboard.showSoftInput(edt, 0);

	}

	@Override
	public void onClick(View view) {

		switch (view.getId()) {
		case R.id.add_person_button_cancel:
			((MainActivity) activity).closeAddPersonAct(viewRootSub);
			break;

		case R.id.add_person_button_save:
			savePerson();
			break;

		default:
			break;
		}

	}

	private void savePerson() {

		String name = edtName.getText().toString().trim()
				.toUpperCase(Utils.getLocale());

		if (!name.isEmpty()
				&& !NhcBO.getInstance().getBuddyListDB().containsKey(name)) {
			PersonItem item = new PersonItem(name, 0d, 0d);

			NhcBO.getInstance().getBuddyListDB().put(name, item);
			
			focusOn(edtName);
			
		}

		((MainActivity) activity).closeAddPersonAct(viewRootSub);
	}

}
