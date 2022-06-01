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

    public CartSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread().getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given("I am on restaurants page after selecting location {string} from home page")
    public void iAmOnRestaurantsPageAfterSelectingLocationFromHomePage(String loc) {
        LOGGER.info(System.out.printf("I am on restaurants page after selecting location - Persona:'%s' on Platform: '%s'", SAMPLE_TEST_CONTEXT.ME, Runner.platform));
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        context.addTestState(SAMPLE_TEST_CONTEXT.ME, Runner.platform);
        new CartBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).selectLocation(loc);
    }

    @And("I sort the restaurants' listings by rating before selecting a restaurant")
    public void iSortTheRestaurantsListingsByRatingBeforeSelectingARestaurant() {
        LOGGER.info(System.out.printf("I sort the restaurants listings by rating before selecting a restaurant"));
        new CartBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).sortByRating();
        new CartBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).selectRestaurant();
    }

    @And("I am able to create cart by adding {int} food items to cart")
    public void iAmAbleToCreateCartByAddingFewFoodItemsToCart(int itemCount) {
        LOGGER.info(System.out.printf("I am able to create cart by adding food items to cart"));
        new CartBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).addToCart(itemCount);
    }

    @Then("the corresponding food items are displayed in the cart")
    public void theCorrespondingFoodItemsAreDisplayedInTheCart() {
        LOGGER.info((System.out.printf("Items added to cart are being displayed in cart")));
        new CartBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).verifyCartContentAdd();
    }

    @And("the cart counter increases")
    public void theCartCounterIncreases() {
        LOGGER.info((System.out.printf("The cart counter increases")));
        new CartBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).verifyCartCounter();
    }

    @When("I remove all the food item from the cart")
    public void iRemoveAllTheFoodItemFromTheCart() {
        LOGGER.info((System.out.printf("All items in the cart are removed")));
        new CartBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).removeFromCart();
    }

    @Then("the corresponding food items are removed from the cart")
    public void theCorrespondingFoodItemsAreRemovedFromTheCart() {
        LOGGER.info((System.out.printf("Cart shows no items present")));
        new CartBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).verifyCartContentRemove();
    }

    @And("the cart becomes empty")
    public void theCartBecomesEmpty() {
        LOGGER.info((System.out.printf("Check if cart is empty")));
        new CartBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).verifyEmptyCart();
    }
}
