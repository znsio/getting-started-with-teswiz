package com.znsio.sample.e2e.businessLayer.Indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherPreviewScreen;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherPromocodeScreen;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherScreen;
import com.znsio.sample.e2e.screen.web.GiftVoucherPreviewScreenWeb;
import com.znsio.sample.e2e.screen.web.GiftVoucherPromocodeScreenWeb;
import com.znsio.sample.e2e.screen.indigo.IndigoHomePage;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GiftVoucherBL {

    private static final Logger LOGGER = Logger.getLogger(GiftVoucherBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public GiftVoucherBL(String userPersona, Platform forPlatform){
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public GiftVoucherBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public GiftVoucherBL personaliseAndPreviewGiftVoucher(String quantity, String amount) {
        LOGGER.info(String.format("'%s' Gift vouchers of '%s' are selected", quantity, amount));
        IndigoHomePage.get().goToGiftVoucher().
                personaliseGiftVoucher(quantity,amount).
                previewAndProceed();
        return this;
    }

    public GiftVoucherBL enterInvalidPromoCode() {
        LOGGER.info("On Promocode screen");
        GiftVoucherPromocodeScreen giftVoucherPromocodeScreen = GiftVoucherPromocodeScreen.get();
        String amountBeforePromocode = giftVoucherPromocodeScreen.getAmountOnPromocodeScreen();
        String amountAfterPromocode = giftVoucherPromocodeScreen.applyInvalidPromocode().
                getAmountOnPromocodeScreen();
        String promocodeErrMsg = giftVoucherPromocodeScreen.getPromocodeErrMsg();
        assertThat(promocodeErrMsg).as("Invalid promocode message should be 'Invalid Promo Code.'").isEqualTo("Invalid Promo Code.");
        assertThat(amountBeforePromocode).as("Amount after applying invalid promocode should be same as original amount ").isEqualTo(amountAfterPromocode);
        giftVoucherPromocodeScreen.enterDeliveryOptionDetails();
        return this;
    }
}


