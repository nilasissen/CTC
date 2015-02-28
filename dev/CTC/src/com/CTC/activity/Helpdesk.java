package com.CTC.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.CTC.R;
import com.CTC.async.WomanAlert;

public class Helpdesk extends Activity implements OnClickListener {
	Button changeInfo, accident, women, complain, fire, medical, photo,
			entertext, panic;
	private Uri mMakePhotoUri;
	File sdImageMainDirectory;
	final int REQUEST_FROM_CAMERA = 1;

	Bitmap bmp = null;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			Window window = this.getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.setStatusBarColor(this.getResources().getColor(
					R.color.status));
		}
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
		Enter_EM_Contact em_Contact = null;
		switch (v.getId()) {
		case R.id.changeInfo:
			em_Contact = new Enter_EM_Contact(Helpdesk.this);
			em_Contact.ShowPopup();
			break;
		case R.id.accident:
			// EmergencyAlert();
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
			try {
				File root = new File(Environment.getExternalStorageDirectory()
						+ File.separator + "CTC_phoots"
						+ File.separator);
				root.mkdirs();
				sdImageMainDirectory = new File(root,
						"panic_photo.JPEG");
				startCameraActivity();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case R.id.entertext:
			new SetInfo(Helpdesk.this).showPopup();
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

	/*
	 * private void EmergencyAlert() { Button emeralert = (Button)
	 * findViewById(R.id.emergencyalert); emeralert.setOnClickListener(new
	 * View.OnClickListener() {
	 * 
	 * public void onClick(View v) { // TODO Auto-generated method stub Intent i
	 * = new Intent(getApplicationContext(), EmergencyAlert.class);
	 * startActivity(i); // startActivity(new //
	 * Intent("android.intent.action.EMERGENCYALERT")); } }); }
	 */

	protected void startCameraActivity() {

		Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		mMakePhotoUri = Uri.fromFile(sdImageMainDirectory);
		i.putExtra(MediaStore.EXTRA_OUTPUT, mMakePhotoUri);
		startActivityForResult(i, REQUEST_FROM_CAMERA);
	}

	public Bitmap getResizedBitmap(Bitmap image, int bitmapWidth,
			int bitmapHeight) {
		return Bitmap
				.createScaledBitmap(image, bitmapWidth, bitmapHeight, true);
	}

	private byte[] getImageBytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); // bm is the bitmap
															// object
		byte[] b = baos.toByteArray();
		return b;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (resultCode) {
		case RESULT_OK:

			InputStream is = null;

			/*
			 * File file = sdImageMainDirectory; try { is = new
			 * FileInputStream(file);
			 * 
			 * } catch (FileNotFoundException e) { e.printStackTrace(); }
			 */

			if (is == null) {
				try {
					if (data != null) {
						Uri u = data.getData();
						Log.v("Uri", u.toString());
						is = getContentResolver().openInputStream(u);
					} else if (data == null && mMakePhotoUri != null) {
						Uri u = mMakePhotoUri;
						is = getContentResolver().openInputStream(u);
					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

			}
			BitmapFactory.Options options = new BitmapFactory.Options();
			bmp = BitmapFactory.decodeStream(is, null, options);
			bmp = getResizedBitmap(bmp, 800, 600);
			break;

		default: // do nothing
			super.onActivityResult(requestCode, resultCode, data);
		}
	}

}
