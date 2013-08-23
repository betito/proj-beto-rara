package apps.br.nhc.controller.activity.subactivity;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import apps.br.nhc.R;
import apps.br.nhc.controller.NhcBO;
import apps.br.nhc.controller.activity.MainActivity;
import apps.br.nhc.model.domain.PersonItem;
import apps.br.nhc.view.adapter.ListBuddyAdapter;

public class MainSubActivity extends SubActivity implements OnClickListener,
		OnItemClickListener {

	private TextView txvTotal;
	private TextView txvTotalP10;

	// adapter of person list
	private ListBuddyAdapter listItemBuddyAdapter;

	public MainSubActivity(final Activity activity, final View viewRootSub) {
		super(activity, viewRootSub);

		txvTotal = (TextView) activity.findViewById(R.id.main_screen_total);
		txvTotalP10 = (TextView) activity
				.findViewById(R.id.main_screen_plus_10);

		ListView personList = (ListView) activity
				.findViewById(R.id.main_screen_people_list);
		ImageButton addPersonButton = (ImageButton) activity
				.findViewById(R.id.main_screen_add_person_button);
		ImageButton addItemButton = (ImageButton) activity
				.findViewById(R.id.main_screen_add_item_button);

		addPersonButton.setOnClickListener(this);
		addItemButton.setOnClickListener(this);

		// get list of person
		List<PersonItem> listPerson = Arrays.asList(NhcBO
				.getInstance()
				.getBuddyListDB()
				.values()
				.toArray(
						new PersonItem[NhcBO.getInstance().getBuddyListDB()
								.values().size()]));

		personList.setAdapter(listItemBuddyAdapter = new ListBuddyAdapter(
				activity, listPerson));

		personList.setOnItemClickListener(this);
	}

	@Override
	public void onClick(final View view) {

		switch (view.getId()) {
		case R.id.main_screen_add_person_button:
			((MainActivity) activity).openAddPersonScreen();
			break;

		case R.id.main_screen_add_item_button:
			((MainActivity) activity).openAddItemScreen();
			break;

		default:
			break;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		TextView txvName = (TextView) view.findViewById(R.id.person_list_name);

		((MainActivity) activity).openMergeScreen(txvName.getText().toString());

	}

	public void notifyChangeList() {
		// get list of person
		List<PersonItem> listPerson = Arrays.asList(NhcBO
				.getInstance()
				.getBuddyListDB()
				.values()
				.toArray(
						new PersonItem[NhcBO.getInstance().getBuddyListDB()
								.values().size()]));

		listItemBuddyAdapter.setList(listPerson);

		listItemBuddyAdapter.notifyDataSetChanged();
	}

	public void updateTotals() {

		txvTotal.setText(" = "
				+ String.format("%.2f",
						NhcBO.getInstance().getTotalBill()));
		txvTotalP10.setText(" = "
				+ String.format("%.2f",
						NhcBO.getInstance().getTotalP10()));

	}

}
