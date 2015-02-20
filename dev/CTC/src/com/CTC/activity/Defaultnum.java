package com.CTC.activity;

import com.CTC.R;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class Defaultnum {
	private Context context;
	private EditText friend1;
	private EditText friend2;
	private EditText friend3;
	private Button numberset;


public  Defaultnum (Context mcontext){
	this.context = mcontext;
	
}

public void ShowPopup(){
	final Dialog dialog = new Dialog(context);
	dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	dialog.setContentView(R.layout.defaultnum);
	
	friend1 = (EditText) dialog.findViewById(R.id.ffriend1);
	friend2 = (EditText) dialog.findViewById(R.id.sfriend);
	friend3 = (EditText) dialog.findViewById(R.id.tfriend);
	numberset = (Button) dialog.findViewById(R.id.numberset);
}
}