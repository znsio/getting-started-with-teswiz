package com.znsio.sample.e2e.screen.amazon.web;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.LaunchScreen;
import org.apache.log4j.Logger;

public class LaunchScreenWeb extends LaunchScreen {

    private static final String SCREEN_NAME = LaunchScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(LaunchScreenWeb.class.getName());
    private static final String PAGE_TITLE = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
    private final Driver driver;
    private final Visual visually;


    public LaunchScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public boolean verifyPageTitle() {
        visually.takeScreenshot(SCREEN_NAME, " To verify page title");
        return driver.getInnerDriver().getTitle().equals(PAGE_TITLE);
    }
}
