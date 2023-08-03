package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.sample.e2e.businessLayer.ajio.AjioHomeBL;
import com.znsio.sample.e2e.businessLayer.ajio.AjioProductBL;
import com.znsio.sample.e2e.businessLayer.ajio.AjioSearchBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.teswiz.runner.Drivers;
import com.znsio.teswiz.runner.Runner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class AjioSteps {
    private static final Logger LOGGER = Logger.getLogger(AjioSteps.class.getName());
    private final TestExecutionContext context;

    public AjioSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread().getId());
        LOGGER.info("context: " + context.getTestName());
    }


    @Given("I, a guest user, search for {string} products")
    public void iAGuestUserSearchForProducts(String product) {
        LOGGER.info(System.out.printf("iAGuestUserSearchForProducts - Persona:'%s', Platform: '%s'",
                SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()));
        Drivers.createDriverFor(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.getPlatform(), context);
        new AjioSearchBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.getPlatform()).searchFor(product);
    }

    @Given("I open {string} from {string} section for {string}")
    public void iOpenShirtsSectionForMen(String product, String category, String gender) {
        Drivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform(), context);
        new AjioHomeBL().openProduct(product, category, gender);
    }

    @When("I select the first result")
    public void iSelectTheFirstResult() {
        new AjioProductBL().selectTheFirstResultFromList();
    }

    @Then("I should be able to perform flick and view images")
    public void iShouldBeAbleToPerformFlickAndViewImages() {
        new AjioProductBL().flickAndViewImages();
    }

    @Given("I search for products using {string}")
    public void iSearchForProductsUsing(String searchtype) {
        LOGGER.info(System.out.printf("iSearchForProductsUsing:'%s' - Persona:'%s'", searchtype,
                SAMPLE_TEST_CONTEXT.ME));
        Drivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform(), context);
        new AjioSearchBL().searchProduct(Runner.getTestDataAsMap(searchtype));
    }

    @When("I add the product to the cart")
    public void iAddTheProductToTheCart() {
        LOGGER.info(System.out.printf("iAddTheProductToTheCart:- Persona:'%s'",
                SAMPLE_TEST_CONTEXT.ME));
        new AjioSearchBL().prepareCart();
    }

    @Then("I should see the product in the cart")
    public void iShouldSeeTheProductInTheCart() {
        LOGGER.info(System.out.printf("iShouldSeeTheProductInTheCart:- Persona:'%s'",
                SAMPLE_TEST_CONTEXT.ME));
        new AjioSearchBL().verifyCart();
    }
}
