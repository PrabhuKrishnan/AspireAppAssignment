package com.app.tests;

import com.app.annotations.FrameworkAnnotation;
import com.app.enums.CategoryType;
import com.app.testdata.TestData;
import com.app.utils.DataProviderUtils;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.Map;

import static com.app.pages.AmazonPage.amazonPage;

public class SearchWristWatchTest extends BaseTest {


    @FrameworkAnnotation(author = {"prabhu Krishnan"}, category = {CategoryType.REGRESSION})
    @Test(description = "To verify that user is able to search and filter the Wrist Watch", dataProvider = "getData", dataProviderClass = DataProviderUtils.class)
    public void searchAnalogueWristWatchesTest(TestData testData) {


        Map<String, String> productDetails = amazonPage()
                .searchWristWatch(testData.searchKeyword)
                .withFilter()
                .selectWatchDisplayType(testData.displayType)
                .selectWatchBrandMaterial(testData.brandsMaterial)
                .selectWatchBrandName(testData.brandName)
                .selectDiscount(testData.discount)
                .getProductDetails();

        Assertions.assertThat(productDetails)
                .isNotNull()
                .containsKey("Titan")
                .containsKey("Purple Upgrades Analog Black Dial Men's Watch-NN1585NL02/NP1585NL02")
                .containsValue("FREE Delivery by Amazon");


    }


}
