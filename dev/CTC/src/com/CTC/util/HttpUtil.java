package com.CTC.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtil {

	public String getResponse(String urlStr) {
		String data = null;
		try {
			URL url = new URL(urlStr);
			URLConnection urlConn = url.openConnection();
			HttpURLConnection httpConn = (HttpURLConnection) urlConn;
			httpConn.connect();
			InputStream in = null;
			int resCode = -1;
			resCode = httpConn.getResponseCode();

			if (resCode == HttpURLConnection.HTTP_OK) {
				in = httpConn.getInputStream();
				byte[] buffer = new byte[250 * 1024];
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				int read = in.read(buffer);
				while (read > 0) {
					outputStream.write(buffer, 0, read);
					read = in.read(buffer);
				}
				data = new String(outputStream.toByteArray());
			}
		} catch (Exception e) {
			data = null;
		}
		return data;

	}

	public String writeLogInRequestStream(String urlStr,
			InputStream requestInputStream) {
		String data = null;
		try {
			URL url = new URL(urlStr);
			URLConnection urlConn = url.openConnection();
			HttpURLConnection httpConn = (HttpURLConnection) urlConn;
			httpConn.setRequestMethod("POST");
			httpConn.setConnectTimeout(1 * 60 * 1000);
			OutputStream requestOutputStream = httpConn.getOutputStream();
			byte[] fileBuffer = new byte[1024 * 1024];
			int fileRead = requestInputStream.read(fileBuffer);
			while (fileRead > 0) {
				requestOutputStream.write(fileBuffer, 0, fileRead);
				fileRead = requestInputStream.read(fileBuffer);
			}
			httpConn.connect();
			InputStream in = null;
			int resCode = -1;
			resCode = httpConn.getResponseCode();
			Constant.STATUS_CODE = resCode;
			if (resCode == HttpURLConnection.HTTP_OK) {
				in = httpConn.getInputStream();
				byte[] buffer = new byte[250 * 1024];
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				int read = in.read(buffer);
				while (read > 0) {
					outputStream.write(buffer, 0, read);
					read = in.read(buffer);
				}
				data = new String(outputStream.toByteArray());
			}

		} catch (Exception e) {
			data = null;
		}
		return data;
	}
}
