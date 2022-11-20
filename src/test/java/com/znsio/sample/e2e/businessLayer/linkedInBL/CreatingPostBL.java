package com.znsio.sample.e2e.businessLayer.linkedInBL;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.linkedInScreen.CreatingPostScreen;
import org.apache.log4j.Logger;
import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;

public class CreatingPostBL {
    private static final Logger LOGGER = Logger.getLogger(CreatingPostBL.class.getName());
    private final TestExecutionContext context;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public CreatingPostBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public CreatingPostBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public PostSubmitBL createPostContainingTextAndDocument() throws AWTException {
        String getProfileName = CreatingPostScreen.get().isLoginToLinkedInForUser1();
        assertThat(getProfileName).containsIgnoringCase("Welcome, Ishant");
        LOGGER.info("login verified for user1 for creating post containing text and document");
        boolean isTextAndDocumentSet = CreatingPostScreen.get().isTextAndDocumentPresentBeforePosting();
        assertThat(isTextAndDocumentSet).isTrue();
        LOGGER.info("text and document is successfully added to the post");
        return new PostSubmitBL();
    }

    public PostSubmitBL createPostContainingTextAndVideo() throws AWTException {
        String getProfileName = CreatingPostScreen.get().isLoginToLinkedInForUser1();
        assertThat(getProfileName).containsIgnoringCase("Welcome, Ishant");
        LOGGER.info("login verified for user1 for creating post containing text and video");
        boolean isTextAndVideoSet = CreatingPostScreen.get().isTextAndVideoPresentBeforePosting();
        assertThat(isTextAndVideoSet).isTrue();
        LOGGER.info("text and video is successfully added to the post");
        return new PostSubmitBL();
    }

    public PostSubmitBL createPostContainingPoll() {
        String getProfileName = CreatingPostScreen.get().isLoginToLinkedInForUser1();
        assertThat(getProfileName).containsIgnoringCase("Welcome, Ishant");
        LOGGER.info("login verified for user1 for creating post containing poll");
        boolean isPollSet = CreatingPostScreen.get().isPollPresentBeforePosting();
        assertThat(isPollSet).isTrue();
        LOGGER.info("poll is successfully added to the post");
        return new PostSubmitBL();
    }

    public PostSubmitBL createPostWithViewPermissionOnlyToConnections() {
        String getProfileName = CreatingPostScreen.get().isLoginToLinkedInForUser1();
        assertThat(getProfileName).containsIgnoringCase("Welcome, Ishant");
        LOGGER.info("login verified for user1 for creating post where only connections can view the post");
        boolean isViewPermissionSet = CreatingPostScreen.get().isViewPermissionOnlyToUser1ConnectionsPresentBeforePosting();
        assertThat(isViewPermissionSet).isTrue();
        LOGGER.info("view permission and text is successfully added to the post");
        return new PostSubmitBL();
    }

    public PostSubmitBL createPostWithCommentPermissionOnlyToConnections() {
        String getProfileName = CreatingPostScreen.get().isLoginToLinkedInForUser1();
        assertThat(getProfileName).containsIgnoringCase("Welcome, Ishant");
        LOGGER.info("login verified for user1 for creating post where only connections can comment on the post");
        boolean isCommentPermissionSet = CreatingPostScreen.get().isCommentPermissionOnlyToUser1ConnectionsPresentBeforePosting();
        assertThat(isCommentPermissionSet).isTrue();
        LOGGER.info("comment permission and text is successfully added to the post");
        return new PostSubmitBL();
    }
}
