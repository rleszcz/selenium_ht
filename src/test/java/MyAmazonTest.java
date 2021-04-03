import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyAmazonTest extends MyBasicTest {
    static final String PAGE_URL = "https://www.amazon.com/";

    static final String SIDE_MENU_XPATH = "//*[@id=\"nav-hamburger-menu\"]";
    static final String ITEM1_XPATH = "//div[@id=\"hmenu-content\"]//*[contains(text(), \"Amazon Music\")]";
    static final String ITEM2_XPATH = "//div[@id=\"hmenu-content\"]//*[contains(text(), \"Kindle E-readers & Books\")]";
    static final String ITEM3_XPATH = "//div[@id=\"hmenu-content\"]//*[contains(text(), \"Appstore for Android\")]";

    static final String SEARCH_FIELD_ID = "twotabsearchtextbox";
    static final String SEARCH_BUTTON_ID = "nav-search-submit-button";
    static final String SEARCH_PHRASE = "LEGO 42100";
    static final String ITEM_NAME = "LEGO Technic Liebherr R 9800 Excavator 42100 Building Kit (4,108 Pieces)";
    static final String ITEM_IN_BASKET_XPATH = "//div[@class=\"sc-list-item-content\"]";

    static final String ADD_TO_CART_BUTTON_ID = "add-to-cart-button";
    static final String GO_TO_CART_BUTTON_XPATH = "//*[@id=\"attach-sidesheet-view-cart-button\"]";

    static final String DELIVER_TO_BUTTON_XPATH = "//*[@id=\"nav-global-location-data-modal-action\"]";
    static final String COUNTRY_DROPDOWN_XPATH = "//select[@class=\"a-native-dropdown\"]";
    static final String SELECTED_COUNTRY = "Poland";
    static final String DONE_BUTTON_XPATH = "//span[@class=\"a-declarative\"]";
    static final String COUNTRY_XPATH = "//span[@class=\"nav-line-2 nav-progressive-content\"]";

    static final String LANGUAGE_BUTTON_XPATH = "//*[@id=\"icp-nav-flyout\"]";
    static final String CURRENCY_DROPDOWN_XPATH = "//select[@id=\"icp-sc-dropdown\"]";
    static final String SELECTED_CURRENCY = "€ - EUR - Euro";
    static final String SELECTED_CURRENCY_SHORT = "EUR";
    static final String SAVE_CHANGES_BUTTON_XPATH = "//input[@aria-labelledby=\"icp-btn-save-announce\"]";
    static final String BUYBOX_PRICE_XPATH = "//span[@id=\"price_inside_buybox\"]";

    @Test
    public void checkSideMenuItemsOfDigitalContentSectionTest(){
        driver.navigate().to(PAGE_URL);
        waitClickable(SIDE_MENU_XPATH, 3).click();
        waitPresent(ITEM1_XPATH, 1);
        Assert.assertTrue(driver.findElement(By.xpath(ITEM1_XPATH)).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath(ITEM2_XPATH)).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath(ITEM3_XPATH)).isDisplayed());
    }

    @Test
    public void findItemAndAddToBasketTest() {
        driver.navigate().to(PAGE_URL);
        driver.findElement(By.id(SEARCH_FIELD_ID)).sendKeys(SEARCH_PHRASE);
        driver.findElement(By.id(SEARCH_BUTTON_ID)).click();
        driver.findElement(By.partialLinkText(ITEM_NAME)).click();
        driver.findElement(By.id(ADD_TO_CART_BUTTON_ID)).click();
        waitClickable(GO_TO_CART_BUTTON_XPATH, 5).click();
        waitPresent(ITEM_IN_BASKET_XPATH, 3);
        Assert.assertTrue(driver.findElement(By.xpath(ITEM_IN_BASKET_XPATH)).getText().contains(ITEM_NAME));
    }

    @Test
    public void changeDeliveryDestinationToPolandTest() {
        driver.navigate().to(PAGE_URL);
        driver.findElement(By.xpath(DELIVER_TO_BUTTON_XPATH)).click();
        waitClickable(COUNTRY_DROPDOWN_XPATH, 3);
        new Select(driver.findElement(By.xpath(COUNTRY_DROPDOWN_XPATH))).selectByVisibleText(SELECTED_COUNTRY);
        driver.findElement(By.xpath(DONE_BUTTON_XPATH)).click();
        waitPresent(COUNTRY_XPATH, 3);
        Assert.assertTrue(driver.findElement(By.xpath(COUNTRY_XPATH)).getText().equals(SELECTED_COUNTRY));
    }

    @Test
    public void changeCurrencyToEuroTest() {
        driver.navigate().to(PAGE_URL);
        driver.findElement(By.xpath(LANGUAGE_BUTTON_XPATH)).click();
        new Select(driver.findElement(By.xpath(CURRENCY_DROPDOWN_XPATH))).selectByVisibleText(SELECTED_CURRENCY);
        driver.findElement(By.xpath(SAVE_CHANGES_BUTTON_XPATH)).click();
        driver.findElement(By.id(SEARCH_FIELD_ID)).sendKeys(SEARCH_PHRASE);
        driver.findElement(By.id(SEARCH_BUTTON_ID)).click();
        driver.findElement(By.partialLinkText(ITEM_NAME)).click();
        Assert.assertTrue(driver.findElement(By.xpath(BUYBOX_PRICE_XPATH)).getText().contains(SELECTED_CURRENCY_SHORT));
    }

}
