package com.znsio.sample.e2e.screen.web.zomato;

import com.applitools.eyes.selenium.fluent.Target;
import com.znsio.sample.e2e.screen.zomato.DeliveryScreen;
import com.znsio.sample.e2e.screen.zomato.DineOutScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class DeliveryScreenWeb extends DeliveryScreen {
    private static final String SCREEN_NAME = DeliveryScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final Driver driver;
    private final Visual visually;
    private final By byDineOutXpath = By.xpath("//div[text()='Dining Out']");
    private final By byDeliveryTabXpath = By.xpath("//div[text()='Delivery']");

    public DeliveryScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public boolean isDeliveryWindowVisible() {
        driver.waitTillElementIsVisible(byDeliveryTabXpath);
        visually.check(SCREEN_NAME, "Delivery tab", Target.region(byDeliveryTabXpath));
        return driver.isElementPresent(byDeliveryTabXpath);
    }

    @Override
    public DineOutScreen clickDineOutTab() {
        driver.waitTillElementIsVisible(byDineOutXpath);
        driver.findElement(byDineOutXpath).click();
        return DineOutScreen.get();
    }
}
