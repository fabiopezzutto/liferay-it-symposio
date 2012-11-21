package it.liferay.demo.mb.messaging;

import it.liferay.demo.mb.util.PortletPropsValues;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.util.Date;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;

public class SettingsLoggerMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {

		File file = new File(PortletPropsValues.LOG_FILE_PATH);

		int energy = message.getInteger("energy");
		int coolingCycles = message.getInteger("coolingCycles");

		Date now = new Date();
		DateFormat formatter = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
		String txt = MessageFormat.format(
			"\t\n{0}\t\t Energy:{1}\t\t Cooling Cycles:{2}",
			formatter.format(now), energy, coolingCycles);

		try {
			FileWriter writer = new FileWriter(file, true);
			BufferedWriter out = new BufferedWriter(writer);
			out.write(txt);
			out.close();
		} catch (IOException e) {
		}
	}

}
