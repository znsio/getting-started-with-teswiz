package com.znsio.sample.e2e.screen.web.ajio;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.ajio.ShoppingCartScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.znsio.e2e.tools.Wait.waitFor;

public class ShoppingCartScreenWeb extends ShoppingCartScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = ShoppingCartScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    private static final By byProductNameCss = By.cssSelector("[class='product-name'] > a > span");
    private static final By byOrderTotalXpath = By.xpath("//section[@id=\"orderTotal\"]//span[contains(@class, 'price-value')]");
    private static final By byFirstVoucherXpath = By.xpath("(//div[@class = 'voucher-list-item']//input)[1]");
    private static final By byApplyOptionClassName = By.className("apply-button");

    public ShoppingCartScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public String getProductBrand() {
        String productName = driver.waitTillElementIsPresent(byProductNameCss).getText().trim();
        visually.checkWindow(SCREEN_NAME, "Shopping cart screen");
        LOGGER.info(String.format("Product present in the cart '%s'", productName));
        return productName;
    }

    @Override
    public double getOrderTotal() {
        waitFor(4);
        String orderTotal = driver.waitTillElementIsPresent(byOrderTotalXpath).getText();
        LOGGER.info(String.format("Order total: '%s'", orderTotal));
        return stringPriceToInteger(orderTotal);
    }

    @Override
    public ShoppingCartScreen selectVoucher() {
        WebElement element = driver.waitTillElementIsPresent(byFirstVoucherXpath);
        LOGGER.info(String.format("First voucher name: '%s'", element.getAttribute("value")));
        element.click();
        return this;
    }

    @Override
    public ShoppingCartScreen applyVoucher() {
        driver.waitForClickabilityOf(byApplyOptionClassName).click();
        return this;
    }

    private double stringPriceToInteger(String orderTotal) {
        String price = orderTotal.substring(1).replace(",", "");
        return Double.parseDouble(price);
    }
}
