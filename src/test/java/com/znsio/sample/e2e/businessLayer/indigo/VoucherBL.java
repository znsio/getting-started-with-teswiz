package com.znsio.sample.e2e.businessLayer.indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Randomizer;
import com.znsio.sample.e2e.entities.INDIGO_VOUCHER_CONTEXT;
import com.znsio.sample.e2e.screen.indigo.LandingPageScreen;
import com.znsio.sample.e2e.screen.indigo.PromoScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;


import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertThat;

public class VoucherBL {
    private static final Map<String, String> data = new LinkedHashMap<>();
    private static final Logger LOGGER = Logger.getLogger(VoucherBL.class.getName());
    public final TestExecutionContext context;
    private final SoftAssertions softly;
    Map<String, String> testData = Runner.getTestDataAsMap(System.getProperty("user.name"));


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
        data.put(INDIGO_VOUCHER_CONTEXT.RECEIVER,Randomizer.randomizeString(5));
        data.put(INDIGO_VOUCHER_CONTEXT.MESSAGE, Randomizer.randomizeAlphaNumericString(10));
        data.put(INDIGO_VOUCHER_CONTEXT.PROMOCODE, Randomizer.randomizeAlphaNumericString(5));

    }


    public VoucherBL personalizeGiftVoucher(String quantity, String amount) {
        LOGGER.info("Personalizing Indigo Gift Voucher and Previewing it.");
       softly.assertThat(LandingPageScreen.get().navigateToGiftVoucher().configureVoucher(quantity,amount).personaliseGiftVoucher(data).previewVoucher());
         return this;

    }

    public VoucherBL applyPromoCode(){
        PromoScreen promoScreen = PromoScreen.get();
        int expectedTotalAmount = promoScreen.getVoucherAmount();
        LOGGER.info("Expected Voucher Amount : " + expectedTotalAmount);
        data.put(INDIGO_VOUCHER_CONTEXT.EXPECTED_AMOUNT,String.valueOf(expectedTotalAmount));
        String errorCode = promoScreen.applyPromoCode(data.get(INDIGO_VOUCHER_CONTEXT.PROMOCODE)).getErrorCode();
        softly.assertThat(errorCode).contains("Invalid Promo Code");
        return this;

    }
    public VoucherBL validatePurchase(){
        int actualTotalAmount = PromoScreen.get().fetchActualPaymentAmount();
        softly.assertThat(data.get(INDIGO_VOUCHER_CONTEXT.EXPECTED_AMOUNT)).isEqualTo(String.valueOf(actualTotalAmount));
        return  this;

    }
}


