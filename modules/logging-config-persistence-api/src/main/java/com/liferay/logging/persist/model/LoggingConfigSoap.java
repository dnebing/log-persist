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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class LoggingConfigSoap implements Serializable {

	public static LoggingConfigSoap toSoapModel(LoggingConfig model) {
		LoggingConfigSoap soapModel = new LoggingConfigSoap();

		soapModel.setLogConfigId(model.getLogConfigId());
		soapModel.setLogger(model.getLogger());
		soapModel.setLevel(model.getLevel());

		return soapModel;
	}

	public static LoggingConfigSoap[] toSoapModels(LoggingConfig[] models) {
		LoggingConfigSoap[] soapModels = new LoggingConfigSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LoggingConfigSoap[][] toSoapModels(LoggingConfig[][] models) {
		LoggingConfigSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LoggingConfigSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LoggingConfigSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LoggingConfigSoap[] toSoapModels(List<LoggingConfig> models) {
		List<LoggingConfigSoap> soapModels = new ArrayList<LoggingConfigSoap>(
			models.size());

		for (LoggingConfig model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LoggingConfigSoap[soapModels.size()]);
	}

	public LoggingConfigSoap() {
	}

	public long getPrimaryKey() {
		return _logConfigId;
	}

	public void setPrimaryKey(long pk) {
		setLogConfigId(pk);
	}

	public long getLogConfigId() {
		return _logConfigId;
	}

	public void setLogConfigId(long logConfigId) {
		_logConfigId = logConfigId;
	}

	public String getLogger() {
		return _logger;
	}

	public void setLogger(String logger) {
		_logger = logger;
	}

	public String getLevel() {
		return _level;
	}

	public void setLevel(String level) {
		_level = level;
	}

	private long _logConfigId;
	private String _logger;
	private String _level;

}