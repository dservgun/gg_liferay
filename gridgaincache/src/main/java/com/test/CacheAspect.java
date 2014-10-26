package com.test;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.gridgain.grid.GridGain;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
//An aspect to start stop caches 
@Aspect 
public class CacheAspect {
    private static Logger _log = LoggerFactory.getLogger("com.test.CacheAspect");
    private static AtomicBoolean cacheStarted;
    
    @Before("com.test.GridgainCacheService.init()")
    public void startCache(){
        try {
            _log.info("Initializing cache");
            if(!cacheStarted.get()){ 
                GridGain.start("/home/emperor/gg_liferay/liferay-gg-config.xml");
                cacheStarted.set(true);
            }else {
                _log.warn("Cache already started. Not starting again");
            }            
            _log.info("Cache initialized");
        }catch(Exception e){
            _log.error("ERROR", e);
            throw new RuntimeException(e); 
        }
    }



}