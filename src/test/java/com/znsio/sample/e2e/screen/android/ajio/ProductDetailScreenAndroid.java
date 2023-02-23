package com.znsio.sample.e2e.screen.android.ajio;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.ajio.ProductDetailScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class ProductDetailScreenAndroid extends ProductDetailScreen {

    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = ProductDetailScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By byProductBrandId = By.id("com.ril.ajio:id/brand_name");
    private static final By byAddToCartId = By.id("com.ril.ajio:id/add_to_cart_tv");
    private static final By byProductSizeXpath = By.xpath("//android.widget.TextView[@resource-id = 'com.ril.ajio:id/row_pdp_fixed_size_tv' and @text = '8']");
    private static final By byProceedToCartId = By.id("com.ril.ajio:id/menu_cart_iv");


    public ProductDetailScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public ProductDetailScreen selectProductSize() {
        LOGGER.info("selectProductSize: Select size for the product");
        removeGuide();
        addToCart();
        driver.waitTillElementIsVisible(byProductSizeXpath).click();
        return this;
    }

    @Override
    public ProductDetailScreen addToCart() {
        LOGGER.info("addToCart: Select add to cart option");
        driver.findElement(byAddToCartId).click();
        return this;
    }

    @Override
    public String getProductBrand() {
        String productBrand = driver.findElement(byProductBrandId).getText();
        LOGGER.info(String.format("getProductBrand: Product brand name: '%s'", productBrand));
        return productBrand;
    }

    @Override
    public ProductDetailScreen proceedToCart() {
        LOGGER.info("proceedToCart: Proceed to the cart");
        driver.waitForClickabilityOf(byProceedToCartId).click();
        return this;
    }
    private ProductDetailScreen removeGuide(){
        LOGGER.info("removeGuide: Remove guide");
        driver.findElement(byProductBrandId).click();
        driver.findElement(byProductBrandId).click();
        return this;
    }
}
