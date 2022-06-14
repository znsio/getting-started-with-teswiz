package com.znsio.e2e.screen.web.amazon;

import com.znsio.e2e.screen.amazon.MyCartScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.apache.log4j.Logger;

public class MyCartScreenWeb extends MyCartScreen {
    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final Logger LOGGER = Logger.getLogger(MyCartScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = MyCartScreenWeb.class.getSimpleName();

    public MyCartScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Cart screen");
    }

    @Override
    public MyCartScreen verifyCart() {
        driver.findElementByXpath("//span[@class='nav-cart-icon nav-sprite']").click();
        // Verify cart code
        return this;
    }
}
