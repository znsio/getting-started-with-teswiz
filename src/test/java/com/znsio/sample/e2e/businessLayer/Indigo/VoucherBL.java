package com.znsio.sample.e2e.businessLayer.Indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.screen.Indigo.IndigoLandingScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import java.util.Map;

public class VoucherBL {
    private static final Logger LOGGER = Logger.getLogger(VoucherBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    Map<String, String> testData = Runner.getTestDataAsMap(System.getProperty("user.name")); // put in cukes
    private String giftVoucherOption = testData.get("value"); // Start with private
    String denomination = testData.get("denomination");
    String quantity = testData.get("quantity");




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
        IndigoLandingScreen.get()
                .selectGiftVoucher()
                .selectDenomination(testData.get("denomination"))
                .selectQuantity(quantity);
   //             .personalizeVoucher(dear_tag,message);
    //    .previewVoucher();
        return new PromoCodeBL();
    }
}
