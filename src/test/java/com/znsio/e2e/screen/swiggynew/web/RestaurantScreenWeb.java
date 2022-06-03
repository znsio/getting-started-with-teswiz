package com.znsio.e2e.screen.swiggynew.web;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.swiggynew.RestaurantScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import static com.znsio.e2e.tools.Wait.waitFor;

public class RestaurantScreenWeb extends RestaurantScreen {

    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final Logger LOGGER = Logger.getLogger(LandingScreenWeb.class.getName());
    private final Driver driver;
    private final TestExecutionContext context;
    private final Visual visually;
    private final String SCREEN_NAME = LandingScreenWeb.class.getSimpleName();
    private int indexOfFoodItemToAdd = 1;
    private static String foodCategory;

    private String foodItemXpathStr = "//div[@id='menu-content']//h2[contains(text(),'%s')]/parent::div//h3[contains(@class,'itemNameText')]";
    private String CartAddButtonXpathStr = "//div[@id='menu-content']//h2[contains(text(),'%s')]/parent::div//div[@itemscope][%s]//div[contains(text(),'ADD')]";
    private String CartIncrementButtonXpathStr = "//div[@id='menu-content']//h2[contains(text(),'%s')]/parent::div//div[@itemscope][%s]//div[contains(text(),'+')]";
    private String CartMultipleIncrementButtonXpathStr = "//div[@id='menu-content']//h2[contains(text(),'%s')]/parent::div//div[@itemscope][%s]//div[contains(text(),'+')][2]";
    private String CartDecrementButtonXpathStr = "//div[@id='menu-content']//h2[contains(text(),'%s')]/parent::div//div[@itemscope][%s]//div[contains(text(),'+')]/following-sibling::div[1]";
    private String CartDecrementButtonCustomisableXpathStr = "//div[@id='menu-content']//h2[contains(text(),'%s')]/parent::div//div[@itemscope][%s]//div[contains(text(),'+')]/following-sibling::div[2]";
    private String orderCounterOnImageXpathStr = "//div[@id='menu-content']//h2[contains(text(),'%s')]/parent::div//div[@itemscope][%s]//div[contains(text(),'+')]//following-sibling::div/following-sibling::div";
    private String customisableOrderCounterOnImageXpathStr = "//div[@id='menu-content']//h2[contains(text(),'%s')]/parent::div//div[@itemscope][%s]//div[contains(text(),'+')]//following-sibling::div[not(contains(text(),'Customisable'))][3]";
    private By addItemBtnXpath = By.xpath("//span[contains(text(),'Add Item')]");
    private By continueBtnXpath = By.xpath("//span[contains(text(),'Continue')]");
    private By repeatLastSelXpath = By.xpath("//button[contains(text(),'REPEAT LAST')]");
    private String foodCategoriesListXpathStr = "//div[@id='menu-content']//a[%s]/div";

