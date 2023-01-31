package com.znsio.sample.e2e.screen.android.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonCartScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class AmazonCartScreenAndroid extends AmazonCartScreen {
    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final String SCREEN_NAME = AmazonCartScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final Driver driver;
    private final Visual visually;

    private final By cartNavigateByXpath = By.xpath(" //android.view.View[@content-desc='Cart']/android.view.View");

    private final By byProductNameOnCartByXpath = By.xpath("//android.view.View[contains(@content-desc,'Apple iPhone 13 ')]");

    public AmazonCartScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Cart screen");
    }

    @Override
    public AmazonCartScreen navigateToCart() {
        driver.waitForClickabilityOf(cartNavigateByXpath,5).click();
        visually.checkWindow(SCREEN_NAME, "land on shopping cart");
       return AmazonCartScreen.get();
    }

    @Override
    public String getProductNameInCart() {
        return  driver.waitTillElementIsPresent(byProductNameOnCartByXpath,2).getText();
    }

}
