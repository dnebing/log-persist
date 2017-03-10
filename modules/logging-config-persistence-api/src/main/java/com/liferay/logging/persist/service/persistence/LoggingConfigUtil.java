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

import aQute.bnd.annotation.ProviderType;

import com.liferay.logging.persist.model.LoggingConfig;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the logging config service. This utility wraps {@link com.liferay.logging.persist.service.persistence.impl.LoggingConfigPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LoggingConfigPersistence
 * @see com.liferay.logging.persist.service.persistence.impl.LoggingConfigPersistenceImpl
 * @generated
 */
@ProviderType
public class LoggingConfigUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(LoggingConfig loggingConfig) {
		getPersistence().clearCache(loggingConfig);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<LoggingConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LoggingConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LoggingConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LoggingConfig> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LoggingConfig update(LoggingConfig loggingConfig) {
		return getPersistence().update(loggingConfig);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LoggingConfig update(LoggingConfig loggingConfig,
		ServiceContext serviceContext) {
		return getPersistence().update(loggingConfig, serviceContext);
	}

	/**
	* Returns the logging config where logger = &#63; or throws a {@link NoSuchLoggingConfigException} if it could not be found.
	*
	* @param logger the logger
	* @return the matching logging config
	* @throws NoSuchLoggingConfigException if a matching logging config could not be found
	*/
	public static LoggingConfig findByLogger(java.lang.String logger)
		throws com.liferay.logging.persist.exception.NoSuchLoggingConfigException {
		return getPersistence().findByLogger(logger);
	}

	/**
	* Returns the logging config where logger = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param logger the logger
	* @return the matching logging config, or <code>null</code> if a matching logging config could not be found
	*/
	public static LoggingConfig fetchByLogger(java.lang.String logger) {
		return getPersistence().fetchByLogger(logger);
	}

	/**
	* Returns the logging config where logger = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param logger the logger
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching logging config, or <code>null</code> if a matching logging config could not be found
	*/
	public static LoggingConfig fetchByLogger(java.lang.String logger,
		boolean retrieveFromCache) {
		return getPersistence().fetchByLogger(logger, retrieveFromCache);
	}

	/**
	* Removes the logging config where logger = &#63; from the database.
	*
	* @param logger the logger
	* @return the logging config that was removed
	*/
	public static LoggingConfig removeByLogger(java.lang.String logger)
		throws com.liferay.logging.persist.exception.NoSuchLoggingConfigException {
		return getPersistence().removeByLogger(logger);
	}

	/**
	* Returns the number of logging configs where logger = &#63;.
	*
	* @param logger the logger
	* @return the number of matching logging configs
	*/
	public static int countByLogger(java.lang.String logger) {
		return getPersistence().countByLogger(logger);
	}

	/**
	* Caches the logging config in the entity cache if it is enabled.
	*
	* @param loggingConfig the logging config
	*/
	public static void cacheResult(LoggingConfig loggingConfig) {
		getPersistence().cacheResult(loggingConfig);
	}

	/**
	* Caches the logging configs in the entity cache if it is enabled.
	*
	* @param loggingConfigs the logging configs
	*/
	public static void cacheResult(List<LoggingConfig> loggingConfigs) {
		getPersistence().cacheResult(loggingConfigs);
	}

	/**
	* Creates a new logging config with the primary key. Does not add the logging config to the database.
	*
	* @param logConfigId the primary key for the new logging config
	* @return the new logging config
	*/
	public static LoggingConfig create(long logConfigId) {
		return getPersistence().create(logConfigId);
	}

	/**
	* Removes the logging config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param logConfigId the primary key of the logging config
	* @return the logging config that was removed
	* @throws NoSuchLoggingConfigException if a logging config with the primary key could not be found
	*/
	public static LoggingConfig remove(long logConfigId)
		throws com.liferay.logging.persist.exception.NoSuchLoggingConfigException {
		return getPersistence().remove(logConfigId);
	}

	public static LoggingConfig updateImpl(LoggingConfig loggingConfig) {
		return getPersistence().updateImpl(loggingConfig);
	}

	/**
	* Returns the logging config with the primary key or throws a {@link NoSuchLoggingConfigException} if it could not be found.
	*
	* @param logConfigId the primary key of the logging config
	* @return the logging config
	* @throws NoSuchLoggingConfigException if a logging config with the primary key could not be found
	*/
	public static LoggingConfig findByPrimaryKey(long logConfigId)
		throws com.liferay.logging.persist.exception.NoSuchLoggingConfigException {
		return getPersistence().findByPrimaryKey(logConfigId);
	}

	/**
	* Returns the logging config with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param logConfigId the primary key of the logging config
	* @return the logging config, or <code>null</code> if a logging config with the primary key could not be found
	*/
	public static LoggingConfig fetchByPrimaryKey(long logConfigId) {
		return getPersistence().fetchByPrimaryKey(logConfigId);
	}

	public static java.util.Map<java.io.Serializable, LoggingConfig> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the logging configs.
	*
	* @return the logging configs
	*/
	public static List<LoggingConfig> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the logging configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoggingConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of logging configs
	* @param end the upper bound of the range of logging configs (not inclusive)
	* @return the range of logging configs
	*/
	public static List<LoggingConfig> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the logging configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoggingConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of logging configs
	* @param end the upper bound of the range of logging configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of logging configs
	*/
	public static List<LoggingConfig> findAll(int start, int end,
		OrderByComparator<LoggingConfig> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the logging configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoggingConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of logging configs
	* @param end the upper bound of the range of logging configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of logging configs
	*/
	public static List<LoggingConfig> findAll(int start, int end,
		OrderByComparator<LoggingConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the logging configs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of logging configs.
	*
	* @return the number of logging configs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LoggingConfigPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LoggingConfigPersistence, LoggingConfigPersistence> _serviceTracker =
		ServiceTrackerFactory.open(LoggingConfigPersistence.class);
}