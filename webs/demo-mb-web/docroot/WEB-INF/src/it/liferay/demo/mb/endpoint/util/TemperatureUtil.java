package it.liferay.demo.mb.endpoint.util;

import it.liferay.demo.mb.endpoint.connection.Connection;
import it.liferay.demo.mb.util.PortletPropsValues;

import java.util.HashMap;
import java.util.Map;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;

public class TemperatureUtil {

	public static int getCoolingCyclesSettings() {
		String httpQuery = getURL("get-cooling-cycles", null);

		try {
			String result = _connection.sendHttpRequest(httpQuery);
			return Integer.valueOf(result);
		}
		catch (Exception e) {
			_log.error(e);
			return 0;
		}
	}

	public static int getEnergySettings() {
		String httpQuery = getURL("get-energy", null);

		try {
			String result = _connection.sendHttpRequest(httpQuery);
			return Integer.valueOf(result);
		}
		catch (Exception e) {
			_log.error(e);
			return 0;
		}
	}

	public static int getMaxTemperature() {
		String httpQuery = getURL("get-max", null);

		try {
			String result = _connection.sendHttpRequest(httpQuery);
			return Integer.valueOf(result);
		}
		catch (Exception e) {
			_log.error(e);
			return 0;
		}
	}

	public static int getTemperature() {
		String httpQuery = getURL("temperature", null);

		try {
			String result = _connection.sendHttpRequest(httpQuery);
			return Integer.valueOf(result);
		}
		catch (Exception e) {
			_log.error(e);
			return 0;
		}
	}

	public static void setSettings(int energy, int coolingCycles) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("energy", String.valueOf(energy));
		params.put("cooling-cycles", String.valueOf(coolingCycles));

		String httpQuery = getURL("settings", params);

		try {
			_connection.sendHttpRequest(httpQuery);
		}
		catch (Exception e) {
			_log.error(e);
		}
	}

	private static String getURL(String action, Map<String, String> params) {
		StringBuilder sb = new StringBuilder();

		sb.append(StringPool.SLASH);
		sb.append(PortletPropsValues.TEMPERATURE_ENDPOINT_CONTEXT);

		sb.append(StringPool.SLASH);
		sb.append("controller");

		sb.append(StringPool.QUESTION);
		sb.append("action");
		sb.append(StringPool.EQUAL);
		sb.append(action);

		if (params != null) {
			for (String key : params.keySet()) {
				sb.append(StringPool.AMPERSAND);
				sb.append(key);
				sb.append(StringPool.EQUAL);
				sb.append(params.get(key));
			}
		}

		return sb.toString();
	}

	static {
		_connection = new Connection(
			PortletPropsValues.TEMPERATURE_ENDPOINT_HOST,
			PortletPropsValues.TEMPERATURE_ENDPOINT_PORT);
	}

	private static Connection _connection;
	private static Log _log = LogFactoryUtil.getLog(TemperatureUtil.class);

}
