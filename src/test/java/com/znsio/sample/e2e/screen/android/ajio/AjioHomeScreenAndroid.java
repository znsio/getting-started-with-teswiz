package com.znsio.sample.e2e.screen.android.ajio;

import com.znsio.sample.e2e.screen.ajio.AjioHomeScreen;
import com.znsio.sample.e2e.screen.ajio.AjioSearchResultsScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class AjioHomeScreenAndroid extends AjioHomeScreen {
    private static final String SCREEN_NAME = AjioHomeScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = LogManager.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By byStartSearchBoxId = By.id("com.ril.ajio:id/llpsTvSearch");
    private static final By bySearchFieldId = By.id("com.ril.ajio:id/searchETV");
    private static final By byStartSearchId = By.id("com.ril.ajio:id/search_image");
    private static final By bySideMenuId = By.id("com.ril.ajio:id/fahIvMenu");
    private static final String byFilterProductXpath = "//android.widget.TextView[@text='%s']";
    private static final By byDismissButtonId = By.id("com.ril.ajio:id/footer_button_2");
    private static final By byUploadPhotoButtonId = By.id("com.ril.ajio:id/layout_select_photo");
    private static final By bySystemPermissionMessageId = By.id(
            "com.android.permissioncontroller:id/permission_message");
    private static final By byAllowButtonId = By.id(
            "com.android.permissioncontroller:id/permission_allow_button");
    private static final By byImageDirectoryXpath = By.xpath(
            "//android.widget.TextView[contains(@text, 'Images')]");
    private static final By byImageXpath = By.xpath(
            "//android.view.ViewGroup[contains(@content-desc,'Photo taken')][1]");
    private final Driver driver;
    private final Visual visually;

    public AjioHomeScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home page");
    }

    @Override
    public AjioSearchResultsScreen searchFor(String product) {
        LOGGER.info(String.format("Search for: '%s'", product));
        driver.waitTillElementIsPresent(byStartSearchBoxId).click();
        WebElement searchBoxElement = driver.waitTillElementIsPresent(bySearchFieldId);
        searchBoxElement.click();
        searchBoxElement.clear();
        searchBoxElement.sendKeys(product);
        visually.checkWindow(SCREEN_NAME, String.format("Search for product: %s", product));
        driver.waitTillElementIsPresent(byStartSearchId).click();
        return AjioSearchResultsScreen.get();
    }

    @Override
    public AjioHomeScreen goToMenu() {
        LOGGER.info("Opening Side Drawer Menu");
        driver.waitTillElementIsVisible(bySideMenuId).click();
        return this;
    }

    @Override
    public AjioSearchResultsScreen selectProductFromCategory(String product, String category, String gender) {
        LOGGER.info(String.format("Selecting %s for %s", product, gender));
        driver.waitTillElementIsVisible(By.xpath(String.format(byFilterProductXpath, gender))).click();
        driver.scrollVertically(20, 60, 50);
        driver.waitTillElementIsVisible(By.xpath(String.format(byFilterProductXpath, category))).click();
        driver.waitTillElementIsVisible(By.xpath(String.format(byFilterProductXpath, product))).click();
        return AjioSearchResultsScreen.get();
    }

    @Override
    public AjioHomeScreen attachFileToDevice(Map imageData) {
        String sourceFileLocation = System.getProperty("user.dir") + imageData.get(
                "IMAGE_FILE_LOCATION");
        String destinationFileLocation = (String) imageData.get("UPLOAD_IMAGE_LOCATION");
        LOGGER.info("searchByImage");

        if (driver.isElementPresent(byDismissButtonId)) {
            driver.findElement(byDismissButtonId).click();
        }

        driver.waitTillElementIsPresent(byStartSearchBoxId).click();
        visually.checkWindow(SCREEN_NAME, "Upload a Photo");
        driver.waitTillElementIsPresent(byUploadPhotoButtonId).click();
        if (driver.isElementPresent(bySystemPermissionMessageId)) {
            driver.waitTillElementIsPresent(byAllowButtonId).click();
        }

        driver.pushFileToDevice(sourceFileLocation, destinationFileLocation);
        LOGGER.info("Image Pushed to Device path" + destinationFileLocation);
        return this;
    }

    @Override
    public AjioSearchResultsScreen searchByImage() {
        driver.waitTillElementIsPresent(byImageDirectoryXpath).click();
        driver.waitTillElementIsPresent(byImageXpath).click();
        return AjioSearchResultsScreen.get();
    }
}
