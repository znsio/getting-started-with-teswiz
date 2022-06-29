package com.znsio.sample.e2e.screen.web.AmazonCartWeb;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.AmazonCart.ProductCartDetailsScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.List;

public class ProductCartDetailsScreenWeb extends ProductCartDetailsScreen {

    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final Logger LOGGER = Logger.getLogger(ProductDetailsScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = ProductDetailsScreenWeb.class.getSimpleName();
    private final By cartProductName = By.xpath("(//span[@class='a-truncate-cut'])[1]");
    private final By cartNumber = By.xpath("//span[@id='nav-cart-count']");


    private final By noOfRowsInCart = By.xpath("//div[@class='a-row sc-list-item sc-list-item-border sc-java-remote-feature']");
    private final By quantity = By.xpath("//div[@class='a-row sc-list-item sc-list-item-border sc-java-remote-feature']/descendant::span[@class='a-dropdown-prompt']");
    private final By newCartItem = By.xpath("(//span[@class='a-truncate-cut'])[2]");

    public ProductCartDetailsScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Cart Details screen");
    }

    public String getNoOfItemsInCart(){
        return driver.findElement(cartNumber).getText();
    }
    public String getProductTitleFromCart(){
        return driver.findElement(cartProductName).getText();
    }


}
