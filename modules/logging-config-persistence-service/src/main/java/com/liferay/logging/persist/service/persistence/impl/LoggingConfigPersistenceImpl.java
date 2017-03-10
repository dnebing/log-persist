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

import aQute.bnd.annotation.ProviderType;

import com.liferay.logging.persist.exception.NoSuchLoggingConfigException;
import com.liferay.logging.persist.model.LoggingConfig;
import com.liferay.logging.persist.model.impl.LoggingConfigImpl;
import com.liferay.logging.persist.model.impl.LoggingConfigModelImpl;
import com.liferay.logging.persist.service.persistence.LoggingConfigPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the logging config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LoggingConfigPersistence
 * @see com.liferay.logging.persist.service.persistence.LoggingConfigUtil
 * @generated
 */
@ProviderType
public class LoggingConfigPersistenceImpl extends BasePersistenceImpl<LoggingConfig>
	implements LoggingConfigPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LoggingConfigUtil} to access the logging config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LoggingConfigImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LoggingConfigModelImpl.ENTITY_CACHE_ENABLED,
			LoggingConfigModelImpl.FINDER_CACHE_ENABLED,
			LoggingConfigImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LoggingConfigModelImpl.ENTITY_CACHE_ENABLED,
			LoggingConfigModelImpl.FINDER_CACHE_ENABLED,
			LoggingConfigImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LoggingConfigModelImpl.ENTITY_CACHE_ENABLED,
			LoggingConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_LOGGER = new FinderPath(LoggingConfigModelImpl.ENTITY_CACHE_ENABLED,
			LoggingConfigModelImpl.FINDER_CACHE_ENABLED,
			LoggingConfigImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByLogger",
			new String[] { String.class.getName() },
			LoggingConfigModelImpl.LOGGER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LOGGER = new FinderPath(LoggingConfigModelImpl.ENTITY_CACHE_ENABLED,
			LoggingConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLogger",
			new String[] { String.class.getName() });

	/**
	 * Returns the logging config where logger = &#63; or throws a {@link NoSuchLoggingConfigException} if it could not be found.
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
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("logger=");
			msg.append(logger);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLoggingConfigException(msg.toString());
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
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching logging config, or <code>null</code> if a matching logging config could not be found
	 */
	@Override
	public LoggingConfig fetchByLogger(String logger, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { logger };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_LOGGER,
					finderArgs, this);
		}

		if (result instanceof LoggingConfig) {
			LoggingConfig loggingConfig = (LoggingConfig)result;

			if (!Objects.equals(logger, loggingConfig.getLogger())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_LOGGINGCONFIG_WHERE);

			boolean bindLogger = false;

			if (logger == null) {
				query.append(_FINDER_COLUMN_LOGGER_LOGGER_1);
			}
			else if (logger.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LOGGER_LOGGER_3);
			}
			else {
				bindLogger = true;

				query.append(_FINDER_COLUMN_LOGGER_LOGGER_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindLogger) {
					qPos.add(logger);
				}

				List<LoggingConfig> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_LOGGER,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"LoggingConfigPersistenceImpl.fetchByLogger(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					LoggingConfig loggingConfig = list.get(0);

					result = loggingConfig;

					cacheResult(loggingConfig);

					if ((loggingConfig.getLogger() == null) ||
							!loggingConfig.getLogger().equals(logger)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_LOGGER,
							finderArgs, loggingConfig);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_LOGGER, finderArgs);

				throw processException(e);
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
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LOGGER;

		Object[] finderArgs = new Object[] { logger };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LOGGINGCONFIG_WHERE);

			boolean bindLogger = false;

			if (logger == null) {
				query.append(_FINDER_COLUMN_LOGGER_LOGGER_1);
			}
			else if (logger.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LOGGER_LOGGER_3);
			}
			else {
				bindLogger = true;

				query.append(_FINDER_COLUMN_LOGGER_LOGGER_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindLogger) {
					qPos.add(logger);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_LOGGER_LOGGER_1 = "loggingConfig.logger IS NULL";
	private static final String _FINDER_COLUMN_LOGGER_LOGGER_2 = "loggingConfig.logger = ?";
	private static final String _FINDER_COLUMN_LOGGER_LOGGER_3 = "(loggingConfig.logger IS NULL OR loggingConfig.logger = '')";

	public LoggingConfigPersistenceImpl() {
		setModelClass(LoggingConfig.class);
	}

	/**
	 * Caches the logging config in the entity cache if it is enabled.
	 *
	 * @param loggingConfig the logging config
	 */
	@Override
	public void cacheResult(LoggingConfig loggingConfig) {
		entityCache.putResult(LoggingConfigModelImpl.ENTITY_CACHE_ENABLED,
			LoggingConfigImpl.class, loggingConfig.getPrimaryKey(),
			loggingConfig);

		finderCache.putResult(FINDER_PATH_FETCH_BY_LOGGER,
			new Object[] { loggingConfig.getLogger() }, loggingConfig);

		loggingConfig.resetOriginalValues();
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
						LoggingConfigModelImpl.ENTITY_CACHE_ENABLED,
						LoggingConfigImpl.class, loggingConfig.getPrimaryKey()) == null) {
				cacheResult(loggingConfig);
			}
			else {
				loggingConfig.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all logging configs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
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
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LoggingConfig loggingConfig) {
		entityCache.removeResult(LoggingConfigModelImpl.ENTITY_CACHE_ENABLED,
			LoggingConfigImpl.class, loggingConfig.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((LoggingConfigModelImpl)loggingConfig, true);
	}

	@Override
	public void clearCache(List<LoggingConfig> loggingConfigs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LoggingConfig loggingConfig : loggingConfigs) {
			entityCache.removeResult(LoggingConfigModelImpl.ENTITY_CACHE_ENABLED,
				LoggingConfigImpl.class, loggingConfig.getPrimaryKey());

			clearUniqueFindersCache((LoggingConfigModelImpl)loggingConfig, true);
		}
	}

	protected void cacheUniqueFindersCache(
		LoggingConfigModelImpl loggingConfigModelImpl) {
		Object[] args = new Object[] { loggingConfigModelImpl.getLogger() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_LOGGER, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_LOGGER, args,
			loggingConfigModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		LoggingConfigModelImpl loggingConfigModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] { loggingConfigModelImpl.getLogger() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_LOGGER, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_LOGGER, args);
		}

		if ((loggingConfigModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_LOGGER.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					loggingConfigModelImpl.getOriginalLogger()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_LOGGER, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_LOGGER, args);
		}
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

			LoggingConfig loggingConfig = (LoggingConfig)session.get(LoggingConfigImpl.class,
					primaryKey);

			if (loggingConfig == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLoggingConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(loggingConfig);
		}
		catch (NoSuchLoggingConfigException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected LoggingConfig removeImpl(LoggingConfig loggingConfig) {
		loggingConfig = toUnwrappedModel(loggingConfig);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(loggingConfig)) {
				loggingConfig = (LoggingConfig)session.get(LoggingConfigImpl.class,
						loggingConfig.getPrimaryKeyObj());
			}

			if (loggingConfig != null) {
				session.delete(loggingConfig);
			}
		}
		catch (Exception e) {
			throw processException(e);
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
		loggingConfig = toUnwrappedModel(loggingConfig);

		boolean isNew = loggingConfig.isNew();

		LoggingConfigModelImpl loggingConfigModelImpl = (LoggingConfigModelImpl)loggingConfig;

		Session session = null;

		try {
			session = openSession();

			if (loggingConfig.isNew()) {
				session.save(loggingConfig);

				loggingConfig.setNew(false);
			}
			else {
				loggingConfig = (LoggingConfig)session.merge(loggingConfig);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !LoggingConfigModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		entityCache.putResult(LoggingConfigModelImpl.ENTITY_CACHE_ENABLED,
			LoggingConfigImpl.class, loggingConfig.getPrimaryKey(),
			loggingConfig, false);

		clearUniqueFindersCache(loggingConfigModelImpl, false);
		cacheUniqueFindersCache(loggingConfigModelImpl);

		loggingConfig.resetOriginalValues();

		return loggingConfig;
	}

	protected LoggingConfig toUnwrappedModel(LoggingConfig loggingConfig) {
		if (loggingConfig instanceof LoggingConfigImpl) {
			return loggingConfig;
		}

		LoggingConfigImpl loggingConfigImpl = new LoggingConfigImpl();

		loggingConfigImpl.setNew(loggingConfig.isNew());
		loggingConfigImpl.setPrimaryKey(loggingConfig.getPrimaryKey());

		loggingConfigImpl.setLogConfigId(loggingConfig.getLogConfigId());
		loggingConfigImpl.setLogger(loggingConfig.getLogger());
		loggingConfigImpl.setLevel(loggingConfig.getLevel());

		return loggingConfigImpl;
	}

	/**
	 * Returns the logging config with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
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

			throw new NoSuchLoggingConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return loggingConfig;
	}

	/**
	 * Returns the logging config with the primary key or throws a {@link NoSuchLoggingConfigException} if it could not be found.
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
	 * @param primaryKey the primary key of the logging config
	 * @return the logging config, or <code>null</code> if a logging config with the primary key could not be found
	 */
	@Override
	public LoggingConfig fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LoggingConfigModelImpl.ENTITY_CACHE_ENABLED,
				LoggingConfigImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LoggingConfig loggingConfig = (LoggingConfig)serializable;

		if (loggingConfig == null) {
			Session session = null;

			try {
				session = openSession();

				loggingConfig = (LoggingConfig)session.get(LoggingConfigImpl.class,
						primaryKey);

				if (loggingConfig != null) {
					cacheResult(loggingConfig);
				}
				else {
					entityCache.putResult(LoggingConfigModelImpl.ENTITY_CACHE_ENABLED,
						LoggingConfigImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LoggingConfigModelImpl.ENTITY_CACHE_ENABLED,
					LoggingConfigImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return loggingConfig;
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

	@Override
	public Map<Serializable, LoggingConfig> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LoggingConfig> map = new HashMap<Serializable, LoggingConfig>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LoggingConfig loggingConfig = fetchByPrimaryKey(primaryKey);

			if (loggingConfig != null) {
				map.put(primaryKey, loggingConfig);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LoggingConfigModelImpl.ENTITY_CACHE_ENABLED,
					LoggingConfigImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LoggingConfig)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LOGGINGCONFIG_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (LoggingConfig loggingConfig : (List<LoggingConfig>)q.list()) {
				map.put(loggingConfig.getPrimaryKeyObj(), loggingConfig);

				cacheResult(loggingConfig);

				uncachedPrimaryKeys.remove(loggingConfig.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LoggingConfigModelImpl.ENTITY_CACHE_ENABLED,
					LoggingConfigImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoggingConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoggingConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of logging configs
	 * @param end the upper bound of the range of logging configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of logging configs
	 */
	@Override
	public List<LoggingConfig> findAll(int start, int end,
		OrderByComparator<LoggingConfig> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<LoggingConfig> findAll(int start, int end,
		OrderByComparator<LoggingConfig> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<LoggingConfig> list = null;

		if (retrieveFromCache) {
			list = (List<LoggingConfig>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LOGGINGCONFIG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LOGGINGCONFIG;

				if (pagination) {
					sql = sql.concat(LoggingConfigModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LoggingConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LoggingConfig>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
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
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LOGGINGCONFIG);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return LoggingConfigModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the logging config persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LoggingConfigImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_LOGGINGCONFIG = "SELECT loggingConfig FROM LoggingConfig loggingConfig";
	private static final String _SQL_SELECT_LOGGINGCONFIG_WHERE_PKS_IN = "SELECT loggingConfig FROM LoggingConfig loggingConfig WHERE logConfigId IN (";
	private static final String _SQL_SELECT_LOGGINGCONFIG_WHERE = "SELECT loggingConfig FROM LoggingConfig loggingConfig WHERE ";
	private static final String _SQL_COUNT_LOGGINGCONFIG = "SELECT COUNT(loggingConfig) FROM LoggingConfig loggingConfig";
	private static final String _SQL_COUNT_LOGGINGCONFIG_WHERE = "SELECT COUNT(loggingConfig) FROM LoggingConfig loggingConfig WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "loggingConfig.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LoggingConfig exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LoggingConfig exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LoggingConfigPersistenceImpl.class);
}