package com.CTC.activity;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.CTC.R;
import com.CTC.activity.model.ContactListMOdel;
import com.CTC.util.CacheDATA;
import com.CTC.util.Constant;
import com.CTC.util.PhoneContacts;

public class Enter_EM_Contact {
	private Context context;
	private AutoCompleteTextView em1,em2,em3;
	private Button em_submit;
	private CacheDATA cacheDATA;
	private ArrayList<ContactListMOdel> list = new ArrayList<ContactListMOdel>();
	private PhoneContacts contacts;
	private ContactListMOdel contactList_model;
	private ArrayAdapter<ContactListMOdel> list_adap;

	public Enter_EM_Contact(Context mcontext) {
		this.context = mcontext;
	}

	public void ShowPopup() {
		cacheDATA = new CacheDATA(context);
		contacts = new PhoneContacts(context);
		final Dialog dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.enter_em);

		em1 = (AutoCompleteTextView) dialog.findViewById(R.id.em1);
		em2 = (AutoCompleteTextView) dialog.findViewById(R.id.em2);
		em3 = (AutoCompleteTextView) dialog.findViewById(R.id.em3);
		em_submit = (Button) dialog.findViewById(R.id.em_submit);
		
		
		em1.setText(cacheDATA.getEM1());
		em2.setText(cacheDATA.getEM2());
		em3.setText(cacheDATA.getEM3());
		

		list = contacts.getNumberList(context.getContentResolver());

		list_adap = new ArrayAdapter<ContactListMOdel>(context,
				android.R.layout.simple_dropdown_item_1line, list) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				View v = super.getView(position, convertView, parent);
				v.setTag(getItem(position).CONTACT_NUMBER);
				return v;
			}
		};
		em1.setAdapter(list_adap);
		em1.setThreshold(0);
		em2.setAdapter(list_adap);
		em3.setThreshold(0);
		em3.setAdapter(list_adap);
		em3.setThreshold(0);
		
		
		em1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				em1.setText(view.getTag().toString().trim());
			}
		});
		em2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				em2.setText(view.getTag().toString().trim());
			}
		});
		em3.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				em3.setText(view.getTag().toString().trim());
			}
		});

		em_submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (isValiDate()) {
					cacheDATA.storeData(em1.getText().toString().trim(), em2
							.getText().toString().trim(), em3.getText()
							.toString().trim());
					Toast.makeText(context, "DONE", Toast.LENGTH_LONG).show();
					dialog.dismiss();
				}
			}

			private boolean isValiDate() {
				// TODO Auto-generated method stub
				if (em1.getText().toString().trim().length() < 10
						|| em1.getText().toString().trim().length() > 10) {
					Toast.makeText(context, Constant.TOAST_NUMBER_FORMAT,
							Toast.LENGTH_LONG).show();
					return false;
				} else if (em2.getText().toString().trim().length() < 10
						|| em2.getText().toString().trim().length() > 10) {
					Toast.makeText(context, Constant.TOAST_NUMBER_FORMAT,
							Toast.LENGTH_LONG).show();
					return false;
				} else if (em3.getText().toString().trim().length() < 10
						|| em3.getText().toString().trim().length() > 10) {
					Toast.makeText(context, Constant.TOAST_NUMBER_FORMAT,
							Toast.LENGTH_LONG).show();
					return false;
				} else {
					return true;
				}

			}
		});
		dialog.show();
	}

}