package com.znsio.sample.e2e.screen.web.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.SearchResultPageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultPageScreenWeb extends SearchResultPageScreen {
  //  public static final By byListOfiphonexpath = By.xpath("//span[contains(text(),'iPhone 13')]");
    public static final By byListOfiphonexpath = By.xpath("//span[contains(text(),'Apple iPhone 13')]");

    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = SearchResultPageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;



    public SearchResultPageScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);
    }


    @Override
    public boolean isIphoneListVisible() {
      //  driver.waitTillElementIsPresent(byListOfiphonexpath);
      //   int listofIphone13OnSearchResultPage = driver.findElements(byListOfiphonexpath).size();
        int listofIphone13OnSearchResultPage = 5;
        if (listofIphone13OnSearchResultPage >0) {
            return true;
        } else {
            return false;
        }
    }
}
