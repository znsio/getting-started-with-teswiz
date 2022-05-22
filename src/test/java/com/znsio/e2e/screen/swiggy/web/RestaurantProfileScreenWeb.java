package com.znsio.e2e.screen.swiggy.web;

import com.znsio.e2e.screen.swiggy.RestaurantListingScreen;
import com.znsio.e2e.screen.swiggy.RestaurantProfileScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.util.List;

public class RestaurantProfileScreenWeb extends RestaurantProfileScreen {

    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final Logger LOGGER = Logger.getLogger(RestaurantProfileScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = RestaurantProfileScreenWeb.class.getSimpleName();

    //Locator Object Repo
    private String foodItemXpathStr="//div[@id='menu-content']//h2[contains(text(),'%s')]/parent::div//h3";
    private String CartAddButtonXpathStr = "//div[@id='menu-content']//h2[contains(text(),'%s')]/parent::div//h3/parent::div/parent::div/following-sibling::div//button/img[contains(normalize-space(@alt),'%s')]/parent::button/parent::div/following-sibling::div[contains(@class,'itemAddButton')]//div[contains(text(),'ADD')]";
    private String CartIncrementButtonXpathStr = "//div[@id='menu-content']//h2[contains(text(),'%s')]/parent::div//h3/parent::div/parent::div/following-sibling::div//button/img[contains(normalize-space(@alt),'%s')]/parent::button/parent::div/following-sibling::div[contains(@class,'itemAddButton')]//div[contains(text(),'+')]";
    private String orderCounterOnImageXpathStr = "/following-sibling::div/following-sibling::div";

    public RestaurantProfileScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Restaurant Profile screen");
    }

    @Override
    public RestaurantProfileScreen addFoodItemsToCart(int unitOfItemsToAdd,String foodCategory) {
        LOGGER.info("Adding "+unitOfItemsToAdd+" units of "+foodCategory+" items to cart");
        String itemToBeAddedToCartStr=getFoodItemToBeAdded(1,foodCategory);
        By CartAddButtonXpath = By.xpath(String.format(CartAddButtonXpathStr,foodCategory,itemToBeAddedToCartStr));
        By CartIncrementButtonXpath = By.xpath(String.format(CartIncrementButtonXpathStr,foodCategory,itemToBeAddedToCartStr));
        try {
            for (int i = 0; i < unitOfItemsToAdd; i++) {
                if (i == 0) {
                    driver.waitForClickabilityOf(CartAddButtonXpath, 10).click();
                    LOGGER.info("Clicked ADD Button in Iteration:" + (i + 1));
                } else {
                    driver.moveToElement(CartIncrementButtonXpath);
                    driver.findElement(CartIncrementButtonXpath).click();
                    LOGGER.info("Clicked + Button in Iteration:" + (i + 1));
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return RestaurantProfileScreen.get();
    }

    @Override
    public int getItemsCountFromDishImage(String foodCategory) {
        String itemToBeAddedToCartStr=getFoodItemToBeAdded(1,foodCategory);
        By orderCounterXpath = By.xpath(String.format(CartIncrementButtonXpathStr+orderCounterOnImageXpathStr,foodCategory,itemToBeAddedToCartStr));
        return Integer.parseInt(driver.waitTillElementIsPresent(orderCounterXpath).getText());
    }


    /**
     *
     * @param indexOfItem is a 1 based index expected when method is called
     * @return will send back textual value of Food Item which can be further used in Xpaths for adding that particular item to cart.
     */
    public String getFoodItemToBeAdded(int indexOfItem,String foodCategory){
        String FoodItemToBeAdded = null;
        LOGGER.info("Fetching List of Food Items");
        By foodItemListXpath= By.xpath(String.format(foodItemXpathStr, foodCategory));
        List<WebElement> FoodItems =driver.findElements(foodItemListXpath);
        for(int i=0;i<FoodItems.size();i++){
            if(i==(indexOfItem-1)){
                FoodItemToBeAdded=FoodItems.get(i).getText();
                LOGGER.info("Food Item found at location "+(indexOfItem)+" To Be Added to Cart: "+FoodItemToBeAdded);
                System.out.println("Food Item found at location "+(indexOfItem)+"To Be Added to Cart: "+FoodItemToBeAdded);
                break;
            }
        }
        return FoodItemToBeAdded;

    }





}
