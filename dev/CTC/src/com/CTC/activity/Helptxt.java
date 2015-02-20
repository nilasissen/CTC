package com.CTC.activity;

import com.CTC.R;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;



public class Helptxt {
	private Context context;
	private EditText defaulthelptxt;
	private Button edit;
	private Button save;
	


public Helptxt(Context mcontext) {
	this.context = mcontext;
}
public void showPopup() {
	
	final Dialog dialog = new Dialog(context);
	dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	dialog.setContentView(R.layout.helptxt);
	
	
	defaulthelptxt = (EditText) dialog.findViewById(R.id.defaulthelptxt);
	edit = (Button) dialog.findViewById(R.id.helpedit);
	save = (Button) dialog.findViewById(R.id.helpsave);
	
}

}