package com.znsio.sample.e2e.steps;

import com.znsio.teswiz.context.SessionContext;
import com.znsio.teswiz.context.TestExecutionContext;
import com.znsio.sample.e2e.businessLayer.cryptoAPI.CryptoAPIBL;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class CryptoAPISteps {

    private HttpResponse<JsonNode> jsonResponse;
    private static final Logger LOGGER = LogManager.getLogger(CryptoAPISteps.class.getName());
    private final TestExecutionContext context;

    public CryptoAPISteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread().getId());
        LOGGER.info("context: " + context.getTestName());
    }

    @Given("I send GET request for crypto {string}")
    public void iSendGETRequestWithValidCryptoSymbol(String symbol) {
        jsonResponse = new CryptoAPIBL().getDataUsingCryptoSymbol(symbol);
    }

    @Then("price change should be less than {int}")
    public void iVerifyPriceChangeIsLessThan(int maxPriceChange) {
        new CryptoAPIBL().verifypriceChange(jsonResponse, maxPriceChange);
    }

    @Then("price change percentage should be less than {int}")
    public void iVerifyPriceChangePercentageIsLessThan(int maxPriceChangePercent) {
        new CryptoAPIBL().verifyPriceChangePercent(jsonResponse, maxPriceChangePercent);
    }
}
