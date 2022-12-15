package com.znsio.sample.e2e.businessLayer.indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Randomizer;
import com.znsio.sample.e2e.businessLayer.calculator.CalculatorBL;
import com.znsio.sample.e2e.entities.INDIGO_TEST_CONTEXT;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherScreen;
import com.znsio.sample.e2e.screen.indigo.HomePageScreen;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GiftVoucherBL {

    private static final Logger LOGGER = Logger.getLogger(CalculatorBL.class.getName());
    private final TestExecutionContext context;
    private final Map testData;

    public GiftVoucherBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
        testData = (Map) Runner.getTestDataAsMap(userPersona).get("indigoGiftVoucher");
        getRandomTestDataAsMap();
    }

    public GiftVoucherBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        testData = (Map) Runner.getTestDataAsMap(INDIGO_TEST_CONTEXT.GUEST_USER).get("indigoGiftVoucher");
    }

    public GiftVoucherBL personaliseAndPreviewGiftVoucher() {

        String firstName = (String) context.getTestState(INDIGO_TEST_CONTEXT.FIRST_NAME);
        String message = (String) context.getTestState(INDIGO_TEST_CONTEXT.MESSAGE);
        int denomination = (int) context.getTestState(INDIGO_TEST_CONTEXT.DENOMINATIONS);
        int quantity = (int) context.getTestState(INDIGO_TEST_CONTEXT.QUANTITIES);
        GiftVoucherScreen giftVoucherScreen = HomePageScreen.get().navigateToGiftVoucherScreen().selectDenomination(denomination).selectQuantity(quantity).personaliseMessage(firstName, message);

        assertThat(giftVoucherScreen.getTotalAmountOfGiftCard()).as("Personalize Page : Total Amount Mismatch Found").isEqualTo(context.getTestState(INDIGO_TEST_CONTEXT.TOTAL_AMOUNT));
        assertThat(giftVoucherScreen.previewGiftVoucher(firstName, message, denomination)).as("Preview Page : Total Amount and Message Mismatch Found").isTrue();
        return this;
    }

    public GiftVoucherBL applyPromoCode(String promoCode) {

        GiftVoucherScreen giftVoucherScreen = GiftVoucherScreen.get().applyPromoCode(promoCode);
        assertThat(giftVoucherScreen.getErrorMessage()).as("Error Message Validation Failed").isEqualTo((String) context.getTestState(INDIGO_TEST_CONTEXT.ERROR_MESSAGE));

        List<Integer> totalAmountlist = giftVoucherScreen.getFinalAmountOfGiftCard();
        int calculatedTotalAmount = (int) context.getTestState(INDIGO_TEST_CONTEXT.TOTAL_AMOUNT);
        for (int totalAmount : totalAmountlist) {
            assertThat(totalAmount).as("Final Amount Match Failed with Expected Amount").isEqualTo(calculatedTotalAmount);
        }
        return this;
    }

    public GiftVoucherBL fillTheDetailsAndProceedToPayment() {

        int calculatedTotalAmount = (int) context.getTestState(INDIGO_TEST_CONTEXT.TOTAL_AMOUNT);
        assertThat(GiftVoucherScreen.get().fillTheSenderDetailsAndProceedToPaymentPage(context).getFinalAmount()).as("PaymentPage : Final Amount Match Failed with Calculated Amount").isEqualTo(calculatedTotalAmount);
        return this;
    }

    private void getRandomTestDataAsMap() {

        if (Objects.isNull(context.getTestState(INDIGO_TEST_CONTEXT.TOTAL_AMOUNT))) {
            List<Double> denominations = (List) testData.get(INDIGO_TEST_CONTEXT.DENOMINATIONS);
            List<Double> quantities = (List) testData.get(INDIGO_TEST_CONTEXT.QUANTITIES);
            context.addTestState(INDIGO_TEST_CONTEXT.PROMO_CODES,  Randomizer.randomizeString(8));
            int denomination = denominations.get(getRandomIndex(denominations.size())).intValue();
            int quantity = quantities.get(getRandomIndex(quantities.size())).intValue();
            int totalAmount = denomination * quantity;
            context.addTestState(INDIGO_TEST_CONTEXT.TOTAL_AMOUNT, totalAmount);
            context.addTestState(INDIGO_TEST_CONTEXT.MESSAGE, Randomizer.randomizeString(20));
            context.addTestState(INDIGO_TEST_CONTEXT.FIRST_NAME, Randomizer.randomizeString(5));
            context.addTestState(INDIGO_TEST_CONTEXT.LAST_NAME, Randomizer.randomizeString(5));
            context.addTestState(INDIGO_TEST_CONTEXT.PHONE, RandomStringUtils.randomNumeric(10));
            context.addTestState(INDIGO_TEST_CONTEXT.EMAIL, Randomizer.randomizeString(5) + "@" + Randomizer.randomizeString(5) + ".com");
            context.addTestState(INDIGO_TEST_CONTEXT.ERROR_MESSAGE, testData.get(INDIGO_TEST_CONTEXT.ERROR_MESSAGE));
            context.addTestState(INDIGO_TEST_CONTEXT.DENOMINATIONS, denomination);
            context.addTestState(INDIGO_TEST_CONTEXT.QUANTITIES, quantity);
        }
    }

    private int getRandomIndex(int size) {
        return (int) (Math.random() * size);
    }
}
