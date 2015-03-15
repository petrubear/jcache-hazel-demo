package emg.demo.jcache;

import java.util.List;

import org.apache.log4j.Logger;

import com.hazelcast.cache.ICache;
import com.hazelcast.cache.impl.HazelcastServerCacheManager;
import com.hazelcast.cache.impl.HazelcastServerCachingProvider;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

/**
 * Created by edison on 14/3/15. Basada en la clase FisaCacheManager
 */
public class HazelcastProvider {
	private static HazelcastProvider instance;
	private HazelcastInstance hzInstance;
	private HazelcastServerCachingProvider cachingProvider;
	private HazelcastServerCacheManager cacheManager;
	private ICache<Object, Object> cache;
	private final static Logger logger = Logger
			.getLogger(HazelcastProvider.class);

	private HazelcastProvider() {
		hzInstance = Hazelcast.newHazelcastInstance();
		// Retrieve the CachingProvider
		cachingProvider = HazelcastServerCachingProvider
				.createCachingProvider(hzInstance);
		// Create a CacheManager
		cacheManager = (HazelcastServerCacheManager) cachingProvider
				.getCacheManager();

		cache = cacheManager.getCache("fisaCache");
	}

	public static HazelcastProvider getInstance() {
		if (instance == null) {
			instance = new HazelcastProvider();
		}
		return instance;
	}

	public HazelcastInstance getHazelcastInstance() {
		return hzInstance;
	}

	public Object getCachedObject(Object keyObject) {
		return cache.get(keyObject);
	}

	public void putObjectInCache(Object keyObject, Object valueObject) {
		if (valueObject == null) {
			// la excepcion es muy cara prefieo mostrar warning
			// throw new
			// NullPointerException("valueObject no debe ser null en cache.putObjectInCache");
			logger.error("valueObject no debe ser null en cache.putObjectInCache");
			return;
		}
		if (keyObject == null) {
			logger.error("keyObject no debe ser null en cache.putObjectInCache");
			return;
		}
		cache.put(keyObject, valueObject);
	}

	public List<Object> getKeys() {
		// TODO: implementar esto!
		return null;
	}

	public void removeAll() {
		cache.removeAll();
	}

	public void removeRMIAll() {
		removeAll();
	}

	public boolean removeCachedObject(Object keyObject) {
		return cache.remove(keyObject);
	}

	public void removeRMICachedObject(Object keyObject) {
		removeCachedObject(keyObject);
	}

	public boolean isCacheAlive() {
		// TODO: implementar esto!
		return true;
	}

	public void updateCacheServersDistributed(Object key) {
		// TODO: se debe implementar esto??
	}

	public void shutDown() {
		hzInstance.shutdown();
	}
}
