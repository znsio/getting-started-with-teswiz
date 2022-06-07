package com.znsio.e2e.screen;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.web.SwiggyHomeScreenWeb;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartScreenWeb extends CartScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = SwiggyHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final By cartCounter = By.xpath("//a[@href='/checkout']/span/span");
    private final By cartItem = By.xpath("//div[@class='_33KRy']");
    private final TestExecutionContext context;
    private final List<String> cartItems = new ArrayList<>();

    public CartScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, " Cart screen for Swiggy");
    }

    @Override
    public boolean verifyContentAdd() {
        String expectedItem = "";
        boolean contentChecker = true;
        List<WebElement> cartItemsObjects = driver.findElements(cartItem);
        for (int cartItemCounter = 0; cartItemCounter < cartItemsObjects.size(); cartItemCounter++) {
            cartItems.add(cartItemsObjects.get(cartItemCounter).getText());
        }
        for (int ithItem = 1; ithItem <= (Integer) context.getTestState("ExpectedCartCount"); ithItem++) {
            //check if Item name x is = null then skip the loop and continue
            expectedItem = context.getTestState("Item name " + ithItem).toString();
            if (!cartItems.contains(expectedItem))
                contentChecker = false;
        }
        return contentChecker;
    }

    @Override
    public boolean verifyContentRemove(){
        String expectedItem = "";
        boolean contentChecker = true;
        List<WebElement> cartItemsObjects = driver.findElements(cartItem);
        for (int cartItemCounter = 0; cartItemCounter < cartItemsObjects.size(); cartItemCounter++) {
            cartItems.add(cartItemsObjects.get(cartItemCounter).getText());
        }
        for (int ithItem = 1; ithItem <= (Integer) context.getTestState("ExpectedCartCount"); ithItem++) {
            expectedItem = context.getTestState("Removed item name " + ithItem).toString();
            if (cartItems.contains(expectedItem))
                contentChecker = false;
        }
        return contentChecker;
    }

    @Override
    public String getCartCounter() {
        //"//a[@href='/checkout']/span/span"
        return driver.waitForClickabilityOf(cartCounter).getText();
    }
}