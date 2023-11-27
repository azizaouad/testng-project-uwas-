package stepDefinitions;

import java.time.LocalDate;
import java.util.Random;
import org.openqa.selenium.By;
import Base.Base;
import POM.AddEventPage;
import POM.EditEventPage;
import POM.LoginPage;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class edit_event_steps extends Base {
    ITestResult testResult ;
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
    public void PhotographerChangeTheNameOfEvent() {
        try {
            testResult = Reporter.getCurrentTestResult(); // Récupérer le résultat du test
            String testName = testResult.getMethod().getMethodName();
            System.out.println("Début du test : " + testName);
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
    public void PhotographerChangeTheLocationOfEvent() {

        try {
            testResult = Reporter.getCurrentTestResult(); // Récupérer le résultat du test
            String testName = testResult.getMethod().getMethodName();
            System.out.println("Début du test : " + testName);
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
    public void PhotographerChangeTheDateOfEvent() {

        try {
            testResult = Reporter.getCurrentTestResult(); // Récupérer le résultat du test
            String testName = testResult.getMethod().getMethodName();
            System.out.println("Début du test : " + testName);
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
    public void PhotographerChangeTheDateAndTheTitleAndTheLocationOfEvent() {

        try {
            testResult = Reporter.getCurrentTestResult(); // Récupérer le résultat du test
            String testName = testResult.getMethod().getMethodName();
            System.out.println("Début du test : " + testName);
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

}
