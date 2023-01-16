package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.amzon.AmazonCartsBL;
import com.znsio.sample.e2e.businessLayer.amzon.AmazonHomeBL;
import com.znsio.sample.e2e.businessLayer.amzon.AmazonItemDetailsBL;
import com.znsio.sample.e2e.businessLayer.amzon.AmazonSearchResultsBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class AmazonSteps {
    private static final Logger LOGGER = Logger.getLogger(AmazonSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public AmazonSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given("I, a guest user, search for the item {string} in Search bar")
    public void iAGuestUserSearchForItemInSearchBar(String item) {
        LOGGER.info(System.out.printf("iSearchForProducts - Persona:'%s', Platform: '%s'", SAMPLE_TEST_CONTEXT.ME, Runner.platform));
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform, context);
        new AmazonHomeBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform).itemSearch(item);
    }

    @And("I select the {string} item from the search results")
    public void iSelectTheItemFromTheSearchResults(String position) {
        new AmazonSearchResultsBL().viewItemByPosition(position);
    }

    @When("I add an item to the Cart")
    public void iAddAnItemToTheCart() {
        new AmazonItemDetailsBL().placeItemInToCart();
    }

    @Then("item should be added to the Cart")
    public void itemShouldBeAddedToTheCart() {
        new AmazonCartsBL().verifyItemAddedToCart();
    }
}
