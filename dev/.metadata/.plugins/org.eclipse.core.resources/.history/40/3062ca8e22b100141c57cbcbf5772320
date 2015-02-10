package com.CTC.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.CTC.R;
import com.CTC.async.WomanAlert;

public class Helpdesk extends Activity implements OnClickListener {
	Button womenalert, emergencyalert, complianlauge, settings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_helpdesk);

		initViews();

		womenalert.setOnClickListener(this);
		emergencyalert.setOnClickListener(this);
		complianlauge.setOnClickListener(this);
		settings.setOnClickListener(this);

	}

	private void initViews() {
		// TODO Auto-generated method stub
		womenalert = (Button) findViewById(R.id.womenalert);
		emergencyalert = (Button) findViewById(R.id.emergencyalert);
		complianlauge = (Button) findViewById(R.id.complianlauge);
		settings = (Button) findViewById(R.id.settings);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.womenalert:
			askConssent();
			break;
		case R.id.emergencyalert:
			EmergencyAlert();
			break;
		case R.id.complianlauge:

			break;
		case R.id.settings:

			break;

		default:
			break;
		}
	}

	private void askConssent() {
		// TODO Auto-generated method stub
		new AlertDialog.Builder(this)
				.setTitle("ALERT!!")
				.setMessage("Are you sure you want to send any alert??")
				.setPositiveButton(android.R.string.yes,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// continue with delete
								new WomanAlert(Helpdesk.this).execute();
							}
						})
				.setNegativeButton(android.R.string.no,
						new DialogInterface.OnClickListener() {
							@SuppressWarnings("deprecation")
							public void onClick(DialogInterface dialog,
									int which) {
								// do nothing
								dismissDialog(0);
							}
						}).setIcon(android.R.drawable.ic_dialog_alert).show();
	}

	private void EmergencyAlert()
	{
			
		
	}
}

