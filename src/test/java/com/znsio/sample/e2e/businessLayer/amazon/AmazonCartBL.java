package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonCartScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonProductDetailsScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import static org.assertj.core.api.Assertions.assertThat;

public class AmazonCartBL {
    private static final Logger LOGGER = Logger.getLogger(AmazonCartBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AmazonCartBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public AmazonCartBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.GUEST_USER;
        this.currentPlatform = Runner.platform;
    }

    public AmazonCartBL verifyCartItem() {
        AmazonProductDetailsScreen amazonProductDetailsScreen = AmazonProductDetailsScreen.get();
        String actualName = amazonProductDetailsScreen.getActualItemName();
        AmazonCartScreen amazonCartScreen = amazonProductDetailsScreen.goToCartPage();
        softly.assertThat(amazonCartScreen.isCartHeadingVisible()).as("Shopping Cart heading is not visible");

        String cartItemName = amazonCartScreen.getCartItemName();
        softly.assertThat(actualName).as("Cart Item name is different from clicked item").isEqualTo(cartItemName);

        String searchedString = context.getTestStateAsString(SAMPLE_TEST_CONTEXT.SEARCH_KEYWORD);
        System.out.println("Searched String" + " " + searchedString);
        assertThat(cartItemName.contains(searchedString)).as("Cart item is different from searched string").isTrue();

        return this;
    }

}
