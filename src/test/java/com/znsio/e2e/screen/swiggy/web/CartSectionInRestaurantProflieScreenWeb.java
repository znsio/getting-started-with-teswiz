package com.znsio.e2e.screen.swiggy.web;

import com.znsio.e2e.screen.swiggy.CartSectionInRestaurantProfileScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class CartSectionInRestaurantProflieScreenWeb extends CartSectionInRestaurantProfileScreen {

        private final Logger LOGGER = Logger.getLogger(CartSectionInRestaurantProflieScreenWeb.class.getName());
        private final Driver driver;
        private final Visual visually;
        private final String SCREEN_NAME = CartSectionInRestaurantProflieScreenWeb.class.getSimpleName();
        private By cartSectionTotalItems = By.xpath("//div[text()='Cart']/div");

        public CartSectionInRestaurantProflieScreenWeb(Driver driver, Visual visually) {
            this.driver = driver;
            this.visually = visually;
            visually.takeScreenshot(SCREEN_NAME, "Cart section in Restaurant Profile screen");
        }

        public String getItemsCountInCartSection(){
            return driver.waitTillElementIsPresent(cartSectionTotalItems).getText();
        }


}
