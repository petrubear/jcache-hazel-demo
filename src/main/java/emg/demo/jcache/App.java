package emg.demo.jcache;

import org.apache.log4j.Logger;

import com.hazelcast.cache.ICache;
import com.hazelcast.cache.impl.HazelcastServerCacheManager;
import com.hazelcast.cache.impl.HazelcastServerCachingProvider;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class App {
	private final static Logger logger = Logger.getLogger(App.class);

	public static void main(String[] args) {
		HazelcastProvider cache = HazelcastProvider.getInstance();

		// Put a value into the cache
		cache.putObjectInCache("world", "hello!");

		// Retrieve the value again from the cache
		String value = (String) cache.getCachedObject("world");

		// Print the value 'Hello World'
		if (logger.isInfoEnabled())
			logger.info(value);

		cache.putObjectInCache("world", "hello world!!");
		value = (String) cache.getCachedObject("world");
		if (logger.isInfoEnabled())
			logger.info(value);

		// pruebas Obj
		cache.putObjectInCache(1, 2);
		cache.putObjectInCache(10.2, 2.10);

		if (logger.isInfoEnabled()) {
			logger.info(cache.getCachedObject(1));
			logger.info(cache.getCachedObject(10.2));
		}

		// pruebas de valores null
		cache.putObjectInCache("nullTest", null);
		if (logger.isInfoEnabled())
			logger.info("nullTest: " + cache.getCachedObject("nullTest"));
		String notFound = (String) cache.getCachedObject("notFound");
		if (logger.isInfoEnabled())
			logger.info("notFound: " + notFound);

		int index = 0;
		int sleepTime = 10;
		String key = null;
		String val = null;
		boolean stopServer = false;
		while (index < 2000) {
			key = "Item" + index;
			val = "Value" + index;
			cache.putObjectInCache(key, val);
			index++;
			if (logger.isDebugEnabled())
				logger.debug(index);
			if (index % 2 == 0) {
				cache.getCachedObject(key);
				if (index % 5 == 0) {
					cache.removeCachedObject(key);
				}
			}
			try {
				Thread.sleep(sleepTime);
			} catch (Exception ex) {
			}
		}
		if (logger.isInfoEnabled())
			logger.info("done");
		if (stopServer)
			cache.shutDown();
	}
}
