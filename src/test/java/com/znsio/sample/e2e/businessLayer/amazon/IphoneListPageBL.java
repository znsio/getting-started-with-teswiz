package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.IphoneListScreen;
import org.apache.log4j.Logger;

import static org.assertj.core.api.Assertions.assertThat;
public class IphoneListPageBL {
    private static final Logger LOGGER = Logger.getLogger(IphoneListPageBL.class.getName());
    private final TestExecutionContext context;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public IphoneListPageBL(String userPersona, Platform forPlatform){
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public IphoneListPageBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public IphoneListPageBL validateIphoneResult() {
        boolean isPhoneNamePresent = IphoneListScreen.get().verifyProductName();
        assertThat(isPhoneNamePresent).isTrue();
        boolean isIphoneListPresent = IphoneListScreen.get().listCount();
        assertThat(isIphoneListPresent).isTrue();
        boolean isResultsTextPresent = IphoneListScreen.get().verifyThePresenceOfResultsText();
        assertThat(isResultsTextPresent).isTrue();
        return this;
    }
    public ProductPageBL clickOnFirstProduct() {
        IphoneListScreen.get().clickOnIphone();
        return new ProductPageBL();
    }
}
