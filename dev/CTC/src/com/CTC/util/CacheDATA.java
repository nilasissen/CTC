package com.CTC.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class CacheDATA {
	private SharedPreferences prefs;

	private String SHARED_PREFERENCE_NAME = "CTC_pref";
	private String SHARED_PREFERENCE_EM1 = "EM1";
	private String SHARED_PREFERENCE_EM2 = "EM2";
	private String SHARED_PREFERENCE_EM3 = "Em3";
	private String SHARED_PREFERENCE_EM_TEXT = "Em_Text";

	private String SHARED_PREFERENCE_EM_NUMBER_FLAG = "Number_Flag";
	private String SHARED_PREFERENCE_EM_TEXT_FLAG = "Text_Flag";

	public CacheDATA(Context context) {
		prefs = context.getSharedPreferences(SHARED_PREFERENCE_NAME,
				Context.MODE_PRIVATE);
	}

	public void storeData(String em1, String em2, String em3) {
		Editor editor = prefs.edit();
		editor.putString(SHARED_PREFERENCE_EM1, em1);
		editor.putString(SHARED_PREFERENCE_EM2, em2);
		editor.putString(SHARED_PREFERENCE_EM3, em3);
		editor.putBoolean(SHARED_PREFERENCE_EM_NUMBER_FLAG, true);
		editor.commit();
	}

	public void store_EM_TEXT(String text){
		Editor editor = prefs.edit();
		editor.putString(SHARED_PREFERENCE_EM_TEXT, text);
		editor.putBoolean(SHARED_PREFERENCE_EM_TEXT_FLAG, true);
		editor.commit();
	}

	public String getEM1() {
		return prefs.getString(SHARED_PREFERENCE_EM1, "");
	}

	public String getEM2() {
		return prefs.getString(SHARED_PREFERENCE_EM2, "");
	}

	public String getEM3() {
		return prefs.getString(SHARED_PREFERENCE_EM3, "");
	}
	
	public String getEM_TEXT() {
		return prefs.getString(SHARED_PREFERENCE_EM_TEXT, "");
	}

	public Boolean getEM_number_exists() {
		return prefs.getBoolean(SHARED_PREFERENCE_EM_NUMBER_FLAG, false);
	}

	public Boolean getEM_text_exists() {
		return prefs.getBoolean(SHARED_PREFERENCE_EM_TEXT_FLAG, false);
	}

}
