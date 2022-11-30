package com.znsio.sample.e2e.businessLayer.indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.screen.indigo.CustomizeVoucherScreen;
import com.znsio.sample.e2e.screen.indigo.PreviewVoucherScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class IndigoVoucherBL {

    private static final Logger LOGGER = Logger.getLogger(IndigoVoucherBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    private final String expectedPreviewMessage = "Preview Your Voucher";
    private final String expectedErrorMessage = "Invalid Promo Code.";

    public IndigoVoucherBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }


    public IndigoVoucherBL personalize(String amount, String quantity)
    {
        LOGGER.info("Personalizing the voucher with custom message.");
        PreviewVoucherScreen previewScreen  = CustomizeVoucherScreen.get().customizeVoucher(amount, quantity);
        assertThat(previewScreen.voucherDetails()).as("Voucher customized message is incorrect")
                .isEqualTo(expectedPreviewMessage);
        return this;
    }

    public IndigoVoucherBL enterInvalidCode() {
        LOGGER.info("Entering and apply invalid promo code.");
        String errorMessage = PreviewVoucherScreen.get().applyAndVerifyPromoCode();
        assertThat(errorMessage).as("Invalid promo message is incorrect")
                .isEqualTo(expectedErrorMessage);
        return this;
    }

    public IndigoVoucherBL proceedToPayment(String amount) {
        LOGGER.info("Fill the details of sender and receiver, and proceed to payment.");
        String finalPaymentAmount = PreviewVoucherScreen.get().fillDetailsAndPay();
        assertThat(finalPaymentAmount).as("Final payment amount is incorrect")
                .isEqualTo(amount);
        return this;

    }
}

