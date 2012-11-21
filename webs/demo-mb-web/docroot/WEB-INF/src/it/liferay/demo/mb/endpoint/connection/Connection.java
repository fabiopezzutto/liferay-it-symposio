package it.liferay.demo.mb.endpoint.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;

public class Connection {

	public Connection(String host, int port) {

		_host = host;
		_port = port;

		_httpClient = new HttpClient();
	}

	public synchronized String sendHttpRequest(String query)
		throws Exception {

		String response = null;

		try {
			URL url = new URL("http", _host, _port, query);
			response = doSendHttpRequest(url.toString());
		} catch (Exception e) {
			_log.error("Error sending HTTP request to server", e);
		}

		return response;
	}

	protected String doSendHttpRequest(String url)
		throws HttpException, IOException {

		GetMethod method = new GetMethod(url);

		method.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);

		StringBuffer responseMsg = new StringBuffer();

		try {
			_httpClient.executeMethod(method);

			InputStream input = method.getResponseBodyAsStream();
			BufferedReader br = new BufferedReader(
				new InputStreamReader(input, StringPool.UTF8));

			String line = null;
			while ((line = br.readLine()) != null) {
				responseMsg.append(line);
			}
		} catch (HttpException e) {
			_log.error("Http error getting http GET request", e);
			throw e;
		} catch (IOException e) {
			_log.error("IO error getting http GET request", e);
			throw e;
		} finally {
			method.releaseConnection();
		}

		return responseMsg.toString();
	}

	private String _host;
	private HttpClient _httpClient;
	private int _port;

	private static Log _log = LogFactoryUtil.getLog(Connection.class);

}
