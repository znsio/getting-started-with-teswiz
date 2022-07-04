package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonCartScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class AmazonCartScreenWeb extends AmazonCartScreen {
    private static final String SCREEN_NAME = AmazonCartScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private final Driver driver;
    private final Visual visually;

    private static final By byShoppingCartHeaderXpath=By.xpath("//div[@id='sc-active-cart']//child::h1");
    private static final By byTitleOfProductInCartClass =By.className("a-truncate-cut");
    private static final By byCartSubtotalId =By.id("sc-subtotal-label-activecart");
    private static final By byQuantityOfProductInCartXpath =By.xpath("//span[@class='a-dropdown-prompt']");
    private static final By byPriceOfProductInCartXpath=By.xpath("//div/p/span[contains(@class,'sc-price')]");
    private static final String CART_HEADER_CONTAINS_KEYWORD_EMPTY ="empty";
    private static final String DEFAULT_QUANTITY_OF_PRODUCT_ADDED="1";

    public AmazonCartScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public boolean isUserOnCartPage() {

        LOGGER.debug("On Cart Page");
        visually.checkWindow(SCREEN_NAME,"On Cart page");
        return driver.isElementPresent(byCartSubtotalId);
    }

    @Override
    public boolean isAddedProductFoundInCart(String addedProductName,String addedProductPrice) {
        if(!isCartEmpty()){
            if(getProductTitleInCart().equals(addedProductName)
                //&& getProductPriceInCart().equals(addedProductPrice)
                && getProductQuantityInCart().equals(DEFAULT_QUANTITY_OF_PRODUCT_ADDED)){
                return true;
            }
            else
                return false;

        }
        return false;
    }
    private String getProductTitleInCart(){
        return driver.findElement(byTitleOfProductInCartClass).getText();

    }
    private String getProductPriceInCart(){
        return driver.findElement(byPriceOfProductInCartXpath).getText();

    }
    private String getProductQuantityInCart(){
        return driver.findElement(byQuantityOfProductInCartXpath).getText();
    }
    private boolean isCartEmpty(){
        String cartHeader=driver.findElement(byShoppingCartHeaderXpath).getText();
        if(cartHeader.contains(CART_HEADER_CONTAINS_KEYWORD_EMPTY)){
            return true;
        }
        return false;

    }
}
