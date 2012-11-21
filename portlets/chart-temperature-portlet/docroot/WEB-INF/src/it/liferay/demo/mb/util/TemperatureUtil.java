package it.liferay.demo.mb.util;

import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.util.Constants;

public class TemperatureUtil {

	public static int getTemperature() {

		Message message = new Message();
		message.setResponseDestinationName("core/temperature/response");
		message.put(Constants.CMD, "temperature");
		Object response = null;

		try {
			response = MessageBusUtil.sendSynchronousMessage(
				"core/temperature", message);
			return (Integer)response;
		}
		catch (Exception e) {
			_log.error(e);
			return 0;
		}
	}

	public static int getMaxTemperatureThreshold() {

		PortalCache cache =
			MultiVMPoolUtil.getCache(TemperatureUtil.class.getName());

		Object response = cache.get("max-temperature");

		if (response == null) {
			Message message = new Message();
			message.put(Constants.CMD, "max-temperature");
			message.setResponseDestinationName("core/temperature/response");

			try {
				response = MessageBusUtil.sendSynchronousMessage(
					"core/temperature", message);

				cache.put("max-temperature", response);
			}
			catch (Exception e) {
				_log.error(e);
				return 0;
			}
		}

		return (Integer)response;
	}

	public static int[] getTemperatureSettings() {

		Message message = new Message();
		message.setResponseDestinationName("core/temperature/response");
		message.put(Constants.CMD, "get-settings");

		int[] settings = new int[2];

		try {
			JSONObject response =
				(JSONObject)MessageBusUtil.sendSynchronousMessage(
					"core/temperature", message);

			settings[0] = response.getInt("energy");
			settings[1] = response.getInt("coolingCycles");
		}
		catch (Exception e) {
			_log.error(e);
		}

		return settings;

	}

	public static void setTemperatureSettings(int energy, int coolingCycles) {

		Message message = new Message();
		message.put(Constants.CMD, "set-settings");
		message.put("energy", energy);
		message.put("coolingCycles", coolingCycles);

		try {
			MessageBusUtil.sendMessage("core/settings", message);
		}
		catch (Exception e) {
			_log.error(e);
		}

	}

	private static Log _log = LogFactoryUtil.getLog(TemperatureUtil.class);

}
