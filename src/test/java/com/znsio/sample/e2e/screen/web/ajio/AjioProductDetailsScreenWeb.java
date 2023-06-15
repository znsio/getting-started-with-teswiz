package com.znsio.sample.e2e.screen.web.ajio;


import com.znsio.sample.e2e.screen.ajio.AjioProductDetailsScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AjioProductDetailsScreenWeb
        extends AjioProductDetailsScreen {
    private static final String SCREEN_NAME = AjioProductDetailsScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By bySearchBoxXpath = By.xpath("//input[@name='searchVal']");


    private final Driver driver;
    private final Visual visually;

    public AjioProductDetailsScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home page");
    }

    @Override
    public AjioProductDetailsScreen wishlistTheProduct() {
        return null;
    }
}