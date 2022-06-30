package com.znsio.sample.e2e.screen.web.theapp;

import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.theapp.AmazonResultsScreen;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;


public class AmazonResultsScreenWeb extends AmazonResultsScreen {

    private final Driver driver;
    private final Visual visually;

    private final SoftAssertions softly;
    private final String SCREEN_NAME = AmazonResultsScreenWeb.class.getSimpleName();
    private final By SearchBox = By.id("twotabsearchtextbox");
    private final By ProductDetail=By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']/child::span[3]");
    //Used Indexing as no other unique attribute was available in DOM


    public AmazonResultsScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        long threadId = Thread.currentThread().getId();
        softly = Runner.getSoftAssertion(threadId);
        visually.takeScreenshot(SCREEN_NAME, "Amazon Results screen");
    }

    @Override
    public  AmazonResultsScreen SearchProduct(String product)
    {
        driver.findElement(SearchBox).click();
        driver.findElement(SearchBox).sendKeys(product+ "\n");
        return AmazonResultsScreen.get();
    }

    @Override
    public  AmazonResultsScreen VerifyProductDetail(String product)
    {
        visually.takeScreenshot(SCREEN_NAME, "ProductDetails");
        visually.checkWindow(SCREEN_NAME, "Amazon Results Screen");
        String ProductDisplayed= driver.findElement(ProductDetail).getText();
        softly.assertThat(ProductDisplayed).contains(product);
        return AmazonResultsScreen.get();
    }



}
