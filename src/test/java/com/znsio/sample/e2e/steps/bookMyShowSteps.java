package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.sample.e2e.businessLayer.bookMyShow.*;
import com.znsio.sample.e2e.entities.bookMyShow.BOOKMYSHOW_SAMPLE_TEST_CONTEXT;
import com.znsio.teswiz.runner.Drivers;
import com.znsio.teswiz.runner.Runner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class bookMyShowSteps {
    private static final Logger LOGGER = Logger.getLogger(bookMyShowSteps.class.getName());
    private final TestExecutionContext context;
    public bookMyShowSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
    }
    
    @Given("Login with a valid user")
    public void loginWithAValidUser() {
        LOGGER.info(System.out.printf("userIsOnBookMyShowHomePage - Persona:'%s', on platform: '%s'", BOOKMYSHOW_SAMPLE_TEST_CONTEXT.ME,
                Runner.getPlatform()));
        Drivers.createDriverFor(BOOKMYSHOW_SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform(), context);
        context.addTestState(BOOKMYSHOW_SAMPLE_TEST_CONTEXT.ME, BOOKMYSHOW_SAMPLE_TEST_CONTEXT.ME);
        new BookMyShowHomePageBL(BOOKMYSHOW_SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).launchBookMyShowHomePage();
    }

    @When("Select location as {string}")
    public void selectLocationAsDelhiNCR(String cityName){
        new BookMyShowHomePageBL().setLocationAsDelhi(cityName);
    }

    @Then("Open Movies page")
    public void openMoviesPage() {
        new BookMyShowHomePageBL().goToMoviesPage();
    }

    @Then("Open movie at position {int} from the list of Now Showing movies")
    public void openMovieAtPositionFromTheListOfNowShowingMovies(int moviePosition) {
        new MoviePageBL().userOpenSecondMovie(moviePosition);
    }

    @Then("Choose the screen type if applicable for the chosen movie")
    public void chooseTheScreenTypeIfApplicableForTheChosenMovie() {
        new MovieLandingPageBL().selectScreenType();
    }

    @Then("Choose day after tomorrow as date for the show")
    public void chooseDayAfterTomorrowAsDateForTheShow() {
        String secondMovieTitle = context.getTestStateAsString(BOOKMYSHOW_SAMPLE_TEST_CONTEXT.SECOND_MOVIE_TITLE);
        new BookingPageBL().selectDateAfterTomorrow(secondMovieTitle);
    }

    @Then("Choose third cinema hall from the list")
    public void chooseThirdCinemaHallFromTheList() {
        new SelectedMovieBL().selectThirdHall();
    }

    @Then("Choose the second last time slot out of the available time slots for the chosen cinema hall")
    public void chooseTheSecondLastTimeSlotOutOfTheAvailableTimeSlotsForTheChosenCinemaHall() {
        String cinemaHallTitle = context.getTestStateAsString(BOOKMYSHOW_SAMPLE_TEST_CONTEXT.THIRD_HALL_TITLE);
        new CinemaHallBL().userSelectsSecondSlot(cinemaHallTitle);
    }

    @Then("Choose number of people as {int}")
    public void chooseNumberOfPeopleAsTwo(int numberOfSeats) {
        new CinemaHallBL().userSelectsNumberOfPeople(numberOfSeats);
    }

    @And("Fail the test if the seat availability is less than {int}% out of all the available seats.")
    public void failTheTestIfTheSeatAvailabilityIsLessThanOutOfAllTheAvailableSeats(int percentage) {
        new SeatSelectionBL().checkAvailabilityOfSeats(percentage);
    }
}
