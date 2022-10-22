package com.app.pages;

import com.app.enums.WaitType;
import com.app.utils.DynamicsXpathUtils;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static com.app.utils.SeleniumUtils.*;

public final class AmazonPage {

    private static final By BTN_DONT_CHANGE_LOCATION = By.xpath("//input[@data-action-type='DISMISS']/parent::span");
    private static final By LABEL_SEARCH_ALL = By.xpath("//div[@id='nav-search-dropdown-card']");
    private static final By DROPDOWN_OPTIONS = By.xpath("//select[@id='searchDropdownBox']");
    private static final By TXT_BOX_SEARCH = By.xpath("//input[@id='twotabsearchtextbox']");
    private static final By BTN_SEARCH = By.xpath("//input[@id='nav-search-submit-button']");
    private String featureBrandsFilter = "//*[@aria-label='%s']/span/a";
    private static final By TXT_BOX_MIN_PRICE = By.xpath("//input[@name='low-price']");
    private static final By TXT_BOX_MAX_PRICE = By.xpath("//input[@name='high-price']");
    private static final By BTN_GO = By.xpath("//span[@id='a-autoid-1']//input[@type='submit']");
    private static final By PRODUCT_PRICE = By.xpath("//div[@class='a-row a-size-base a-color-base']//span[@class='a-price-whole']");
    private static final By LNK_PRODUCT = By.xpath("(//span[@class='a-size-base-plus a-color-base a-text-normal']/parent::a)[1]");
    private static final By LNK_LOCATION = By.xpath("//div[@id='glow-ingress-block']/parent::a");
    private static final By DROPDOWN_LOCATION_COUNTRY = By.xpath("//select[@Id='GLUXCountryList']");
    private static final By BTN_LOCATION_DONE = By.xpath("//button[@name='glowDoneButton']");
    private static final By BTN_ADD_TO_CART = By.xpath("//input[@id='add-to-cart-button']");
    private static final By BTN_GO_TO_CART = By.xpath("//span[@id='sw-gtc']//a");
    private static final By BTN_PROCEED_TO_CHECKOUT = By.xpath("//input[@name='proceedToRetailCheckout']");
    private static final By DROPDOWN_SORT_BY = By.xpath("//select[@id='s-result-sort-select']");

    private static final By BTN_PAGINATION_NEXT_BUTTON = By.cssSelector(".s-pagination-item.s-pagination-next");

    public static AmazonPage amazonPage() {
        return new AmazonPage();
    }
    public AmazonPage withFilter() {
        return this;
    }

    public AmazonPage locationPopup() {
        click(BTN_DONT_CHANGE_LOCATION, WaitType.CLICKABLE, "Location change");
        click(LNK_LOCATION, WaitType.CLICKABLE, "Location Icon");
        selectDropDownByVisibleText(DROPDOWN_LOCATION_COUNTRY, WaitType.VISIBLE, "Singapore", "Location Country");
        click(BTN_LOCATION_DONE, WaitType.CLICKABLE, "Location Done Button");
        return this;
    }

    public AmazonPage selectSearchLabel() {
        locationPopup();
        click(LABEL_SEARCH_ALL, WaitType.VISIBLE, "label");
        return this;
    }

    public AmazonPage selectCategory(String category) {
        selectSearchLabel();
        selectDropDownByVisibleText(DROPDOWN_OPTIONS, WaitType.NONE, category, "Category");
        return this;
    }

    public AmazonPage sendSearchKeyword(String searchKeyword) {
        sendKeys(TXT_BOX_SEARCH, searchKeyword, WaitType.CLICKABLE, "Search Keyword");
        clickOnSearchButton();
        return this;
    }

    private AmazonPage clickOnSearchButton() {
        click(BTN_SEARCH, WaitType.CLICKABLE, "Search Button");
        return this;
    }

    public AmazonPage selectFeaturedBrandsFilter(String brandName) {
        String featuredBrandNameXpath = DynamicsXpathUtils.getXpath(featureBrandsFilter, brandName);
        click(By.xpath(featuredBrandNameXpath), WaitType.CLICKABLE, " " + brandName + " Brand");
        return this;
    }

    public AmazonPage enterMinimumPrice(String minimumPrice) {
        sendKeys(TXT_BOX_MIN_PRICE, minimumPrice, WaitType.CLICKABLE, " Maximum Price " + minimumPrice + " ");
        return this;
    }

    public AmazonPage enterMaximumPrice(String maximumPrice) {
        sendKeys(TXT_BOX_MAX_PRICE, maximumPrice, WaitType.CLICKABLE, " Minimum Price " + maximumPrice + " ");
        return this;
    }

    public AmazonPage clickOnMinAndMaxPriceGoButton() {
        click(BTN_GO, WaitType.CLICKABLE, "Go Button");
        return this;
    }

    public AmazonPage clickOnProduct() {
        click(LNK_PRODUCT, WaitType.CLICKABLE, "First Product");
        return this;
    }

    public AmazonPage clickOnAddToCardButton() {
        click(BTN_ADD_TO_CART, WaitType.CLICKABLE, "Add to Cart Button");
        return this;
    }

    public AmazonPage clickOnGoToCartButton() {
        click(BTN_GO_TO_CART, WaitType.CLICKABLE, "Go to Cart Button");
        return this;
    }

    public AmazonPage clickOnProceedToCheckOut() {
        click(BTN_PROCEED_TO_CHECKOUT, WaitType.VISIBLE, "Proceed to Check Button");
        return this;
    }

    public String getSignInPageTitle() {
        return getPageTitle();
    }

    @SneakyThrows
    public AmazonPage selectSortByDropDown(String dropDownValue) {
        selectDropDownByVisibleText(DROPDOWN_SORT_BY, WaitType.VISIBLE, dropDownValue, "Sort By " + dropDownValue + " ");
        Thread.sleep(2000);
        return this;
    }

    public List<Integer> getProductPrice() {
        List<WebElement> searchResult = getTextFromFindElements(PRODUCT_PRICE);
        List<Integer> productPrice = new ArrayList<>();
        try {
            for (WebElement result : searchResult) {
                String value = result.getText();
                if (value != null) {
                    productPrice.add(Integer.valueOf(value));
                }
            }
        } catch (NullPointerException e) {
            throw new RuntimeException("Not able to get the product price from the page, check the product price web element" + Arrays.toString(e.getStackTrace()));
        }
        return productPrice;
    }

    @SneakyThrows
    public List<Integer> getProductPriceOnAllPages() {

        List<WebElement> price = getTextFromFindElements(PRODUCT_PRICE);
        List<Integer> productPrice = new ArrayList<>();
        try {
            for (WebElement element : price) {
                String p = element.getText();
                productPrice.add(Integer.valueOf(p));
            }
            String nextButtonClassName = getWebElementClassAttribute(BTN_PAGINATION_NEXT_BUTTON);
            while (!nextButtonClassName.contains("disabled")) {
                click(BTN_PAGINATION_NEXT_BUTTON, WaitType.CLICKABLE, "Pagination Next Button");
                Thread.sleep(3000);
                price = getTextFromFindElements(PRODUCT_PRICE);
                for (WebElement element : price) {
                    String p = element.getText();
                    productPrice.add(Integer.valueOf(p));
                }
                nextButtonClassName = getWebElementClassAttribute(BTN_PAGINATION_NEXT_BUTTON);
            }
        } catch (NullPointerException | NoSuchElementException e) {
            throw new RuntimeException("Not able to get the product price when clicking next button in pagination" + Arrays.toString(e.getStackTrace()));
        }
        return productPrice;
    }
}
