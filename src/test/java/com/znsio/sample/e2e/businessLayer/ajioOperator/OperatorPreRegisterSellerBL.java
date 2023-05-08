package com.znsio.sample.e2e.businessLayer.ajioOperator;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.ajioOperator.OperatorPreRegisterSellerScreen;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.tools.Randomizer;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;

public class OperatorPreRegisterSellerBL {
    private static final Logger LOGGER = Logger.getLogger(OperatorPreRegisterSellerBL.class.getName());
    private final SoftAssertions softly;
    private final TestExecutionContext context;
    private final Platform platform;
    private final String currentUserPersona;
    private final List<String> categories = new ArrayList<>();

    private final float defaultMargin = 10;

    private final String pdfFile = Runner.isRunningInCI() ? Runner.getTestData("uploadFilePathForCI") :
            System.getProperty("user.dir") + Runner.getTestData("uploadFilePathLocal");

    private final String brandName = "adidas";

    public OperatorPreRegisterSellerBL() {
        long threadId = Thread.currentThread().getId();
        this.softly = Runner.getSoftAssertion(threadId);
        this.context = Runner.getTestExecutionContext(threadId);
        this.platform = Runner.getPlatform();
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.SELLER_PLATFORM_USER;
    }


    public OperatorPreRegisterSellerBL createPreRegisterSeller(String fulfilmentType, String eanType, String businessType) throws InterruptedException {
        LOGGER.info("Started: createPreRegisterSeller() ");
        OperatorPreRegisterSellerScreen operatorPreRegisterSellerScreen = OperatorPreRegisterSellerScreen.get();
        categories.add("FOOTWEAR");
        categories.add("APPAREL");
        generateSellerDetails();
        assertThat(operatorPreRegisterSellerScreen.navigateToPreRegisterSellerTab()
                .selectFulfilmentType(fulfilmentType)
                .selectEanType(eanType)
                .enterSellerDetails(context.getTestStateAsString(SAMPLE_TEST_CONTEXT.SELLER_EMAIL),
                        context.getTestStateAsString(SAMPLE_TEST_CONTEXT.SELLER_COMMERCIAL_PHONE),
                        context.getTestStateAsString(SAMPLE_TEST_CONTEXT.SELLER_MOBILE))
                .selectBusinessTypeCategoriesAndMargin(getBusinessTypeList(businessType), categories, defaultMargin)
                .uploadTheMarginApprovalFile(pdfFile)
                .searchForBrandAndSelectBrand(brandName)
                .isSubmitBtnIsVisible()).as("seller new pre Register is not created ").isTrue();
        operatorPreRegisterSellerScreen.navigateToManageSeller();
        return this;
    }

    private List<String> getBusinessTypeList(String businessType) {
        List<String> businessTypeList = new ArrayList<>();
        switch (businessType.toUpperCase()) {
            case "B2C":
                businessTypeList.add("B2C");
                break;
            case "B2B":
                businessTypeList.add("B2B");
                break;
            default:
                businessTypeList.add("B2C");
                businessTypeList.add("B2C");
                break;
        }
        return businessTypeList;
    }

    private void generateSellerDetails() {
        //org.apache.commons.lang3.RandomUtils;
        LOGGER.info("Starting the generateSellerDetails:");
        String sellerEmail = Randomizer.randomizeString(6) + "@test.com";
        String sellerCommercialPhone = "99" + Randomizer.randomize(8);
        String sellerMobileNumber = "98" + Randomizer.randomize(8);

        LOGGER.info("Generated randomized emailId: %s" + sellerEmail);
        LOGGER.info("Generated randomized commercialPhone: %s" + sellerCommercialPhone);
        LOGGER.info("Generated randomized mobileNumber: %s" + sellerMobileNumber);

        context.addTestState(SAMPLE_TEST_CONTEXT.SELLER_EMAIL, sellerEmail);
        context.addTestState(SAMPLE_TEST_CONTEXT.SELLER_MOBILE, sellerMobileNumber);
        context.addTestState(SAMPLE_TEST_CONTEXT.SELLER_COMMERCIAL_PHONE, sellerCommercialPhone);

    }
}
