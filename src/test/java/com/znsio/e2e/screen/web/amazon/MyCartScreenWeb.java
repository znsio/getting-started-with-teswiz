package com.znsio.e2e.screen.web.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.amazon.MyCartScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.junit.Assert;

public class MyCartScreenWeb extends MyCartScreen {
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = MyCartScreenWeb.class.getSimpleName();
    private final TestExecutionContext context;

    public MyCartScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Cart screen");
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
    }

    @Override
    public MyCartScreen verifyCart() {
        driver.findElementByXpath("//span[@class='nav-cart-icon nav-sprite']").click();
        Assert.assertTrue(context.getTestStateAsString("selectedItemName").equals(driver.findElementByXpath("//span[@class='a-truncate-cut']").getText()));
        return this;
    }
}