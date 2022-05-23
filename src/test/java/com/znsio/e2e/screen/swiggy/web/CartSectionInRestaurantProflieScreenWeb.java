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
        private By cartSectionAddedItemsNameXpath = By.xpath("//div[contains(text(),'Cart')]/following-sibling::div//div/i/following-sibling::div");
        private By cartSectionAddedItemsCountXpath = By.xpath("//div[contains(text(),'Cart')]/following-sibling::div//div/i/following-sibling::div/parent::div/following-sibling::div//div[contains(text(),'ADD')]/following-sibling::div[3]");
        private By cartSectionAddedCustomisableItemsCountXpath = By.xpath("//div[contains(text(),'Cart')]/following-sibling::div//div/i/following-sibling::div/parent::div/following-sibling::div//div[contains(text(),'ADD')]/following-sibling::div[4]");
        private By customisableItemIndXpath = By.xpath("//div[contains(text(),'Cart')]/following-sibling::div//div/i/following-sibling::div/button");
        private By cartIsEmptyXpath = By.xpath("//div[contains(text(),'Cart Empty')]");

        public CartSectionInRestaurantProflieScreenWeb(Driver driver, Visual visually) {
            this.driver = driver;
            this.visually = visually;
            visually.takeScreenshot(SCREEN_NAME, "Cart section in Restaurant Profile screen");
        }

        public String getTotalItemsCountInCartSection(){
            try{Thread.sleep(1000);}
            catch(Exception e){e.printStackTrace();}
            return driver.waitTillElementIsPresent(cartSectionTotalItems).getText();
        }

    @Override
    public String getSingleAddedItemNameFromCartSection() {
            return driver.waitTillElementIsPresent(cartSectionAddedItemsNameXpath).getText();
    }

    @Override
    public int getSingleAddedItemCountFromCartSection() {
        if(driver.isElementPresent(customisableItemIndXpath)){
            return Integer.parseInt(driver.waitTillElementIsPresent(cartSectionAddedCustomisableItemsCountXpath).getText());
        }
        else {
            return Integer.parseInt(driver.waitTillElementIsPresent(cartSectionAddedItemsCountXpath).getText());
        }
    }

    @Override
    public boolean isCartEmpty() {
        return driver.isElementPresent(cartIsEmptyXpath);
    }


}
