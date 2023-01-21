package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonProductScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonSearchScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AmazonSearchScreenWeb extends AmazonSearchScreen {
    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final String SCREEN_NAME = AmazonSearchScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final Driver driver;
    private final Visual visually;

    private final By bySearchLabelXpath = By.xpath("//span[@class='a-color-state a-text-bold']");
    private final By byFirstSearchProductXpathAxes = By.xpath("//div[@data-cel-widget='search_result_2']/descendant::div[contains(@class,'a-section a-spacing-none puis-padding-right-small')]/descendant::span[@class='a-size-medium a-color-base a-text-normal']");

    public AmazonSearchScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home screen");
    }

    @Override
    public String getTextOfSearchResult() {
        String searchLabelText = driver.findElement(bySearchLabelXpath).getText();
        return searchLabelText;
    }

    @Override
    public AmazonProductScreen clickFirstProductOnSearchResult() {
        driver.waitTillElementIsPresent(byFirstSearchProductXpathAxes, 5).click();
        driver.switchToNextTab();
        visually.checkWindow(SCREEN_NAME, "Product on search");
        return AmazonProductScreen.get();
    }


}
