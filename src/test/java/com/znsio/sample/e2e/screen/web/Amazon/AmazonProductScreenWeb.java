package com.znsio.sample.e2e.screen.web.Amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonCartScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonProductScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AmazonProductScreenWeb extends AmazonProductScreen {

    private final Driver driver;
    private final WebDriver innerDriver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonSearchScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By byAddToCartId = By.id("add-to-cart-button");
    private static final By byProductResultXpath=By.xpath("//span[contains(@class,'a-text-normal') and contains(text(),'Apple iPhone 13')]");

    private static  final By byCartId =By.id("attach-desktop-sideSheet");

    public AmazonProductScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        visually.checkWindow(SCREEN_NAME, "Product Detailed page");
    }
    @Override
    public AmazonCartScreen addToCart()
    {
        visually.checkWindow(SCREEN_NAME, "Cart screen");
        driver.switchToNextTab();
        WebElement addCartElement=driver.waitTillElementIsPresent(byAddToCartId);
        addCartElement.click();
        driver.waitTillElementIsVisible(byCartId);
        LOGGER.info("Added to cart is displayed");
        return AmazonCartScreen.get();
    }

    @Override
    public boolean isProductAvailableAfterSearching()
    {
        List<WebElement> actualProducts=driver.findElements(byProductResultXpath);
        visually.checkWindow(SCREEN_NAME, "Viewing after search");
        if(actualProducts.size()!=0)
        {
            WebElement firstProduct=driver.waitTillElementIsPresent(byProductResultXpath);
            JavascriptExecutor js = (JavascriptExecutor) innerDriver;
            js.executeScript("arguments[0].click()", firstProduct);
            LOGGER.info("Clicked on first search related product");
            return true;
        }
        return  false;
    }


}
