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

package com.liferay.logging.persist.model.impl;

import com.liferay.logging.persist.model.LoggingConfig;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing LoggingConfig in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class LoggingConfigCacheModel
	implements CacheModel<LoggingConfig>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof LoggingConfigCacheModel)) {
			return false;
		}

		LoggingConfigCacheModel loggingConfigCacheModel =
			(LoggingConfigCacheModel)object;

		if (logConfigId == loggingConfigCacheModel.logConfigId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, logConfigId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{logConfigId=");
		sb.append(logConfigId);
		sb.append(", logger=");
		sb.append(logger);
		sb.append(", level=");
		sb.append(level);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LoggingConfig toEntityModel() {
		LoggingConfigImpl loggingConfigImpl = new LoggingConfigImpl();

		loggingConfigImpl.setLogConfigId(logConfigId);

		if (logger == null) {
			loggingConfigImpl.setLogger("");
		}
		else {
			loggingConfigImpl.setLogger(logger);
		}

		if (level == null) {
			loggingConfigImpl.setLevel("");
		}
		else {
			loggingConfigImpl.setLevel(level);
		}

		loggingConfigImpl.resetOriginalValues();

		return loggingConfigImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		logConfigId = objectInput.readLong();
		logger = objectInput.readUTF();
		level = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(logConfigId);

		if (logger == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(logger);
		}

		if (level == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(level);
		}
	}

	public long logConfigId;
	public String logger;
	public String level;

}