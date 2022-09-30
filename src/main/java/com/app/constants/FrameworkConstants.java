package com.app.constants;

import com.app.config.PropertyConfigReader;


public final  class FrameworkConstants {

    private  FrameworkConstants(){}

    private static final String EXTENT_REPORT_FOLDER_PATH = System.getProperty("user.dir")+"/extent-test-output/";
    private static String extentReportFilePath = "";

    public static String getExtentReportFilePath()
    {

        if(extentReportFilePath.isEmpty())
        {
            extentReportFilePath = createReportPath();
        }

        return extentReportFilePath;
    }
    private static String createReportPath()
    {
        if(PropertyConfigReader.getConfig().overrideReports().equalsIgnoreCase("no"))
        {
            return EXTENT_REPORT_FOLDER_PATH+System.currentTimeMillis()+"/index.html";
        }
        else {
            return EXTENT_REPORT_FOLDER_PATH+"/index.html";
        }
    }

}
