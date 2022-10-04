package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonLandingScreen;
import com.znsio.sample.e2e.screen.amazon.SearchResultsScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class AmazonLandingScreenWeb extends AmazonLandingScreen {
    private static final String SCREEN_NAME = AmazonLandingScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(AmazonLandingScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final By bySearchTextBoxId = By.id("twotabsearchtextbox");
    private final By bySearchIconBtnId = By.id("nav-search-submit-button");


    public AmazonLandingScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Amazon Landing Screen");
    }

    @Override
    public SearchResultsScreen search(String productName) {
        LOGGER.info("User searched for productName : " + productName);
        driver.waitTillElementIsPresent(bySearchTextBoxId).sendKeys(productName);
        driver.findElement(bySearchIconBtnId).click();
        return SearchResultsScreen.get();
    }
}
