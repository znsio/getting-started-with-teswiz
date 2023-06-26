package com.znsio.sample.e2e.businessLayer.bookMyShow;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.entities.bookMyShow.BOOKMYSHOW_SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.bookMyShow.BookMyShowLoginPageScreen;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Runner;
import org.assertj.core.api.SoftAssertions;
import static org.assertj.core.api.Assertions.assertThat;

public class BookMyShowHomePageBL {
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public BookMyShowHomePageBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public BookMyShowHomePageBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = BOOKMYSHOW_SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.getPlatform();
    }

    public BookMyShowHomePageBL launchBookMyShowHomePage() {
        boolean isBookMyShowHomepageLaunched = BookMyShowLoginPageScreen.get().isBookMyShowHomepageLaunched();
        assertThat(isBookMyShowHomepageLaunched).as(String.format("BookMyShow Home Page Not Launched")).isTrue();
        return this;
    }

    public BookMyShowHomePageBL setLocationAsDelhi(String cityName) {
        boolean locationAsDelhi = BookMyShowLoginPageScreen.get().isLocationDelhi(cityName);
        softly.assertThat(locationAsDelhi).as(String.format("Location is not set as: "+cityName)).isTrue();
        boolean isValidLogin = (boolean) BookMyShowLoginPageScreen.get().isValidLogin();
        assertThat(isValidLogin).as(String.format("Not a valid Login")).isTrue();
        return this;
    }

    public MoviePageBL goToMoviesPage() {
        BookMyShowLoginPageScreen.get().moviesPage();
        return new MoviePageBL();
    }

}
