package com.znsio.sample.e2e.businessLayer.indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherScreen;
import com.znsio.sample.e2e.screen.indigo.IndigoHomeScreen;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import static org.assertj.core.api.Assertions.assertThat;



public class IndigoGiftVoucherBL {
    private static final Logger LOGGER = Logger.getLogger(IndigoGiftVoucherBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;
    public IndigoGiftVoucherBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }
    public IndigoGiftVoucherBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }
    public BuyGiftVoucherBL personaliseAndPreviewVoucher(String denomination, String quantity) throws InterruptedException {
        String message = RandomStringUtils.randomAlphabetic(20);
        String titleName = RandomStringUtils.randomAlphabetic(5);
        int voucherPrice = Integer.parseInt(denomination)*Integer.parseInt(quantity);
        LOGGER.info("Personalising Gift Voucher");
        boolean verifyPreviewVoucher = IndigoHomeScreen.get().goToGiftVoucherSection().
                addDenominationAndQuantity(denomination, quantity).
                personaliseGiftVoucher(titleName, message).
                previewVoucher().
                verifyVoucherDetails(titleName,message,voucherPrice);
        assertThat(verifyPreviewVoucher).as("Voucher Details Incorrect in Preview").isTrue();
        boolean verifyNavigationToPurchaseDetails = GiftVoucherScreen.get().proceedToBuy();
        assertThat(verifyNavigationToPurchaseDetails).as("Failed to navigate to Purchase gift voucher page").isTrue();
        return new BuyGiftVoucherBL();

    }
}
