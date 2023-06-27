package com.znsio.sample.e2e.businessLayer.ajioShopping;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.ajioShopping.AjioHomePageScreen;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Runner;
import org.assertj.core.api.SoftAssertions;

import java.util.Map;

public class AjioHomePageBL {
    private final String currentUserPersona;
    private final Platform currentPlatform;
    private final TestExecutionContext context;
    private final SoftAssertions softly;

    public AjioHomePageBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public AjioHomePageBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.getPlatform();
    }

    public ProductListBL signIn(Map userDetails, String product) {
        String username = String.valueOf(userDetails.get("username"));
        String password = String.valueOf(userDetails.get("password"));
        String firstName = String.valueOf(userDetails.get("firstName"));

        boolean isSignedIn = AjioHomePageScreen.get().signIn(username, password, firstName);
        softly.assertThat(isSignedIn).as(String.format(firstName+" is not logged In")).isTrue();
        AjioHomePageScreen.get().searchProduct(product);
        return new ProductListBL();
    }
}
