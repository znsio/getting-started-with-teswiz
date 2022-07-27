package com.znsio.sample.e2e.screen.web.theapp;

import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.theapp.AmazonCartScreen;
import com.znsio.sample.e2e.screen.theapp.AmazonResultsScreen;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;


public class AmazonCartScreenWeb extends AmazonCartScreen {

    private final Driver driver;
    private final Visual visually;
    private final SoftAssertions softly;
    private final String SCREEN_NAME = AmazonResultsScreenWeb.class.getSimpleName();
    private final By FirstProduct = By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']");
    private final By ProductTitle = By.id("productTitle");
    private final By AddToCartButton = By.id("submit.add-to-cart");
    private final By CartDetailsButton = By.xpath("//span[@id='attach-sidesheet-view-cart-button']/span/input");
    private final By CartProductDetails = By.xpath("//span[@class='a-truncate-cut']");
    public static String ProductName = "";
    public static String CartProduct = "";


    public AmazonCartScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        long threadId = Thread.currentThread().getId();
        softly = Runner.getSoftAssertion(threadId);
        visually.takeScreenshot(SCREEN_NAME, "Amazon Cart screen");
    }

    @Override
    public AmazonResultsScreen AddingProduct() {
        driver.findElement(FirstProduct).click();
        driver.switchToNextTab();
        ProductName = driver.findElement(ProductTitle).getText();
        return AmazonResultsScreen.get();
    }

    @Override
    public AmazonCartScreen AddToCart() {
        driver.waitForClickabilityOf(AddToCartButton);
        driver.findElement(AddToCartButton).click();
        driver.waitForClickabilityOf(CartDetailsButton);
        driver.findElement(CartDetailsButton).click();
        CartProduct = driver.findElement(CartProductDetails).getText();
        return AmazonCartScreen.get();
    }

    @Override
    public boolean ProductNameVerification() {
        if (ProductName.equalsIgnoreCase(CartProduct))
            return true;
        else
            return false;
    }


}
