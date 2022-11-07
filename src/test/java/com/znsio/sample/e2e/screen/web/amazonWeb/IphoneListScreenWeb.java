package com.znsio.sample.e2e.screen.web.amazonWeb;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.IphoneListScreen;
import com.znsio.sample.e2e.screen.amazon.ProductPageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class IphoneListScreenWeb extends IphoneListScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = IphoneListScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final By byResultsText = By.xpath("//span[contains(text(),'RESULTS')]");
    private final By bySearchResults = By.xpath("//span[contains(text(),'Apple iPhone 13')]");
    private final By byFirstProductName = By.xpath("(//span[contains(text(),'iPhone 13')])[3]");

    public IphoneListScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "listing screen");
    }

    public boolean verifyProductName(){
       String firstProductName = driver.findElement(byFirstProductName).getText();
      if(firstProductName.contains("iPhone 13"))
          return true;
      else
          return false;
    }

    public boolean verifyThePresenceOfResultsText(){
        LOGGER.debug("Verify the presence of RESULTS text");
        String checkForResultsText = driver.findElement(byResultsText).getText();
        if(checkForResultsText.equalsIgnoreCase("results"))
        return true;
                else
                  return  false;
    }

    public boolean listCount(){
        LOGGER.debug("Verify the presence of searched products");
        driver.findElements(bySearchResults);
        int result = driver.findElements(bySearchResults).size();
        if(result>0)
            return true;
        else
        return false;
    }

    public ProductPageScreen clickOnIphone(){
        LOGGER.debug("clicking on first product from the list");
        driver.findElement(byFirstProductName).click();
        driver.switchToNextTab();
        LOGGER.debug("Have successfully redirected to the product page");
        return ProductPageScreen.get();
    }
}
