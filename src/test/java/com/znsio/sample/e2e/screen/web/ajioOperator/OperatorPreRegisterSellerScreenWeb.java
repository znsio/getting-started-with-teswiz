package com.znsio.sample.e2e.screen.web.ajioOperator;

import com.applitools.eyes.selenium.fluent.Target;
import com.znsio.sample.e2e.screen.ajioOperator.OperatorManageSellerScreen;
import com.znsio.sample.e2e.screen.ajioOperator.OperatorPreRegisterSellerScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;

public class OperatorPreRegisterSellerScreenWeb extends OperatorPreRegisterSellerScreen {

    private static final Logger LOGGER = Logger.getLogger(OperatorPreRegisterSellerScreenWeb.class.getName());
    private static final String SCREEN_NAME = OperatorPreRegisterSellerScreenWeb.class.getSimpleName();
    private Driver driver;
    private final Visual visually;

    private final By preRegisterSellerTab = By.xpath("//span[text()='Pre-Register New Seller']");
    private static final String fulfilmentTypeByXpath = "//input[@value='%s']";
    private static final String eanTypeByXpath = "//input[@value='%s']";
    private final static  String businessTypeByXpath = "//input[@value='%s']";
    private final By sellerMailTxtBoxById = By.id("outlined-email-input");
    private final By sellerCommercialPhNoTxtBoxById = By.id("commercialPhone");
    private final By sellerMobilePhNoTxtBoxById = By.id("mobileNumber");
    private static final String categoryCheckBoxCss = "input[name='category-%s-FNL'][value='%s']";
    private static final String defaultMarginByCss = "[name=margin-%s-FNL]";
    private final By marginApprovalFiledToUploadB2CById = By.id("marginApprovalMailB2C");
    private final By selectBrandTextBoxByCss =By.cssSelector(".rt-th>[type='text']");
    private final String searchResultOfBrandNameByXpath = "//p[text()='%s']";
    private static final String brandNameCheckBoxByXpath = "//p[text()='%s']/parent::div/preceding-sibling::div";

    private final By preRegisterSellerSubmitBtnByXpath = By.xpath("//span[starts-with(text(),'Submit')]");

    public OperatorPreRegisterSellerScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public OperatorPreRegisterSellerScreen navigateToPreRegisterSellerTab() {
        LOGGER.info("operator on manage seller screen");
        driver.findElement(preRegisterSellerTab).click();
        LOGGER.info("Operator navigate to Pre Register new Seller screen");
        return this;
    }

    @Override
    public OperatorPreRegisterSellerScreen selectFulfilmentType(String fulfilmentType) {
        LOGGER.info(String.format("operator trying to select seller as %s fulfilment type", fulfilmentType));
        By fulfilmentTypeLocator = By.xpath(String.format(fulfilmentTypeByXpath, fulfilmentType.toUpperCase()));
        driver.waitTillElementIsPresent(fulfilmentTypeLocator, 10).click();
        return this;
    }

    @Override
    public OperatorPreRegisterSellerScreen selectEanType(String eanType) {
        LOGGER.info(String.format("operator trying to select seller as %s EAN type", eanType));
        By eanTypeLocator = By.xpath(String.format(eanTypeByXpath, eanType.toUpperCase()));
        driver.findElement(eanTypeLocator).click();
        return this;
    }

    @Override
    public OperatorPreRegisterSellerScreen enterSellerDetails(String sellerEmail, String sellerMobilePhone, String sellerCommercialPhone) {
        LOGGER.info("operator is filling the seller details for Email , sellerMobile and commercialPhone");
        driver.findElement(sellerMailTxtBoxById).clear();
        driver.findElement(sellerMailTxtBoxById).sendKeys(sellerEmail);
        driver.findElement(sellerCommercialPhNoTxtBoxById).clear();
        driver.findElement(sellerCommercialPhNoTxtBoxById).sendKeys(sellerCommercialPhone);
        driver.findElement(sellerMobilePhNoTxtBoxById).clear();
        driver.findElement(sellerMobilePhNoTxtBoxById).sendKeys(sellerMobilePhone);
        return this;
    }

    @Override
    public OperatorPreRegisterSellerScreen selectBusinessTypeCategoriesAndMargin(List<String> businessTypeList, List<String> categories, float defaultMargin) {
        for (String business : businessTypeList) {
            LOGGER.info(String.format("selecting the business type as %s ", business));
            By businessTypeLocator = By.xpath(String.format(businessTypeByXpath, business.toUpperCase()));
            driver.findElement(businessTypeLocator).click();
            for (String category : categories) {
                LOGGER.info(String.format("****selecting the %s category for %s business type", category, business));
                driver.waitTillElementIsPresent(By.cssSelector(String.format(categoryCheckBoxCss, business, category.toUpperCase())), 10).click();
            }
            LOGGER.info(String.format("selecting the default margin as %s for %s business type", defaultMargin, business));
            driver.waitTillElementIsPresent(By.cssSelector(String.format(defaultMarginByCss, business))).sendKeys(String.valueOf(defaultMargin));
        }
        return this;
    }

    @Override
    public OperatorPreRegisterSellerScreen uploadTheMarginApprovalFile(String pdfFile) {
        LOGGER.info("Operator trying to uploading Margin approval pdf");
        LOGGER.info(String.format("path of the file to upload %s",pdfFile));
        driver.waitTillElementIsPresent(marginApprovalFiledToUploadB2CById);
        driver.scrollTillElementIntoView(marginApprovalFiledToUploadB2CById);
        driver.findElement(marginApprovalFiledToUploadB2CById).sendKeys(pdfFile);
        return this;
    }

    @Override
    public OperatorPreRegisterSellerScreen searchForBrandAndSelectBrand(String brandName)  {
        LOGGER.info(String.format("searching the %s brand name",brandName));
        driver.waitTillElementIsPresent(selectBrandTextBoxByCss).sendKeys(brandName);
        try{
            driver.waitTillElementIsPresent(By.xpath(String.format(searchResultOfBrandNameByXpath,brandName.toUpperCase())));
        }catch (NoSuchElementException e){
           throw new NoSuchElementException(String.format("no such %s brand name is available",brandName));
        }
        driver.waitTillElementIsPresent(By.xpath(String.format(brandNameCheckBoxByXpath,brandName.toUpperCase()))).click();
        visually.checkWindow(SCREEN_NAME,String.format("selecting %s brand name",brandName));
        return this;
    }

    @Override
    public boolean isSubmitBtnIsVisible() {
        visually.check(SCREEN_NAME, "pre-register seller button", Target.region(preRegisterSellerSubmitBtnByXpath).strict());
        return driver.findElement(preRegisterSellerSubmitBtnByXpath).isDisplayed();
    }

    @Override
    public OperatorManageSellerScreen navigateToManageSeller() throws InterruptedException {
        LOGGER.info("operator navigating to manage seller ");
        driver.findElement(preRegisterSellerSubmitBtnByXpath).click();
      //  driver.getInnerDriver().manage().timeouts().pageLoadTimeout(1000,TimeUnit.MILLISECONDS);
        Thread.sleep(1500);
        return OperatorManageSellerScreen.get();
    }

}
