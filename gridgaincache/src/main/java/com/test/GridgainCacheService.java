package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class GridgainCacheService {
    private static Logger log = 
        LoggerFactory.getLogger(GridgainCacheService.class);
    public GridgainCacheService(){
        log.info("Calling init");
        this.init();
    }
    public void init(){
        log.info("Inside init");
        AppConfig config;
    }
}