package com.znsio.sample.e2e.screen.web;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.zomato.ZomatoDishPageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ZomatoDishPageScreenWeb extends ZomatoDishPageScreen {

    public static final By byFoodStatusXpath = By.xpath("(//section[@role='tablist'])[1]//div[@selected]");
    public static final By byDishXpath = By.xpath("(//div[@selected])[2]");
    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = ZomatoDishPageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;

    public ZomatoDishPageScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);
    }

    @Override
    public boolean validateDish(String dish, String foodStatus) {
        driver.waitTillElementIsPresent(byFoodStatusXpath,10);
        String getFoodstatus = driver.findElement(byFoodStatusXpath).getText().trim();
        driver.waitTillElementIsPresent(byDishXpath,10);
        String getDish = driver.findElement(byDishXpath).getText().trim();
        if (foodStatus.equalsIgnoreCase("Dine-out")) {
            foodStatus =  "Dining Out";
        }
        if (dish.equalsIgnoreCase(getDish) && (foodStatus.equalsIgnoreCase(getFoodstatus))) {
            LOGGER.info("Successfully selected" +dish+ "for" +foodStatus);
            return true;
        } else {
            LOGGER.error("Expected dish is"+dish+ "for" +foodStatus);
            LOGGER.error("But getting as" +getDish+ "for" +getFoodstatus);
            return false;
        }
    }

    @Override
    public int getResturantcount() {
        int resturantCount = driver.findElements(By.xpath("//img[@alt='Restaurant Card']")).size();
        LOGGER.info("Resturant available:" +resturantCount);
        return resturantCount;
    }
}
