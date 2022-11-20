package com.znsio.sample.e2e.businessLayer.linkedInBL;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.linkedInScreen.TimeLineScreen;
import org.apache.log4j.Logger;

import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;

public class TimeLineBL {

    private static final Logger LOGGER = Logger.getLogger(TimeLineBL.class.getName());
    private final TestExecutionContext context;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public TimeLineBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public TimeLineBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }
    public TimeLineBL verifyPostContainingTextAndDocument() throws AWTException{
        boolean checkForTextAndDocument = TimeLineScreen.get().isTextAndDocumentPresent();
        assertThat(checkForTextAndDocument).isTrue();
        LOGGER.info("successfully verified post that contains text and document");
        TimeLineScreen.get().deletePost();
        LOGGER.info("successfully deleted the post that contains text and document");
        return this;
    }

    public TimeLineBL verifyPostContainingTextAndVideo() throws AWTException {
        boolean checkForTextAndVideo = TimeLineScreen.get().isTextAndVideoPresent();
        assertThat(checkForTextAndVideo).isTrue();
        LOGGER.info("successfully verified post that contains text and video");
        TimeLineScreen.get().deletePost();
        LOGGER.info("successfully deleted the post that contains text and video");
        return this;
    }

    public TimeLineBL verifyPostContainingPoll() {
        boolean checkForPoll = TimeLineScreen.get().isPollPresent();
        assertThat(checkForPoll).isTrue();
        LOGGER.info("successfully verified post that contains poll");
        return this;
    }

    public TimeLineBL verifyOnlyConnectionsCanViewMyPost() throws AWTException {
        String getProfileName = TimeLineScreen.get().isLoginToLinkedInForUser2();
        assertThat(getProfileName).isEqualToIgnoringCase("ISHANT GUPTA");
        LOGGER.info("login verified for user2");
        boolean isPostVisibleToUser2 = TimeLineScreen.get().isUser2AbleToSeeUser1Post();
        assertThat(isPostVisibleToUser2).isFalse();
        LOGGER.info("successfully verified that user2 is not allowed to view the post of user1");
        return this;
    }

    public TimeLineBL VerifyOnlyConnectionsCanCommentOnMyPost() throws AWTException {
        String getProfileName = TimeLineScreen.get().isLoginToLinkedInForUser2();
        assertThat(getProfileName).isEqualToIgnoringCase("ISHANT GUPTA");
        LOGGER.info("login verified for user2");
        boolean isUser2AbleToMakeAComment = TimeLineScreen.get().isUser2AbleToCommentOnUser1Post();
        assertThat(isUser2AbleToMakeAComment).isTrue();
        LOGGER.info("successfully verified that user2 is not allowed to comment on the post of user1");
        return this;
    }
}
