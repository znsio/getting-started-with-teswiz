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

    public ProductDetailScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home page");
    }

    @Override
    public ProductDetailScreen selectProductSize() {
        return null;
    }

    @Override
    public ProductDetailScreen addToCart() {
        return null;
    }

    @Override
    public String getProductBrand() {
        return null;
    }

    @Override
    public ProductDetailScreen proceedToCart() {
        return null;
    }
}
