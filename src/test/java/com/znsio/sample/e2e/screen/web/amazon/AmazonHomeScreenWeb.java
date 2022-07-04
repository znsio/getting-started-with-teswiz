package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonHomeScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class AmazonHomeScreenWeb extends AmazonHomeScreen {
    private static final String SCREEN_NAME = AmazonHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private final Driver driver;
    private final Visual visually;

    private static final By byHomePageSectionHeaders= By.xpath("//div[contains(@id,'desktop-grid')]//h2");
    private static final String PAGE_TITLE = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";

    public AmazonHomeScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public boolean isUserOnHomePage() {
        LOGGER.debug("Amazon Application is Launched");
        visually.checkWindow(SCREEN_NAME,"On Amazon Launch/Home Screen");
        return driver.getInnerDriver().getTitle().equals(PAGE_TITLE);

    }

}
