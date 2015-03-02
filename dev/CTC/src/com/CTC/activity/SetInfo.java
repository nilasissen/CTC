package com.CTC.activity;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.CTC.R;
import com.CTC.activity.model.PanicModel;
import com.CTC.util.CacheDATA;
import com.CTC.util.Constant;
import com.CTC.util.LocationShare;

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
	private PanicModel model;
	private LocationShare locationShare;
	private CacheDATA data;

	public SetInfo(Context _context, PanicModel _model) {
		this.context = _context;
		this.model = _model;
	}

	public void showPopup() {

		locationShare = new LocationShare(context);
		data = new CacheDATA(context);

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
		changeInfo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				if (textContent.getText().toString().trim()
						.equalsIgnoreCase("")) {
					Toast.makeText(context, Constant.TOAST_Emeergency_TEXT,
							Toast.LENGTH_LONG).show();
				} else {
					model.setEM_TEXT(textContent.getText().toString().trim());
					model.setEM1(data.getEM1());
					model.setEM1(data.getEM2());
					model.setEM1(data.getEM3());

					if (location.isChecked()) {
						model.setLAT_LON(latlon.getText().toString().trim());
					} else if (!location.isChecked()) {
						model.setLAT_LON("");
					}
					if (photo.isChecked()) {
						if (model.getPicture() == null
								|| model.getPicture().equals("")) {
							//
						} else {
							model.setPicture(null);
						}
					} else if (!photo.isChecked()) {
						model.setPicture(null);
					}

					dialog.dismiss();
				}
			}
		});
		
		
		textContent.setText(data.getEM_TEXT().toString().trim());

		if (model.getPicture() == null || model.getPicture().equals("")) {
			// do nothing
		} else {
			capturedImage.setImageBitmap(model.getPicture());
		}

		photo.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				// layout_FLAG = true;
				if (arg0.isChecked()) {
					image_FLAG = true;
					lay.setVisibility(View.VISIBLE);
					capturedImage.setVisibility(View.VISIBLE);
				} else {
					image_FLAG = false;
					capturedImage.setVisibility(View.GONE);
					if (!location_FLAG) {
						lay.setVisibility(View.GONE);
					} else {
						lay.setVisibility(View.VISIBLE);
					}
				}
			}
		});

		location.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				// layout_FLAG = true;
				if (arg0.isChecked()) {

					latlon.setText(locationShare.fetchCurrentLocation());
					location_FLAG = true;
					lay.setVisibility(View.VISIBLE);
					latlon.setVisibility(View.VISIBLE);
				} else {
					location_FLAG = false;
					latlon.setVisibility(View.GONE);
					if (!image_FLAG) {
						lay.setVisibility(View.GONE);
					} else {
						lay.setVisibility(View.VISIBLE);
					}
				}
			}
		});

		dialog.show();
	}
}
