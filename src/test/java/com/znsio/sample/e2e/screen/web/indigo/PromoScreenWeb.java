package com.znsio.sample.e2e.screen.web.indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.PromoScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PromoScreenWeb extends PromoScreen {
    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private final TestExecutionContext context;
    private static final String SCREEN_NAME = GiftVoucherScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    private final By byPromoCodexpath = By.xpath(("//*[@id=\"PromoCode\"]"));
    private final By byApplyPromoCodeId = By.id("btnApplyPromoCode");
    private final By byPromoCodeMessageXpath = By.xpath("//input[@id=\"PromoCode\"]//following-sibling::div");
    private final By byTotalAmountxpath = By.xpath("(//*[@id=\"lblTotal\"])[1]");
    private final By byTotalPaymentAmountxpath = By.xpath("(//*[@id=\"lblTotal\"])[2]");

    public PromoScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread().getId();
        context = Runner.getTestExecutionContext(threadId);
    }
    @Override
    public  int fetchActualPaymentAmount(){
        int paymentAmount = Integer.parseInt(driver.findElement(byTotalPaymentAmountxpath).getText().split(" ")[1]);
        LOGGER.info("Total amount after selecting Denominator and quantity" +paymentAmount);
        return paymentAmount;

    };

    @Override
    public int getVoucherAmount() {
        int expectedTotalAmount = Integer.parseInt(driver.findElement(byTotalAmountxpath).getText().split(" ")[1]);
        LOGGER.info("Total amount after selecting Denominator and quantity" +expectedTotalAmount);
        return expectedTotalAmount;
    }
    @Override
    public PromoScreenWeb applyPromoCode(String promoCode) {

        LOGGER.info("Applying  promocode in gift voucher screen" + promoCode);
        driver.waitTillElementIsPresent(byPromoCodexpath).sendKeys(promoCode);
        visually.checkWindow(SCREEN_NAME,"Applying PromoCode");
        driver.findElement(byApplyPromoCodeId).click();
        return this;
    }

    @Override
    public String getErrorCode() {
        String errorCode = driver.waitTillElementIsPresent(byPromoCodeMessageXpath).getText();
        visually.checkWindow(SCREEN_NAME,"Invalid Promo Code");
        return errorCode;

    }


}
