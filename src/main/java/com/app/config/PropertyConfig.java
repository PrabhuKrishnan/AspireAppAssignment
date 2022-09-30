package com.app.config;

import com.app.enums.BrowserType;
import org.aeonbits.owner.Config;


/**
 * Need to handle the exceptions here, because we are reading a file
 * If the mapping from config and properties are incorrect, then how to handle exception here
 * is it good practice to use the getter and setter methods here?
 * how handle the exception using the config library in calling and caller method
 */
@Config.Sources(value = "file:${user.dir}/src/test/resources/config/config.properties")
public interface PropertyConfig extends Config {

    long timeout();

    String url();

    BrowserType browserName();

    String runMode();

    String remoteURL();

    String overrideReports();

    String passedStepsScreenshot();

    String failedStepsScreenshot();

    String skippedStepsScreenshot();

    String retryFailedTest();

    String sendResultsToELK();

    String elasticURL();

}


