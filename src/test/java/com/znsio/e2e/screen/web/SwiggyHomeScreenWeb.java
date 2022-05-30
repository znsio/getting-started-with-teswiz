package com.znsio.e2e.screen.web;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.SwiggyHomeScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.znsio.e2e.tools.Wait.waitFor;

public class SwiggyHomeScreenWeb extends SwiggyHomeScreen {

    private final Driver driver;
    private final TestExecutionContext context;
    private final Visual visually;
    private final String SCREEN_NAME = SwiggyHomeScreenWeb.class.getSimpleName();
    private final By location = By.id("location");
    private final By locationList = By.xpath("//div[@class='_1oLDb']//span[2]");

    public SwiggyHomeScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Swiggy Home screen");
    }


    public SwiggyHomeScreen addRestaurantLocation(String userEnteredLocation) {
        driver.waitTillElementIsPresent(location).click();
        driver.findElement(location).sendKeys(userEnteredLocation);
        driver.waitTillElementIsPresent(locationList);
        List<WebElement> list = driver.findElements(locationList);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getText().contains(userEnteredLocation)) {
                    context.addTestState(SAMPLE_TEST_CONTEXT.USER_ENTERED_LOCATION,list.get(i).getText());
                    list.get(i).click();
                    waitFor(3);
                    break;
                }
            }
        }

        return this;
    }


}
