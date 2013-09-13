package apps.br.nhc.controller.activity.subactivity;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import apps.br.nhc.R;
import apps.br.nhc.controller.NhcBO;
import apps.br.nhc.controller.activity.MainActivity;
import apps.br.nhc.controller.util.Utils;
import apps.br.nhc.model.domain.DataItem;
import apps.br.nhc.view.adapter.ListItemAdapter;

public class AddItemSubActivity extends SubActivity implements OnClickListener {

	private EditText edtName;
	private EditText edtPrice;
	private EditText edtQtd;
	private Button butCancel;
	private Button butSave;

	private ListView listItem;

	private ListItemAdapter listItemAdapter;

	public AddItemSubActivity(Activity activity, View viewRootSub) {
		super(activity, viewRootSub);

		edtName = (EditText) activity.findViewById(R.id.add_item_edit_name);
		edtPrice = (EditText) activity
				.findViewById(R.id.add_item_edit_unit_price);
		edtQtd = (EditText) activity.findViewById(R.id.add_item_edit_qtd);
		butCancel = (Button) activity.findViewById(R.id.add_item_button_cancel);
		butSave = (Button) activity.findViewById(R.id.add_item_button_save);
		listItem = (ListView) activity.findViewById(R.id.add_item_list);

		butCancel.setOnClickListener(this);
		butSave.setOnClickListener(this);

		// get list of itens
		List<DataItem> list = Arrays.asList(NhcBO
				.getInstance()
				.getItemListDB()
				.values()
				.toArray(
						new DataItem[NhcBO.getInstance().getItemListDB()
								.values().size()]));

		listItem.setAdapter(listItemAdapter = new ListItemAdapter(activity,
				list));

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
		case R.id.add_item_button_cancel:
			((MainActivity) activity).closeAddItemAct(viewRootSub);
			break;

		case R.id.add_item_button_save:
			saveItem();
			break;

		default:
			break;
		}

	}

	private void saveItem() {

		String name = edtName.getText().toString().trim()
				.toUpperCase(Utils.getLocale());
		String price = edtPrice.getText().toString().trim();
		String quant = edtQtd.getText().toString().trim();

		if (NhcBO.getInstance().getBuddyListDB().containsKey(name)) {
			// TODO: make warning user
		} else if (name.isEmpty()) {
			// TODO: make warning user
		} else if (price.isEmpty()) {
			// TODO: make warning user
		} else if (quant.isEmpty()) {
			// TODO: make warning user
		} else {

			int id = NhcBO.getInstance().getItemListDB().size();

			DataItem item = new DataItem(id, name, price, quant);

			NhcBO.getInstance().getItemListDB().put(name, item);

			// get list of itens
			List<DataItem> list = Arrays.asList(NhcBO
					.getInstance()
					.getItemListDB()
					.values()
					.toArray(
							new DataItem[NhcBO.getInstance().getItemListDB()
									.values().size()]));

			listItemAdapter.setList(list);

			listItemAdapter.notifyDataSetChanged();

			edtPrice.setText("");
			edtQtd.setText("");
			edtName.setText("");
			focusOn(edtName);

		}

	}

}
