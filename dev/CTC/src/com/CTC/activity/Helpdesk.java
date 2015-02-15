package com.CTC.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.CTC.R;
import com.CTC.async.WomanAlert;

public class Helpdesk extends Activity implements OnClickListener {
	Button changeInfo, accident, women, complain, fire, medical, photo,
			entertext, panic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.helpdesk);

		initViews();

		changeInfo.setOnClickListener(this);
		accident.setOnClickListener(this);
		women.setOnClickListener(this);
		complain.setOnClickListener(this);
		fire.setOnClickListener(this);
		medical.setOnClickListener(this);
		photo.setOnClickListener(this);
		entertext.setOnClickListener(this);
		panic.setOnClickListener(this);
	}

	private void initViews() {
		// TODO Auto-generated method stub
		changeInfo = (Button) findViewById(R.id.changeInfo);
		accident = (Button) findViewById(R.id.accident);
		women = (Button) findViewById(R.id.women);
		complain = (Button) findViewById(R.id.complain);
		fire = (Button) findViewById(R.id.fire);
		medical = (Button) findViewById(R.id.medical);
		photo = (Button) findViewById(R.id.photo);
		entertext = (Button) findViewById(R.id.entertext);
		panic = (Button) findViewById(R.id.panic);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.changeInfo:
			askConssent();
			break;
		case R.id.accident:
			//EmergencyAlert();
			break;
		case R.id.women:

			break;
		case R.id.complain:

			break;
		case R.id.fire:

			break;
		case R.id.medical:

			break;
		case R.id.photo:

			break;
		case R.id.entertext:

			break;
		case R.id.panic:

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

	/*private void EmergencyAlert() {
		Button emeralert = (Button) findViewById(R.id.emergencyalert);
		emeralert.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),
						EmergencyAlert.class);
				startActivity(i);
				// startActivity(new
				// Intent("android.intent.action.EMERGENCYALERT"));
			}
		});
	}*/

}
