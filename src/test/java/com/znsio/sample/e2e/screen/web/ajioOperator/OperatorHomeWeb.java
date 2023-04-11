package com.znsio.sample.e2e.screen.web.ajioOperator;




import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.businessLayer.ajioOperator.OperatorLoginBL;
import com.znsio.sample.e2e.screen.ajioOperator.OperatorHomeScreen;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class OperatorHomeWeb extends OperatorHomeScreen {
    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final String SCREEN_NAME = OperatorHomeWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final Driver driver;
    private final Visual visually;

    private final By profileBtnByXpath = By.xpath("//button[@class='jss264 jss258 jss259']");
    private final By logoutBtnByXpath = By.xpath("//div[@class='jss455 jss199']");

    public OperatorHomeWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
      //  visually.checkWindow(SCREEN_NAME, "home screen");
    }


    @Override
    public OperatorLoginBL logOff() {
        driver.findElementByXpath("profileBtnByXpath").click();
        visually.checkWindow(SCREEN_NAME, "page logout");
        driver.waitTillElementIsPresent(logoutBtnByXpath,5).click();
        driver.findElementByXpath("logoutBtnByXpath").click();
        return null;
    }
}
