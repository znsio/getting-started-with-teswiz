package com.znsio.sample.e2e.screen.amazon;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.AMAZON_ASSIGNMENT_TEST_CONTEXT;
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
    private static int index=2;
    private static final By productTupleTitle= By.xpath("//div[@data-index="+index+"]//span[@class='a-size-medium a-color-base a-text-normal']");
    private static final By productTuplePrice= By.xpath("//div[@data-index="+index+"]//span[@class='a-price-whole']");


    public ProductListPageScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Search Result Screen");
    }

    @Override
    public String getTupleTitle(){
        LOGGER.info("get product title from Tuple");
        String n= driver.findElement(productTupleTitle).getText().trim();
        return n;
    }

    @Override
    public String getTuplePrice(){
        LOGGER.info("get product price from Tuple");
        return driver.findElement(productTuplePrice).getText().trim();
    }

    @Override
    public ProductLandingPageScreen userSelectsFirstProduct() {
        LOGGER.info("Opening Landing page for selected tuple");
        driver.findElement(productTupleTitle).click();
        return ProductLandingPageScreen.get();
    }
}