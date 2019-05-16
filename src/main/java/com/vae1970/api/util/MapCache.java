package com.vae1970.api.util;

import lombok.Data;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;

/**
 * map cache(async supplier)
 *
 * @author vae
 * @since JDK1.8
 */
@Data
@ThreadSafe
public class MapCache implements java.io.Serializable {
	/**
	 * cache data
	 */
	private volatile Map<String, Object> dataMap = new HashMap<>();
	/**
	 * data lock
	 */
	private volatile ReentrantReadWriteLock dataLock = new ReentrantReadWriteLock();
	/**
	 * lock for every key
	 */
	private volatile Map<String, ReentrantReadWriteLock> isFirstMap = new HashMap<>();

	/**
	 * if present return cache,otherwise get supplier and return data
	 *
	 * @param key      key
	 * @param supplier supplier
	 * @param <T>      <T>
	 * @return data
	 */
	@SuppressWarnings("unchecked")
	public <T> T getAndSet(String key, Supplier<T> supplier) {
		try {
			dataLock.readLock().lock();
			if (isFirstMap.get(key) == null) {
				synchronized (this) {
					if (isFirstMap.get(key) == null) {
						isFirstMap.put(key, new ReentrantReadWriteLock());
					}
				}
			}
			isFirstMap.get(key).readLock().lock();
			if (dataMap.get(key) == null) {
				isFirstMap.get(key).readLock().unlock();
				isFirstMap.get(key).writeLock().lock();
				if (dataMap.get(key) == null) {
					dataMap.put(key, supplier.get());
				}
				isFirstMap.get(key).readLock().lock();
				isFirstMap.get(key).writeLock().unlock();
			}
			isFirstMap.get(key).readLock().unlock();
			return (T) dataMap.get(key);
		} finally {
			dataLock.readLock().unlock();
		}
	}

}
