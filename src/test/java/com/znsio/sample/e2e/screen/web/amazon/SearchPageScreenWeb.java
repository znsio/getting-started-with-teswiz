package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.ProductPageScreen;
import com.znsio.sample.e2e.screen.amazon.SearchPageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.znsio.sample.e2e.entities.CONTEXT_AMAZON;
import com.context.TestExecutionContext;


public class SearchPageScreenWeb extends SearchPageScreen {

    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = SearchPageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;
    private final WebDriver innerDriver;
    private final By bySearchResultPageMessageBarXpath = By.xpath("//span[@data-component-type='s-result-info-bar']");
    private final By bySearchResultPageNoResultFoundXpath = By.xpath("//span[@data-component-type='s-search-results']");
    private final By byProductWidgetsOnSearchResultPageXpath = By.xpath("//div[contains(@class,'s-card-container')]");
    private final By byWidgetTitleChildXpath = By.xpath(".//span[contains(@class,'a-text-normal')]");
    private final By byWidgetClickableElemChildXpath = By.xpath(".//span[@data-component-type='s-product-image']");

    public SearchPageScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);
    }

    @Override
    public boolean isSearchResultPageLoaded() {
        return false;
    }

    @Override
    public String getSearchResultLabelText() {
        return null;
    }

    @Override
    public SearchPageScreen waitTillSearchResultMessageIsDisplayed() {
        driver.waitTillElementIsPresent(bySearchResultPageMessageBarXpath);
        return this;
    }

    @Override
    public ProductPageScreen selectProductFromSearchResultPage() {
        String widgetTitle;

        //grabbing first search result card
        WebElement firstWidget = driver.findElement(byProductWidgetsOnSearchResultPageXpath);

        //fetching card title
        widgetTitle = firstWidget.findElement(byWidgetTitleChildXpath).getText().trim();
        context.addTestState(CONTEXT_AMAZON.PRODUCT_SELECTED, widgetTitle);

        //complete card is not clickable, clicking on specific element
        firstWidget.findElement(byWidgetClickableElemChildXpath).click();
        driver.switchToNextTab();

        return waitForSelectProductPageScreenToLoad();
    }

    private ProductPageScreen waitForSelectProductPageScreenToLoad() {
        ProductPageScreen productPageScreen = ProductPageScreen.get().waitTillProductPageIsDisplayed();
        return productPageScreen;
    }
}
