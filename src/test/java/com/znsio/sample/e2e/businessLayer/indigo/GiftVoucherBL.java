package com.znsio.sample.e2e.businessLayer.indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Randomizer;
import com.znsio.sample.e2e.entities.GIFT_VOUCHER_TEST_CONTEXT;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherScreen;
import com.znsio.sample.e2e.screen.indigo.HomeScreen;
import com.znsio.sample.e2e.screen.indigo.PaymentScreen;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class GiftVoucherBL {

    private static final Logger LOGGER = Logger.getLogger(GiftVoucherBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;
    private static int totalAmount = 0;
    private Map testData = Runner.getTestDataAsMap(System.getProperty("user.name"));
    private static final Map details = new LinkedHashMap();

    public GiftVoucherBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;

        details.put(GIFT_VOUCHER_TEST_CONTEXT.FIRST_NAME, Randomizer.randomizeString(5));
        details.put(GIFT_VOUCHER_TEST_CONTEXT.MESSAGE, Randomizer.randomizeString(15));
        details.put(GIFT_VOUCHER_TEST_CONTEXT.LAST_NAME, Randomizer.randomizeString(5));
        details.put(GIFT_VOUCHER_TEST_CONTEXT.PHONE, RandomStringUtils.randomNumeric(10));
        details.put(GIFT_VOUCHER_TEST_CONTEXT.EMAIL, Randomizer.randomizeString(5) + "@" + Randomizer.randomizeString(5) + ".com");

        Runner.setCurrentDriverForUser(this.currentUserPersona, this.currentPlatform, context);
    }

    public GiftVoucherBL previewAndPersonaliseGiftVoucher(String quantity, String denominations) {

        LOGGER.info("Navigate to Gift Vouchers");
        HomeScreen.get().navigateToGiftVouchers();

        LOGGER.info("Preview and Personalise the "+quantity+" Gift Vouchers of "+denominations+" rupees ");
        GiftVoucherScreen.get().selectQuantity(quantity).selectDenomination(denominations)
                .personaliseGiftVoucher(details)
                .previewGiftVoucher();

        totalAmount = Integer.parseInt(quantity) * Integer.parseInt(denominations);

        LOGGER.info("Verify total amount is "+totalAmount+" rupees");
        assertThat(GiftVoucherScreen.get().getTotalAmount()).isEqualTo(this.totalAmount);

        return this;
    }

    public GiftVoucherBL applyAPromoCode(String promoCode) {

        LOGGER.info("Apply a "+promoCode+" promo code");
        String promo = "";
        if(promoCode.equals("invalid")){
            promo = Randomizer.randomizeAlphaNumericString(8);
        }
        else{
            promo = promoCode;
        }
        GiftVoucherScreen.get().applyPromoCode(promo);

        softly.assertThat(GiftVoucherScreen.get().getPromoCodeMessage())
                .as("Promo code message should be equal to expected message")
                .isEqualTo((String) testData.get("invalidMessage"));

        assertThat(GiftVoucherScreen.get().getTotalAmount())
                .as("Total amount should be equal to expected amount after applying promo code")
                .isEqualTo(totalAmount);

        return this;
    }

    public GiftVoucherBL purchaseGiftVoucher() {

        LOGGER.info("Purchase Gift Voucher");

        String deliveryOption = (String) testData.get(GIFT_VOUCHER_TEST_CONTEXT.DELIVERY_OPTION);
        LOGGER.info("Select delivery options - "+deliveryOption);

        GiftVoucherScreen.get().selectDeliveryOption(deliveryOption)
                        .enterSenderDetails(details)
                        .checkTermsAndConditions(true)
                        .clickPayNow();

        assertThat("" + PaymentScreen.get().getPaymentAmount())
                .as("Payment screen should be equal to total payment")
                .isEqualTo(totalAmount);

        return this;
    }
}
