package com.znsio.sample.e2e.screen.android.ajio;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.ajio.ShoppingCartScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class ShoppingCartScreenAndroid extends ShoppingCartScreen {

    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = ShoppingCartScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By byProductBrandId = By.id("com.ril.ajio:id/brandInfo");
    private static final By byOrderPriceId = By.id("com.ril.ajio:id/fragment_cart_list_tv_price");
    private static final By byApplyCouponId = By.id("com.ril.ajio:id/changeCoupon");
    private static final By bySelectVoucherXpath = By.xpath("(//android.widget.TextView[@text='Apply coupon'])[1]");
    private static final By byCloseDialogId = By.id("com.ril.ajio:id/close_dialog");


    public ShoppingCartScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public String getProductBrand() {
        closeGuide();
        String productBrand = driver.waitTillElementIsPresent(byProductBrandId).getText();
        LOGGER.info(String.format("getProductBrand: product brand in shopping cart: '%s'", productBrand));
        return productBrand;
    }

    private ShoppingCartScreen closeGuide() {
        LOGGER.info("closeGuide: close user guide");
        driver.waitTillElementIsPresent(byProductBrandId).click();
        return this;
    }

    @Override
    public double getOrderTotal() {
        String grandTotal = driver.findElement(byOrderPriceId).getText();
        LOGGER.info(String.format("getOrderTotal: Order total price '%s'", grandTotal));
        return convertStringPriceToInteger(grandTotal);
    }

    @Override
    public ShoppingCartScreen selectVoucher() {
        LOGGER.info("selectVoucher: Select apply coupon option");
        driver.findElement(byApplyCouponId).click();
        return this;
    }

    @Override
    public ShoppingCartScreen applyVoucher() {
        LOGGER.info("applyVoucher: Select voucher");
        driver.findElement(bySelectVoucherXpath).click();
        closeDialog();
        return this;
    }

    private ShoppingCartScreen closeDialog() {
        LOGGER.info("closeDialog: Close dialog");
        driver.waitTillElementIsPresent(byCloseDialogId).click();
        return this;
    }


    private double convertStringPriceToInteger(String orderTotal) {
        String price = orderTotal.substring(1).replace(",", "");
        return Double.parseDouble(price);
    }
}
