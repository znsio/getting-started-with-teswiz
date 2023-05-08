package com.znsio.sample.e2e.screen.ajioOperator;


import com.znsio.sample.e2e.screen.web.ajioOperator.OperatorLoginScreenWeb;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Drivers;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Visual;
import org.apache.commons.lang3.NotImplementedException;


public abstract class OperatorLoginScreen {

    public static final String SCREEN_NAME = OperatorLoginScreen.class.getSimpleName();

    public static OperatorLoginScreen get() {
        Platform platform = Runner.fetchPlatform(Thread.currentThread().getId());
        Visual visually = Drivers.getVisualDriverForCurrentUser(Thread.currentThread().getId());
        switch (platform) {
            case web:
                return new OperatorLoginScreenWeb(Drivers.getDriverForCurrentUser(Thread.currentThread().getId()),visually);
        }
        throw new NotImplementedException(SCREEN_NAME + " is not implemented in " + Runner.getPlatform());
    }

    public abstract OperatorManageSellerScreen loginToSellerCentralPage(String email, String password);
    public abstract boolean isLoginPageSignInDisplayed();

    public abstract boolean operatorOnLoginPage();
}
