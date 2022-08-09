package com.znsio.sample.e2e.screen.AmazonScreens.AmazonWeb;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.AmazonShopping.AMAZON_SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.AmazonScreens.ProductLandingPageScreen;
import com.znsio.sample.e2e.screen.AmazonScreens.ProductListPageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ProductListPageScreenWeb extends ProductListPageScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = ProductListPageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;
    private static final By byListOfProductsXpath = By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']");
    private String firstProductTitle;
    private static final By byproductPriceCXpath = By.xpath("//div[@class='s-widget-container s-spacing-small s-widget-container-height-small celwidget slot=MAIN template=SEARCH_RESULTS widgetId=search-results_1']/div/div/div/div/div/div[2]/div/div/div[3]/div/div/div/div[2]/a/span[1]/span[2]/span[2]");
    private String firstProductPrice;
    private String title;
    private boolean compare;
    private String resultFor;
    private By byResultFor = By.xpath("//*[@id='search']/span/div/h1/div/div[1]/div/div/span[3]");


    public ProductListPageScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Product List Screen");
    }

    @Override
    public List<WebElement> getProductLinksList() {
        List<WebElement> ProductList = driver.findElements(byListOfProductsXpath);
        context.addTestState(AMAZON_SAMPLE_TEST_CONTEXT.PRODUCT_LIST, ProductList);
        return ProductList;
    }

    @Override
    public boolean validateListOfProducts(String Product, List<WebElement> productList) {
        resultFor = driver.findElement(byResultFor).getText();
        String[] arrResultFor = resultFor.split("\"", 3);
        String showingResultFor = arrResultFor[1];
        if (showingResultFor.equals(Product)) {
            for (WebElement item : productList) {
                title = item.getText();
                compare = title.contains(Product);
                if (compare) {
                    continue;
                } else {
                    break;
                }
            }
            return compare;
        }
    return false;
    }

    @Override
    public String getFirstProductTittle(List<WebElement> productList) {
        firstProductTitle = productList.get(0).getText();
        context.addTestState(AMAZON_SAMPLE_TEST_CONTEXT.FIRST_PRODUCT_TITLE, firstProductTitle);
        return firstProductTitle;
    }

    @Override
    public String getFirstProductPrice() {
        firstProductPrice = driver.findElement(byproductPriceCXpath ).getText();
        context.addTestState(AMAZON_SAMPLE_TEST_CONTEXT.FIRST_PRODUCT_PRICE, firstProductPrice);
        return firstProductPrice;
    }

    @Override
    public ProductLandingPageScreen userSelectsFirstProduct(List<WebElement> productList) {
        productList.get(0).click();
        return ProductLandingPageScreen.get();
    }
}