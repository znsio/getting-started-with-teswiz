package com.znsio.sample.e2e.screen.web;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherPreviewScreen;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherScreen;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class GiftVoucherScreenWeb extends GiftVoucherScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = GiftVoucherScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final By byDenominationDropDownId = By.id("SelectedVoucherValue");
    private final By byQuantityDropDownId = By.id("SelectedVoucherQuantity");
    private final By byTotalAmountXpath = By.xpath("//span[@id='lblTotal']");
    private final By byMakePersonalchkboxId = By.id("chkPersonal");
    private final By byDearNameTextBoxId = By.id("Per_Fname");
    private final By byMessageTextBoxId = By.id("Message");
    private final By byPreviewButtonXpath = By.xpath("//input[@class='preview-btn']");



    public GiftVoucherScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }


    @Override
    public String getTotal() {
        LOGGER.info("Total amount for selected denomination and quantity");
        System.out.println("Total amount for selected denomination and quantity before applying promo-code");
        String totalAmount = driver.findElement(byTotalAmountXpath).getText();
        return totalAmount;
    }

    @Override
    public GiftVoucherPreviewScreen personaliseGiftVoucher(String quantity, String amount) {
        LOGGER.info("Selecting denomination for the voucher");
        Select select = new Select(driver.findElement(byDenominationDropDownId));
        select.selectByValue(amount);
        LOGGER.info("Selecting voucher quantity");
        Select sc = new Select(driver.findElement(byQuantityDropDownId));
        sc.selectByValue(quantity);
        LOGGER.info("Personalising Gift Voucher");
        driver.findElement(byMakePersonalchkboxId).click();
        String random = RandomStringUtils.randomAlphabetic(10);
        driver.findElement(byDearNameTextBoxId).sendKeys(random);
        driver.findElement(byMessageTextBoxId).sendKeys(random);
        driver.findElement(byPreviewButtonXpath).click();
        return GiftVoucherPreviewScreen.get();

    }


}
