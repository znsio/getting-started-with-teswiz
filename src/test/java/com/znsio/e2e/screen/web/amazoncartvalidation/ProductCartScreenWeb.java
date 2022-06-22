package com.znsio.e2e.screen.web.amazoncartvalidation;

import com.znsio.e2e.screen.amazoncartvalidation.ProductCartScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.openqa.selenium.By;

public class ProductCartScreenWeb extends ProductCartScreen {
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = ProductCartScreenWeb.class.getSimpleName();
    private static final By presentProductTitle=By.xpath("//span[@class='a-truncate-cut']");

    public ProductCartScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Product Cart screen");
    }




    @Override
    public String getTitleOfProductPresentInCart() {
        return driver.findElement(presentProductTitle).getText();
    }
}
