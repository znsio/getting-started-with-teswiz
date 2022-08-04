package com.znsio.sample.e2e.screen.web.jiomeet;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.jiomeet.LandingScreen;
import com.znsio.sample.e2e.screen.jiomeet.SignInScreen;
import org.apache.commons.lang3.NotImplementedException;

public class SignInScreenWeb
        extends SignInScreen {
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = SignInScreenWeb.class.getSimpleName();
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";

    public SignInScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public LandingScreen signIn(String username, String password) {
        throw new NotImplementedException(SCREEN_NAME + ":" + new Throwable().getStackTrace()[0].getMethodName() + NOT_YET_IMPLEMENTED);
    }
}
