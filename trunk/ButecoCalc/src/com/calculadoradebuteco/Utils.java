package com.calculadoradebuteco;

import java.util.Enumeration;

import com.calculadoradebuteco.model.CalcButeco;

import android.content.Context;
import android.content.Intent;

public class Utils {

	public static void openActivity(final Context context, final Class act) {

		Intent intent = new Intent(context.getApplicationContext(), act);
		context.startActivity(intent);
	}

	public static String updateDisplayBuddyList() {

		StringBuilder tmp = new StringBuilder();

		if (CalcButeco.getInstance().getBuddyList_DB() != null) {

			for (Enumeration<String> en = CalcButeco.getInstance()
					.getBuddyList_DB().keys(); en.hasMoreElements();) {
				String name = en.nextElement();
				int id = CalcButeco.getInstance().getBuddyList_DB().get(name);

				tmp.append(name);
				tmp.append("\t[" + id + "]");
				tmp.append("\n");
			}
		}

		return tmp.toString();

	}

	private static int getInt(Integer data) {
		return data.intValue();
	}

}
