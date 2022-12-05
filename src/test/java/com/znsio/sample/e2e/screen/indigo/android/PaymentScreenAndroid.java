package com.znsio.sample.e2e.screen.indigo.android;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.PaymentScreen;
import org.apache.commons.lang.NotImplementedException;
import org.apache.log4j.Logger;

public class PaymentScreenAndroid extends PaymentScreen {


    private static final Logger LOGGER = Logger.getLogger(GiftVoucherScreenAndroid.class.getName());

    private final Driver driver;

    private final Visual visually;

    public PaymentScreenAndroid(Driver driver, Visual visually) {

        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public int getPaymentAmount() {
        throw new NotImplementedException();
    }
}
