<%@ include file="/init.jsp" %>

<div class="chart-area">
</div>

<!-- Quick legend, very quick e temporally code :( -->
<span class="legend" style="float: right; margin-right: 5%; margin-top: -480px;">
	<table style="border: solid 1px gray; padding: 5px;">
		<tr>
			<td style="padding: 7px;">
				<div class="color-box" style="background-color:#6084D0; border:solid 1px #426AB3; height: 7px; width: 7px;"> </div>
			</td>
			<td>
				Max core temperature
			</td>
		</tr>
		<tr>
			<td style="padding: 7px;">
				<div class="color-box" style="background-color:#EEB647; border:solid 1px #B38206; height: 7px; width: 7px;"> </div>
			</td>
			<td>
				Current temperature
			</td>
		</tr>
	</table>
</span>

<%
JSONObject data = JSONUtil.getTemperatureToJSON();
%>

<aui:script use="charts,charts-legend,aui-base,liferay-poller">
var SESSION_EXPIRED = 'sessionExpired',
	portletId = "<%= portletDisplay.getId()  %>";

var data = [ <%= data.toString() %>];
var chart = new A.Chart(
	{
		dataProvider:data,
		render:".chart-temperature-portlet .chart-area",
		styles: {
			axes: {
				date: {
					label: {
						rotation: -45
					}
				}
			}
		},
		horizontalGridlines: {
			styles: {
				line: {
					color: "#dad8c9"
				}
			}
		},
		legend: {
			position: "right",
			width: 300,
			height: 300,
			styles: {
				hAlign: "center",
				hSpacing: 4
			}
		},
		categoryKey:"date"
	}
);


A.on(
	'domready',
	function() {

		var fun = function(response, chunkId) {
			console.log("RECEIVE RESPONSE: " + JSON.stringify(response));

			while (data.length > 40) {
				data.shift();
			}
			data.push(response.entry);

			chart.set("dataProvider", data);
		}

		Liferay.Poller.addListener(portletId, fun, this);
	}
);

Liferay.bind(
	SESSION_EXPIRED,
	function(event) {
		Liferay.Poller.removeListener(portletId);
	}
);
</aui:script>