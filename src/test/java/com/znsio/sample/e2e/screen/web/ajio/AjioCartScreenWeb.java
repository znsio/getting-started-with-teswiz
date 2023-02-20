package com.znsio.sample.e2e.screen.web.ajio;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.e2e.tools.Wait;
import com.znsio.sample.e2e.screen.ajio.AjioCartScreen;
import net.minidev.json.JSONUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;


public class AjioCartScreenWeb extends AjioCartScreen {

    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AjioCartScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By byAddCouponClassName = By.className("voucher-list-item");
    private static final By byApplyButtonXpath = By.xpath("//button[text()='Apply']");
    private static final By byCouponAppliedClassName = By.className("applied-coupon-section");
    private static final By byCouponSavingsXpath = By.xpath("//section[@id='couponDiscount']/span[@class='price-value discount-price']");
    private static final By byProductNameInCartXpath = By.xpath("//div[@class='product-name']/a");
    private static final By bySelectedProductSizeXpath= By.xpath("//div[@class='cartsize']/div");


    public AjioCartScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "SearchResult page");
    }

    @Override
    public AjioCartScreen addCoupon() {
        LOGGER.info("Adding coupon to Product in cart");
        Wait.waitFor(5);
        driver.waitForClickabilityOf(byAddCouponClassName).click();
        driver.waitForClickabilityOf(byApplyButtonXpath).click();
        return this;
    }

    @Override
    public boolean verifyCouponApplied() {
        LOGGER.info("verifying coupon is applied");
        System.out.println(driver.isElementPresent(byCouponAppliedClassName));
        return driver.isElementPresent(byCouponAppliedClassName);
    }

    @Override
    public double getCouponSavings() {
        LOGGER.info("Fetching coupon savings");
        System.out.println(driver.waitTillElementIsPresent(byCouponSavingsXpath).getText() + "  -000000");
        return Double.parseDouble(driver.waitTillElementIsPresent(byCouponSavingsXpath).getText().substring(1));
    }

    @Override
    public String getProductNameInCart() {
        LOGGER.info("Getting product name in cart");
        return driver.waitTillElementIsPresent(byProductNameInCartXpath).getText();
    }

    @Override
    public String getProductSizeInCart() {
        LOGGER.info("Getting size of product in cart");
        return driver.waitTillElementIsPresent(bySelectedProductSizeXpath).getText();
    }
}
