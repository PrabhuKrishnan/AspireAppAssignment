package com.app.utils;

import com.app.testdata.TestData;
import io.github.sskorol.core.DataSupplier;
import io.github.sskorol.data.XlsxReader;
import one.util.streamex.StreamEx;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

import static io.github.sskorol.data.TestDataReader.use;

public final class DataProviderUtils {

    private DataProviderUtils(){}

    @DataSupplier
    public static StreamEx<TestData> getData(Method method)
    {
        return use(XlsxReader.class)
                .withTarget(TestData.class)
                .withSource("WristWatches_TestData.xlsx")
                .read()
                .filter(testData -> testData.testCaseName.equalsIgnoreCase(method.getName()));
    }


}
