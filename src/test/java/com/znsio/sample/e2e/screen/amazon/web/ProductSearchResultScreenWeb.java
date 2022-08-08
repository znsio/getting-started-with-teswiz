package com.znsio.sample.e2e.screen.amazon.web;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.ProductDetailsScreen;
import com.znsio.sample.e2e.screen.amazon.ProductSearchResultScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static com.znsio.e2e.tools.Wait.waitFor;

public class ProductSearchResultScreenWeb extends ProductSearchResultScreen {
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = ProductSearchResultScreenWeb.class.getSimpleName();
    private final By resultList = By.xpath("//div[starts-with(@data-cel-widget,'search_result_')]");
    private By firstResultLink = By.xpath("//div[@data-cel-widget='search_result_1']//h2/a");
    private final By sponsoredLink = By.xpath("//span[@data-action='a-popover']/a");

    public ProductSearchResultScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    public boolean verifyResultsDisplayed() {
        waitFor(5);
        return driver.findElements(resultList).size()>1;
    }

    public ProductDetailsScreen openFirstResult() {
//        waitFor(2);
        List<WebElement> sponsoredLinkList = driver.findElements(sponsoredLink);
        System.out.println("Size: "+sponsoredLinkList.size());
        if(sponsoredLinkList.size()>20){
            firstResultLink = By.xpath("//div[@data-cel-widget='search_result_2']//h2/a");
        } else if (sponsoredLinkList.size()>15) {
            firstResultLink = By.xpath("//div[@data-cel-widget='search_result_1']//h2/a");
        }
        driver.findElement(firstResultLink).click();
        waitFor(3);
        driver.switchToNextTab();
        return new ProductDetailsScreenWeb(driver,visually);
    }
}
