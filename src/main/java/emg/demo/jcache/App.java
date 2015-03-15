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
		// instance.shutdown();
		/*
		 * int index = 0; String key = null; String val = null; while (true) {
		 * key = "Item" + index; val = "Value" + index; cache.put(key, val);
		 * index++; System.out.println(index); if (index % 2 == 0) {
		 * cache.get(key); if (index % 5 == 0) { cache.remove(key); } } try {
		 * Thread.sleep(2000); } catch (Exception ex) { } }
		 */
		
		//pruebas de valores null
		cache.putObjectInCache("nullTest", null);
		if (logger.isInfoEnabled())
			logger.info("nullTest: " + cache.getCachedObject("nullTest"));
		String notFound = (String) cache.getCachedObject("notFound");
		if (logger.isInfoEnabled())
			logger.info("notFound: " + notFound);
		cache.shutDown();
	}
}
