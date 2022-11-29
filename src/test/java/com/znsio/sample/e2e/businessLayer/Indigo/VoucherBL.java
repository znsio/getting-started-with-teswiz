package com.znsio.sample.e2e.businessLayer.Indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.INDIGO_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.Indigo.IndigoLandingScreen;
import com.znsio.sample.e2e.screen.Indigo.IndigoVoucherScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import java.util.Map;

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
                .selectDenomination()
                .selectQuantity();
        int denominationVal = (int) context.getTestState(INDIGO_TEST_CONTEXT.DENOMINATION);
        int quantityVal = (int) context.getTestState(INDIGO_TEST_CONTEXT.QUANTITY);
        int totalSumExpected = denominationVal * quantityVal;
        softly.assertThat(Integer.parseInt(TotalAmount)).isEqualTo(totalSumExpected);
        IndigoVoucherScreen.get().personalizeVoucher(System.getProperty("user.name"), "dkcnenckenwkcnwkc")
                .previewVoucher();
        return new PromoCodeBL();
    }
}
