package emg.demo.jcache.listener;

import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryExpiredListener;
import javax.cache.event.CacheEntryListenerException;
import javax.cache.event.CacheEntryRemovedListener;
import javax.cache.event.CacheEntryUpdatedListener;

import org.apache.log4j.Logger;

public class FisaCacheEntryListener implements CacheEntryCreatedListener<Object, Object>,
CacheEntryUpdatedListener<Object, Object>,
CacheEntryRemovedListener<Object, Object>,
CacheEntryExpiredListener<Object, Object>{
	private final static Logger logger = Logger
			.getLogger(FisaCacheEntryListener.class);
	

	public void onExpired(
			Iterable<CacheEntryEvent<? extends Object, ? extends Object>> arg0)
			throws CacheEntryListenerException {
		logger.info("added: " + arg0.iterator().next().getKey());
	}

	public void onRemoved(
			Iterable<CacheEntryEvent<? extends Object, ? extends Object>> arg0)
			throws CacheEntryListenerException {
		logger.info("removed: " + arg0.iterator().next().getKey());	}

	public void onUpdated(
			Iterable<CacheEntryEvent<? extends Object, ? extends Object>> arg0)
			throws CacheEntryListenerException {
		logger.info("updated: " + arg0.iterator().next().getKey());	
	}

	public void onCreated(
			Iterable<CacheEntryEvent<? extends Object, ? extends Object>> arg0)
			throws CacheEntryListenerException {
		logger.info("created: " + arg0.iterator().next().getKey());	
	}
	
}
