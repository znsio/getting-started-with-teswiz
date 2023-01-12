package com.znsio.sample.e2e.steps;
import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.amazon.AmazonHomepageBL;
import com.znsio.sample.e2e.businessLayer.amazon.ProductViewBL;
import com.znsio.sample.e2e.businessLayer.amazon.ShoppingCartBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.*;
import org.apache.log4j.Logger;
public class AmazonSteps {
    private static final Logger LOGGER = Logger.getLogger(JioMeetSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public AmazonSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given("I, a guest user, search for product {string} on amazon")
    public void iAGuestUserSearchForStringProduct(String product) {
        context.addTestState(SAMPLE_TEST_CONTEXT.SEARCH_KEYWORD, product);
        LOGGER.info(System.out.printf("Persona:'%s', Platform: '%s'", SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform));
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform, context);
        new AmazonHomepageBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform).searchFor(product);
    }

    @When("I select the first product from the search results page")
    public void iSelectTheFirstProductFromTheSearchResultsPage() {
        LOGGER.info(System.out.printf("Persona:'%s', Platform: '%s'", SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform));
        new AmazonHomepageBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform).selectFirstProductInSearchResultsList();
    }

    @And("I add the product from the product view page to the shopping cart")
    public void iAddTheProductFromTheProductViewPageToTheShoppingCart() {
        LOGGER.info(System.out.printf("Persona:'%s', Platform: '%s'", SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform));
        new ProductViewBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform).addToCart().navigateToShoppingCart();
    }

    @Then("I should be able to see the product in the shopping cart")
    public void iVerifyTheStringProductIsPresentInTheShoppingCart() {
        String product = context.getTestStateAsString(SAMPLE_TEST_CONTEXT.SEARCH_KEYWORD);
        LOGGER.info(System.out.printf("Persona:'%s', Platform: '%s'", SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform));
        new ShoppingCartBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform).verifyProductIsPresentInShoppingCart(product);
    }
}
