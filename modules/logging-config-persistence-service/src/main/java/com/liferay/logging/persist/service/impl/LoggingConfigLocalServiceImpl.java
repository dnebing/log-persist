/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.logging.persist.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.logging.persist.model.LoggingConfig;
import com.liferay.logging.persist.service.base.LoggingConfigLocalServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;

/**
 * The implementation of the logging config local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.logging.persist.service.LoggingConfigLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LoggingConfigLocalServiceBaseImpl
 * @see com.liferay.logging.persist.service.LoggingConfigLocalServiceUtil
 */
@ProviderType
public class LoggingConfigLocalServiceImpl
	extends LoggingConfigLocalServiceBaseImpl {

	/**
	 * updateLogger: Updates the named logger.
	 * @param name
	 * @param level
	 */
	public void updateLogger(final String name, final String level) {
		LoggingConfig config = loggingConfigPersistence.fetchByLogger(name);

		if (config == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("Adding a new logger [" + name + "] at level [" + level + "].");
			}

			config = createLoggingConfig(counterLocalService.increment(LoggingConfig.class.getName()));

			config.setLogger(name);
		} else if (_log.isDebugEnabled()) {
			_log.debug("Updating logger [" + name + "] at level [" + level + "].");
		}

		config.setLevel(level);

		updateLoggingConfig(config);
	}

	/**
	 * getLoggingConfigs: Returns all of the logging configs.
	 * @return List The list of logging config instances.
	 */
	public List<LoggingConfig> getLoggingConfigs() {
		return getLoggingConfigs(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	private static final Log _log = LogFactoryUtil.getLog(LoggingConfigLocalServiceImpl.class);
}