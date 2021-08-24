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

package com.liferay.logging.persist.service.persistence;

import com.liferay.logging.persist.exception.NoSuchLoggingConfigException;
import com.liferay.logging.persist.model.LoggingConfig;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the logging config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LoggingConfigUtil
 * @generated
 */
@ProviderType
public interface LoggingConfigPersistence
	extends BasePersistence<LoggingConfig> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LoggingConfigUtil} to access the logging config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the logging config where logger = &#63; or throws a <code>NoSuchLoggingConfigException</code> if it could not be found.
	 *
	 * @param logger the logger
	 * @return the matching logging config
	 * @throws NoSuchLoggingConfigException if a matching logging config could not be found
	 */
	public LoggingConfig findByLogger(String logger)
		throws NoSuchLoggingConfigException;

	/**
	 * Returns the logging config where logger = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param logger the logger
	 * @return the matching logging config, or <code>null</code> if a matching logging config could not be found
	 */
	public LoggingConfig fetchByLogger(String logger);

	/**
	 * Returns the logging config where logger = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param logger the logger
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching logging config, or <code>null</code> if a matching logging config could not be found
	 */
	public LoggingConfig fetchByLogger(String logger, boolean useFinderCache);

	/**
	 * Removes the logging config where logger = &#63; from the database.
	 *
	 * @param logger the logger
	 * @return the logging config that was removed
	 */
	public LoggingConfig removeByLogger(String logger)
		throws NoSuchLoggingConfigException;

	/**
	 * Returns the number of logging configs where logger = &#63;.
	 *
	 * @param logger the logger
	 * @return the number of matching logging configs
	 */
	public int countByLogger(String logger);

	/**
	 * Caches the logging config in the entity cache if it is enabled.
	 *
	 * @param loggingConfig the logging config
	 */
	public void cacheResult(LoggingConfig loggingConfig);

	/**
	 * Caches the logging configs in the entity cache if it is enabled.
	 *
	 * @param loggingConfigs the logging configs
	 */
	public void cacheResult(java.util.List<LoggingConfig> loggingConfigs);

	/**
	 * Creates a new logging config with the primary key. Does not add the logging config to the database.
	 *
	 * @param logConfigId the primary key for the new logging config
	 * @return the new logging config
	 */
	public LoggingConfig create(long logConfigId);

	/**
	 * Removes the logging config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param logConfigId the primary key of the logging config
	 * @return the logging config that was removed
	 * @throws NoSuchLoggingConfigException if a logging config with the primary key could not be found
	 */
	public LoggingConfig remove(long logConfigId)
		throws NoSuchLoggingConfigException;

	public LoggingConfig updateImpl(LoggingConfig loggingConfig);

	/**
	 * Returns the logging config with the primary key or throws a <code>NoSuchLoggingConfigException</code> if it could not be found.
	 *
	 * @param logConfigId the primary key of the logging config
	 * @return the logging config
	 * @throws NoSuchLoggingConfigException if a logging config with the primary key could not be found
	 */
	public LoggingConfig findByPrimaryKey(long logConfigId)
		throws NoSuchLoggingConfigException;

	/**
	 * Returns the logging config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param logConfigId the primary key of the logging config
	 * @return the logging config, or <code>null</code> if a logging config with the primary key could not be found
	 */
	public LoggingConfig fetchByPrimaryKey(long logConfigId);

	/**
	 * Returns all the logging configs.
	 *
	 * @return the logging configs
	 */
	public java.util.List<LoggingConfig> findAll();

	/**
	 * Returns a range of all the logging configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoggingConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of logging configs
	 * @param end the upper bound of the range of logging configs (not inclusive)
	 * @return the range of logging configs
	 */
	public java.util.List<LoggingConfig> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the logging configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoggingConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of logging configs
	 * @param end the upper bound of the range of logging configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of logging configs
	 */
	public java.util.List<LoggingConfig> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoggingConfig>
			orderByComparator);

	/**
	 * Returns an ordered range of all the logging configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoggingConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of logging configs
	 * @param end the upper bound of the range of logging configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of logging configs
	 */
	public java.util.List<LoggingConfig> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoggingConfig>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the logging configs from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of logging configs.
	 *
	 * @return the number of logging configs
	 */
	public int countAll();

}