package com.calculadoradebuteco;

import android.content.Context;
import android.content.Intent;

public class Utils {

	
	public static void openActivity (final Context context, final Class act){
		
		Intent intent = new Intent(context.getApplicationContext(),
				act);
		context.startActivity(intent);
	}
	
}
