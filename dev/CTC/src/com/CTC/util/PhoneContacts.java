package com.CTC.util;

import java.util.ArrayList;

import com.CTC.activity.model.ContactListMOdel;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

public class PhoneContacts {
	private Context context;
	private ContactListMOdel list_model;
	private ArrayList<ContactListMOdel> list;

	public PhoneContacts(Context _context) {
		this.context = _context;
	}

	public ArrayList<ContactListMOdel> getNumberList(ContentResolver cr) {
		Cursor phones = cr.query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
				null, null);
		// use the cursor to access the contacts
		String name = null, number = null;
		list = new ArrayList<ContactListMOdel>();
		while (phones.moveToNext()) {
			list_model = new ContactListMOdel();
			name = phones
					.getString(phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY));
			// get display name

			number = phones
					.getString(phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER));
			// get phone number

			if (number == null || number.equalsIgnoreCase("")) {

			} else {
				list_model.setCONTACT_NUMBER(number);
				list_model.setCONTACT_NAME(name);
				list.add(list_model);
			}

//			System.out.println("NAME:" + name);
//			System.out.println("NUMBER:" + number);
			

		}
		return list;

	}
}
