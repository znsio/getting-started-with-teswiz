package com.znsio.e2e.businessLayer;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.CalcNewScreen;
import com.znsio.e2e.screen.CalculatorScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class CalcNewBL {

    private static final Logger LOGGER = Logger.getLogger(CalcNewBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public CalcNewBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public CalcNewBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public CalcNewBL startCalculator() {
        CalcNewScreen.get().handlePopupIfPresent();
        return this;
    }

    public CalcNewBL selectValue(String inputValue) {
        LOGGER.info("Selecting input:"+inputValue);
        /*softly.assertThat(CalcNewScreen.get().selectValue(inputValue).getValueFromscreen())
                .as("Verifying on screen value equals to "+inputValue)
                .isEqualTo(inputValue);*/
        CalcNewScreen.get().selectValue(inputValue);
        return this;
    }

    public CalcNewBL performOperation(String operator) {
        LOGGER.info("Pressing operator:"+operator);
        CalcNewScreen.get().performOperation(operator);
        return this;
    }

    public CalcNewBL isValueMatching(String expectedValue) {
        softly.assertThat(CalcNewScreen.get().getValueFromscreen())
                .as("Verifying on screen value equals to "+expectedValue)
                .isEqualTo(expectedValue);
        return this;
    }
}
