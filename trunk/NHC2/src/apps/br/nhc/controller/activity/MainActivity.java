package apps.br.nhc.controller.activity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import apps.br.nhc.R;
import apps.br.nhc.controller.activity.subactivity.AddItemSubActivity;
import apps.br.nhc.controller.activity.subactivity.AddPersonSubActivity;
import apps.br.nhc.controller.activity.subactivity.MainSubActivity;

public class MainActivity extends Activity {

	private MainSubActivity mainSubActivity;
	private AddPersonSubActivity addPersonSubActivity;
	private AddItemSubActivity addItemSubActivity;
	
	// layout inflater
	private LayoutInflater inflater;
	
	// view root
	private ViewGroup viewRoot;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		viewRoot = (ViewGroup) getWindow().getDecorView().getRootView();
		
		mainSubActivity = new MainSubActivity(this, getWindow().getDecorView().getRootView());
		
	}
	
	public void openAddPersonScreen() {
		
		View viewRootAddPerson = inflater.inflate(R.layout.add_person_screen, viewRoot, false);
		
		viewRoot.addView(viewRootAddPerson);
		
		addPersonSubActivity = new AddPersonSubActivity(this, viewRootAddPerson);
	}
	
	public void openAddItemScreen() {
		
		View viewRootAddItem = inflater.inflate(R.layout.add_item_screen, viewRoot, false);
		
		viewRoot.addView(viewRootAddItem);
		
		addItemSubActivity = new AddItemSubActivity(this, viewRootAddItem);
	}
	
	public void closeAddPersonAct(final View viewRootSub) {
		closeSubActivity(viewRootSub);
		
		addPersonSubActivity = null;
	}
	
	public void closeAddItemAct(final View viewRootSub) {
		closeSubActivity(viewRootSub);
		
		addItemSubActivity = null;
	}
	
	/**
	 * Closes a sub activity
	 * @param viewRootSub - root view of the sub activity
	 */
	private void closeSubActivity(final View viewRootSub) {
		mainSubActivity.notifyChangeList();
		
		// remove view from viewgroup root
		viewRoot.removeView(viewRootSub);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
		return false;
	}

}
