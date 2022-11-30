package com.znsio.sample.e2e.businessLayer.indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherPreviewScreen;
import com.znsio.sample.e2e.screen.indigo.HomeScreen;
import com.znsio.sample.e2e.screen.indigo.PaymentDetailsScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.testng.Assert;

import java.util.Map;

public class GiftVoucherPreviewBL {


    private static final Logger LOGGER = Logger.getLogger(GiftVoucherPreviewBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    private final Map userDetails;

    public GiftVoucherPreviewBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;

        String userSuffix=System.getProperty("user.name");
        userDetails = Runner.getTestDataAsMap(userSuffix);
    }

    public GiftVoucherPreviewBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
        String userSuffix=System.getProperty("user.name");
        userDetails = Runner.getTestDataAsMap(userSuffix);
    }

    public GiftVoucherPreviewBL personalizeAndPreviewTheTotalBasedOnGivenDenominationAndQuantity(String denomination,String qnty)  {
        int totalAmount= HomeScreen.get().selectGiftVoucher().setAndFetchTheAmountBasedOnDenominationAnQuantity(denomination,qnty);
        int expectedTotal= Integer.parseInt(denomination) * Integer.parseInt(qnty);
        Assert.assertEquals(totalAmount,expectedTotal," Amount calculated for given denomination " +
                "and quantity is wrong");
        LOGGER.info("Amount calculated is correct for given denomination and quantity");
        boolean status=GiftVoucherPreviewScreen.get().personalizeGiftVoucher().clickOnPreview().validatePersonalizationOfGiftVoucher();
        softly.assertThat(status).as("Personalization is not done for the user").isEqualTo(true);

        GiftVoucherPreviewScreen.get().clickOnProceed();

        context.addTestState(SAMPLE_TEST_CONTEXT.TOTAL_AMOUNT, totalAmount);

        return this;
    }

    public GiftVoucherPreviewBL setInvalidPromoCode(){

     String invalidErrorMessage =GiftVoucherPreviewScreen.get().applyInvalidPromocode().fetchInvalidPromoCodeMessage();

     Assert.assertEquals(invalidErrorMessage,String.valueOf(userDetails.get("invalidpromocodemessage")),"Invalid promo code message is not displayed");
     LOGGER.info("Invalid promo code message is displayed");
        int expectedTotalAmount = Integer.parseInt(String.valueOf(context.getTestState(SAMPLE_TEST_CONTEXT.TOTAL_AMOUNT)));
     Assert.assertEquals(GiftVoucherPreviewScreen.get().fetchTotalAmountAfterApplyingThePromoCode(),expectedTotalAmount,"Total Gift Voucher amount is changed after applying invalid promo code");
     LOGGER.info("Gift Voucher amount is remained same after applying invalid promo code");
     return this;
    }



    public PaymentDetailsScreen enterUserDetailsAndProceed(){
     return GiftVoucherPreviewScreen.get().setDeliveryOptions().clickOnPayNow();
    }
}
