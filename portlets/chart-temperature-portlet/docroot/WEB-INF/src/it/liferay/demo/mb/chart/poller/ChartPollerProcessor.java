package it.liferay.demo.mb.chart.poller;

import it.liferay.demo.mb.chart.util.JSONUtil;

import com.liferay.portal.kernel.poller.BasePollerProcessor;
import com.liferay.portal.kernel.poller.PollerRequest;
import com.liferay.portal.kernel.poller.PollerResponse;

public class ChartPollerProcessor extends BasePollerProcessor {

	@Override
	protected void doReceive(PollerRequest pollerRequest,
			PollerResponse pollerResponse) throws Exception {

		pollerResponse.setParameter(
			"entry", JSONUtil.getTemperatureToJSON());

		pollerResponse.setParameter(
			PollerResponse.POLLER_HINT_HIGH_CONNECTIVITY,
			Boolean.TRUE.toString());
	}

	@Override
	protected void doSend(PollerRequest pollerRequest) throws Exception {
		return;
	}

}
