package com.znsio.e2e.screen.android;

import com.znsio.e2e.screen.CalcNewScreen;
import com.znsio.e2e.screen.CalculatorScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import static com.znsio.e2e.tools.Wait.waitFor;

public class CalcNewScreenAndroid extends CalcNewScreen {
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = CalcNewScreenAndroid.class.getSimpleName();

    private String commonCalcIdPrefixStr = "com.android2.calculator3:id/%s";
    private By eleOnScreenByClass = By.className("android.widget.EditText");

    public CalcNewScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public CalcNewScreen handlePopupIfPresent() {
        waitFor(1);
        visually.checkWindow(SCREEN_NAME, "Calculator launched");
        boolean isUpgradeAppNotificationElement = driver.isElementPresent(By.id("android:id/button1"));
        if (isUpgradeAppNotificationElement) {
            driver.findElement(By.id("android:id/button1")).click();
            waitFor(1);
        }
        boolean isClingElementPresent = driver.isElementPresent(By.id("com.android2.calculator3:id/cling_dismiss"));
        if (isClingElementPresent) {
            driver.findElementById("com.android2.calculator3:id/cling_dismiss").click();
            waitFor(1);
        }
        visually.checkWindow(SCREEN_NAME, "Calculator popup handled");
        return this;
    }

    @Override
    public CalcNewScreen selectValue(String inputValue) {
        MobileElement inputValueMobEle = (MobileElement) driver.findElementById(String.format(commonCalcIdPrefixStr,"digit"+inputValue));
        inputValueMobEle.click();
        visually.checkWindow(SCREEN_NAME, "Value entered="+inputValue);
        return this;
    }

    @Override
    public String getValueFromscreen() {
        MobileElement onScreenEleMobEle = (MobileElement) driver.findElement(eleOnScreenByClass);
        visually.checkWindow(SCREEN_NAME, "Value on screen should="+eleOnScreenByClass);
        return onScreenEleMobEle.getText();
    }

    @Override
    public CalcNewScreen performOperation(String operator) {
        String mappedOperator="";
        switch (operator){
            case "multiply":
                mappedOperator="mul";
                break;
            case "plus":
                mappedOperator="plus";
                break;
            case "divide":
                mappedOperator="div";
                break;
            case "minus":
                mappedOperator="minus";
                break;
            case "equals":
                mappedOperator="equal";
                break;
            default:
                System.out.println("Invalid choice of operation requested:" +mappedOperator);
                break;
        }
        MobileElement operatorMobEle = (MobileElement) driver.findElementById(String.format(commonCalcIdPrefixStr,mappedOperator));
        operatorMobEle.click();
        return this;
    }
}
