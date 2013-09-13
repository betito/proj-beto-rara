package apps.br.nhc.controller.activity.subactivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import apps.br.nhc.R;
import apps.br.nhc.controller.activity.MainActivity;

public class SplashSubActivity extends SubActivity {

	private TextView appName = null;
	private Context context = null;

	public SplashSubActivity(Activity activity, final View viewRootSub) {
		super(activity, viewRootSub);

		context = activity.getApplicationContext();

		this.appName = (TextView) viewRootSub.findViewById(R.id.splash_appname);

	}

	private void saveQtt() {

		((MainActivity) activity).closeAddPersonAct(viewRootSub);
	}

}
