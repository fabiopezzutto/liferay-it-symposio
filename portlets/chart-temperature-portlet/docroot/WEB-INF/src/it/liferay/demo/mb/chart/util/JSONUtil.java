package it.liferay.demo.mb.chart.util;

import it.liferay.demo.mb.util.TemperatureUtil;

import java.text.DateFormat;
import java.util.Date;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;

public class JSONUtil {

	public static JSONObject getTemperatureToJSON() {

		int temperature = TemperatureUtil.getTemperature();

		Date now = new Date();
		DateFormat format = DateFormatFactoryUtil.getSimpleDateFormat(
			"HH:mm:ss");

		JSONObject entry = JSONFactoryUtil.createJSONObject();
		entry.put("date", format.format(now));
		entry.put("value", temperature);
		entry.put("max", TemperatureUtil.getMaxTemperatureThreshold());

		return entry;
	}

}
