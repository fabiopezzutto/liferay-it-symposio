package it.liferay.demo.mb.messaging;

import it.liferay.demo.mb.endpoint.util.TemperatureUtil;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

public class SettingsTemperatureMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {

		int energy = message.getInteger("energy");
		int coolingCycles = message.getInteger("coolingCycles");

		TemperatureUtil.setSettings(energy, coolingCycles);
	}

}
