package com.znsio.sample.e2e.screen.android.Indigo;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherScreen;
import com.znsio.sample.e2e.screen.indigo.PaymentScreen;
import org.apache.commons.lang.NotImplementedException;

public class GiftVoucherScreenAndroid extends GiftVoucherScreen {
    public GiftVoucherScreenAndroid(Driver driver, Visual visually) {
        super();
    }

    @Override
    public GiftVoucherScreen addDenominationAndQuantity(String denomination, String quantity) {
       return this;
    }
    @Override
    public GiftVoucherScreen personaliseGiftVoucher(String titleName,String message) {
        return this;
    }
    @Override
    public GiftVoucherScreen previewVoucher() {
        return this;
    }
    @Override
    public boolean verifyVoucherDetails(String titleName, String message, int voucherPrice) {
        return false;
    }
    @Override
    public boolean proceedToBuy() {
        return false;
    }
    @Override
    public boolean applyPromoCode(String promoCode) {
        return true;
    }
    @Override
    public GiftVoucherScreenAndroid giveDeliveryOptions() {
        return this;
    }
    @Override
    public PaymentScreen proceedToPaymentPage() {
        return PaymentScreen.get();
    }
}
