package com.znsio.sample.e2e.screen.amazon.web;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.businessLayer.amazon.CartPageBL;
import com.znsio.sample.e2e.screen.amazon.AmazonCartPageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.util.function.BooleanSupplier;

public class AmazonCartPageWeb extends AmazonCartPageScreen {
    private final Visual visually;
    private final Driver driver;
    public static final String SCREEN_NAME = AmazonGridWallPageWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final By button_Cart = By.xpath("//span[text()=' Cart ']/preceding-sibling::input[@type='submit']");
    public By isProductAddedInCart(String productToBeSelected) {
        By isProductAddedInCart = By.xpath("//div[@data-name='Active Items']//span[text()='"+productToBeSelected+"']");
        return isProductAddedInCart;
    }
    public AmazonCartPageWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public CartPageBL clickCartButton() {
        driver.waitForClickabilityOf(button_Cart,20).click();
        visually.takeScreenshot(SCREEN_NAME, "After Selecting the cart button in Express checkout path page");
        return new CartPageBL();
    }

    @Override
    public Boolean verifyAddedProductIsReflectionInCart(String expectedProductDesc) {
        Boolean isProductAddedInCart = false;
        driver.waitTillElementIsPresent(isProductAddedInCart(expectedProductDesc),15);
        if (driver.isElementPresent(isProductAddedInCart(expectedProductDesc))) {
            isProductAddedInCart = true;
        }
        return isProductAddedInCart;
    }
}
