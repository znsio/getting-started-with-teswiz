package com.znsio.sample.e2e.steps.AmazonShoppingSteps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.Amazon.AmazonHomePageBL;
import com.znsio.sample.e2e.businessLayer.Amazon.CartLandingPageBL;
import com.znsio.sample.e2e.businessLayer.Amazon.ProductsLandingPageBL;
import com.znsio.sample.e2e.businessLayer.Amazon.ProductsListsPageBL;
import com.znsio.sample.e2e.entities.AmazonShopping.AMAZON_SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.steps.AppLaunchSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AmazonShoppingSteps {
    private static final Logger LOGGER = Logger.getLogger(AmazonShoppingSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public AmazonShoppingSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(AMAZON_SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given("User launches Amazon HomePage")
    public void userLaunchesAmazonHomePage() {
        LOGGER.info(System.out.printf("userIsOnAmazonHomePage - Persona:'%s', on platform: '%s'", AMAZON_SAMPLE_TEST_CONTEXT.ME,
                Runner.platform));
        allDrivers.createDriverFor(AMAZON_SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        context.addTestState(AMAZON_SAMPLE_TEST_CONTEXT.ME, AMAZON_SAMPLE_TEST_CONTEXT.ME);
        new AmazonHomePageBL(AMAZON_SAMPLE_TEST_CONTEXT.ME, Runner.platform).launchAmazonHomePage();
    }

    @When("User searches for {string}")
    public void userSearchesFor(String Product) {
        new AmazonHomePageBL().searchProduct(Product);
    }

    @Then("User should see valid list of Product links for {string}")
    public void userShouldSeeValidListOfProductLinksFor(String productToFind) {
        new ProductsListsPageBL().userSeesListOfProducts(productToFind);
    }

    @When("User selects the first Product Link")
    public void userSelectsTheFirstProductLink() {
        List<WebElement> ProductList = (List<WebElement>) context.getTestState(AMAZON_SAMPLE_TEST_CONTEXT.PRODUCT_LIST);
        new ProductsListsPageBL().userSelectsFirstProduct(ProductList);
    }

    @Then("User lands on Product Landing Page")
    public void userLandsOnProductLandingPage() {
        String firstProductTitle = context.getTestStateAsString(AMAZON_SAMPLE_TEST_CONTEXT.FIRST_PRODUCT_TITLE);
        String firstProductPrice = context.getTestStateAsString(AMAZON_SAMPLE_TEST_CONTEXT.FIRST_PRODUCT_PRICE);
        new ProductsLandingPageBL().validateProductDetailsAndTitle(firstProductTitle, firstProductPrice);
    }

    @When("User add the Product to Cart")
    public void userAddTheProductToCart() {
        new ProductsLandingPageBL().addProductToCart();
    }

    @And("User goes to Cart")
    public void userGoesToCart() {
        new ProductsLandingPageBL().goToCartPage();
    }

    @Then("User lands on Cart Page")
    public void userLandsOnCartPage() {
        String firstProductTitle = context.getTestStateAsString(AMAZON_SAMPLE_TEST_CONTEXT.FIRST_PRODUCT_TITLE);
        String firstProductPrice = context.getTestStateAsString(AMAZON_SAMPLE_TEST_CONTEXT.FIRST_PRODUCT_PRICE);
        new CartLandingPageBL().VerifyCartDetails(firstProductTitle, firstProductPrice);
    }

}
