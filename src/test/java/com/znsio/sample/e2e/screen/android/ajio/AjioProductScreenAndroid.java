package com.znsio.sample.e2e.screen.android.ajio;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.ajio.AjioProductScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class AjioProductScreenAndroid extends AjioProductScreen {

    private static final String SCREEN_NAME = AjioProductScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final Driver driver;
    private final Visual visually;
    private final TestExecutionContext context;

    private static final By byProductImageId = By.id("com.ril.ajio:id/pdp_product_img");


    public AjioProductScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        long threadId = Thread.currentThread().getId();
        context = Runner.getTestExecutionContext(threadId);
    }

    @Override
    public boolean isProductDetailsLoaded() {
        LOGGER.info("Verifying if Product Details page is loaded");
        boolean isProductedLoaded = false;
        driver.tapOnMiddleOfScreen();
        driver.waitTillElementIsPresent(byProductImageId);
        driver.findElement(byProductImageId).click();
        if (driver.isElementPresent(byProductImageId)) {
            isProductedLoaded = true;
        }
        return isProductedLoaded;
    }

    @Override
    public AjioProductScreen flickImage() {
        LOGGER.info("Performing flick to view multiple product images");
        driver.findElement(byProductImageId).click();
        String initialElementId = driver.findElement(byProductImageId).getAttribute("bounds");
        context.addTestState(SAMPLE_TEST_CONTEXT.INITIAL_ELEMENT_ID, initialElementId);
        driver.flick();
        return this;
    }

    @Override
    public String isElementIdChanged() {
        LOGGER.info("Verifying other images after flick");
        visually.checkWindow(SCREEN_NAME, "Other visible images after flick");
        return driver.findElement(byProductImageId).getAttribute("bounds");
    }
}
