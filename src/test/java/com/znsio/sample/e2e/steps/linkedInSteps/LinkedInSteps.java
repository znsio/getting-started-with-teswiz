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
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        new CreatingPostBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).createPostContainingTextAndDocument();
    }

    @When("I submit the post containing text and document")
    public void iSubmitThePostContainingTextAndDocument() {
        new PostSubmitBL().submitPost();
    }

    @Then("the post containing text and document should be visible on user timeline")
    public void thePostContainingTextAndDocumentShouldBeVisibleOnUserTimeline() throws AWTException {
        new TimeLineBL().verifyPostContainingTextAndDocument();
    }

    @Given("I as a logged in user create a post containing text and video")
    public void iAsALoggedInUserCreateAPostContainingTextAndVideo() throws AWTException {
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        new CreatingPostBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).createPostContainingTextAndVideo();
    }

    @When("I submit the post containing text and video")
    public void iSubmitThePostContainingTextAndVideo() {
        new PostSubmitBL().submitPost();
    }

    @Then("the post containing text and video should be visible on user timeline")
    public void thePostContainingTextAndVideoShouldBeVisibleOnUserTimeline() throws AWTException {
        new TimeLineBL().verifyPostContainingTextAndVideo();
    }

    @Given("I as a logged in user create a post containing poll")
    public void iAsALoggedInUserCreateAPostContainingPoll(){
     allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        new CreatingPostBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).createPostContainingPoll();
    }

    @When("I submit the post containing poll")
    public void iSubmitThePostContainingPoll() {
        new PostSubmitBL().submitPost();
    }

    @Then("the post containing text and poll should be visible on user timeline")
    public void thePostContainingTextAndPollShouldBeVisibleOnUserTimeline() {
        new TimeLineBL().verifyPostContainingPoll();
    }

    @Given("I as a logged in user create a post with view permission to my connections only")
    public void iAsALoggedInUserCreateAPostWithViewPermissionToMyConnectionsOnly() {
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        new CreatingPostBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).createPostWithViewPermissionOnlyToConnections();
    }

    @When("I submit the post containing text")
    public void iSubmitThePostContainingText() {
        new PostSubmitBL().submitPost();
    }

    @Then("user2 in my connection can view my post")
    public void userInMyConnectionCanViewMyPost() throws AWTException {
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME2, Runner.platform, context);
        new TimeLineBL(SAMPLE_TEST_CONTEXT.ME2, Runner.platform).verifyOnlyConnectionsCanViewMyPost();
    }

    @Given("I as a logged in user create a post with comment permission to my connections only")
    public void iAsALoggedInUserCreateAPostWithCommentPermissionToMyConnectionsOnly() {
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        new CreatingPostBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).createPostWithCommentPermissionOnlyToConnections();
    }

    @Then("user2 in my connection can comment on my post")
    public void userInMyConnectionCanCommentOnMyPost() throws AWTException {
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME2, Runner.platform, context);
        new TimeLineBL(SAMPLE_TEST_CONTEXT.ME2, Runner.platform).VerifyOnlyConnectionsCanCommentOnMyPost();
    }
}
