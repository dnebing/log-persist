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

package com.liferay.logging.persist.service.persistence.impl;

import com.liferay.logging.persist.exception.NoSuchLoggingConfigException;
import com.liferay.logging.persist.model.LoggingConfig;
import com.liferay.logging.persist.model.impl.LoggingConfigImpl;
import com.liferay.logging.persist.model.impl.LoggingConfigModelImpl;
import com.liferay.logging.persist.service.persistence.LoggingConfigPersistence;
import com.liferay.logging.persist.service.persistence.impl.constants.LOGPersistenceConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the logging config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = LoggingConfigPersistence.class)
public class LoggingConfigPersistenceImpl
	extends BasePersistenceImpl<LoggingConfig>
	implements LoggingConfigPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>LoggingConfigUtil</code> to access the logging config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		LoggingConfigImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByLogger;
	private FinderPath _finderPathCountByLogger;

	/**
	 * Returns the logging config where logger = &#63; or throws a <code>NoSuchLoggingConfigException</code> if it could not be found.
	 *
	 * @param logger the logger
	 * @return the matching logging config
	 * @throws NoSuchLoggingConfigException if a matching logging config could not be found
	 */
	@Override
	public LoggingConfig findByLogger(String logger)
		throws NoSuchLoggingConfigException {

		LoggingConfig loggingConfig = fetchByLogger(logger);

		if (loggingConfig == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("logger=");
			sb.append(logger);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchLoggingConfigException(sb.toString());
		}

		return loggingConfig;
	}

	/**
	 * Returns the logging config where logger = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param logger the logger
	 * @return the matching logging config, or <code>null</code> if a matching logging config could not be found
	 */
	@Override
	public LoggingConfig fetchByLogger(String logger) {
		return fetchByLogger(logger, true);
	}

	/**
	 * Returns the logging config where logger = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param logger the logger
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching logging config, or <code>null</code> if a matching logging config could not be found
	 */
	@Override
	public LoggingConfig fetchByLogger(String logger, boolean useFinderCache) {
		logger = Objects.toString(logger, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {logger};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByLogger, finderArgs, this);
		}

		if (result instanceof LoggingConfig) {
			LoggingConfig loggingConfig = (LoggingConfig)result;

			if (!Objects.equals(logger, loggingConfig.getLogger())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_LOGGINGCONFIG_WHERE);

			boolean bindLogger = false;

			if (logger.isEmpty()) {
				sb.append(_FINDER_COLUMN_LOGGER_LOGGER_3);
			}
			else {
				bindLogger = true;

				sb.append(_FINDER_COLUMN_LOGGER_LOGGER_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindLogger) {
					queryPos.add(logger);
				}

				List<LoggingConfig> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByLogger, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {logger};
							}

							_log.warn(
								"LoggingConfigPersistenceImpl.fetchByLogger(String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					LoggingConfig loggingConfig = list.get(0);

					result = loggingConfig;

					cacheResult(loggingConfig);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (LoggingConfig)result;
		}
	}

	/**
	 * Removes the logging config where logger = &#63; from the database.
	 *
	 * @param logger the logger
	 * @return the logging config that was removed
	 */
	@Override
	public LoggingConfig removeByLogger(String logger)
		throws NoSuchLoggingConfigException {

		LoggingConfig loggingConfig = findByLogger(logger);

		return remove(loggingConfig);
	}

	/**
	 * Returns the number of logging configs where logger = &#63;.
	 *
	 * @param logger the logger
	 * @return the number of matching logging configs
	 */
	@Override
	public int countByLogger(String logger) {
		logger = Objects.toString(logger, "");

		FinderPath finderPath = _finderPathCountByLogger;

		Object[] finderArgs = new Object[] {logger};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_LOGGINGCONFIG_WHERE);

			boolean bindLogger = false;

			if (logger.isEmpty()) {
				sb.append(_FINDER_COLUMN_LOGGER_LOGGER_3);
			}
			else {
				bindLogger = true;

				sb.append(_FINDER_COLUMN_LOGGER_LOGGER_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindLogger) {
					queryPos.add(logger);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_LOGGER_LOGGER_2 =
		"loggingConfig.logger = ?";

	private static final String _FINDER_COLUMN_LOGGER_LOGGER_3 =
		"(loggingConfig.logger IS NULL OR loggingConfig.logger = '')";

	public LoggingConfigPersistenceImpl() {
		setModelClass(LoggingConfig.class);

		setModelImplClass(LoggingConfigImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the logging config in the entity cache if it is enabled.
	 *
	 * @param loggingConfig the logging config
	 */
	@Override
	public void cacheResult(LoggingConfig loggingConfig) {
		entityCache.putResult(
			LoggingConfigImpl.class, loggingConfig.getPrimaryKey(),
			loggingConfig);

		finderCache.putResult(
			_finderPathFetchByLogger, new Object[] {loggingConfig.getLogger()},
			loggingConfig);
	}

	/**
	 * Caches the logging configs in the entity cache if it is enabled.
	 *
	 * @param loggingConfigs the logging configs
	 */
	@Override
	public void cacheResult(List<LoggingConfig> loggingConfigs) {
		for (LoggingConfig loggingConfig : loggingConfigs) {
			if (entityCache.getResult(
					LoggingConfigImpl.class, loggingConfig.getPrimaryKey()) ==
						null) {

				cacheResult(loggingConfig);
			}
		}
	}

	/**
	 * Clears the cache for all logging configs.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LoggingConfigImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the logging config.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LoggingConfig loggingConfig) {
		entityCache.removeResult(LoggingConfigImpl.class, loggingConfig);
	}

	@Override
	public void clearCache(List<LoggingConfig> loggingConfigs) {
		for (LoggingConfig loggingConfig : loggingConfigs) {
			entityCache.removeResult(LoggingConfigImpl.class, loggingConfig);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(LoggingConfigImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		LoggingConfigModelImpl loggingConfigModelImpl) {

		Object[] args = new Object[] {loggingConfigModelImpl.getLogger()};

		finderCache.putResult(
			_finderPathCountByLogger, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByLogger, args, loggingConfigModelImpl, false);
	}

	/**
	 * Creates a new logging config with the primary key. Does not add the logging config to the database.
	 *
	 * @param logConfigId the primary key for the new logging config
	 * @return the new logging config
	 */
	@Override
	public LoggingConfig create(long logConfigId) {
		LoggingConfig loggingConfig = new LoggingConfigImpl();

		loggingConfig.setNew(true);
		loggingConfig.setPrimaryKey(logConfigId);

		return loggingConfig;
	}

	/**
	 * Removes the logging config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param logConfigId the primary key of the logging config
	 * @return the logging config that was removed
	 * @throws NoSuchLoggingConfigException if a logging config with the primary key could not be found
	 */
	@Override
	public LoggingConfig remove(long logConfigId)
		throws NoSuchLoggingConfigException {

		return remove((Serializable)logConfigId);
	}

	/**
	 * Removes the logging config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the logging config
	 * @return the logging config that was removed
	 * @throws NoSuchLoggingConfigException if a logging config with the primary key could not be found
	 */
	@Override
	public LoggingConfig remove(Serializable primaryKey)
		throws NoSuchLoggingConfigException {

		Session session = null;

		try {
			session = openSession();

			LoggingConfig loggingConfig = (LoggingConfig)session.get(
				LoggingConfigImpl.class, primaryKey);

			if (loggingConfig == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLoggingConfigException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(loggingConfig);
		}
		catch (NoSuchLoggingConfigException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected LoggingConfig removeImpl(LoggingConfig loggingConfig) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(loggingConfig)) {
				loggingConfig = (LoggingConfig)session.get(
					LoggingConfigImpl.class, loggingConfig.getPrimaryKeyObj());
			}

			if (loggingConfig != null) {
				session.delete(loggingConfig);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (loggingConfig != null) {
			clearCache(loggingConfig);
		}

		return loggingConfig;
	}

	@Override
	public LoggingConfig updateImpl(LoggingConfig loggingConfig) {
		boolean isNew = loggingConfig.isNew();

		if (!(loggingConfig instanceof LoggingConfigModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(loggingConfig.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					loggingConfig);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in loggingConfig proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom LoggingConfig implementation " +
					loggingConfig.getClass());
		}

		LoggingConfigModelImpl loggingConfigModelImpl =
			(LoggingConfigModelImpl)loggingConfig;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(loggingConfig);
			}
			else {
				loggingConfig = (LoggingConfig)session.merge(loggingConfig);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			LoggingConfigImpl.class, loggingConfigModelImpl, false, true);

		cacheUniqueFindersCache(loggingConfigModelImpl);

		if (isNew) {
			loggingConfig.setNew(false);
		}

		loggingConfig.resetOriginalValues();

		return loggingConfig;
	}

	/**
	 * Returns the logging config with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the logging config
	 * @return the logging config
	 * @throws NoSuchLoggingConfigException if a logging config with the primary key could not be found
	 */
	@Override
	public LoggingConfig findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLoggingConfigException {

		LoggingConfig loggingConfig = fetchByPrimaryKey(primaryKey);

		if (loggingConfig == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLoggingConfigException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return loggingConfig;
	}

	/**
	 * Returns the logging config with the primary key or throws a <code>NoSuchLoggingConfigException</code> if it could not be found.
	 *
	 * @param logConfigId the primary key of the logging config
	 * @return the logging config
	 * @throws NoSuchLoggingConfigException if a logging config with the primary key could not be found
	 */
	@Override
	public LoggingConfig findByPrimaryKey(long logConfigId)
		throws NoSuchLoggingConfigException {

		return findByPrimaryKey((Serializable)logConfigId);
	}

	/**
	 * Returns the logging config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param logConfigId the primary key of the logging config
	 * @return the logging config, or <code>null</code> if a logging config with the primary key could not be found
	 */
	@Override
	public LoggingConfig fetchByPrimaryKey(long logConfigId) {
		return fetchByPrimaryKey((Serializable)logConfigId);
	}

	/**
	 * Returns all the logging configs.
	 *
	 * @return the logging configs
	 */
	@Override
	public List<LoggingConfig> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<LoggingConfig> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<LoggingConfig> findAll(
		int start, int end,
		OrderByComparator<LoggingConfig> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<LoggingConfig> findAll(
		int start, int end, OrderByComparator<LoggingConfig> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<LoggingConfig> list = null;

		if (useFinderCache) {
			list = (List<LoggingConfig>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_LOGGINGCONFIG);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_LOGGINGCONFIG;

				sql = sql.concat(LoggingConfigModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<LoggingConfig>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the logging configs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LoggingConfig loggingConfig : findAll()) {
			remove(loggingConfig);
		}
	}

	/**
	 * Returns the number of logging configs.
	 *
	 * @return the number of logging configs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_LOGGINGCONFIG);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "logConfigId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_LOGGINGCONFIG;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return LoggingConfigModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the logging config persistence.
	 */
	@Activate
	public void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_argumentsResolverServiceRegistration = _bundleContext.registerService(
			ArgumentsResolver.class, new LoggingConfigModelArgumentsResolver(),
			MapUtil.singletonDictionary(
				"model.class.name", LoggingConfig.class.getName()));

		_finderPathWithPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathFetchByLogger = _createFinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByLogger",
			new String[] {String.class.getName()}, new String[] {"logger"},
			true);

		_finderPathCountByLogger = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLogger",
			new String[] {String.class.getName()}, new String[] {"logger"},
			false);
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(LoggingConfigImpl.class.getName());

		_argumentsResolverServiceRegistration.unregister();

		for (ServiceRegistration<FinderPath> serviceRegistration :
				_serviceRegistrations) {

			serviceRegistration.unregister();
		}
	}

	@Override
	@Reference(
		target = LOGPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = LOGPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = LOGPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private BundleContext _bundleContext;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_LOGGINGCONFIG =
		"SELECT loggingConfig FROM LoggingConfig loggingConfig";

	private static final String _SQL_SELECT_LOGGINGCONFIG_WHERE =
		"SELECT loggingConfig FROM LoggingConfig loggingConfig WHERE ";

	private static final String _SQL_COUNT_LOGGINGCONFIG =
		"SELECT COUNT(loggingConfig) FROM LoggingConfig loggingConfig";

	private static final String _SQL_COUNT_LOGGINGCONFIG_WHERE =
		"SELECT COUNT(loggingConfig) FROM LoggingConfig loggingConfig WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "loggingConfig.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No LoggingConfig exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No LoggingConfig exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		LoggingConfigPersistenceImpl.class);

	private FinderPath _createFinderPath(
		String cacheName, String methodName, String[] params,
		String[] columnNames, boolean baseModelResult) {

		FinderPath finderPath = new FinderPath(
			cacheName, methodName, params, columnNames, baseModelResult);

		if (!cacheName.equals(FINDER_CLASS_NAME_LIST_WITH_PAGINATION)) {
			_serviceRegistrations.add(
				_bundleContext.registerService(
					FinderPath.class, finderPath,
					MapUtil.singletonDictionary("cache.name", cacheName)));
		}

		return finderPath;
	}

	private Set<ServiceRegistration<FinderPath>> _serviceRegistrations =
		new HashSet<>();
	private ServiceRegistration<ArgumentsResolver>
		_argumentsResolverServiceRegistration;

	private static class LoggingConfigModelArgumentsResolver
		implements ArgumentsResolver {

		@Override
		public Object[] getArguments(
			FinderPath finderPath, BaseModel<?> baseModel, boolean checkColumn,
			boolean original) {

			String[] columnNames = finderPath.getColumnNames();

			if ((columnNames == null) || (columnNames.length == 0)) {
				if (baseModel.isNew()) {
					return FINDER_ARGS_EMPTY;
				}

				return null;
			}

			LoggingConfigModelImpl loggingConfigModelImpl =
				(LoggingConfigModelImpl)baseModel;

			long columnBitmask = loggingConfigModelImpl.getColumnBitmask();

			if (!checkColumn || (columnBitmask == 0)) {
				return _getValue(loggingConfigModelImpl, columnNames, original);
			}

			Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
				finderPath);

			if (finderPathColumnBitmask == null) {
				finderPathColumnBitmask = 0L;

				for (String columnName : columnNames) {
					finderPathColumnBitmask |=
						loggingConfigModelImpl.getColumnBitmask(columnName);
				}

				_finderPathColumnBitmasksCache.put(
					finderPath, finderPathColumnBitmask);
			}

			if ((columnBitmask & finderPathColumnBitmask) != 0) {
				return _getValue(loggingConfigModelImpl, columnNames, original);
			}

			return null;
		}

		private Object[] _getValue(
			LoggingConfigModelImpl loggingConfigModelImpl, String[] columnNames,
			boolean original) {

			Object[] arguments = new Object[columnNames.length];

			for (int i = 0; i < arguments.length; i++) {
				String columnName = columnNames[i];

				if (original) {
					arguments[i] =
						loggingConfigModelImpl.getColumnOriginalValue(
							columnName);
				}
				else {
					arguments[i] = loggingConfigModelImpl.getColumnValue(
						columnName);
				}
			}

			return arguments;
		}

		private static Map<FinderPath, Long> _finderPathColumnBitmasksCache =
			new ConcurrentHashMap<>();

	}

}