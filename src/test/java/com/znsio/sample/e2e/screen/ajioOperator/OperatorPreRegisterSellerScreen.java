package com.znsio.sample.e2e.screen.ajioOperator;


import com.znsio.sample.e2e.screen.web.ajioOperator.OperatorPreRegisterSellerScreenWeb;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Drivers;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Visual;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

public abstract class OperatorPreRegisterSellerScreen {

    private static final String SCREEN_NAME = OperatorPreRegisterSellerScreen.class.getSimpleName();
    public static OperatorPreRegisterSellerScreen get() {
        Platform platform = Runner.fetchPlatform(Thread.currentThread().getId());
        Visual visually = Drivers.getVisualDriverForCurrentUser(Thread.currentThread().getId());
        switch (platform) {
            case web:
                return new OperatorPreRegisterSellerScreenWeb(Drivers.getDriverForCurrentUser(Thread.currentThread().getId()), visually);
        }
        throw new NotImplementedException(SCREEN_NAME + " is not implemented in " + Runner.getPlatform());
    }

    public abstract OperatorPreRegisterSellerScreen navigateToPreRegisterSellerTab();
    public abstract OperatorPreRegisterSellerScreen selectFulfilmentType(String fulfilmentType);
    public abstract OperatorPreRegisterSellerScreen  selectEanType(String eanType);
    public abstract OperatorPreRegisterSellerScreen enterSellerDetails(String sellerEmail, String sellerMobile, String sellerCommercialPhone);
    public abstract OperatorPreRegisterSellerScreen selectBusinessTypeCategoriesAndMargin(List<String> businessTypeList, List<String> categories, float defaultMargin);
    public abstract OperatorPreRegisterSellerScreen uploadTheMarginApprovalFile(String pdfFile);
    public abstract OperatorPreRegisterSellerScreen searchForBrandAndSelectBrand(String brandName) ;
   public abstract boolean isSubmitBtnIsVisible();
    public abstract OperatorManageSellerScreen navigateToManageSeller() throws InterruptedException;

}
