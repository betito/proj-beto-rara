package apps.br.nhc.controller.activity.subactivity;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import apps.br.nhc.R;
import apps.br.nhc.controller.activity.NhcBO;
import apps.br.nhc.model.domain.PersonItem;
import apps.br.nhc.view.adapter.ListItemBuddyAdapter;

public class MainSubActivity extends SubActivity implements OnClickListener {

	// adapter of person list
	private ListItemBuddyAdapter listItemBuddyAdapter;
	
	// layout inflater
	private final LayoutInflater inflater;
	
	public MainSubActivity(Activity activity) {
		super(activity);
		
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		ListView personList = (ListView) activity.findViewById(R.id.main_screen_people_list);
		Button addPersonButton = (Button) activity.findViewById(R.id.main_screen_add_person_button);
		Button addItemButton = (Button) activity.findViewById(R.id.main_screen_add_item_button);
		
		addPersonButton.setOnClickListener(this);
		addItemButton.setOnClickListener(this);
		
		// get list of person
		List<PersonItem> listPerson = NhcBO.getInstance().getBuddyListDB();
		
		personList.setAdapter(listItemBuddyAdapter = new ListItemBuddyAdapter(activity, listPerson));
	}
	
	@Override
	public void onClick(final View view) {
		
		switch (view.getId()) {
		case R.id.main_screen_add_person_button:
			openAddPersonScreen();
			break;
			
		case R.id.main_screen_add_item_button:
			openAddItemScreen();
			break;

		default:
			break;
		}
		
	}
	
	public void notifyChangeList() {
		listItemBuddyAdapter.notifyDataSetChanged();
	}
	
	private void openAddPersonScreen() {
		
		ViewGroup root = (ViewGroup) activity.getWindow().getDecorView().getRootView();
		
		inflater.inflate(R.layout.add_person_screen, root, true);
	}
	
	private void openAddItemScreen() {
		
		ViewGroup root = (ViewGroup) activity.getWindow().getDecorView().getRootView();
		
		inflater.inflate(R.layout.add_item_screen, root, true);
	}

	

}
