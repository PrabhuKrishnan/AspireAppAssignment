package com.app.utils;

public final class DynamicsXpathUtils {

    private DynamicsXpathUtils() {
    }

    public static String getXpath(String xpath, String value) {
        return String.format(xpath, value);

    }
}

