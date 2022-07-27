package com.znsio.sample.e2e.screen.amazon.web;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.SearchResultsScreen;
import com.znsio.sample.e2e.screen.amazon.web.pageFragment.NavBarScreenWeb;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class SearchResultsScreenWeb extends SearchResultsScreen {
    private static final String SCREEN_NAME = SearchResultsScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SearchResultsScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;

    private final By searchResultsTitles = By.cssSelector("h2.a-size-mini");

    public SearchResultsScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    /**
     * Opens product by {@code index} .
     */
    @Override
    public String openProductByIndex(int productIndexInSearchResults) {
        visually.takeScreenshot(SCREEN_NAME, " Product search results");
        WebElement element = driver.findElements(searchResultsTitles).get(productIndexInSearchResults).findElement(By.tagName("a"));
        String productName = element.getText();
        element.click();

        switchToNewTab();//driver.switchToNextTab();
        driver.waitForClickabilityOf(new NavBarScreenWeb(driver, visually).getSearchInputByLocator());
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

    @Override
    public List<String> getAllSearchResults() {
        driver.waitForClickabilityOf(searchResultsTitles);
        return driver.findElements(searchResultsTitles).stream().map(WebElement::getText).collect(Collectors.toList());

    }
}
