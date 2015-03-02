package com.CTC.activity.model;

import java.io.Serializable;

import android.graphics.Bitmap;

public class PanicModel implements Serializable {
	Bitmap picture;
	String LAT_LON, EM1, EM2, EM3, EM_TEXT;

	public Bitmap getPicture() {
		return picture;
	}

	public void setPicture(Bitmap picture) {
		this.picture = picture;
	}

	public String getLAT_LON() {
		return LAT_LON;
	}

	public void setLAT_LON(String lAT_LON) {
		LAT_LON = lAT_LON;
	}

	public String getEM1() {
		return EM1;
	}

	public void setEM1(String eM1) {
		EM1 = eM1;
	}

	public String getEM2() {
		return EM2;
	}

	public void setEM2(String eM2) {
		EM2 = eM2;
	}

	public String getEM3() {
		return EM3;
	}

	public void setEM3(String eM3) {
		EM3 = eM3;
	}

	public String getEM_TEXT() {
		return EM_TEXT;
	}

	public void setEM_TEXT(String eM_TEXT) {
		EM_TEXT = eM_TEXT;
	}

}
