package com.app.reports;

import com.app.enums.LogType;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Consumer;

public final class FrameworkLogger {

    private FrameworkLogger() {
    }

    private static final Consumer<String> PASS = (message) -> ExtentReportManager.getExtentTest().pass(message);
    private static final Consumer<String> FAIL = (message) -> ExtentReportManager.getExtentTest().fail(message);
    private static final Consumer<String> SKIP = (message) -> ExtentReportManager.getExtentTest().skip(message);
    private static final Consumer<String> INFO = (message) -> ExtentReportManager.getExtentTest().info(message);
    private static final Consumer<String> CONSOLE = (message) -> System.out.println("INFO--->" + message);
    private static final Consumer<String> EXTENT_AND_CONSOLE = PASS.andThen(CONSOLE);
    private static final Map<LogType, Consumer<String>> MAP = new EnumMap<>(LogType.class);

    static {
        MAP.put(LogType.PASS, PASS);
        MAP.put(LogType.FAIL, FAIL);
        MAP.put(LogType.SKIP, SKIP);
        MAP.put(LogType.INFO, INFO);
        MAP.put(LogType.CONSOLE, CONSOLE);
        MAP.put(LogType.EXTENT_AND_CONSOLE, EXTENT_AND_CONSOLE);
    }

    public static void log(LogType status, String message) {

        MAP.get(status).accept(message);
    }

}
