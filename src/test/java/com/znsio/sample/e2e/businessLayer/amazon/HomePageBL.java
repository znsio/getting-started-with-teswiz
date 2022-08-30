package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.screen.amazon.AmazonGridWallPageScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonHomePageScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class HomePageBL {
    private static final Logger LOGGER = Logger.getLogger(HomePageBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public HomePageBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public GridWallPageBL searchProduct(String productToSearch)  {
        AmazonHomePageScreen.get().searchProduct(productToSearch);
        return new GridWallPageBL();
    }
}
