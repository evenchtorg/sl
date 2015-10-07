package com.my.shili.util;

import java.net.URLDecoder;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class Function { 

	public final static String encode = "utf-8";
@Deprecated
	public static String webRequest(String urlRequest, ArrayList<NameValuePair> params) throws Exception {
		String strResult = "";
		HttpPost httpRequest = new HttpPost(urlRequest);
		httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			strResult = URLDecoder.decode(EntityUtils.toString(httpResponse.getEntity()), encode);
		} else {
			strResult = httpResponse.getStatusLine().toString();
		}
		return strResult;
	}
}