<%@ include file="/init.jsp" %>

<%
int[] settings = TemperatureUtil.getTemperatureSettings();
%>

<div>
	<liferay-portlet:actionURL name="updateSettings" var="updateSettingsURL" />

	<aui:column columnWidth="60">

		<aui:form action="<%= updateSettingsURL %>" name="fm" onSubmit='<%= "event.preventDefault();" %>'>
			<aui:fieldset>
				<aui:input name="energy" value="<%= settings[0] %>" />

				<aui:select name="coolingCycles" >
					<%
					for (int i=2; i < 21; i++) {
					%>
						<aui:option label='<%= LanguageUtil.format(pageContext, "x-per-minute", i) %>' selected="<%= settings[1] == i %>" value="<%= i %>" />
					<%
					}
					%>
				</aui:select>
			</aui:fieldset>

			<aui:button-row>
				<aui:button name="save" onClick='<%= renderResponse.getNamespace() + "saveSettings();" %>' type="submit" value="save" />
			</aui:button-row>

		</aui:form>
	</aui:column>

	<aui:column columnWidth="40">
		<%
		String iconPath = request.getContextPath() + "/images/settings.png";
		%>
		<liferay-ui:icon
			message="settings"
			src="<%= iconPath %>"
		/>
	</aui:column>

</div>
<div style="clear:both;"><!--  --></div>


<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />saveSettings',
		function() {
			var A = AUI();

			A.io.request(
				'<%= updateSettingsURL %>',
				{
					form: {
						id: '<portlet:namespace />fm'
					},
					method: 'POST'
				}
			);
		},
		['aui-io-request']
	);
</aui:script>