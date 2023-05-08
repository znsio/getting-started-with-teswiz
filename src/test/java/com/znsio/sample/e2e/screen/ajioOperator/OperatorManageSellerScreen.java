package com.znsio.sample.e2e.screen.ajioOperator;

import com.znsio.sample.e2e.screen.web.ajioOperator.OperatorManageSellerScreenWeb;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Drivers;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Visual;
import org.apache.commons.lang3.NotImplementedException;

public abstract class OperatorManageSellerScreen {

    public static final String SCREEN_NAME = OperatorManageSellerScreen.class.getSimpleName();
    public static OperatorManageSellerScreen get() {
        Platform platform = Runner.fetchPlatform(Thread.currentThread().getId());
        Visual visually = Drivers.getVisualDriverForCurrentUser(Thread.currentThread().getId());
        switch (platform) {
            case web:
                return new OperatorManageSellerScreenWeb(Drivers.getDriverForCurrentUser(Thread.currentThread().getId()),visually );
        }
        throw new NotImplementedException(SCREEN_NAME + " is not implemented in " + Runner.getPlatform());
    }
    public abstract OperatorLoginScreen logOutFromHomePage();
    public abstract OperatorManageSellerScreen searchSellerEmailAddress();
    public abstract OperatorManageSellerScreen verifyTheSellerDetails();
    public abstract boolean isManageSellerTitleDisplayed();
    public abstract boolean isSearchFilterFieldDisplayed();
    public abstract boolean VerifySellerRegistrationAndApprovalStatus(String actionItems);
    public abstract  boolean statusFilterIsEnabledOnPendingStatus(String statusItems);
    public abstract OperatorManageSellerScreen verifyThePreRegisterSellerStatusOnManageSeller(String preRegisterSellerMail,String sellerStatus) throws InterruptedException;

}
