package com.liferay.logging.persist.filter;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.filter.ActionFilter;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.PortletFilter;

import com.liferay.logging.persist.model.LoggingConfig;
import com.liferay.logging.persist.service.LoggingConfigLocalService;
import com.liferay.petra.log4j.Log4JUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.Level;

/**
 * class LoggingConfigPersister: This is a portlet filter implementation to attach to what the server administration
 * portlet is doing for log management, but it will save log changes to the DB.
 *
 * It also has an activate method so when the component starts it will retrieve the list of log settings from the database
 * and will invoke the Liferay log4j utilities to force the change.
 *
 * @author dnebinger
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=com_liferay_server_admin_web_portlet_ServerAdminPortlet"
	},
	service = PortletFilter.class
)
public class LoggingConfigPersister implements ActionFilter {

	/**
	 * doFilter: Handles the filter logic, this doesn't change a request but it will persist changes to the
	 * database.
	 *
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws PortletException
	 */
	@Override
	public void doFilter(ActionRequest request, ActionResponse response, FilterChain chain) throws IOException, PortletException {

		// pull out some stuff
		String cmd = ParamUtil.getString(request, Constants.CMD);

		// let the chain do it's thing
		chain.doFilter(request, response);

		// persist the logger details.
		if (cmd.equals("addLogLevel")) {
			addLogLevel(request);
		}
		else if (cmd.equals("updateLogLevels")) {
			updateLogLevels(request);
		}

	}

	/**
	 * addLogLevel: Called to add the log level to the database.
	 * @param actionRequest
	 */
	protected void addLogLevel(ActionRequest actionRequest) {
		// extract the values from the incoming request
		String loggerName = ParamUtil.getString(actionRequest, "loggerName");
		String priority = ParamUtil.getString(actionRequest, "priority");

		// persist the log data to the database.
		_loggingConfigLocalService.updateLogger(loggerName, priority);
	}

	/**
	 * updateLogLevels: Updates all log levels in the database.
	 * @param actionRequest
	 */
	protected void updateLogLevels(ActionRequest actionRequest) {

		// fetch the enumeration of parameters on the request
		Enumeration<String> enu = actionRequest.getParameterNames();

		// for each parameter name
		while (enu.hasMoreElements()) {
			// extract the name
			String name = enu.nextElement();

			// if the parm name starts with logLevel it is one of the incoming changes
			if (name.startsWith("logLevel")) {
				// extract the logger name from the parameter name
				String loggerName = name.substring(8);

				// extract the priority from the request
				String priority = ParamUtil.getString(
					actionRequest, name, Level.INFO.toString());

				// update the logger details in the db
				_loggingConfigLocalService.updateLogger(loggerName, priority);
			}
		}
	}

	/**
	 * activate: Triggered when the component has been activated.
	 */
	@Activate
	protected void activate() {
		// fetch all of the stored logging configs
		List<LoggingConfig> configs = _loggingConfigLocalService.getLoggingConfigs();

		// if there are no configs, just return
		if ((configs == null) || (configs.isEmpty())) {
			return;
		}

		// for each config record we find
		for (LoggingConfig config : configs) {
			if (_log.isDebugEnabled()) {
				_log.debug("Restoring logger [" + config.getLogger() + "] to level [" + config.getLevel() + "].");
			}

			// set the level for the logger
			Log4JUtil.setLevel(config.getLogger(), config.getLevel(), true);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws PortletException {
		// does nothing
	}

	@Override
	public void destroy() {
		// does nothing
	}

	@Reference(unbind = "-")
	protected void setLoggingConfigLocalService(final LoggingConfigLocalService loggingConfigLocalService) {
		_loggingConfigLocalService = loggingConfigLocalService;
	}

	private LoggingConfigLocalService _loggingConfigLocalService;

	private static final Log _log = LogFactoryUtil.getLog(LoggingConfigPersister.class);
}