package com.znsio.sample.e2e.screen.android.ajio;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.ajio.AjioCartScreen;
import com.znsio.sample.e2e.screen.ajio.ProductDetailsScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class ProductDetailsScreenAndroid
        extends ProductDetailsScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = ProductDetailsScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By byNumberOfProductsFoundId = By.id("com.ril.ajio:id/toolbar_subtitle_tv");


    public ProductDetailsScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public ProductDetailsScreen selectSizeAndAddProductToBag(String productSize) {
        return null;
    }

    @Override
    public AjioCartScreen goToBag() {
        return AjioCartScreen.get();
    }

    @Override
    public String getNameOfselectedProduct() {
        return null;
    }
}