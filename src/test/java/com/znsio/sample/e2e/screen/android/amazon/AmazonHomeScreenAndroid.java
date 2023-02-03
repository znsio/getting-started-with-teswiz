package com.znsio.sample.e2e.screen.android.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazonsearch.AmazonHomeScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;


public class AmazonHomeScreenAndroid extends AmazonHomeScreen {

    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonHomeScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    private static By bySearchAreaXpath = By.xpath("//android.widget.EditText[]");
    private static By bySearchButtonXpath = By.xpath("//android.view.View[@resource-id = \"nav-search-form\"]//android.widget.Button");
    private static By byFirstProductXpath = By.xpath("//android.view.View[contains(@content-desc, \""+SAMPLE_TEST_CONTEXT.PRODUCT_NAME+"\")]/android.view.View[2]");
    private static By byCountElementXpath = By.xpath("//android.view.View[contains(@content-desc,\""+ SAMPLE_TEST_CONTEXT.PRODUCT_NAME+"\")]");

    public AmazonHomeScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public AmazonHomeScreen searchProductInAmazonSearch(String productName){
        visually.checkWindow(SCREEN_NAME, "Amazon launched");
        WebElement element = driver.waitTillElementIsPresent(bySearchAreaXpath);
        element.click();
        element.sendKeys(productName);
        element.sendKeys(Keys.ENTER);
        driver.findElement(bySearchButtonXpath).click();
        return this;
    }

    @Override
    public String getActualSearchProduct(){
        visually.checkWindow(SCREEN_NAME, "Product list view");
        String productName = driver.waitTillElementIsPresent(byFirstProductXpath).getText();
        LOGGER.info("The product searched: "+ productName);
        return productName;
    }

    @Override
    public int getproductCount(){
        int count = driver.findElements(byCountElementXpath).size();
        LOGGER.info("Get the number of product available: "+ count);
        return count;
    }
}
