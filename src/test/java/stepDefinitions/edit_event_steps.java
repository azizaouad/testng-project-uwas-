package stepDefinitions;

import java.time.LocalDate;
import java.util.Random;
import org.openqa.selenium.By;
import Base.Base;
import POM.AddEventPage;
// import POM.ArchiveEventPage;
import POM.EditEventPage;
import POM.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class edit_event_steps extends Base {
    private String addRandomCharacter(String title) {
        Random random = new Random();
        char randomChar = (char) (random.nextInt(26) + 'a'); // Generate a random lowercase letter

        String modifiedTitle = title + randomChar; // Append the random character to the title
        return modifiedTitle;
    }

    String nameofevent = addRandomCharacter("nameToEdit");
    String newnameofevent = addRandomCharacter(nameofevent);
    String locationofevent = addRandomCharacter("locationToEdit");
    String newlocationofevent = addRandomCharacter(locationofevent);
    LocalDate localDate = LocalDate.now();
    String Today = localDate.toString();
    String newdate = generateRandomDate(2023, 2024);

    @BeforeMethod(groups = "@edit")
    public void UserCreateAnEvent() {
        launch_browser();
        waitForVisibilityOfElement(By.id("email"));
        LoginPage.enterEmail(props.getProperty("emailphotographe"));
        LoginPage.enterPassword(props.getProperty("password"));
        LoginPage.clickOnTheLoginBTN();
        AddEventPage.ClickOnAddEvent();
        AddEventPage.enterTitleOfEvent(nameofevent);
        AddEventPage.enterLocationOfEvent(locationofevent);
        AddEventPage.ClickOnOKBTN();
    }

    @AfterMethod(groups = "@edit")
    public void tearDown() {
        closeBrowser();
    }

    @Test(groups = "@edit")
    @And("photographer click on the three buttouns for updating")
    public void PhotographerChangeTheNameOfEvent() {
        try {
            Thread.sleep(3000);
            EditEventPage.ClickOnTheThreePoints(nameofevent, locationofevent);
            EditEventPage.ClickOnEditBTN();
            EditEventPage.enterTheNewTitleOfEvent(newnameofevent);
            AddEventPage.ClickOnOKBTN();
            EditEventPage.TheDetailOfEventIsUpdated(newnameofevent, locationofevent, Today);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    @Test(groups = "@edit")
    @And("photographer choose edit")
    public void PhotographerChangeTheLocationOfEvent() {

        try {
            Thread.sleep(3000);
            EditEventPage.ClickOnTheThreePoints(nameofevent, locationofevent);
            EditEventPage.ClickOnEditBTN();
            EditEventPage.enterTheNewLocationOfEvent(newlocationofevent);
            AddEventPage.ClickOnOKBTN();
            EditEventPage.TheDetailOfEventIsUpdated(nameofevent, newlocationofevent, Today);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Test(groups = "@edit")
    @And("photographer choose edit")
    public void PhotographerChangeTheDateOfEvent() {

        try {
            Thread.sleep(3000);
            EditEventPage.ClickOnTheThreePoints(nameofevent, locationofevent);
            EditEventPage.ClickOnEditBTN();
            AddEventPage.enterDateOfEvent(newdate);
            AddEventPage.ClickOnOKBTN();
            EditEventPage.TheDetailOfEventIsUpdated(nameofevent, locationofevent, newdate);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    @Test(groups = "@edit")
    @And("photographer choose edit")
    public void PhotographerChangeTheDateAndTheTitleAndTheLocationOfEvent() {

        try {
            Thread.sleep(3000);
            EditEventPage.ClickOnTheThreePoints(nameofevent, locationofevent);
            EditEventPage.ClickOnEditBTN();
            EditEventPage.enterTheNewLocationOfEvent(newlocationofevent);
            EditEventPage.enterTheNewTitleOfEvent(newnameofevent);
            AddEventPage.enterDateOfEvent(newdate);
            AddEventPage.ClickOnOKBTN();
            EditEventPage.TheDetailOfEventIsUpdated(newnameofevent, newlocationofevent, newdate);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    @And("photographer should change the location of event")
    public void change_location_of_event() {
        EditEventPage.enterTheNewLocationOfEvent(newlocationofevent);
    }

    @Then("location of event is updated")
    public void location_is_updated() throws InterruptedException {
        EditEventPage.TheDetailOfEventIsUpdated(nameofevent, newlocationofevent, Today);
    }

    @And("photographer should change the date of event")
    public void change_date_of_event() {
        AddEventPage.enterDateOfEvent(newdate);
    }

    @And("photographer click on the button of ok")
    public void photographer_click_on_the_button_of_ok() {
        AddEventPage.ClickOnOKBTN();
    }

    @Then("date of event is updated")
    public void date_is_updated() throws InterruptedException {
        EditEventPage.TheDetailOfEventIsUpdated(nameofevent, locationofevent, newdate);
    }

    @Then("details of event is updated")
    public void details_is_updated() throws InterruptedException {
        EditEventPage.TheDetailOfEventIsUpdated(newnameofevent, newlocationofevent, newdate);
    }

}
