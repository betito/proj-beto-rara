package apps.br.nhc.controller.activity.subactivity;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import apps.br.nhc.R;
import apps.br.nhc.controller.NhcBO;
import apps.br.nhc.controller.activity.MainActivity;
import apps.br.nhc.model.domain.DataItem;
import apps.br.nhc.view.adapter.ListItemAdapter;

public class CheckSubActivity extends SubActivity implements OnClickListener {

	private Button butCancel;
	private Button butSave;

	private ListView listItem;

	private ListItemAdapter listItemAdapter;

	public CheckSubActivity(Activity activity, final View viewRootSub) {
		super(activity, viewRootSub);

		butCancel = (Button) activity.findViewById(R.id.qtt_item_button_cancel);
		butSave = (Button) activity.findViewById(R.id.qtt_item_button_save);

		listItem = (ListView) activity.findViewById(R.id.qtt_item_list);

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

	}

	@Override
	public void onClick(View view) {

		switch (view.getId()) {
		case R.id.qtt_item_button_cancel:
			((MainActivity) activity).closeAddPersonAct(viewRootSub);
			break;

		case R.id.qtt_item_button_save:
			saveQtt();
			break;

		default:
			break;
		}

	}

	private void saveQtt() {

		((MainActivity) activity).closeAddPersonAct(viewRootSub);
	}

}
