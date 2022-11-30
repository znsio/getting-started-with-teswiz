package com.znsio.sample.e2e.screen.web.indigo;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.CustomizeVoucherScreen;
import com.znsio.sample.e2e.screen.indigo.PreviewVoucherScreen;
import com.znsio.sample.e2e.screen.web.theapp.AppLaunchScreenWeb;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class CustomizeVoucherScreenWeb extends CustomizeVoucherScreen {

    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final Logger LOGGER = Logger.getLogger(AppLaunchScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = CustomizeVoucherScreenWeb.class.getSimpleName();
    private final By bySelectedVoucherValueId = By.id("SelectedVoucherValue");
    private final By bySelectedVoucherQuantityId = By.id("SelectedVoucherQuantity");
    private final By byMakePersonalId = By.id("chkPersonal");
    private final By byDearId = By.id("Per_Fname");
    private final By byMessageId = By.id("Message");

    private final By byPreview_btnXpath = By.xpath("//input[@type='submit']");

    public CustomizeVoucherScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Indigo Voucher Customize Screen");
    }

    @Override
    public PreviewVoucherScreen customizeVoucher(String amount, String quantity) {
        Select denomination_select = new Select(driver.findElement(bySelectedVoucherValueId));
        denomination_select.selectByValue(amount);
        Select quantity_select = new Select(driver.findElement(bySelectedVoucherQuantityId));
        quantity_select.selectByValue(quantity);
        driver.findElement(byMakePersonalId).click();
        driver.findElement(byDearId).sendKeys("Karan");
        driver.findElement(byMessageId).sendKeys("Gift for you!");
        driver.waitForClickabilityOf(byPreview_btnXpath,10).submit();
        return PreviewVoucherScreen.get();

    }
}
