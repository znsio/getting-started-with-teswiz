package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazonsearch.AmazonHomeScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AmazonHomeScreenWeb extends AmazonHomeScreen {

    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By bySearchBoxId = By.id("twotabsearchtextbox");
    private static final By bySearchIconCss = By.cssSelector("[value='Go']");
    public static final By byProductNameHeadingXpath = By.xpath("//span[@class='a-color-state a-text-bold']");
    public static final By byProductCountXpath = By.xpath("//span[contains(text(), 'results')]");

    public AmazonHomeScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public AmazonHomeScreen searchProductInAmazonSearch(String productName){
        visually.checkWindow(SCREEN_NAME, "Amazon home page");
        WebElement searchElement = driver.waitTillElementIsPresent(bySearchBoxId);
        searchElement.click();
        searchElement.clear();
        searchElement.sendKeys(productName);
        LOGGER.info("On Web the product search name: "+productName);
        driver.waitTillElementIsPresent(bySearchIconCss).click();
        return this;
    }

    @Override
    public String getActualSearchProduct(){
        visually.checkWindow(SCREEN_NAME, "Result Page for the product");
        WebElement searchElement = driver.waitTillElementIsVisible(byProductNameHeadingXpath);
        String itemSearched = searchElement.getText().replace("\"", "").trim();
        LOGGER.info("On Web the product name searched: "+itemSearched);
        return itemSearched;
    }

    @Override
    public int getproductCount(){
        WebElement searchProductCount = driver.waitTillElementIsPresent(byProductCountXpath);
        String count = searchProductCount.getText().trim().split("\\s")[2];
        LOGGER.info(String.format("Total number of products: " + count));
        return Integer.parseInt(count);
    }
}
