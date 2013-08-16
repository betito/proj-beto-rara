package apps.br.nhc.controller.activity.subactivity;

import java.util.List;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import apps.br.nhc.R;
import apps.br.nhc.controller.NhcBO;
import apps.br.nhc.controller.activity.MainActivity;
import apps.br.nhc.model.domain.PersonItem;
import apps.br.nhc.view.adapter.ListBuddyAdapter;

public class MainSubActivity extends SubActivity implements OnClickListener {

	// adapter of person list
	private ListBuddyAdapter listItemBuddyAdapter;
	
	public MainSubActivity(final Activity activity, final View viewRootSub) {
		super(activity, viewRootSub);
		
		ListView personList = (ListView) activity.findViewById(R.id.main_screen_people_list);
		Button addPersonButton = (Button) activity.findViewById(R.id.main_screen_add_person_button);
		Button addItemButton = (Button) activity.findViewById(R.id.main_screen_add_item_button);
		
		addPersonButton.setOnClickListener(this);
		addItemButton.setOnClickListener(this);
		
		// get list of person
		List<PersonItem> listPerson = NhcBO.getInstance().getBuddyListDB();
		
		personList.setAdapter(listItemBuddyAdapter = new ListBuddyAdapter(activity, listPerson));
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
	
	public void notifyChangeList() {
		listItemBuddyAdapter.notifyDataSetChanged();
	}
	
}
