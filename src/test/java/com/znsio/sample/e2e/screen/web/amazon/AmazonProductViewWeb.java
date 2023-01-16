package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazonsearch.AmazonProductViewScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.znsio.e2e.tools.Wait.waitFor;

public class AmazonProductViewWeb extends AmazonProductViewScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By firstProduct = By.xpath("//div[@data-index='4']//h2//span");
    private static final By addCartButton = By.id("add-to-cart-button");

    private static final By sideBarCloseButton = By.id("attach-close_sideSheet-link");

    public AmazonProductViewWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public String firstProductName(){
        WebElement searchElement = driver.waitTillElementIsPresent(firstProduct);
        return searchElement.getText();
    }

    @Override
    public AmazonProductViewScreen selectFirstItem(){
        driver.scrollTillElementIntoView(firstProduct);
        driver.waitForClickabilityOf(firstProduct).click();

        return AmazonProductViewScreen.get();
    }

    @Override
    public AmazonProductViewScreen addProductToCart(){
        driver.switchToNextTab();
        driver.waitForClickabilityOf(addCartButton).click();
        waitFor(5);
        return closeSideBar();
    }

    @Override
    public AmazonProductViewScreen closeSideBar(){
        driver.waitForClickabilityOf(sideBarCloseButton).click();
        return AmazonProductViewScreen.get();
    }
}
