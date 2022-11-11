//package com.znsio.sample.e2e.businessLayer.jiomeet;
//
//import com.context.TestExecutionContext;
//import com.znsio.e2e.entities.Platform;
//import com.znsio.e2e.runner.Runner;
//import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
//import com.znsio.sample.e2e.screen.jiomeet.SignInScreen;
//import org.apache.log4j.Logger;
//import org.assertj.core.api.SoftAssertions;
//
//import java.util.Map;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class AuthBL {
//    private static final Logger LOGGER = Logger.getLogger(AuthBL.class.getName());
//    private final TestExecutionContext context;
//    private final SoftAssertions softly;
//    private final String currentUserPersona;
//    private final Platform currentPlatform;
//
//    public AuthBL(String userPersona, Platform forPlatform) {
//        long threadId = Thread.currentThread()
//                              .getId();
//        this.context = Runner.getTestExecutionContext(threadId);
//        softly = Runner.getSoftAssertion(threadId);
//        this.currentUserPersona = userPersona;
//        this.currentPlatform = forPlatform;
//        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
//    }
//
//    public AuthBL() {
//        long threadId = Thread.currentThread()
//                              .getId();
//        this.context = Runner.getTestExecutionContext(threadId);
//        softly = Runner.getSoftAssertion(threadId);
//        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
//        this.currentPlatform = Runner.platform;
//    }
//
//    public LandingBL signIn(Map userDetails) {
//        String username = String.valueOf(userDetails.get("username"));
//        String password = String.valueOf(userDetails.get("password"));
//        String firstName = String.valueOf(userDetails.get("firstName"));
//        String lastName = String.valueOf(userDetails.get("lastName"));
//        String expectedWelcomeMessageAndroid = "Hello " + firstName + " \n" + "what would you like to do?";
//        String expectedWelcomeMessageWeb = "Hello " + firstName + " " + lastName + ", what would you like to do?";
//        String expectedWelcomeMessage = currentPlatform.equals(Platform.web) ? expectedWelcomeMessageWeb : expectedWelcomeMessageAndroid;
//
//        String signedInWelcomeMessage = SignInScreen.get()
//                                                    .signIn(username, password)
//                                                    .getSignedInWelcomeMessage();
//
//        LOGGER.info(String.format("signedInWelcomeMessage: '%s'", signedInWelcomeMessage));
//
//        assertThat(signedInWelcomeMessage).as("Welcome message is incorrect")
//                                          .isEqualTo(expectedWelcomeMessage);
//        return new LandingBL(currentUserPersona, currentPlatform);
//    }
//
//    public InAMeetingBL signInAndStartMeeting(Map userPersona) {
//        return signIn(userPersona).startInstantMeeting();
//    }
//}
