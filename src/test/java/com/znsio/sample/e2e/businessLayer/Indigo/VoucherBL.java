package com.znsio.sample.e2e.businessLayer.Indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.INDIGO_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.Indigo.IndigoLandingScreen;
import com.znsio.sample.e2e.screen.Indigo.IndigoVoucherScreen;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;


public class VoucherBL {
    private static final Logger LOGGER = Logger.getLogger(VoucherBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;


    public VoucherBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public VoucherBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
    }

    public PromoCodeBL personaliseAndPreviewGiftVoucher() {
        String TotalAmount = IndigoLandingScreen.get()
                .selectGiftVoucher()
                .selectDenomination(1)
                .selectQuantity(1)
                .getTotalAmount();
        int denominationVal = Integer.parseInt(context.getTestState(INDIGO_TEST_CONTEXT.DENOMINATION).toString());
        int quantityVal = Integer.parseInt(context.getTestState(INDIGO_TEST_CONTEXT.QUANTITY).toString());
        int totalSumExpected = denominationVal * quantityVal;
        LOGGER.info("Total sum in Voucher Page " + TotalAmount);
        String TotalSum = TotalAmount.substring(1).trim();
        softly.assertThat(Integer.parseInt(TotalSum)).isEqualTo(totalSumExpected);
        context.addTestState(INDIGO_TEST_CONTEXT.TOTALAMOUNT, TotalSum);
        String randomMessage = RandomStringUtils.randomAlphabetic(20);
        String voucherDetails = IndigoVoucherScreen.get().personalizeVoucher(System.getProperty("user.name"), randomMessage)
                .previewVoucher();
        String messageExpected = context.getTestState(INDIGO_TEST_CONTEXT.DEAR).toString().trim() + ", " + context.getTestState(INDIGO_TEST_CONTEXT.MESSAGE).toString().trim();
        softly.assertThat(voucherDetails).isEqualTo(messageExpected);
        return new PromoCodeBL();
    }
}
