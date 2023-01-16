package com.znsio.sample.e2e.screen.web.Amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonHomeScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonItemsViewScreen;
import com.znsio.sample.e2e.screen.web.ajio.AjioHomeScreenWeb;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class AmazonHomeScreenWeb extends AmazonHomeScreen {
    private final Driver driver;

    private final Visual visually;
    private static final String SCREEN_NAME = AjioHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By bySearchBoxId = By.id("twotabsearchtextbox");

    public AmazonHomeScreenWeb(Driver driver, Visual visually) {

        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home page");
    }

    @Override
    public AmazonItemsViewScreen enterItemNameInSearchbarAndPressEnter(String product) {
        LOGGER.info(String.format("Search for '%s'", product));
        WebElement searchElement = driver.waitTillElementIsPresent(bySearchBoxId);
        searchElement.click();
        searchElement.clear();
        searchElement.sendKeys(product);
        searchElement.sendKeys(Keys.ENTER);
        return AmazonItemsViewScreen.get();
    }
}
