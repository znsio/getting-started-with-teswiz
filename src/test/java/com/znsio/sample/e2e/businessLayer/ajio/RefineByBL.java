package com.znsio.sample.e2e.businessLayer.ajio;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.ajio.SearchResultsScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RefineByBL {

    private static final Logger LOGGER = Logger.getLogger(AjioSearchBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public RefineByBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public RefineByBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public RefineByBL refineProducts(String genderFilter , String sizeFilter) {
        SearchResultsScreen searchResultsScreen = SearchResultsScreen.get().refineOnGender(genderFilter );
        searchResultsScreen.refineOnSize(sizeFilter).selectApply();
        List<String> appliedFilterNames = searchResultsScreen.getAppliedFilters();
        for(int filter=0; filter<appliedFilterNames.  size(); filter++){
            if(genderFilter.equals(appliedFilterNames.get(filter)))
                softly.assertThat(genderFilter ).as("Product is not refined on the basis of gender").isEqualTo(appliedFilterNames.get(filter));
            if(sizeFilter.equals(appliedFilterNames.get(filter)))
                softly.assertThat(sizeFilter).as("Product is not refined on the basis of size").isEqualTo(appliedFilterNames.get(filter));
        }
        return this;
    }
}
