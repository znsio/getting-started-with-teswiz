package com.znsio.e2e.businessLayer;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.CartScreen;
import org.assertj.core.api.SoftAssertions;
import org.testng.asserts.SoftAssert;

public class SwiggyCartBL {


    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;
    public SwiggyCartBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public SwiggyCartBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.SWIGGY_USER;
        this.currentPlatform = Runner.platform;
    }

    public SwiggyCartBL openCart() {
        CartScreen.get().openCartOption();
        return this;
    }

    public SwiggyCartBL validateItemName() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(CartScreen.get().getItemName(),context.getTestStateAsString(SAMPLE_TEST_CONTEXT.FOOD_ITEM_NAME),"Item Name doesnot matched");
        softAssert.assertAll();
        return this;
    }
}
