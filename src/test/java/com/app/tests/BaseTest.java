package com.app.tests;

import com.app.driver.Driver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest {

    protected BaseTest() {

    }

    @BeforeMethod
    protected void setUp() {
        Driver.initDriver();
    }

    @AfterMethod
    protected void afterMethod() {
        Driver.quitDriver();

    }


}
