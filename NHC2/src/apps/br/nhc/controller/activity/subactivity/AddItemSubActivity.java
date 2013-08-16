package apps.br.nhc.controller.activity.subactivity;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
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
		edtPrice = (EditText) activity.findViewById(R.id.add_item_edit_unit_price);
		edtQtd = (EditText) activity.findViewById(R.id.add_item_edit_qtd);
		butCancel = (Button) activity.findViewById(R.id.add_item_button_cancel);
		butSave = (Button) activity.findViewById(R.id.add_item_button_save);
		listItem = (ListView) activity.findViewById(R.id.add_item_list);
		
		butCancel.setOnClickListener(this);
		butSave.setOnClickListener(this);
		
		listItem.setAdapter(listItemAdapter = new ListItemAdapter(activity, NhcBO.getInstance().getItemListDB()));
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
		
		String name = edtName.getText().toString().trim().toUpperCase(Utils.getLocale());
		String price = edtPrice.getText().toString().trim();
		String quant = edtQtd.getText().toString().trim();
		
		if(NhcBO.getInstance().getBuddyListDB().contains(name)) {
			// TODO: make warning user
		}
		else if(name.isEmpty()) {
			// TODO: make warning user
		}
		else if(price.isEmpty()) {
			// TODO: make warning user
		}
		else if(quant.isEmpty()) {
			// TODO: make warning user
		}
		else {
			
			int id = NhcBO.getInstance().getItemListDB().size();
			
			DataItem ditem = new DataItem(id, name, price, quant);
			
			NhcBO.getInstance().getItemListDB().add(ditem);
			
			listItemAdapter.notifyDataSetChanged();
			
			
			edtName.setText("");
			edtPrice.setText("");
			edtQtd.setText("");
		}
		
	}

}
