package com.znsio.sample.e2e.steps.linkedInSteps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.linkedInBL.CreatingPostBL;
import com.znsio.sample.e2e.businessLayer.linkedInBL.PostSubmitBL;
import com.znsio.sample.e2e.businessLayer.linkedInBL.TimeLineBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

import java.awt.*;

public class LinkedInSteps {
    private static final Logger LOGGER = Logger.getLogger(LinkedInSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public LinkedInSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given("I as a logged in user create a post containing text and document")
    public void iAsALoggedInUserCreateAPostContainingTextAndDocument() throws AWTException {
        LOGGER.info("Filling and verifying all the required details for creating a post containing text and document");
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        new CreatingPostBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).createPostContainingTextAndDocument();
    }

    @When("I submit the post containing text and document")
    public void iSubmitThePostContainingTextAndDocument() {
        LOGGER.info("Clicking on the submit button containing text and document");
        new PostSubmitBL().submitPost();
    }

    @Then("the post containing text and document should be visible on user timeline")
    public void thePostContainingTextAndDocumentShouldBeVisibleOnUserTimeline() throws AWTException {
        LOGGER.info("Verifying the post containing text and document on the user timeline");
        new TimeLineBL().verifyPostContainingTextAndDocument();
    }

    @Given("I as a logged in user create a post containing text and video")
    public void iAsALoggedInUserCreateAPostContainingTextAndVideo() throws AWTException {
        LOGGER.info("Filling and verifying all the required details for creating a post containing text and video");
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        new CreatingPostBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).createPostContainingTextAndVideo();
    }

    @When("I submit the post containing text and video")
    public void iSubmitThePostContainingTextAndVideo() {
        LOGGER.info("Clicking on the submit button containing text and video");
        new PostSubmitBL().submitPost();
    }

    @Then("the post containing text and video should be visible on user timeline")
    public void thePostContainingTextAndVideoShouldBeVisibleOnUserTimeline() throws AWTException {
        LOGGER.info("Verifying the post containing text and video on the user timeline");
        new TimeLineBL().verifyPostContainingTextAndVideo();
    }

    @Given("I as a logged in user create a post containing poll")
    public void iAsALoggedInUserCreateAPostContainingPoll() {
        LOGGER.info("Filling and verifying all the required details for creating a post containing poll");
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        new CreatingPostBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).createPostContainingPoll();
    }

    @When("I submit the post containing poll")
    public void iSubmitThePostContainingPoll() {
        LOGGER.info("Clicking on the submit button containing poll");
        new PostSubmitBL().submitPost();
    }

    @Then("the post containing text and poll should be visible on user timeline")
    public void thePostContainingTextAndPollShouldBeVisibleOnUserTimeline() {
        LOGGER.info("Verifying the post containing text and video on the user timeline");
        new TimeLineBL().verifyPostContainingPoll();
    }

    @Given("I as a logged in user create a post with view permission to my connections only")
    public void iAsALoggedInUserCreateAPostWithViewPermissionToMyConnectionsOnly() {
        LOGGER.info("Providing and verifying the view permission and other details for a post creation");
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        new CreatingPostBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).createPostWithViewPermissionOnlyToConnections();
    }

    @When("I submit the post containing text")
    public void iSubmitThePostContainingText() {
        LOGGER.info("Clicking on the submit button for the post");
        new PostSubmitBL().submitPost();
    }

    @Then("user2 in my connection can view my post")
    public void userInMyConnectionCanViewMyPost() throws AWTException {
        LOGGER.info("Verifying the post with view permission to connections only");
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME2, Runner.platform, context);
        new TimeLineBL(SAMPLE_TEST_CONTEXT.ME2, Runner.platform).verifyOnlyConnectionsCanViewMyPost();
    }

    @Given("I as a logged in user create a post with comment permission to my connections only")
    public void iAsALoggedInUserCreateAPostWithCommentPermissionToMyConnectionsOnly() {
        LOGGER.info("Providing and verifying the comment permission and other details for a post creation");
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        new CreatingPostBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).createPostWithCommentPermissionOnlyToConnections();
    }

    @Then("user2 in my connection can comment on my post")
    public void userInMyConnectionCanCommentOnMyPost() throws AWTException {
        LOGGER.info("Verifying the post with comment permission to connections only");
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME2, Runner.platform, context);
        new TimeLineBL(SAMPLE_TEST_CONTEXT.ME2, Runner.platform).VerifyOnlyConnectionsCanCommentOnMyPost();
    }
}
