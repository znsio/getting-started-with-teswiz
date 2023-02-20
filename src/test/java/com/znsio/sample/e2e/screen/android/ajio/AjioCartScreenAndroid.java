package com.znsio.sample.e2e.screen.android.ajio;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.ajio.AjioCartScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class AjioCartScreenAndroid
        extends AjioCartScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AjioCartScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public AjioCartScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public AjioCartScreen addCoupon() {
        return null;
    }

    @Override
    public boolean verifyCouponApplied() {
        return false;
    }

    @Override
    public double getCouponSavings() {
        return 0;
    }

    @Override
    public String getProductNameInCart() {
        return null;
    }

    @Override
    public String getProductSizeInCart() {
        return null;
    }
}