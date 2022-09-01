package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.amazonCart.ProductDescriptionPageBL;
import com.znsio.sample.e2e.businessLayer.amazonCart.SearchResultsListsPageBL;
import com.znsio.sample.e2e.businessLayer.amazonCart.ShppingCartPageBL;
import com.znsio.sample.e2e.entities.AMAZON_ASSIGNMENT_TEST_CONTEXT;
import com.znsio.sample.e2e.businessLayer.amazonCart.AmazonHomePageBL;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Locale;

public class AmazonCartSteps {
    private static final Logger LOGGER = Logger.getLogger(AmazonCartSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public AmazonCartSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                                                               .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(AMAZON_ASSIGNMENT_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given("user creates search for {string} product on Amazon")
    public void userCreatesSearchForProductOnAmazon(String product) {
        LOGGER.info(System.out.printf("userOpensAmazonBaseURL - Persona:'%s', on platform: '%s'", AMAZON_ASSIGNMENT_TEST_CONTEXT.ME,
                Runner.platform));
        allDrivers.createDriverFor(AMAZON_ASSIGNMENT_TEST_CONTEXT.ME, Runner.platform, context);
        context.addTestState(AMAZON_ASSIGNMENT_TEST_CONTEXT.ME, AMAZON_ASSIGNMENT_TEST_CONTEXT.ME);
        new AmazonHomePageBL(AMAZON_ASSIGNMENT_TEST_CONTEXT.ME, Runner.platform).launchAmazonHomePage();
        new AmazonHomePageBL().createProductSearch(product);
        new SearchResultsListsPageBL().searchedProductIsVisible(product);
    }

    @When("user selects the first product listed in search results")
    public void userSelectsFirstProductFromSearchResults(){
        new SearchResultsListsPageBL().userSelectsFirstProduct();
        String titleProduct1 = context.getTestStateAsString(AMAZON_ASSIGNMENT_TEST_CONTEXT.TITLE_PRODUCT_1);
        String priceProduct1 = context.getTestStateAsString(AMAZON_ASSIGNMENT_TEST_CONTEXT.PRICE_PRODUCT_1);
        new ProductDescriptionPageBL().validateProductDetails(titleProduct1, priceProduct1);
    }

    @And("user adds the product to shopping cart")
    public void userAddsProductToCart(){
        new ProductDescriptionPageBL().addProductToShoppingCart();
    }

    @When("user opens shopping cart")
    public void userOpensShoppingCart(){
        new ProductDescriptionPageBL().openCartPage();
    }

    @Then("product added by user should be visible in shopping cart")
    public void cartContainsAddedProduct(){
        String titleProduct1 = context.getTestStateAsString(AMAZON_ASSIGNMENT_TEST_CONTEXT.TITLE_PRODUCT_1);
        String priceProduct1 = context.getTestStateAsString(AMAZON_ASSIGNMENT_TEST_CONTEXT.PRICE_PRODUCT_1);
        new ShppingCartPageBL().VerifyProductInCart(titleProduct1, priceProduct1);
    }


}
