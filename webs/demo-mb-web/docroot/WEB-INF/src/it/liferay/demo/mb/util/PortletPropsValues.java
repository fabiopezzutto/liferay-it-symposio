package it.liferay.demo.mb.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

public class PortletPropsValues {

	public static String TEMPERATURE_ENDPOINT_CONTEXT =
		PortletProps.get(PortletPropsKeys.TEMPERATURE_ENDPOINT_CONTEXT);

	public static String TEMPERATURE_ENDPOINT_HOST =
		PortletProps.get(PortletPropsKeys.TEMPERATURE_ENDPOINT_HOST);

	public static int TEMPERATURE_ENDPOINT_PORT =
		GetterUtil.getInteger(
			PortletProps.get(PortletPropsKeys.TEMPERATURE_ENDPOINT_PORT));

}
