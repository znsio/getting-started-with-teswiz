package com.znsio.sample.e2e.businessLayer.ajioOperator;


import com.context.TestExecutionContext;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.ajioOperator.OperatorManageSellerScreen;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Runner;
import org.assertj.core.api.SoftAssertions;

import java.util.logging.Logger;
import static org.assertj.core.api.Assertions.assertThat;

public class OperatorManageSellerBL {

    private static final Logger LOGGER = Logger.getLogger(OperatorManageSellerBL.class.getName());
    private final TestExecutionContext context;
    private final String currentUserPersona;
    private final Platform currentPlatform;
    private final SoftAssertions softly;

    public OperatorManageSellerBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.SELLER_PLATFORM_USER;
        this.currentPlatform = Runner.getPlatform();
        this.softly = Runner.getSoftAssertion(threadId);
    }

    public OperatorLoginBL logoutFromSellerPage() {
        assertThat(OperatorManageSellerScreen.get()
                .logOutFromHomePage().isLoginPageSignInDisplayed())
                .as("operator is not able to logoff").isTrue();
        LOGGER.info("operator is successfully logout");
        return new OperatorLoginBL();
    }

    public OperatorManageSellerBL searchByEmailAddressOnEmailIdFilter() {
        LOGGER.info("operator on seller central page");
        assertThat(OperatorManageSellerScreen.get().searchSellerEmailAddress()
                .verifyTheSellerDetails().isManageSellerTitleDisplayed())
                .as("operator is not land on Manage Seller page").isTrue();
        return this;
    }

    public OperatorManageSellerBL verifySellerDetailsByOperator() {
        assertThat(OperatorManageSellerScreen.get().isSearchFilterFieldDisplayed())
                .as("operator is not land on Manage Seller page").isTrue();
        LOGGER.info("operator on search result details on seller central page");
        return this;
    }

    public OperatorManageSellerBL verifyActionItemsForSearchEmail(String actionItems) {
        assertThat( OperatorManageSellerScreen.get().VerifySellerRegistrationAndApprovalStatus(actionItems))
                .as("Action item on search email is not displayed").isTrue();
        LOGGER.info("actions item of SellerRegistration and ApprovalStatus for search email is Displayed ");
        return this;
    }
    public OperatorManageSellerBL enableThePendingStatusOnStatusFilter(String statusItems) {
        assertThat( OperatorManageSellerScreen.get().statusFilterIsEnabledOnPendingStatus(statusItems))
                .as("Pending status is not enabled on Status Filter").isTrue();
        LOGGER.info("Pending status is enabled on status Filter");
        return this;
    }

    public OperatorManageSellerBL verifyStatusOfCreatedSellerOnManageSellerPage(String sellerStatus) throws InterruptedException {
        assertThat( OperatorManageSellerScreen.get()
                .verifyThePreRegisterSellerStatusOnManageSeller(context.getTestStateAsString(SAMPLE_TEST_CONTEXT.SELLER_EMAIL),sellerStatus)
                .isManageSellerTitleDisplayed())
                .as("Operator is not landed on Manage seller screen").isTrue();
        return this;
    }
}
