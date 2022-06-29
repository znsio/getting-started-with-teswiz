package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.screen.amazon.NavBarScreen;
import com.znsio.sample.e2e.screen.amazon.ShoppingCartScreen;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ShoppingCartBL {
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public ShoppingCartBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    /**
     * Verifies the product and total items in cart.
     */
    public ShoppingCartBL verifyProductDetails(String product, int cartCount) {
        String actualProduct = ShoppingCartScreen.get().getProductTitle();
        assertThat(actualProduct)
                .as(" Verify cart details")
                .isEqualTo(context.getTestStateAsString("ProductName"));

        assertThat(cartCount)
                .as(" Verify items count in cart")
                .isEqualTo(NavBarScreen.get().getItemsInCart());
        return this;

    }
}
