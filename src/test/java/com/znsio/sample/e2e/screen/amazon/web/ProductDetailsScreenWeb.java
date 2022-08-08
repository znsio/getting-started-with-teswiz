package com.znsio.sample.e2e.screen.amazon.web;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.ProductDetailsScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.znsio.e2e.tools.Wait.waitFor;

public class ProductDetailsScreenWeb extends ProductDetailsScreen {
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = ProductDetailsScreenWeb.class.getSimpleName();
    static int totalProductsinCart=0;

    private final By addToListButton = By.id("wishListMainButton");
    private final By addToCartButton = By.id("add-to-cart-button");
    private final By cartButton = By.xpath("//form[@id='attach-view-cart-button-form']//input");
    private final By cartButtonLink = By.id("nav-cart");
    private final By cartCurrentValueElement = By.id("nav-cart-count");
    private final By currentlyUnavailableElement = By.id("outOfStock");

    public ProductDetailsScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }
    public boolean verifyProductDetails() {
        waitFor(5);

        return driver.findElement(addToListButton).isDisplayed();
    }

    public int verifyAddToCartButton(){
        if(driver.findElements(currentlyUnavailableElement).size()>0)
            return -2;
        else if(driver.findElement(addToCartButton).isDisplayed())
            return 1;
        else
            return -1;
    }

    public void addProductToCart() {
        totalProductsinCart = Integer.parseInt(driver.findElement(cartCurrentValueElement).getText());
        driver.findElement(addToCartButton).click();
    }

    public void openCart() {
        waitFor(5);
        List<WebElement> cartButtonElements = driver.findElements(cartButton);
        if(cartButtonElements.size()>0)
            cartButtonElements.get(0).click();
        else
            driver.findElement(cartButtonLink).click();
    }

    public boolean verifyProductAddedToCart() {
        return totalProductsinCart+1==Integer.parseInt(driver.findElement(cartCurrentValueElement).getText());
    }
}
