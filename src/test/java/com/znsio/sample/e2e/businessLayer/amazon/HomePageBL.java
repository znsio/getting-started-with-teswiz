package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.screen.amazon.AmazonHomePageScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(AmazonHomePageScreen.get().searchProduct(productToSearch)).as(currentUserPersona + "Added product is not reflected in the Cart page")
                .isTrue();
        return new GridWallPageBL();
    }
}
