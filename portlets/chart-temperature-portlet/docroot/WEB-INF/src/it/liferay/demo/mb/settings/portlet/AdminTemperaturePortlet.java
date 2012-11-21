package it.liferay.demo.mb.settings.portlet;

import it.liferay.demo.mb.util.TemperatureUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

public class AdminTemperaturePortlet extends MVCPortlet {

	public void updateSettings(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		int energy= ParamUtil.getInteger(actionRequest, "energy");
		int coolingCycles = ParamUtil.getInteger(
			actionRequest, "coolingCycles");

		if (energy > 0 && coolingCycles > 0) {
			TemperatureUtil.setTemperatureSettings(energy, coolingCycles);
		}
	}

}
