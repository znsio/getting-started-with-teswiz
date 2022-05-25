package com.znsio.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.businessLayer.CartBL;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import org.apache.log4j.Logger;

public class CartSteps {
    private static final Logger LOGGER = Logger.getLogger(CartSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public CartSteps(){
        context = SessionContext.getTestExecutionContext(Thread.currentThread().getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given("I am on restaurants page after selecting location {string} from home page")
    public void iAmOnRestaurantsPageAfterSelectingLocationFromHomePage(String loc) {

        LOGGER.info(System.out.printf("iLoginWithInvalidCredentials - Persona:'%s' on Platform: '%s'", SAMPLE_TEST_CONTEXT.ME, Runner.platform));
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        context.addTestState(SAMPLE_TEST_CONTEXT.ME,Runner.platform);
        new CartBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).selectLocation(loc);
    }

    @And("I sort the restaurants' listings by rating before selecting a restaurant")
    public void iSortTheRestaurantsListingsByRatingBeforeSelectingARestaurant() {
        new CartBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).sortByRating();
        new CartBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).selectRestaurant();
        System.out.println("In restaurants list page");
    }

    @And("I am able to create cart by adding {int} food items to cart")
    public void iAmAbleToCreateCartByAddingFewFoodItemsToCart(int itemCount) {
        for (int i = 0; i < itemCount; i++) {
            new CartBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).addToCart(itemCount);
        }
        System.out.println("In Restaurant details page "+itemCount);
    }

    @Then("the corresponding food items are displayed in the cart")
    public void theCorrespondingFoodItemsAreDisplayedInTheCart() {
        new CartBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).verifyCartContent();
        System.out.println("In Cart page");
    }

    @And("the cart counter increases")
    public void theCartCounterIncreases() {
        System.out.println("Checking counter in cart");
        new CartBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).verifyCartCounter();
    }

    @When("I remove the food item from a filled cart")
    public void iRemoveTheFoodItemFromAFilledCart() {
        System.out.println("Check for removing item from cart");
        new CartBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).removeFromCart();
    }

    @Then("the corresponding food item is removed from the cart")
    public void theCorrespondingFoodItemIsRemovedFromTheCart() {
        System.out.println("Check for change in cart list details");
        new CartBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).verifyCartContent();
    }

    @And("the cart counter decreases")
    public void theCartCounterDecreases() {
        System.out.println("Check for decrease in cart counter");
        new CartBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).verifyCartCounter();
    }

    @When("I remove the only food item from the cart")
    public void iRemoveTheOnlyFoodItemFromTheCart() {
        System.out.println("Remove last item from cart");
        new CartBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).removeFromCart();
    }

    @And("the cart becomes empty")
    public void theCartBecomesEmpty() {
        System.out.println("Check if cart is empty");
        new CartBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).verifyEmptyCart();
    }
}
