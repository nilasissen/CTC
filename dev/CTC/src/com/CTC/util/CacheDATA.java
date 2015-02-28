package com.CTC.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class CacheDATA {
	private SharedPreferences prefs;

	private String SHARED_PREFERENCE_NAME = "CTC_pref";

	public CacheDATA(Context context) {
		prefs = context.getSharedPreferences(SHARED_PREFERENCE_NAME,
				Context.MODE_PRIVATE);
	}

	public void storeData(String em1, String em2, String em3) {
		Editor editor = prefs.edit();
		editor.putString("EM1", em1);
		editor.putString("EM2", em2);
		editor.putString("EM3", em3);
		editor.commit();
	}

	public String getEM1() {
		return prefs.getString("EM1", "");
	}

	public String getEM2() {
		return prefs.getString("EM2", "");
	}

	public String getEM3() {
		return prefs.getString("EM3", "");
	}
}
