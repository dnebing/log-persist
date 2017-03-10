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

package com.liferay.logging.persist.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LoggingConfigLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see LoggingConfigLocalService
 * @generated
 */
@ProviderType
public class LoggingConfigLocalServiceWrapper
	implements LoggingConfigLocalService,
		ServiceWrapper<LoggingConfigLocalService> {
	public LoggingConfigLocalServiceWrapper(
		LoggingConfigLocalService loggingConfigLocalService) {
		_loggingConfigLocalService = loggingConfigLocalService;
	}

	/**
	* Adds the logging config to the database. Also notifies the appropriate model listeners.
	*
	* @param loggingConfig the logging config
	* @return the logging config that was added
	*/
	@Override
	public com.liferay.logging.persist.model.LoggingConfig addLoggingConfig(
		com.liferay.logging.persist.model.LoggingConfig loggingConfig) {
		return _loggingConfigLocalService.addLoggingConfig(loggingConfig);
	}

	/**
	* Creates a new logging config with the primary key. Does not add the logging config to the database.
	*
	* @param logConfigId the primary key for the new logging config
	* @return the new logging config
	*/
	@Override
	public com.liferay.logging.persist.model.LoggingConfig createLoggingConfig(
		long logConfigId) {
		return _loggingConfigLocalService.createLoggingConfig(logConfigId);
	}

	/**
	* Deletes the logging config from the database. Also notifies the appropriate model listeners.
	*
	* @param loggingConfig the logging config
	* @return the logging config that was removed
	*/
	@Override
	public com.liferay.logging.persist.model.LoggingConfig deleteLoggingConfig(
		com.liferay.logging.persist.model.LoggingConfig loggingConfig) {
		return _loggingConfigLocalService.deleteLoggingConfig(loggingConfig);
	}

	/**
	* Deletes the logging config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param logConfigId the primary key of the logging config
	* @return the logging config that was removed
	* @throws PortalException if a logging config with the primary key could not be found
	*/
	@Override
	public com.liferay.logging.persist.model.LoggingConfig deleteLoggingConfig(
		long logConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loggingConfigLocalService.deleteLoggingConfig(logConfigId);
	}

	@Override
	public com.liferay.logging.persist.model.LoggingConfig fetchLoggingConfig(
		long logConfigId) {
		return _loggingConfigLocalService.fetchLoggingConfig(logConfigId);
	}

	/**
	* Returns the logging config with the primary key.
	*
	* @param logConfigId the primary key of the logging config
	* @return the logging config
	* @throws PortalException if a logging config with the primary key could not be found
	*/
	@Override
	public com.liferay.logging.persist.model.LoggingConfig getLoggingConfig(
		long logConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loggingConfigLocalService.getLoggingConfig(logConfigId);
	}

	/**
	* Updates the logging config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param loggingConfig the logging config
	* @return the logging config that was updated
	*/
	@Override
	public com.liferay.logging.persist.model.LoggingConfig updateLoggingConfig(
		com.liferay.logging.persist.model.LoggingConfig loggingConfig) {
		return _loggingConfigLocalService.updateLoggingConfig(loggingConfig);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _loggingConfigLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _loggingConfigLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _loggingConfigLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loggingConfigLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loggingConfigLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of logging configs.
	*
	* @return the number of logging configs
	*/
	@Override
	public int getLoggingConfigsCount() {
		return _loggingConfigLocalService.getLoggingConfigsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _loggingConfigLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _loggingConfigLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.logging.persist.model.impl.LoggingConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _loggingConfigLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.logging.persist.model.impl.LoggingConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _loggingConfigLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* getLoggingConfigs: Returns all of the logging configs.
	*
	* @return List The list of logging config instances.
	*/
	@Override
	public java.util.List<com.liferay.logging.persist.model.LoggingConfig> getLoggingConfigs() {
		return _loggingConfigLocalService.getLoggingConfigs();
	}

	/**
	* Returns a range of all the logging configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.logging.persist.model.impl.LoggingConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of logging configs
	* @param end the upper bound of the range of logging configs (not inclusive)
	* @return the range of logging configs
	*/
	@Override
	public java.util.List<com.liferay.logging.persist.model.LoggingConfig> getLoggingConfigs(
		int start, int end) {
		return _loggingConfigLocalService.getLoggingConfigs(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _loggingConfigLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _loggingConfigLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* updateLogger: Updates the named logger.
	*
	* @param name
	* @param level
	*/
	@Override
	public void updateLogger(java.lang.String name, java.lang.String level) {
		_loggingConfigLocalService.updateLogger(name, level);
	}

	@Override
	public LoggingConfigLocalService getWrappedService() {
		return _loggingConfigLocalService;
	}

	@Override
	public void setWrappedService(
		LoggingConfigLocalService loggingConfigLocalService) {
		_loggingConfigLocalService = loggingConfigLocalService;
	}

	private LoggingConfigLocalService _loggingConfigLocalService;
}