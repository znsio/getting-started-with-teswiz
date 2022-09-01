package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.CONTEXT_AMAZON;
import com.znsio.sample.e2e.screen.amazon.ProductPageScreen;
import com.znsio.sample.e2e.screen.amazon.SearchPageScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchPageBL {
    private static final Logger LOGGER = Logger.getLogger(SearchPageBL.class.getName());
    private final SoftAssertions softly;
    private final TestExecutionContext context;

    public SearchPageBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        String currentUserPersona = CONTEXT_AMAZON.ME;
        Platform currentPlatform = Runner.platform;
    }

    public ProductPageScreen selectProduct() {
        ProductPageScreen productPageScreen = SearchPageScreen.get().selectProductFromSearchResultPage();
        assertThat(context.getTestState(CONTEXT_AMAZON.PRODUCT_SELECTED))
                .isEqualTo(context.getTestState(CONTEXT_AMAZON.PRODUCT_TITLE));
        LOGGER.info(String.format("Product selected - '%s' MATCHED Product loaded - '%s'",
                context.getTestState(CONTEXT_AMAZON.PRODUCT_SELECTED), context.getTestState(CONTEXT_AMAZON.PRODUCT_TITLE)));
        return productPageScreen;

    }
}
