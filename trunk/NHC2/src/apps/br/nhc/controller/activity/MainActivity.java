package apps.br.nhc.controller.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import apps.br.nhc.R;
import apps.br.nhc.controller.activity.subactivity.MainSubActivity;

public class MainActivity extends Activity {

	private MainSubActivity mainSubActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		mainSubActivity = new MainSubActivity(this);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
		return false;
	}

}
