package stepDefinitions;

import java.util.Random;

import POM.AccountPage;
import POM.AddEventPage;
import POM.LoginPage;
import POM.scenarioPage;

import org.openqa.selenium.By;
import Base.Base;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class scenario_steps extends Base {
    private String addRandomCharacter(String title) {
        Random random = new Random();
        char randomChar = (char) (random.nextInt(26) + 'a'); // Generate a random lowercase letter

        String modifiedTitle = title + randomChar; // Append the random character to the title
        return modifiedTitle;
    }

    String nameofevent = addRandomCharacter("namescenario");
    String locationofevent = addRandomCharacter("locationscenario");
    String newdate = generateRandomDate(2023, 2024);

    @Before("@scenario")
    public void goToAccount() {
        launch_browser();
        waitForVisibilityOfElement(By.id("email"));
        LoginPage.enterEmail(props.getProperty("emailphotographe"));
        LoginPage.enterPassword(props.getProperty("password"));
        LoginPage.clickOnTheLoginBTN();

    }

    @After("@scenario")
    public void tearDown() {
        closeBrowser();
    }

    @And("photographer click on the button of add event")
    public void photographer_should_click_on_the_button_of_add_event() {
        AddEventPage.ClickOnAddEvent();
    }

    @And("photographer fill the title of event")
    public void photographer_should_fill_the_title_of_event() {
        AddEventPage.enterTitleOfEvent(nameofevent);
    }

    @And("photographer fill the location of event")
    public void photographer_should_fill_the_location_of_event() {
        AddEventPage.enterLocationOfEvent(locationofevent);
    }

    @And("photographer fill the date of event")
    public void photographer_should_fill_the_date_of_event() {
        AddEventPage.enterDateOfEvent(newdate);
    }

    @And("photographer put an image")
    public void photographer_put_an_image_for_the_event() {
        AddEventPage.uploadImage("src/test/resources/data/traditions-noel-europe-1024x683.jpg");
    }

    @And("photographer should click on the ok button")
    public void photographer_should_click_on_the_button_ok() {
        AddEventPage.ClickOnOKBTN();
    }

    @And("this event is created")
    public void title_of_event_in_location_at_date_is_created() {
        try {
            Thread.sleep(3000);
            AddEventPage.theEvenisCreated(nameofevent, locationofevent, newdate);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Then("photographer should logout")
    public void photographer_should_logout() {
        try {
            Thread.sleep(4000);
            AccountPage.UserLogout();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @And("user upload some photos")
    public void user_upload_some_photos() {
        scenarioPage.uploadImage();

    }

    @And("the photos are uploaded")
    public void the_photos_are_uploaded() {

        scenarioPage.CheckIfTheImageUploaded();
    }

}
