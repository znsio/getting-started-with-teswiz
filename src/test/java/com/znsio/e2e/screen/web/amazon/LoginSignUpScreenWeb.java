package com.znsio.e2e.screen.web.amazon;

import com.znsio.e2e.screen.amazon.LoginSignUpScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.apache.log4j.Logger;

public class LoginSignUpScreenWeb extends LoginSignUpScreen {
    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final Logger LOGGER = Logger.getLogger(LoginSignUpScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = LoginSignUpScreenWeb.class.getSimpleName();

    public LoginSignUpScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Login or SignUp screen");
    }

    @Override
    public LoginSignUpScreen login() {
        System.out.println("login");
        return this;
    }

    @Override
    public LoginSignUpScreen navigateToPage(String page) {
        System.out.println("navigateToPage");
        return this;
    }
}
