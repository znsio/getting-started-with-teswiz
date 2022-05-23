package com.znsio.e2e.screen.swiggy.web;


import com.znsio.e2e.screen.swiggy.RestaurantProfileScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Random;

public class RestaurantProfileScreenWeb extends RestaurantProfileScreen {

    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final Logger LOGGER = Logger.getLogger(RestaurantProfileScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = RestaurantProfileScreenWeb.class.getSimpleName();

    //Locator Object Repo
    private int indexOfFoodItemToAdd=1;
    private static String foodCategory;
    //private String CartAddButtonXpathStr = "//div[@id='menu-content']//h2[contains(text(),'%s')]/parent::div//h3/parent::div/parent::div/following-sibling::div//button/img[contains(normalize-space(@alt),'%s')]/parent::button/parent::div/following-sibling::div[contains(@class,'itemAddButton')]//div[contains(text(),'ADD')]";
    //private String CartIncrementButtonXpathStr = "//div[@id='menu-content']//h2[contains(text(),'%s')]/parent::div//h3/parent::div/parent::div/following-sibling::div//button/img[contains(normalize-space(@alt),'%s')]/parent::button/parent::div/following-sibling::div[contains(@class,'itemAddButton')]//div[contains(text(),'+')]";
    //private String orderCounterOnImageXpathStr = "/following-sibling::div/following-sibling::div";
    private String foodItemXpathStr="//div[@id='menu-content']//h2[contains(text(),'%s')]/parent::div//h3";
    private String CartAddButtonXpathStr = "//div[@id='menu-content']//h2[contains(text(),'%s')]/parent::div//div[@itemscope][%s]//div[contains(text(),'ADD')]";
    private String CartIncrementButtonXpathStr = "//div[@id='menu-content']//h2[contains(text(),'%s')]/parent::div//div[@itemscope][%s]//div[contains(text(),'+')]";
    private String CartMultipleIncrementButtonXpathStr = "//div[@id='menu-content']//h2[contains(text(),'%s')]/parent::div//div[@itemscope][%s]//div[contains(text(),'+')][2]";
    private String CartDecrementButtonXpathStr = "//div[@id='menu-content']//h2[contains(text(),'%s')]/parent::div//div[@itemscope][%s]//div[contains(text(),'+')]/following-sibling::div[1]";
    private String CartDecrementButtonCustomisableXpathStr = "//div[@id='menu-content']//h2[contains(text(),'%s')]/parent::div//div[@itemscope][%s]//div[contains(text(),'+')]/following-sibling::div[2]";
    private String orderCounterOnImageXpathStr = "//div[@id='menu-content']//h2[contains(text(),'%s')]/parent::div//div[@itemscope][%s]//div[contains(text(),'+')]//following-sibling::div/following-sibling::div";
    private String customisableOrderCounterOnImageXpathStr = "//div[@id='menu-content']//h2[contains(text(),'%s')]/parent::div//div[@itemscope][%s]//div[contains(text(),'+')]//following-sibling::div[not(contains(text(),'Customisable'))][3]";
    private By addItemBtnXpath = By.xpath("//span[contains(text(),'Add Item')]");
    private By repeatLastSelXpath = By.xpath("//button[contains(text(),'REPEAT LAST')]");
    public RestaurantProfileScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Restaurant Profile screen");
    }

