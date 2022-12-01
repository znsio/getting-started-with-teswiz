package com.znsio.sample.e2e.screen.android.Indigo;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.Indigo.IndigoDeliveryScreen;
import com.znsio.sample.e2e.screen.Indigo.IndigoPaymentScreen;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

public class IndigoDeliveryScreenAndroid extends IndigoDeliveryScreen {


    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = IndigoDeliveryScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    public static final String NOT_YET_IMPLEMENTED = "not yet implemented";

    public IndigoDeliveryScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public IndigoDeliveryScreen enterInvalidPromoCode(String promoCode) {
        return null;
    }

    @Override
    public String getErrorMessage() {
        throw new NotImplementedException(SCREEN_NAME + ":" + new Throwable().getStackTrace()[0].getMethodName() + NOT_YET_IMPLEMENTED);
    }

    @Override
    public int getFinalAmount() {
        throw new NotImplementedException(SCREEN_NAME + ":" + new Throwable().getStackTrace()[0].getMethodName() + NOT_YET_IMPLEMENTED);
    }

    @Override
    public IndigoDeliveryScreen enterReceiverDetail(String name, String lastName, String email, String phone) {
        throw new NotImplementedException(SCREEN_NAME + ":" + new Throwable().getStackTrace()[0].getMethodName() + NOT_YET_IMPLEMENTED);
    }

    @Override
    public IndigoDeliveryScreen enterSenderDetails(String senderFirstName, String senderLastName, String senderrMail, String senderPhone) {
        throw new NotImplementedException(SCREEN_NAME + ":" + new Throwable().getStackTrace()[0].getMethodName() + NOT_YET_IMPLEMENTED);
    }

    @Override
    public IndigoDeliveryScreen selectTermsAndConditions() {
        throw new NotImplementedException(SCREEN_NAME + ":" + new Throwable().getStackTrace()[0].getMethodName() + NOT_YET_IMPLEMENTED);
    }

    @Override
    public IndigoPaymentScreen clickOnProceedBtn() {
        throw new NotImplementedException(SCREEN_NAME + ":" + new Throwable().getStackTrace()[0].getMethodName() + NOT_YET_IMPLEMENTED);
    }
}
