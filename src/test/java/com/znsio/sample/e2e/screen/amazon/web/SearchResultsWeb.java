package com.znsio.sample.e2e.screen.amazon.web;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.SearchResultsScreen;
import com.znsio.sample.e2e.screen.amazon.web.pageFragment.NavBarWeb;
import com.znsio.sample.e2e.screen.theapp.AppLaunchScreen;
import com.znsio.sample.e2e.screen.web.theapp.AppLaunchScreenWeb;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class SearchResultsWeb extends SearchResultsScreen {
    private static final String SCREEN_NAME = AppLaunchScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(AppLaunchScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;

    private final By searchResultsTitles = By.cssSelector("h2.a-size-mini");

    public SearchResultsWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    /**
     * Opens product by {@code index} .
     */
    @Override
    public String openProductByIndex(int index) {
        visually.takeScreenshot(SCREEN_NAME, " Product search results");
        WebElement element = driver.findElements(searchResultsTitles).get(index).findElement(By.tagName("a"));
        String productName = element.getText();
        element.click();

        switchToNewTab();
        driver.waitForClickabilityOf(new NavBarWeb(driver, visually).getSearchInput());
        visually.takeScreenshot(SCREEN_NAME, " Product details page ");
        return productName;
    }

    /**
     * Swiches to new tab .
     */
    private void switchToNewTab() {
        String parent = driver.getInnerDriver().getWindowHandle();
        driver.getInnerDriver().switchTo().window(driver.getInnerDriver().getWindowHandles().stream().filter(s -> !s.equals(parent)).findFirst().get());
    }


    /**
     * Returns {@code List} of seach results .
     */
    @Override
    public List<String> getAllSearchResults() {
        return driver.findElements(searchResultsTitles).stream().map(WebElement::getText).collect(Collectors.toList());

    }
}
