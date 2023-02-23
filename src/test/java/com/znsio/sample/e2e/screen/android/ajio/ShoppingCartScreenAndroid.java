package com.znsio.sample.e2e.screen.android.ajio;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.ajio.ShoppingCartScreen;
import org.apache.log4j.Logger;

public class ShoppingCartScreenAndroid extends ShoppingCartScreen {

    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = ShoppingCartScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";

    public ShoppingCartScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home page");
    }

    @Override
    public String getProductBrand() {
        return null;
    }

    @Override
    public double getOrderTotal() {
        return 0;
    }

    @Override
    public ShoppingCartScreen selectVoucher() {
        return null;
    }

    @Override
    public ShoppingCartScreen applyVoucher() {
        return null;
    }
}
