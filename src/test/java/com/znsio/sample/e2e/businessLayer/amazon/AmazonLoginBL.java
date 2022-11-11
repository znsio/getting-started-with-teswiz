package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.screen.amazon.AmazonLoginScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import java.util.Map;


public class AmazonLoginBL {

    private static final Logger LOGGER = Logger.getLogger(AmazonLoginBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    Map<String, String> testData = Runner.getTestDataAsMap(System.getProperty("user.name"));


    public AmazonLoginBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public AmazonLoginBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
    }


    public HomePageBL loginToAmazon() {
        LOGGER.info("Validating login");
        boolean isLoginDone = AmazonLoginScreen.get()
                .login(testData.get("username"), testData.get("password"));
        softly.assertThat(isLoginDone).isTrue();
        return new HomePageBL();
    }
}
