package com.petstore.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;

@LoadPolicy(LoadType.MERGE)
@Config.Sources({"classpath:${environment}.properties"})
public interface Configuration extends Config {

    @Key("api.petstore.uri")
    String petstoreApiUri();

    @Key("log.all")
    boolean logAll();

    @Key("log.allure")
    boolean logAllure();

}
