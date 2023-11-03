package stepDefinitions;

import java.util.Random;
import org.openqa.selenium.By;

import Base.Base;
import POM.FilterPage;
import POM.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class filterClient_steps extends Base {

    // choisir la période à filtrer
    String startdate = generateRandomDate(2023, 2023);
    String enddate = generateRandomDate(2024, 2024);

    // choisir la recherche par nom

    String FilterText = addRandomCharacter("event");

    // choisir à filtrer par location
    String FilterLocation = addRandomCharacter("Location");

    private String addRandomCharacter(String title) {
        Random random = new Random();
        char randomChar = (char) (random.nextInt(26) + 'a'); // Generate a random lowercase letter

        String modifiedTitle = title + randomChar; // Append the random character to the title
        return modifiedTitle;
    }

    @Before("@filterC")
    public void user_should_navigate_to_the_website() throws InterruptedException {

        launch_browser();
        waitForVisibilityOfElement(By.id("email"));
        LoginPage.enterEmail(props.getProperty("emailclient"));
        LoginPage.enterPassword(props.getProperty("password"));
        LoginPage.clickOnTheLoginBTN();
        FilterPage.GoTofilter();

    }

    @After("@filterC")
    public void tearDown() {
        closeBrowser();
    }

    @And("user click on all filters and should click on viewed events")
    public void user_click_on_all_filters_and_should_click_on_viewed_events() throws InterruptedException {
        FilterPage.ChooseViewedEvent();
    }

    @Then("the user must find the events he has consulted")
    public void the_user_must_find_the_events_he_has_consulted() throws InterruptedException {
        FilterPage.CheckingTheViewedEvents();
    }

    @And("user click on all filters and should click on not viewed events")
    public void user_click_on_all_filters_and_should_click_on_not_viewed_events() throws InterruptedException {
        FilterPage.chooseNotViewedEvent();
    }

    @Then("the user must find the events that he has not yet consulted")
    public void the_user_must_find_the_events_that_he_has_not_yet_consulted() throws InterruptedException {
        FilterPage.CheckingTheNotViewedEvents();
    }

}
