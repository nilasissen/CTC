package com.CTC.activity;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.CTC.R;

public class SetInfo {
	private Context context;
	private EditText textContent;
	private CheckBox photo, location;
	private LinearLayout lay;
	private ImageView capturedImage;
	private TextView latlon;
	private Button changeInfo;
	private boolean layout_FLAG = false;
	private boolean image_FLAG = false;
	private boolean location_FLAG = false;

	public SetInfo(Context mcontext) {
		this.context = mcontext;
	}

	public void showPopup() {
		final Dialog dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.infoselect);

		textContent = (EditText) dialog.findViewById(R.id.textContent);
		photo = (CheckBox) dialog.findViewById(R.id.photo);
		location = (CheckBox) dialog.findViewById(R.id.location);
		lay = (LinearLayout) dialog.findViewById(R.id.lay);
		capturedImage = (ImageView) dialog.findViewById(R.id.capturedImage);
		latlon = (TextView) dialog.findViewById(R.id.latlon);

		changeInfo = (Button) dialog.findViewById(R.id.changeInfo);

		photo.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				//layout_FLAG = true;
				if(arg0.isChecked()){
					image_FLAG = true;
					lay.setVisibility(View.VISIBLE);
					capturedImage.setVisibility(View.VISIBLE);
				}else {
					image_FLAG = false;
					capturedImage.setVisibility(View.INVISIBLE);
					if(!location_FLAG){
						lay.setVisibility(View.GONE);
					}else {
						lay.setVisibility(View.VISIBLE);
					}
				}
			}
		});

		location.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				//layout_FLAG = true;
				if(arg0.isChecked()){
					location_FLAG = true;
					lay.setVisibility(View.VISIBLE);
					latlon.setVisibility(View.VISIBLE);
				}else {
					location_FLAG = false;
					latlon.setVisibility(View.INVISIBLE);
					if(!image_FLAG){
						lay.setVisibility(View.GONE);
					}else {
						lay.setVisibility(View.VISIBLE);
					}
				}
			}
		});

		dialog.show();
	}
}
