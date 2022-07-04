package com.znsio.sample.e2e.screen.web.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.ProductScreen;
import com.znsio.sample.e2e.screen.amazon.SearchResultScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultScreenWeb extends SearchResultScreen {
    private static final String SCREEN_NAME = SearchResultScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private final Driver driver;
    private final Visual visually;
    private final TestExecutionContext context;

    private static final By bySearchResultHeaderXpath =By.xpath("//span[@data-component-type='s-result-info-bar']//div[contains(@class,'a-section')]");
    private static final By bySearchResultProductsXpath =By.xpath("//div[@data-component-type='s-search-result']");
    private static final By byProductNameInResultXpath =By.xpath("//div[@data-component-type='s-search-result']//h2/a");
    private static final String textExpectedInSearchCountIndicator="results for";

    public SearchResultScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread().getId();
        this.driver = driver;
        this.visually = visually;
        this.context= Runner.getTestExecutionContext(threadId);
    }

    @Override
    public boolean isUserOnSearchResultPage() {
        LOGGER.debug("On search result Page");
        visually.checkWindow(SCREEN_NAME,"On search results page");
        String searchedProductHeaderText =driver.findElement(bySearchResultHeaderXpath).getText();
        String searchedProduct=context.getTestStateAsString(SAMPLE_TEST_CONTEXT.PRODUCT_SEARCHED);
        if(searchedProductHeaderText.contains(searchedProduct)
            && searchedProductHeaderText.contains(textExpectedInSearchCountIndicator))
            return true;
        return false;
    }

    @Override
    public ProductScreen iClickTheFirstProductInSearchResult() {
        WebElement firstItem=driver.findElement(byProductNameInResultXpath);
        context.addTestState(SAMPLE_TEST_CONTEXT.FIRST_PRODUCT_ON_RESULTS_PAGE,firstItem.getText());
        firstItem.click();
        driver.switchToNextTab();
        return ProductScreen.get();
    }

    @Override
    public boolean isSearchResultEmpty(){
         List<WebElement> productsInSearchResult=driver.findElements(bySearchResultProductsXpath);
         if(productsInSearchResult.isEmpty())
             return true;
         return false;

    }


}
