package com.znsio.sample.e2e.screen.android.Indigo;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.PaymentScreen;

public class PaymentScreenAndroid extends PaymentScreen {
    public PaymentScreenAndroid(Driver driver, Visual visually) {

    }
    @Override
    public boolean landOnPaymentPage() {
        return false;
    }
}
