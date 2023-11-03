package stepDefinitions;

import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Base.Base;
import POM.AddEventPage;
import POM.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class add_event_steps extends Base {

    private String addRandomCharacter(String title) {
        Random random = new Random();
        char randomChar = (char) (random.nextInt(26) + 'a'); // Generate a random lowercase letter

        String modifiedTitle = title + randomChar; // Append the random character to the title
        return modifiedTitle;
    }

    String nameofevent = addRandomCharacter("addNameEvent");
    String locationofevent = addRandomCharacter("addLocationEvent");
    String dateofevent = generateRandomDate(2023, 2024);

    @BeforeMethod(groups = "@addevent")
    public void clickOnTheButtonOffAddevent() {
        launch_browser();
        waitForVisibilityOfElement(By.id("email"));
        LoginPage.enterEmail(props.getProperty("emailphotographe"));
        LoginPage.enterPassword(props.getProperty("password"));
        LoginPage.clickOnTheLoginBTN();
        AddEventPage.ClickOnAddEvent();
    }

    @AfterMethod(groups = "@addevent")
    public void tearDown() {
        closeBrowser();
    }

    @Test(groups = "@addevent")
    @And("photographer should fill the title of event")
    public void photographerFillAllTheFields() {
        try {
            AddEventPage.enterTitleOfEvent(nameofevent);
            AddEventPage.enterLocationOfEvent(locationofevent);
            AddEventPage.enterDateOfEvent(dateofevent);
            AddEventPage.uploadImage("src/test/resources/data/traditions-noel-europe-1024x683.jpg");
            AddEventPage.ClickOnOKBTN();
            Thread.sleep(3000);
            AddEventPage.theEvenisCreated(nameofevent, locationofevent, dateofevent);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        }
    @Test(groups = "@addevent")

    @And("photographer should fill the location of event")
    public void PhottographerMissToFillTheLocationOfEvent() {
        try {
            AddEventPage.enterTitleOfEvent(nameofevent);
//            AddEventPage.enterLocationOfEvent(locationofevent);
            AddEventPage.enterDateOfEvent(dateofevent);
            AddEventPage.uploadImage("src/test/resources/data/traditions-noel-europe-1024x683.jpg");
            AddEventPage.ClickOnOKBTN();
            Thread.sleep(3000);
            AddEventPage.theEvenisCreated(nameofevent, "Not defined", dateofevent);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(groups = "@addevent")

    @And("photographer should fill the date of event")
    public void PhotographerMissToFillTheDateOfEvent() {
        try {
            AddEventPage.enterTitleOfEvent(nameofevent);
            AddEventPage.enterLocationOfEvent(locationofevent);
//            AddEventPage.enterDateOfEvent(dateofevent);
            AddEventPage.uploadImage("src/test/resources/data/traditions-noel-europe-1024x683.jpg");
            AddEventPage.ClickOnOKBTN();
            Thread.sleep(3000);
            AddEventPage.theevenisCreatedtoday(nameofevent,locationofevent);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(groups = "@addevent")

    @And("photographer put an image for the event")
    public void photographerMissToFillTheTitleOfEvent() {
        try {
//            AddEventPage.enterTitleOfEvent(nameofevent);
            AddEventPage.enterLocationOfEvent(locationofevent);
            AddEventPage.enterDateOfEvent(dateofevent);
            AddEventPage.uploadImage("src/test/resources/data/traditions-noel-europe-1024x683.jpg");
            AddEventPage.ClickOnOKBTN();
            Thread.sleep(3000);
            AddEventPage.anErrorMsgAppear();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(groups = "@addevent")

    @And("photographer should click on the button ok")
    public void photographerMissToFillTofillTheTitleAndTheLocationOfevent() {
        try {
//            AddEventPage.enterTitleOfEvent(nameofevent);
//            AddEventPage.enterLocationOfEvent(locationofevent);
            AddEventPage.enterDateOfEvent(dateofevent);
            AddEventPage.uploadImage("src/test/resources/data/traditions-noel-europe-1024x683.jpg");
            AddEventPage.ClickOnOKBTN();
            Thread.sleep(3000);
            AddEventPage.anErrorMsgAppear();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test(groups = "@addevent")

    @Then("the event is created")
    public void photographerMissToFillTofillTheTitleAndTheDateOfevent() {
        try {
//            AddEventPage.enterTitleOfEvent(nameofevent);
            AddEventPage.enterLocationOfEvent(locationofevent);
//            AddEventPage.enterDateOfEvent(dateofevent);
            AddEventPage.uploadImage("src/test/resources/data/traditions-noel-europe-1024x683.jpg");
            AddEventPage.ClickOnOKBTN();
            Thread.sleep(3000);
            AddEventPage.anErrorMsgAppear();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(groups = "@addevent")

    @Then("the event is created")
    public void photographerMissToFillTofillTheTitleAndTheLocationAndTheDateOfevent() {
        try {
//            AddEventPage.enterTitleOfEvent(nameofevent);
//            AddEventPage.enterLocationOfEvent(locationofevent);
//            AddEventPage.enterDateOfEvent(dateofevent);
            AddEventPage.uploadImage("src/test/resources/data/traditions-noel-europe-1024x683.jpg");
            AddEventPage.ClickOnOKBTN();
            Thread.sleep(3000);
            AddEventPage.anErrorMsgAppear();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("the event is created in location of event as {string}")
    public void event_is_created(String location_of_event) {
        try {
            Thread.sleep(3000);
            AddEventPage.theEvenisCreated(nameofevent, location_of_event, dateofevent);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("the event is created with the date added")
    public void the_event_is_created_today() {
        try {
            Thread.sleep(3000);
            AddEventPage.theevenisCreatedtoday(nameofevent, locationofevent);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @And("photographer put a file in the image field for the event")
    public void put_a_file_in_the_image_zone() {
        AddEventPage.uploadImage("src/test/resources/data/TESTER.docx");
    }

    @Then("an error message appear and the event is created without image")
    public void an_error_message_appear_and_the_event_is_created_without_image() {
        try {
            WebElement im = driver.findElement(By.className("ant-card-cover"));
            im = im.findElement(By.tagName("img"));
            Thread.sleep(1000);
            String src_im = im.getAttribute("src");
            boolean find = false;
            if (src_im.contentEquals(
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQW_80vVH0RghGLTxWZjz0EYc9JanOzT-m0wEUvdU0caY6bKU5n8oF5hbOHZlU9GVUM1dQ&usqp=CAU")) {
                find = true;
            }

            Assert.assertTrue(find);
            // System.out.println("test pass");

            Thread.sleep(10);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}