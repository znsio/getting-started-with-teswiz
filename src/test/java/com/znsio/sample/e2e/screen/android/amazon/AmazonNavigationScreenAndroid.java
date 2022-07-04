package com.znsio.sample.e2e.screen.android.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonCartScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonNavigationScreen;
import com.znsio.sample.e2e.screen.amazon.SearchResultScreen;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

public class AmazonNavigationScreenAndroid extends AmazonNavigationScreen {
    private static final String SCREEN_NAME = AmazonNavigationScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private final Driver driver;
    private final Visual visually;

    public AmazonNavigationScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public SearchResultScreen iSearchForProduct(String productName) {
        throw new NotImplementedException(SCREEN_NAME + ":"
                + new Throwable().getStackTrace()[0].getMethodName()
                + NOT_YET_IMPLEMENTED);
    }

    @Override
    public AmazonCartScreen iClickOnCartButton() {
        throw new NotImplementedException(SCREEN_NAME + ":"
                + new Throwable().getStackTrace()[0].getMethodName()
                + NOT_YET_IMPLEMENTED);
    }

}
