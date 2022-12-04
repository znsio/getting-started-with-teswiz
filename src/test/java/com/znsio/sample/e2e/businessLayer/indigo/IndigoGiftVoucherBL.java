package com.znsio.sample.e2e.businessLayer.indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.indigo.INDIGO_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.indigo.IndigoGiftVoucherScreen;
import com.znsio.sample.e2e.screen.indigo.IndigoHomePageScreen;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;


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

    public PromoCodeBL personaliseAndPreviewVoucherWithAmount(String denomination, String quantity)
    {
        int denominationValue = Integer.parseInt(denomination);
        int quantityValue = Integer.parseInt(quantity);
        int expectedVoucherAmount = denominationValue*quantityValue;
        LOGGER.info("Total expected voucher value is " + expectedVoucherAmount);
        IndigoGiftVoucherScreen indigoGiftVoucherScreen = IndigoHomePageScreen.get().clickGiftVoucher().addVoucherWithAmount(denominationValue, quantityValue);
        int actualVoucherAmount = indigoGiftVoucherScreen.fetchVoucherTotalAmount();
        LOGGER.info("Actual amount that is displayed is " + actualVoucherAmount);
        softly.assertThat(actualVoucherAmount).as("Actual displayed voucher amount is not equal to expected voucher amount").isEqualTo(expectedVoucherAmount);
        context.addTestState(INDIGO_TEST_CONTEXT.TOTALVOUCHERAMOUNT, actualVoucherAmount);
        String senderName = RandomStringUtils.randomAlphabetic(6);
        LOGGER.info("Voucher sender name is "+ senderName);
        String voucherMessage = RandomStringUtils.randomAlphabetic(10);
        LOGGER.info("Personalised voucher message is " + voucherMessage);
        indigoGiftVoucherScreen.personalizeVoucher(senderName,voucherMessage).previewVoucher(senderName,voucherMessage);

        return new PromoCodeBL();
    }
}