    @Override
    public RestaurantProfileScreen addFoodItemsToCart(int unitOfItemsToAdd,String foodCategory) {
        LOGGER.info("Adding "+unitOfItemsToAdd+" units of "+foodCategory+" items to cart");
        //String itemToBeAddedToCartStr=getFoodItemToBeAdded(indexOfFoodItemToAdd,foodCategory);
        this.foodCategory=foodCategory; //Setting up Food category at class level for further use
        By CartAddButtonXpath = By.xpath(String.format(CartAddButtonXpathStr,foodCategory,indexOfFoodItemToAdd));
        By CartIncrementButtonXpath = By.xpath(String.format(CartIncrementButtonXpathStr,foodCategory,indexOfFoodItemToAdd));
        By CartIncrementButtonXpathUpd = By.xpath(String.format(CartMultipleIncrementButtonXpathStr,foodCategory,indexOfFoodItemToAdd));
        int itemModificationsAvailable=driver.findElements(CartIncrementButtonXpath).size();
        try {
            for (int i = 0; i < unitOfItemsToAdd; i++) {
                if (i == 0) {
                    if(itemModificationsAvailable>1){
                        driver.waitForClickabilityOf(CartAddButtonXpath, 10).click();
                        LOGGER.info("Clicked ADD Button in Iteration:" + (i + 1));
                        driver.waitForClickabilityOf(addItemBtnXpath).click();
                        LOGGER.info("Clicked ADD ITEMS in Iteration:" + (i + 1));
                        Thread.sleep(3000);
                    }
                    else {
                        driver.waitForClickabilityOf(CartAddButtonXpath, 10).click();
                        LOGGER.info("Clicked ADD Button in Iteration:" + (i + 1));
                    }
                }
                else {
                    if(itemModificationsAvailable>1){
                        driver.moveToElement(CartIncrementButtonXpathUpd);
                        driver.findElement(CartIncrementButtonXpathUpd).click();
                        LOGGER.info("Clicked + Button in Iteration:" + (i + 1));
                        Thread.sleep(3000);
                        driver.findElement(repeatLastSelXpath).click();
                        //driver.waitForClickabilityOf(By.xpath("//button[contains(text(),'REPEAT LAST')]")).click();
                        LOGGER.info("Clicked REPEAT LAST Button in Iteration:" + (i + 1));
                    }
                    else{
                        driver.moveToElement(CartIncrementButtonXpath);
                        driver.findElement(CartIncrementButtonXpath).click();
                        LOGGER.info("Clicked + Button in Iteration:" + (i + 1));
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return RestaurantProfileScreen.get();
    }

    /**
     *
     * @param foodCategory is passed in from feature file
     *                     This method generates an random number in order to decide values to be added to cart: ((max-min)+min)+min
     * @return             Page object for Restaurant profile for performing further actions.
     */
    @Override
    public RestaurantProfileScreen addFoodItemsToCart(String foodCategory) {
        Random random = new Random();
        int unitOfItemsToAdd = random.nextInt((4-1)+1)+1;
        LOGGER.info("Adding "+unitOfItemsToAdd+" units of "+foodCategory+" items to cart");
        //String itemToBeAddedToCartStr=getFoodItemToBeAdded(indexOfFoodItemToAdd,foodCategory);
        this.foodCategory=foodCategory; //Setting up Food category at class level for further use
        By CartAddButtonXpath = By.xpath(String.format(CartAddButtonXpathStr,foodCategory,indexOfFoodItemToAdd));
        By CartIncrementButtonXpath = By.xpath(String.format(CartIncrementButtonXpathStr,foodCategory,indexOfFoodItemToAdd));
        By CartIncrementButtonXpathUpd = By.xpath(String.format(CartMultipleIncrementButtonXpathStr,foodCategory,indexOfFoodItemToAdd));
        try {
            int itemModificationsAvailable=driver.findElements(CartIncrementButtonXpath).size();
            for (int i = 0; i < unitOfItemsToAdd; i++) {
                if (i == 0) {
                    if(itemModificationsAvailable>1){
                        driver.waitForClickabilityOf(CartAddButtonXpath, 10).click();
                        LOGGER.info("Clicked ADD Button in Iteration:" + (i + 1));
                        driver.waitForClickabilityOf(addItemBtnXpath).click();
                        LOGGER.info("Clicked ADD ITEMS in Iteration:" + (i + 1));
                        Thread.sleep(3000);
                    }
                    else {
                        driver.waitForClickabilityOf(CartAddButtonXpath, 10).click();
                        LOGGER.info("Clicked ADD Button in Iteration:" + (i + 1));
                    }
                }
                else {
                    if(itemModificationsAvailable>1){
                        driver.moveToElement(CartIncrementButtonXpathUpd);
                        driver.findElement(CartIncrementButtonXpathUpd).click();
                        LOGGER.info("Clicked + Button in Iteration:" + (i + 1));
                        Thread.sleep(3000);
                        driver.findElement(repeatLastSelXpath).click();
                        //driver.waitForClickabilityOf(By.xpath("//button[contains(text(),'REPEAT LAST')]")).click();
                        LOGGER.info("Clicked REPEAT LAST Button in Iteration:" + (i + 1));
                    }
                    else{
                    driver.moveToElement(CartIncrementButtonXpath);
                    driver.findElement(CartIncrementButtonXpath).click();
                    LOGGER.info("Clicked + Button in Iteration:" + (i + 1));
                    }
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
        //String itemToBeAddedToCartStr=getFoodItemToBeAdded(indexOfFoodItemToAdd,foodCategory);
        By CartIncrementButtonXpath = By.xpath(String.format(CartIncrementButtonXpathStr,foodCategory,indexOfFoodItemToAdd));
        By orderCounterXpath = By.xpath(String.format(orderCounterOnImageXpathStr,foodCategory,indexOfFoodItemToAdd));
        By customisableOrderCounterOnImageXpath = By.xpath(String.format(customisableOrderCounterOnImageXpathStr,foodCategory,indexOfFoodItemToAdd));
        int itemModificationsAvailable=driver.findElements(CartIncrementButtonXpath).size();
        LOGGER.info("Customizable if count greater than 1:"+itemModificationsAvailable);
        if(itemModificationsAvailable==1){
             return Integer.parseInt(driver.waitTillElementIsPresent(orderCounterXpath).getText());}
        else{
            return Integer.parseInt(driver.waitTillElementIsPresent(customisableOrderCounterOnImageXpath).getText());}
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
        System.out.println(String.format(foodItemXpathStr, foodCategory));
        List<WebElement> FoodItems =driver.findElements(foodItemListXpath);
        for(int i=0;i<FoodItems.size();i++){
            if(i==(indexOfItem-1)){
                FoodItemToBeAdded=FoodItems.get(i).getText();
                LOGGER.info("Food Item found at location "+(indexOfItem)+" To Be Added to Cart: "+FoodItemToBeAdded);
                break;
            }
        }
        return FoodItemToBeAdded;

    }

    public String getFoodItemNameToBeAdded(){
        String NameOfFoodItemAdded = getFoodItemToBeAdded(indexOfFoodItemToAdd,foodCategory);
        return NameOfFoodItemAdded;
    }

    public int getFoodItemOrderCount(){
        return getItemsCountFromDishImage(foodCategory);
    }

    @Override
    public RestaurantProfileScreen incrementCartValue() {
        By CartIncrementButtonXpath = By.xpath(String.format(CartIncrementButtonXpathStr,foodCategory,indexOfFoodItemToAdd));
        By CartIncrementButtonXpathUpd = By.xpath(String.format(CartMultipleIncrementButtonXpathStr,foodCategory,indexOfFoodItemToAdd));
        try {
            int itemModificationsAvailable = driver.findElements(CartIncrementButtonXpath).size();
            if (itemModificationsAvailable == 1) {
                driver.moveToElement(CartIncrementButtonXpath);
                driver.waitForClickabilityOf(CartIncrementButtonXpath).click();
                LOGGER.info("Clicked + Button in Restaurant Profile Section to Increment Value for Non-Customisable item");
            } else {
                driver.moveToElement(CartIncrementButtonXpathUpd);
                driver.findElement(CartIncrementButtonXpathUpd).click();
                Thread.sleep(3000);
                driver.findElement(repeatLastSelXpath).click();
                LOGGER.info("Clicked + Button in Restaurant Profile Section to Increment Value for Customisable item");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public RestaurantProfileScreen decrementCartValueFromProfileSection(int itemsAlreadyAdded) {
        By CartIncrementButtonXpath = By.xpath(String.format(CartIncrementButtonXpathStr,foodCategory,indexOfFoodItemToAdd));
        By CartDecrementButtonXpath = By.xpath(String.format(CartDecrementButtonXpathStr,foodCategory,indexOfFoodItemToAdd));
        By CartDecrementButtonCustomXpath = By.xpath(String.format(CartDecrementButtonCustomisableXpathStr,foodCategory,indexOfFoodItemToAdd));
        for(int i=0;i<itemsAlreadyAdded;i++){
            int itemModificationsAvailable = driver.findElements(CartIncrementButtonXpath).size();
            if(itemModificationsAvailable==1) {
                driver.moveToElement(CartDecrementButtonXpath);
                driver.findElement(CartDecrementButtonXpath).click();
                LOGGER.info("Clicked - Button in Iteration:" + (i + 1));
            }
            else{
                driver.moveToElement(CartDecrementButtonCustomXpath);
                driver.findElement(CartDecrementButtonCustomXpath).click();
                LOGGER.info("Clicked - Button for customisable item in Iteration:" + (i + 1));
            }
        }
        return RestaurantProfileScreen.get();
    }


}
