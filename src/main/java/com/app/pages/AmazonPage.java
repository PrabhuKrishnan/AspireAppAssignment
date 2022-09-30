package com.app.pages;

import com.app.driver.DriverManager;
import com.app.enums.WaitType;
import com.app.utils.DynamicsXpathUtils;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;

import static com.app.utils.SeleniumUtils.*;

public final class AmazonPage {


    private String filterMenu = "//*[@aria-label='%s']/span/a";
    private String discount = "//*[contains(text(),'%s')]";

    private static final By TXT_SEARCH_TEXT_BOX = By.id("twotabsearchtextbox");

    private static final By BTN_SEARCH_BUTTON = By.id("nav-search-submit-button");

    private static final By PRODUCT_NAME = By.xpath("//span[@class='a-size-base-plus a-color-base a-text-normal']/ancestor::h2");

    private static final By PRODUCT_PRICE = By.xpath("//span[@class='a-price-whole']");

    private static final By BRAND_NAME = By.xpath("//span[@class='a-size-base-plus a-color-base']");

    private static final By TXT_FREE_DELIVERY = By.xpath("//span[contains(text(),'FREE Delivery by Amazon')]/parent::span");


    public AmazonPage searchWristWatch(String searchKeyword) {
        sendKeys(TXT_SEARCH_TEXT_BOX, searchKeyword, WaitType.VISIBLE, "Amazon Search Keyword ");
        clickOnSearchButton();
        return this;
    }

    private AmazonPage clickOnSearchButton() {
        click(BTN_SEARCH_BUTTON, WaitType.VISIBLE, "Search Button");
        return this;
    }

    public AmazonPage selectWatchDisplayType(String watchDisplayType) {
        String watchDisplayType_Xpath = DynamicsXpathUtils.getXpath(filterMenu, watchDisplayType);
        click(By.xpath(watchDisplayType_Xpath), WaitType.CLICKABLE, "watch Display Type");
        return this;
    }

    public AmazonPage selectWatchBrandMaterial(String watchBrandMaterialName) {
        String watchBrandMaterial_xpath = DynamicsXpathUtils.getXpath(filterMenu, watchBrandMaterialName);
        click(By.xpath(watchBrandMaterial_xpath), WaitType.CLICKABLE, "Watch Brand Material");
        return this;
    }

    public AmazonPage selectWatchBrandName(String watchBrandName) {
        String watchBrandName_Xpath = DynamicsXpathUtils.getXpath(filterMenu, watchBrandName);
        click(By.xpath(watchBrandName_Xpath), WaitType.CLICKABLE, "watch Brand");
        return this;
    }

    @SneakyThrows
    public AmazonPage selectDiscount(String selectDiscount) {
        String watchDiscount_Xpath = DynamicsXpathUtils.getXpath(discount, selectDiscount);
        click(By.xpath(watchDiscount_Xpath), WaitType.CLICKABLE, " Watch Discount");
        Thread.sleep(3000);
        return this;
    }

    public Map<String, String> getProductDetails() {
        List<WebElement> productName = DriverManager.getDriver().findElements(PRODUCT_NAME);
        List<WebElement> productPrice = DriverManager.getDriver().findElements(PRODUCT_PRICE);
        List<WebElement> brandName = DriverManager.getDriver().findElements(BRAND_NAME);
        List<WebElement> freeDelivery = DriverManager.getDriver().findElements(TXT_FREE_DELIVERY);

        Map<String, String> result = new HashMap<>();
        String pName;
        String pPrice;
        String productBrandName;
        String freeDeliveryText;


        //Index- to get the product from the below index
        List<Integer> indexValue = Arrays.asList(5, 10, 15);

        //getting product name and price from the above index
        for (int i = 0; i < indexValue.size(); i++) {
            pName = productName.get(indexValue.get(i)).getText();
            pPrice = productPrice.get(indexValue.get(i)).getText();
            productBrandName = brandName.get(indexValue.get(i)).getText();
            freeDeliveryText = freeDelivery.get(indexValue.get(i)).getText();
            result.put(pName, pPrice);
            result.put(productBrandName, freeDeliveryText);
        }
        return result;
    }

    public static AmazonPage amazonPage() {
        AmazonPage amazonPage = new AmazonPage();
        return amazonPage;
    }
    public AmazonPage withFilter() {
        return this;
    }

}
