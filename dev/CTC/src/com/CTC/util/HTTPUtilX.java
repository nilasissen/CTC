package com.CTC.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;

public class HTTPUtilX {
	private static HTTPUtilX util;
	private final static String PROXY_HOST = "";
	private final static int PROXY_PORT = 80;
	private final static boolean PROXY_REQUIRED = false;

	private boolean securedConnection;
	private static Uri uri;
	private SchemeRegistry supportedSchemes;
	private static HttpParams params;
	private static ClientConnectionManager ccm;
	private static DefaultHttpClient httpclient;
	private HttpHost host;
	private HttpPost post;
	private HttpGet get;
	private static HttpHost proxy;

	private HTTPUtilX() {
		if (PROXY_REQUIRED) {
			supportedSchemes = new SchemeRegistry();
			supportedSchemes.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 80));
			supportedSchemes.register(new Scheme("https", SSLSocketFactory
					.getSocketFactory(), 443));
			params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_0);
			HttpProtocolParams.setContentCharset(params, "UTF-8");
			HttpProtocolParams.setUseExpectContinue(params, true);
			ccm = new ThreadSafeClientConnManager(params, supportedSchemes);
			httpclient = new DefaultHttpClient(ccm, params);
			proxy = new HttpHost(PROXY_HOST, PROXY_PORT, "http");
			httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
					proxy);
		} else {
			httpclient = new DefaultHttpClient();
		}
	}

	public static HTTPUtilX getHTTPUtil() {
		if (util == null) {
			util = new HTTPUtilX();
		}
		return util;
	}

	public String postResponse(String url, ArrayList<NameValuePair> params) {
		try {
			if (PROXY_REQUIRED) {
				preapareHostForUrl(url);
			}
			post = new HttpPost(url);
			if (params != null && params.size() > 0) {
				UrlEncodedFormEntity paramEntity = new UrlEncodedFormEntity(
						params, "UTF-8");
				post.setEntity(paramEntity);
			}
			HttpResponse httpresponse = null;

			if (PROXY_REQUIRED) {
				httpresponse = httpclient.execute(host, post);
			} else {
				httpresponse = httpclient.execute(post);
			}
			return EntityUtils.toString(httpresponse.getEntity());
		} catch (Exception e) {
		}
		return null;
	}

	public String postInBody(String url, String body,
			Hashtable<String, String> params) {
		try {
			if (PROXY_REQUIRED) {
				preapareHostForUrl(url);
			}
			post = new HttpPost(url);

			if (body != null && body.trim().length() > 0) {
				StringEntity bodyEntity = new StringEntity(body);
				post.setEntity(bodyEntity);
			}
			//post.

			if (params != null && params.size() > 0) {
				Enumeration<String> keys = params.keys();
				while (keys.hasMoreElements()) {
					String key = keys.nextElement();
					post.addHeader(key, params.get(key));
				}
			}
			HttpResponse httpresponse = null;

			if (PROXY_REQUIRED) {
				httpresponse = httpclient.execute(host, post);
			} else {
				httpresponse = httpclient.execute(post);
			}
			return EntityUtils.toString(httpresponse.getEntity());
		} catch (Exception e) {
		}
		return null;
	}

	public String postInBodyBinarry(String url, byte[] body,
			Hashtable<String, String> params) {
		try {
			if (PROXY_REQUIRED) {
				preapareHostForUrl(url);
			}
			post = new HttpPost(url);

			if (body != null && body.length > 0) {
				ByteArrayEntity bodyEntity = new ByteArrayEntity(body);
				post.setEntity(bodyEntity);
			}
			//post.

			if (params != null && params.size() > 0) {
				Enumeration<String> keys = params.keys();
				while (keys.hasMoreElements()) {
					String key = keys.nextElement();
					post.addHeader(key, params.get(key));
				}
			}
			HttpResponse httpresponse = null;

			if (PROXY_REQUIRED) {
				httpresponse = httpclient.execute(host, post);
			} else {
				httpresponse = httpclient.execute(post);
			}
			Log.v("No Exception", "No Exception");
			return EntityUtils.toString(httpresponse.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
			Log.v("Exception", "Exception");
		}
		return null;
	}

	
	public String getResponse(String url) {
		try {
			if (PROXY_REQUIRED) {
				preapareHostForUrl(url);
			}
			get = new HttpGet(url);
			HttpResponse httpresponse = null;

			if (PROXY_REQUIRED) {
				httpresponse = httpclient.execute(host, get);
			} else {
				httpresponse = httpclient.execute(get);
			}
			return EntityUtils.toString(httpresponse.getEntity());
		} catch (Exception e) {
		}
		return null;
	}

	public Drawable getDrawableFromUrl(String url) {
		try {
			InputStream in = null;
			in = openHttpConnection(url);
			return Drawable.createFromStream(in, "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void preapareHostForUrl(String url) {
		try {
			uri = Uri.parse(url);
			String protocol = url.substring(0, 5).toLowerCase();
			if (protocol.startsWith("https")) {
				securedConnection = true;
			} else {
				securedConnection = false;
			}
			if (securedConnection) {
				host = new HttpHost(uri.getHost(), 443, uri.getScheme());
			} else {
				host = new HttpHost(uri.getHost(), 80, uri.getScheme());
			}
		} catch (Exception e) {
		}
	}

	public InputStream openHttpConnection(String urlStr) {
		InputStream in = null;
		if (PROXY_REQUIRED) {
			int resCode = -1;
			try {
				URL url = new URL(urlStr);
				URLConnection urlConn = url.openConnection();

				if (!(urlConn instanceof HttpURLConnection)) {
					throw new IOException("URL is not an Http URL");
				}

				HttpURLConnection httpConn = (HttpURLConnection) urlConn;
				httpConn.setAllowUserInteraction(false);
				httpConn.setInstanceFollowRedirects(true);
				httpConn.setRequestMethod("GET");
				//httpConn.setReque
				httpConn.connect();

				resCode = httpConn.getResponseCode();
				if (resCode == HttpURLConnection.HTTP_OK) {
					in = httpConn.getInputStream();
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				preapareHostForUrl(urlStr);
				get = new HttpGet(urlStr);
				HttpResponse httpresponse = httpclient.execute(host, get);
				in = httpresponse.getEntity().getContent();
			} catch (Exception e) {
			}
		}
		return in;
	}
	protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
	       InputStream in = entity.getContent();
	         StringBuffer out = new StringBuffer();
	         int n = 1;
	         while (n>0) {
	             byte[] b = new byte[4096];
	             n =  in.read(b);
	             if (n>0) out.append(new String(b, 0, n));
	         }
	         return out.toString();
			
				
			
	    }
}
