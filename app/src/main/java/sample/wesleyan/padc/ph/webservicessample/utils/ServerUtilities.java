package sample.wesleyan.padc.ph.webservicessample.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public final class ServerUtilities {
	private static final int MAX_ATTEMPTS = 5;
	private static final int BACKOFF_MILLI_SECONDS = 2000;
	private static final Random random = new Random();


	/**
	 * Issue a POST request to the server.
	 * 
	 * @param endpoint
	 *            POST address.
	 * @param params
	 *            request parameters.
	 * 
	 * @throws IOException
	 *             propagated from POST.
	 */
	private String post(String endpoint, Map<String, String> params)
			throws IOException {

        String reply = "";

		URL url;
		try {
			url = new URL(endpoint);
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException("invalid url: " + endpoint);
		}
		StringBuilder bodyBuilder = new StringBuilder();
		Iterator<Entry<String, String>> iterator = params.entrySet().iterator();
		// constructs the POST body using the parameters
		while (iterator.hasNext()) {
			Entry<String, String> param = iterator.next();
			bodyBuilder.append(param.getKey()).append('=')
					.append(param.getValue());
			if (iterator.hasNext()) {
				bodyBuilder.append('&');
			}
		}
		String body = bodyBuilder.toString();
		byte[] bytes = body.getBytes();
		HttpURLConnection conn = null;
		try {
			Log.e("URL", "> " + url);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setFixedLengthStreamingMode(bytes.length);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded;charset=UTF-8");
			// post the request
			OutputStream out = conn.getOutputStream();
			out.write(bytes);
			out.close();
			// handle the response
			int status = conn.getResponseCode();

			if (status != 200) {
				throw new IOException("Post failed with error code " + status);
			}else { // responseCode == HttpURLConnection.HTTP_OK { //success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                reply = response.toString();
            }

		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
        return reply;
	}

    public String doPostJSONFromUrl(String path, HashMap requestData, boolean isHashed) {
        String strResult = "";
        URL url;
        String body ="";
        HttpURLConnection conn = null;

        try {
            url = new URL(path);

            if(!isHashed) {
                body = buildRequestPostParameters(requestData);
            }else{
                body = buildHashedRequestPostParameters(requestData);
            }
            byte[] bytes = body.getBytes();

            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setFixedLengthStreamingMode(bytes.length);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded;charset=UTF-8");
            // post the request
            OutputStream out = conn.getOutputStream();
            out.write(bytes);
            out.close();

            int status = conn.getResponseCode();
            String result = "";
            String line = "";

            if (status != 200) {
                throw new IOException("Post failed with error code " + status);
            } else {
                InputStream stream = conn.getInputStream();
                InputStreamReader isReader = new InputStreamReader(stream);

                BufferedReader br = new BufferedReader(isReader);
                while ((line = br.readLine()) != null) {
                    result += line;
                }

                br.close();
            }
            strResult = result;
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("invalid url: " + path);
        } catch(IOException e){
            e.printStackTrace(System.out );
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

//        System.out.println("RESULT : " + strResult);
        return strResult;
    } // end doPostJSONFromUrl


    private String buildRequestPostParameters(HashMap data) {
        StringBuilder bodyBuilder = new StringBuilder();
        Iterator iterator = data.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry param = (Map.Entry) iterator.next();
            bodyBuilder.append(param.getKey()).append('=')
                    .append(param.getValue());
            if (iterator.hasNext()) {
                bodyBuilder.append('&');
            }
        }

        return bodyBuilder.toString();
    }

    private String buildHashedRequestPostParameters(HashMap data) {
        StringBuilder bodyBuilder = new StringBuilder();
        Iterator iterator = data.entrySet().iterator();

        try {
            while (iterator.hasNext()) {
                Map.Entry param = (Map.Entry) iterator.next();
                bodyBuilder.append(param.getKey()).append('=')
                        .append(URLEncoder.encode(param.getValue().toString(), "UTF-8"));
                if (iterator.hasNext()) {
                    bodyBuilder.append('&');
                }
            }
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace(System.out);
        }
        return bodyBuilder.toString();
    }
}
/*
Map<String, String> params = new HashMap<String, String>();
		params.put("regId", regId);
		try {
			post(serverUrl, params);
 */
