package com.znsio.sample.e2e.businessLayer.linkedInBL;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.linkedInScreen.PostSubmitScreen;
import org.apache.log4j.Logger;
public class PostSubmitBL {

    private static final Logger LOGGER = Logger.getLogger(PostSubmitBL.class.getName());
    private final TestExecutionContext context;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public PostSubmitBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public PostSubmitBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }
    public TimeLineBL submitPost() {
        PostSubmitScreen.get().clickPostButton();
        LOGGER.info("Click on post button");
        return new TimeLineBL();
    }
}
