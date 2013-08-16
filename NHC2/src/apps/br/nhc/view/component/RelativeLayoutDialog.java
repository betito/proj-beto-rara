package apps.br.nhc.view.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class RelativeLayoutDialog extends RelativeLayout {

	public RelativeLayoutDialog(Context context) {
		super(context);
	}

	public RelativeLayoutDialog(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public RelativeLayoutDialog(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		super.onTouchEvent(event);
		
		return true;
	}
	
}
