package apps.br.nhc.controller.activity.subactivity;

import android.app.Activity;
import android.view.View;

public class SubActivity {
	
	protected Activity activity;
	protected View viewRootSub;
	
	public SubActivity(final Activity activity, final View viewRootSub) {
		this.activity = activity;
		this.viewRootSub = viewRootSub;
	}
	
}
