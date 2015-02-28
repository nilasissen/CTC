package com.CTC.activity.model;

import java.io.Serializable;

public class ContactListMOdel implements Serializable {
	public String CONTACT_NAME;
	public String CONTACT_NUMBER;

	public String getCONTACT_NAME() {
		return CONTACT_NAME;
	}

	public void setCONTACT_NAME(String cONTACT_NAME) {
		CONTACT_NAME = cONTACT_NAME;
	}

	public String getCONTACT_NUMBER() {
		return CONTACT_NUMBER;
	}

	public void setCONTACT_NUMBER(String cONTACT_NUMBER) {
		CONTACT_NUMBER = cONTACT_NUMBER;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return CONTACT_NAME;
	}
}
