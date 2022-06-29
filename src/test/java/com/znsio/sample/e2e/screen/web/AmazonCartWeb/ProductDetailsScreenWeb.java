package com.znsio.sample.e2e.screen.web.AmazonCartWeb;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.AmazonCart.ProductCartDetailsScreen;
import com.znsio.sample.e2e.screen.AmazonCart.ProductDetailsScreen;
import com.znsio.sample.e2e.screen.AmazonCart.SearchResultsScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static com.znsio.e2e.tools.Wait.waitFor;

public class ProductDetailsScreenWeb extends ProductDetailsScreen {

    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final Logger LOGGER = Logger.getLogger(ProductDetailsScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = ProductDetailsScreenWeb.class.getSimpleName();
    private final By ProductTitle = By.xpath("//span[@id='productTitle']");
    private final By addToCartButton = By.xpath("//input[@id='add-to-cart-button']");

    private final By submitCart = By.xpath("//span[@id=\"attach-sidesheet-view-cart-button\"]/span/input");

    public ProductDetailsScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Product Details screen");
    }

    @Override
    public String getPageTitle(){
        return driver.getInnerDriver().getTitle();
    }

    @Override
    public String getProductTitle(){
        return driver.findElement(ProductTitle).getText();
    }

    @Override
    public boolean isCartPresent(){
        return driver.isElementPresent(addToCartButton);
    }

    @Override
    public ProductCartDetailsScreen addItemToTheCart(){
        waitFor(6);
        driver.findElement(addToCartButton).click();
        //Cart button is visible interactable only after few seconds
        waitFor(10);
        driver.waitTillElementIsPresent(submitCart);
        driver.findElement(submitCart).click();
        return ProductCartDetailsScreen.get();
    }





}
