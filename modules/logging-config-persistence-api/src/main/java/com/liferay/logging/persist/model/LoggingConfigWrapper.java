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

package com.liferay.logging.persist.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link LoggingConfig}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LoggingConfig
 * @generated
 */
public class LoggingConfigWrapper
	extends BaseModelWrapper<LoggingConfig>
	implements LoggingConfig, ModelWrapper<LoggingConfig> {

	public LoggingConfigWrapper(LoggingConfig loggingConfig) {
		super(loggingConfig);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("logConfigId", getLogConfigId());
		attributes.put("logger", getLogger());
		attributes.put("level", getLevel());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long logConfigId = (Long)attributes.get("logConfigId");

		if (logConfigId != null) {
			setLogConfigId(logConfigId);
		}

		String logger = (String)attributes.get("logger");

		if (logger != null) {
			setLogger(logger);
		}

		String level = (String)attributes.get("level");

		if (level != null) {
			setLevel(level);
		}
	}

	/**
	 * Returns the level of this logging config.
	 *
	 * @return the level of this logging config
	 */
	@Override
	public String getLevel() {
		return model.getLevel();
	}

	/**
	 * Returns the log config ID of this logging config.
	 *
	 * @return the log config ID of this logging config
	 */
	@Override
	public long getLogConfigId() {
		return model.getLogConfigId();
	}

	/**
	 * Returns the logger of this logging config.
	 *
	 * @return the logger of this logging config
	 */
	@Override
	public String getLogger() {
		return model.getLogger();
	}

	/**
	 * Returns the primary key of this logging config.
	 *
	 * @return the primary key of this logging config
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the level of this logging config.
	 *
	 * @param level the level of this logging config
	 */
	@Override
	public void setLevel(String level) {
		model.setLevel(level);
	}

	/**
	 * Sets the log config ID of this logging config.
	 *
	 * @param logConfigId the log config ID of this logging config
	 */
	@Override
	public void setLogConfigId(long logConfigId) {
		model.setLogConfigId(logConfigId);
	}

	/**
	 * Sets the logger of this logging config.
	 *
	 * @param logger the logger of this logging config
	 */
	@Override
	public void setLogger(String logger) {
		model.setLogger(logger);
	}

	/**
	 * Sets the primary key of this logging config.
	 *
	 * @param primaryKey the primary key of this logging config
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected LoggingConfigWrapper wrap(LoggingConfig loggingConfig) {
		return new LoggingConfigWrapper(loggingConfig);
	}

}