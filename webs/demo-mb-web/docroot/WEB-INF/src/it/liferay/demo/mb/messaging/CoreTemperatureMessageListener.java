package it.liferay.demo.mb.messaging;

import it.liferay.demo.mb.endpoint.util.TemperatureUtil;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.util.Constants;

public class CoreTemperatureMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {

		String cmd = message.getString(Constants.CMD);

		Object result = null;

		if (cmd.equals("temperature")) {
			result = TemperatureUtil.getTemperature();
		}
		else if (cmd.equals("max-temperature")) {

			result = TemperatureUtil.getMaxTemperature();

		} else if (cmd.equals("get-settings")) {

			result = doGetSettings();

		}

		Message response = MessageBusUtil.createResponseMessage(message);
		response.setPayload(result);

		MessageBusUtil.sendMessage(response.getDestinationName(), response);
	}

	protected JSONObject doGetSettings() {

		JSONObject settings = JSONFactoryUtil.createJSONObject();
		settings.put("energy", TemperatureUtil.getEnergySettings());
		settings.put(
			"coolingCycles", TemperatureUtil.getCoolingCyclesSettings());

		return settings;
	}
}
