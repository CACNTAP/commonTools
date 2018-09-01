package com.tools.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.stereotype.Service;

@Service
public class CacheService {


    public void putIntoCache(String key, Object value){
        Cache cache = getCache();
        Element element = new Element(key, value);
        cache.put(element);
    }

    public Object getFromCache(String key){
        Cache cache = getCache();

        Element element = cache.get(key);
        if (element == null){
            return null;
        }
        return element.getObjectValue();
    }

    protected Cache getCache(){
        CacheManager manager = CacheManager.create();
        return manager.getCache("cacheManager");
    }

}
