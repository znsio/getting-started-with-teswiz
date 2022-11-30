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

    public boolean previewVoucherAndProceed(String title, String message) {
        return true;
    }
    @Override
    public boolean applyPromoCode(String promoCode) {
        return true;
    }

    @Override
    public PaymentScreen giveDeliveryOptionsAndProceed() {
        return PaymentScreen.get();
    }
}