    public RestaurantScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        visually.takeScreenshot(SCREEN_NAME, "Restaurant screen");
    }

    @Override
    public RestaurantScreen addRandomItemToCart() {
        Random random = new Random();
        int unitOfItemsToAdd = random.nextInt((4 - 1) + 1) + 1;
        context.addTestState(SAMPLE_TEST_CONTEXT.UNIT_OF_ITEMS_TO_ADD, unitOfItemsToAdd);
        //System.out.println("Testing for context here=========="+((Integer)context.getTestState(SAMPLE_TEST_CONTEXT.UNIT_OF_ITEMS_TO_ADD)+40));//Thisssss
        //String foodCategory = driver.findElements(foodCategoriesListXpath).get(0).getText();
        By foodCategoriesListXpath = By.xpath(String.format(foodCategoriesListXpathStr, indexOfFoodItemToAdd));
        String foodCategory = driver.waitTillElementIsPresent(foodCategoriesListXpath).getText();
        RestaurantScreenWeb.foodCategory = foodCategory;
        LOGGER.info("Adding " + unitOfItemsToAdd + " units of " + foodCategory + " items to cart");
        //String itemToBeAddedToCartStr=getFoodItemToBeAdded(indexOfFoodItemToAdd,foodCategory);
        By CartAddButtonXpath = By.xpath(String.format(CartAddButtonXpathStr, foodCategory, indexOfFoodItemToAdd));
        By CartIncrementButtonXpath = By.xpath(String.format(CartIncrementButtonXpathStr, foodCategory, indexOfFoodItemToAdd));
        By CartIncrementButtonXpathUpd = By.xpath(String.format(CartMultipleIncrementButtonXpathStr, foodCategory, indexOfFoodItemToAdd));
        try {
            int itemModificationsAvailable = driver.findElements(CartIncrementButtonXpath).size();
            for (int i = 0; i < unitOfItemsToAdd; i++) {
                if (i == 0) {//Steps for adding the first unit of item
                    if (itemModificationsAvailable > 1) {//Steps when item is customisable
                        driver.waitForClickabilityOf(CartAddButtonXpath, 10).click();
                        LOGGER.info("Clicked ADD Button in Iteration:" + (i + 1));
                        waitFor(1);

                        if (driver.isElementPresent(addItemBtnXpath)) {//Add Item Flow
                            driver.waitForClickabilityOf(addItemBtnXpath, 10).click();
                            LOGGER.info("Clicked ADD ITEMS in Iteration:" + (i + 1));
                            waitFor(1);
                        } else {//Continue->Add Item Flow
                            driver.waitForClickabilityOf(continueBtnXpath).click();
                            LOGGER.info("Clicked Continue in Iteration:" + (i + 1));
                            waitFor(1);
                            driver.waitForClickabilityOf(addItemBtnXpath, 10).click();
                            LOGGER.info("Clicked ADD ITEMS in Iteration:" + (i + 1));
                            waitFor(1);
                        }


                    } else {//Steps when Item is non-customizable
                        driver.waitForClickabilityOf(CartAddButtonXpath, 10).click();
                        LOGGER.info("Clicked ADD Button in Iteration:" + (i + 1));
                        waitFor(1);
                    }
                    //addFirstUnitOfRandomItem(itemModificationsAvailable,CartAddButtonXpath,i);
                } else {//Steps for adding subsequent unit of items
                    if (itemModificationsAvailable > 1) {//Steps when item is customisable
                        driver.moveToElement(CartIncrementButtonXpathUpd);
                        driver.waitForClickabilityOf(CartIncrementButtonXpathUpd).click();
                        LOGGER.info("Clicked + Button in Iteration:" + (i + 1));
                        waitFor(1);
                        driver.findElement(repeatLastSelXpath).click();
                        LOGGER.info("Clicked REPEAT LAST Button in Iteration:" + (i + 1));
                        waitFor(1);
                    } else {//Steps when Item is non-customizable
                        driver.moveToElement(CartIncrementButtonXpath);
                        driver.waitForClickabilityOf(CartIncrementButtonXpath).click();
                        LOGGER.info("Clicked + Button in Iteration:" + (i + 1));
                        waitFor(1);
                    }
                    //addSubsequentUnitsOfRandomItem(itemModificationsAvailable,CartIncrementButtonXpathUpd,CartIncrementButtonXpath,i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    private void addFirstUnitOfRandomItem(int itemModificationsAvailable, By CartAddButtonXpath, int i) {
        if (itemModificationsAvailable > 1) {//Steps when item is customisable
            driver.waitForClickabilityOf(CartAddButtonXpath, 10).click();
            LOGGER.info("Clicked ADD Button in Iteration:" + (i + 1));
            waitFor(1);

            if (driver.isElementPresent(addItemBtnXpath)) {//Add Item Flow
                driver.waitForClickabilityOf(addItemBtnXpath, 10).click();
                LOGGER.info("Clicked ADD ITEMS in Iteration:" + (i + 1));
            } else {//Continue->Add Item Flow
                driver.waitForClickabilityOf(continueBtnXpath).click();
                LOGGER.info("Clicked Continue in Iteration:" + (i + 1));
                driver.waitForClickabilityOf(addItemBtnXpath, 10).click();
                LOGGER.info("Clicked ADD ITEMS in Iteration:" + (i + 1));
            }


        } else {//Steps when Item is non-customizable
            driver.waitForClickabilityOf(CartAddButtonXpath, 10).click();
            LOGGER.info("Clicked ADD Button in Iteration:" + (i + 1));
        }
    }

    private void addSubsequentUnitsOfRandomItem(int itemModificationsAvailable, By CartIncrementButtonXpathUpd, By CartIncrementButtonXpath, int i) {
        if (itemModificationsAvailable > 1) {//Steps when item is customisable
            driver.moveToElement(CartIncrementButtonXpathUpd);
            driver.waitForClickabilityOf(CartIncrementButtonXpathUpd).click();
            LOGGER.info("Clicked + Button in Iteration:" + (i + 1));
            driver.findElement(repeatLastSelXpath).click();
            LOGGER.info("Clicked REPEAT LAST Button in Iteration:" + (i + 1));
        } else {//Steps when Item is non-customizable
            driver.moveToElement(CartIncrementButtonXpath);
            driver.waitForClickabilityOf(CartIncrementButtonXpath).click();
            LOGGER.info("Clicked + Button in Iteration:" + (i + 1));
        }
    }

    @Override
    public String getFoodItemNameToBeAdded() {
        String NameOfFoodItemAdded = getFoodItemToBeAdded(indexOfFoodItemToAdd, foodCategory);
        return NameOfFoodItemAdded;
    }

    @Override
    public int getFoodItemOrderCount() {
        return getItemsCountFromDishImage(foodCategory);
    }

    private int getItemsCountFromDishImage(String foodCategory) {
        By CartIncrementButtonXpath = By.xpath(String.format(CartIncrementButtonXpathStr, foodCategory, indexOfFoodItemToAdd));
        By orderCounterXpath = By.xpath(String.format(orderCounterOnImageXpathStr, foodCategory, indexOfFoodItemToAdd));
        By customisableOrderCounterOnImageXpath = By.xpath(String.format(customisableOrderCounterOnImageXpathStr, foodCategory, indexOfFoodItemToAdd));
        int itemModificationsAvailable = driver.findElements(CartIncrementButtonXpath).size();
        LOGGER.info("Customizable if count greater than 1:" + itemModificationsAvailable);
        if (itemModificationsAvailable == 1) {
            return Integer.parseInt(driver.waitTillElementIsPresent(orderCounterXpath).getText());
        } else {
            return Integer.parseInt(driver.waitTillElementIsPresent(customisableOrderCounterOnImageXpath).getText());
        }
    }

    @Override
    public RestaurantScreen decrementCartValueFromProfileSection(int itemsAlreadyAdded) {
        By CartIncrementButtonXpath = By.xpath(String.format(CartIncrementButtonXpathStr, foodCategory, indexOfFoodItemToAdd));
        By CartDecrementButtonXpath = By.xpath(String.format(CartDecrementButtonXpathStr, foodCategory, indexOfFoodItemToAdd));
        By CartDecrementButtonCustomXpath = By.xpath(String.format(CartDecrementButtonCustomisableXpathStr, foodCategory, indexOfFoodItemToAdd));
        for (int i = 0; i < itemsAlreadyAdded; i++) {
            int itemModificationsAvailable = driver.findElements(CartIncrementButtonXpath).size();
            if (itemModificationsAvailable == 1) {
                driver.moveToElement(CartDecrementButtonXpath);
                driver.findElement(CartDecrementButtonXpath).click();
                LOGGER.info("Clicked - Button in Iteration:" + (i + 1));
            } else {
                driver.moveToElement(CartDecrementButtonCustomXpath);
                driver.findElement(CartDecrementButtonCustomXpath).click();
                LOGGER.info("Clicked - Button for customisable item in Iteration:" + (i + 1));
            }
        }
        return this;
    }

    /**
     * @param indexOfItem is a 1 based index expected when method is called
     * @return will send back textual value of Food Item which can be further used in Xpaths for adding that particular item to cart.
     */
    private String getFoodItemToBeAdded(int indexOfItem, String foodCategory) {
        String FoodItemToBeAdded = null;
        LOGGER.info("Fetching List of Food Items");
        By foodItemListXpath = By.xpath(String.format(foodItemXpathStr, foodCategory));
        //System.out.println(String.format(foodItemXpathStr, foodCategory));
        List<WebElement> FoodItems = driver.findElements(foodItemListXpath);
        for (int i = 0; i < FoodItems.size(); i++) {
            if (i == (indexOfItem - 1)) {
                FoodItemToBeAdded = FoodItems.get(i).getText();
                LOGGER.info("Food Item found at location " + (indexOfItem) + " To Be Added to Cart: " + FoodItemToBeAdded);
                break;
            }
        }
        return FoodItemToBeAdded;

    }


}

