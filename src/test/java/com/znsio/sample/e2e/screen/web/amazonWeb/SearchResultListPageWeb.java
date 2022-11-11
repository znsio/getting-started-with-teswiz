package com.znsio.sample.e2e.screen.web.amazonWeb;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.SearchResultListPageScreen;
import com.znsio.sample.e2e.screen.amazon.ProductPageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchResultListPageWeb extends SearchResultListPageScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = SearchResultListPageWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final By byResultsText = By.xpath("//span[contains(text(),'RESULTS')]");
    private final By byFirstProductName = By.xpath("//span[contains(text(),'iPhone 13')]");

    public SearchResultListPageWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "listing screen");
    }

    public boolean verifyProductName() {
        boolean isTextPresent = false;
        List<WebElement> text = driver.findElements(byFirstProductName);
        for (int i = 0; i < text.size(); i++) {
            if (text.get(i).toString().contains("iPhone")) {
                isTextPresent = true;
                break;
            }
        }
        if (isTextPresent)
            return true;
        else
            return false;
    }

    public boolean verifyThePresenceOfResultsText() {
        LOGGER.debug("Verify the presence of RESULTS text");
        String checkForResultsText = driver.findElement(byResultsText).getText();
        if (checkForResultsText.equalsIgnoreCase("results"))
            return true;
        else
            return false;
    }

    public boolean listCount() {
        LOGGER.debug("Verify the presence of searched products");
        driver.findElements(byFirstProductName);
        int result = driver.findElements(byFirstProductName).size();
        if (result > 0)
            return true;
        else
            return false;
    }

    public ProductPageScreen clickOnIphone() {
        LOGGER.debug("clicking on first product from the searched result");
        driver.waitForClickabilityOf(byFirstProductName);
        List<WebElement> text = driver.findElements(byFirstProductName);
        for (int i = 0; i < text.size(); i++) {
            text.get(1).click();
            break;
        }
        driver.switchToNextTab();
        LOGGER.debug("Have successfully redirected to the product page");
        return ProductPageScreen.get();
    }
}
