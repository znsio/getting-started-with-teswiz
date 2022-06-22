package com.znsio.e2e.screen.web.amazoncartvalidation;

import com.znsio.e2e.screen.amazoncartvalidation.ProductCartScreen;
import com.znsio.e2e.screen.amazoncartvalidation.ProductDetailsScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.openqa.selenium.By;

public class ProductDetailsScreenWeb extends ProductDetailsScreen {

    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = ProductDetailsScreenWeb.class.getSimpleName();
    private static final By titleOfProduct=By.id("productTitle");
    private static final By  addToCartButton=By.id("add-to-cart-button");
    private static final By confirmAddToCartButton=By.id("a-autoid-0");





    public ProductDetailsScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Search Results List screen");
    }


    public String getTitleOfPage(){return driver.getInnerDriver().getTitle();}
    public String getTitleOfProduct(){return driver.findElement(titleOfProduct).getText();}

    public boolean isElementPresent(){return driver.isElementPresent(addToCartButton);
    }

    @Override
    public ProductCartScreen addProductToCart() {
        driver.getInnerDriver().manage().deleteAllCookies();
        driver.findElement(addToCartButton).click();
        driver.waitForClickabilityOf(confirmAddToCartButton);
       driver.findElement(confirmAddToCartButton).click();
       return ProductCartScreen.get();
    }

}
