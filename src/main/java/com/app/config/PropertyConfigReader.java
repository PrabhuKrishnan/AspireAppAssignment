package com.app.config;

import org.aeonbits.owner.ConfigFactory;

public final class PropertyConfigReader {

    private PropertyConfigReader(){}


    public static PropertyConfig getConfig()
    {

        return ConfigFactory.create(PropertyConfig.class);

    }

}
