package com.znsio.sample.e2e.screen.web.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.ProductDetailPageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class ProductDetailPageScreenWeb extends ProductDetailPageScreen {

    public static final By byAddToCartId = By.id("add-to-cart-button");
    public static final By validatingSidePannelById = By.id("attach-accessory-pane");
    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = AmazonHomePageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;
    Map<String, String> testData = Runner.getTestDataAsMap(System.getProperty("user.name"));
    String product = testData.get("item");


    public ProductDetailPageScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);
    }

    @Override
    public boolean addProductToCart() {
        driver.findElement(byAddToCartId).click();
        driver.waitTillElementIsPresent(validatingSidePannelById);
        boolean isSidePannelVisible = driver.findElement(validatingSidePannelById).isDisplayed();
        visually.checkWindow(SCREEN_NAME,"Validating "+product+ " added to cart in "+product+ "Detail page");
        if(isSidePannelVisible) {
            LOGGER.info(product+ " added to cart");
            return true;
        } else {
            LOGGER.error(product+" is not added to cart");
            return false;
        }
    }
}
