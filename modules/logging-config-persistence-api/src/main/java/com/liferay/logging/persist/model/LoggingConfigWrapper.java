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

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link LoggingConfig}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LoggingConfig
 * @generated
 */
@ProviderType
public class LoggingConfigWrapper implements LoggingConfig,
	ModelWrapper<LoggingConfig> {
	public LoggingConfigWrapper(LoggingConfig loggingConfig) {
		_loggingConfig = loggingConfig;
	}

	@Override
	public Class<?> getModelClass() {
		return LoggingConfig.class;
	}

	@Override
	public String getModelClassName() {
		return LoggingConfig.class.getName();
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

	@Override
	public LoggingConfig toEscapedModel() {
		return new LoggingConfigWrapper(_loggingConfig.toEscapedModel());
	}

	@Override
	public LoggingConfig toUnescapedModel() {
		return new LoggingConfigWrapper(_loggingConfig.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _loggingConfig.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _loggingConfig.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _loggingConfig.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _loggingConfig.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LoggingConfig> toCacheModel() {
		return _loggingConfig.toCacheModel();
	}

	@Override
	public int compareTo(LoggingConfig loggingConfig) {
		return _loggingConfig.compareTo(loggingConfig);
	}

	@Override
	public int hashCode() {
		return _loggingConfig.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _loggingConfig.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new LoggingConfigWrapper((LoggingConfig)_loggingConfig.clone());
	}

	/**
	* Returns the level of this logging config.
	*
	* @return the level of this logging config
	*/
	@Override
	public java.lang.String getLevel() {
		return _loggingConfig.getLevel();
	}

	/**
	* Returns the logger of this logging config.
	*
	* @return the logger of this logging config
	*/
	@Override
	public java.lang.String getLogger() {
		return _loggingConfig.getLogger();
	}

	@Override
	public java.lang.String toString() {
		return _loggingConfig.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _loggingConfig.toXmlString();
	}

	/**
	* Returns the log config ID of this logging config.
	*
	* @return the log config ID of this logging config
	*/
	@Override
	public long getLogConfigId() {
		return _loggingConfig.getLogConfigId();
	}

	/**
	* Returns the primary key of this logging config.
	*
	* @return the primary key of this logging config
	*/
	@Override
	public long getPrimaryKey() {
		return _loggingConfig.getPrimaryKey();
	}

	@Override
	public void persist() {
		_loggingConfig.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_loggingConfig.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_loggingConfig.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_loggingConfig.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_loggingConfig.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the level of this logging config.
	*
	* @param level the level of this logging config
	*/
	@Override
	public void setLevel(java.lang.String level) {
		_loggingConfig.setLevel(level);
	}

	/**
	* Sets the log config ID of this logging config.
	*
	* @param logConfigId the log config ID of this logging config
	*/
	@Override
	public void setLogConfigId(long logConfigId) {
		_loggingConfig.setLogConfigId(logConfigId);
	}

	/**
	* Sets the logger of this logging config.
	*
	* @param logger the logger of this logging config
	*/
	@Override
	public void setLogger(java.lang.String logger) {
		_loggingConfig.setLogger(logger);
	}

	@Override
	public void setNew(boolean n) {
		_loggingConfig.setNew(n);
	}

	/**
	* Sets the primary key of this logging config.
	*
	* @param primaryKey the primary key of this logging config
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_loggingConfig.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_loggingConfig.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoggingConfigWrapper)) {
			return false;
		}

		LoggingConfigWrapper loggingConfigWrapper = (LoggingConfigWrapper)obj;

		if (Objects.equals(_loggingConfig, loggingConfigWrapper._loggingConfig)) {
			return true;
		}

		return false;
	}

	@Override
	public LoggingConfig getWrappedModel() {
		return _loggingConfig;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _loggingConfig.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _loggingConfig.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_loggingConfig.resetOriginalValues();
	}

	private final LoggingConfig _loggingConfig;
}