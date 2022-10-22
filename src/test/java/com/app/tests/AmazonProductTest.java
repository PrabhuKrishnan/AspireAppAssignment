package com.app.tests;

import com.app.annotations.FrameworkAnnotation;
import com.app.enums.CategoryType;
import com.app.testdata.TestData;
import com.app.utils.DataProviderUtils;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.app.pages.AmazonPage.amazonPage;
import static org.assertj.core.api.Assertions.assertThat;

public class AmazonProductTest extends BaseTest {

    private SoftAssertions softAssertions = new SoftAssertions();

    @FrameworkAnnotation(author = {"prabhu Krishnan"}, category = {CategoryType.REGRESSION})
    @Test(description = "To Validate all items have 100$ <= price <= 2000$ on the result", dataProvider = "getData", dataProviderClass = DataProviderUtils.class)
    public void validateProductPriceLessThanMinAndMaxPrice(TestData testData) {

        List<Integer> productPrice = amazonPage()
                .withFilter()
                .selectCategory(testData.searchCategory)
                .sendSearchKeyword(testData.searchKeyword)
                .selectFeaturedBrandsFilter(testData.featureBrands)
                .enterMinimumPrice(testData.minimumPrice)
                .enterMaximumPrice(testData.maximumPrice)
                .clickOnMinAndMaxPriceGoButton()
                .getProductPrice();

        softAssertions.assertThat(productPrice)
                .allSatisfy(price ->
                {
                    assertThat(price).isLessThanOrEqualTo(Integer.parseInt(testData.minimumPrice));
                    assertThat(price).isLessThanOrEqualTo(Integer.parseInt(testData.maximumPrice));
                });
        softAssertions.assertAll();
    }

    @FrameworkAnnotation(author = {"prabhu Krishnan"}, category = {CategoryType.REGRESSION})
    @Test(description = "To Validate the items on all pages were sorted by price from High to Low", dataProvider = "getData", dataProviderClass = DataProviderUtils.class)
    public void validateIsPriceSortedByHighToLow(TestData testData) {

        List<Integer> productPriceOnAllPages = amazonPage()
                .withFilter()
                .selectCategory(testData.searchCategory)
                .sendSearchKeyword(testData.searchKeyword)
                .enterMinimumPrice(testData.minimumPrice)
                .enterMaximumPrice(testData.maximumPrice)
                .clickOnMinAndMaxPriceGoButton()
                .selectSortByDropDown(testData.sortByFilter)
                .getProductPriceOnAllPages();

        List<Integer> sortedList = new ArrayList<>(productPriceOnAllPages);
        sortedList.sort(Collections.reverseOrder());

        softAssertions.assertThat(productPriceOnAllPages)
                .isNotEmpty()
                .isSortedAccordingTo(Comparator.reverseOrder())
                .isEqualTo(sortedList);
        softAssertions.assertAll();

    }

    @FrameworkAnnotation(author = {"prabhu Krishnan"}, category = {CategoryType.REGRESSION})
    @Test(description = "To Validate the sign-In Page is displayed or not", dataProvider = "getData", dataProviderClass = DataProviderUtils.class)
    public void validateSignPage(TestData testData) {

        String signInPageTitle = amazonPage()
                .withFilter()
                .selectCategory(testData.searchCategory)
                .sendSearchKeyword(testData.searchKeyword)
                .enterMinimumPrice(testData.minimumPrice)
                .enterMaximumPrice(testData.maximumPrice)
                .clickOnProduct()
                .clickOnAddToCardButton()
                .clickOnGoToCartButton()
                .clickOnProceedToCheckOut()
                .getSignInPageTitle();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(signInPageTitle)
                .isNotEmpty()
                .isEqualTo(testData.signInPageTitle);
        softAssertions.assertAll();

    }
}
