package com.znsio.sample.e2e.businessLayer.ajio;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.ajio.AjioCartScreen;
import com.znsio.sample.e2e.screen.ajio.AjioSearchResultsScreen;
import com.znsio.sample.e2e.screen.ajio.ProductDetailsScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;


public class AjioProductBL {
    private static final Logger LOGGER = Logger.getLogger(AjioSearchBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;


    public AjioProductBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public AjioProductBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }


    public AjioProductBL applyFilterForGenderWithSize(String gender, String productSize) {
        context.addTestState(SAMPLE_TEST_CONTEXT.PRODUCT_SIZE, productSize);
        AjioSearchResultsScreen ajioSearchResultsScreen = AjioSearchResultsScreen.get().applyFilterForGender(gender);
        String genderFilterSelected = ajioSearchResultsScreen.getActualGenderFilter();
        assertThat(genderFilterSelected).as("Gender filter was for a different value").isEqualTo(gender);
        String sizeFilterSelected = ajioSearchResultsScreen.applyFilterForSize(productSize).getActualSizeFilter();
        assertThat(sizeFilterSelected).as("Size filter was for a different value").isEqualTo(productSize);
        int productsFoundForFilter = ajioSearchResultsScreen.getNumberOfProductsFound();
        assertThat(productsFoundForFilter).as("No products for the applied filter").isPositive();
        return this;
    }

    public AjioProductBL selectAndAddProductToBag(int productNumber) {
        LOGGER.info("Selecting and adding product to bag");
        AjioSearchResultsScreen ajioSearchResultsScreen = AjioSearchResultsScreen.get();
        String nameOfSelectedProduct = ajioSearchResultsScreen.selectProduct(productNumber).getNameOfselectedProduct();
        String size = (String) context.getTestState(SAMPLE_TEST_CONTEXT.PRODUCT_SIZE);
        AjioCartScreen ajioCartScreen = ProductDetailsScreen.get().selectSizeAndAddProductToBag(size.split(" ")[1]).goToBag();
        assertThat(ajioCartScreen.getProductNameInCart().split("-")[1].substring(1)).as("Wrong product added to bag").isEqualTo(nameOfSelectedProduct);
        assertThat(ajioCartScreen.getProductSizeInCart()).as("Wrong Size selected for product").isEqualTo(size.split(" ")[1]);
        return this;
    }

    public AjioProductBL addCouponToProduct() {
        LOGGER.info("adding coupon to product in cart");
        AjioCartScreen ajioCartScreen = AjioCartScreen.get().addCoupon();
        boolean couponApplied = ajioCartScreen.verifyCouponApplied();
        softly.assertThat(couponApplied).as("Coupon is not applied on the product").isTrue();
        double couponSavings = ajioCartScreen.getCouponSavings();
        assertThat(couponSavings).as("no savings from applied coupon").isPositive();
        return this;
    }
}
