package stepDefinitions;

import java.util.Random;
import org.openqa.selenium.By;
import Base.Base;
import POM.AddEventPage;
import POM.LoginPage;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class add_event_steps extends Base {
    ITestResult testResult ;

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
    public void photographerFillAllTheFields() {
        try {
            testResult = Reporter.getCurrentTestResult(); // Récupérer le résultat du test
            String testName = testResult.getMethod().getMethodName();
            System.out.println("Début du test : " + testName);
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
    public void PhotographerMissToFillTheLocationOfEvent() {
        try {
            testResult = Reporter.getCurrentTestResult(); // Récupérer le résultat du test
            String testName = testResult.getMethod().getMethodName();
            System.out.println("Début du test : " + testName);            
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
    public void PhotographerMissToFillTheDateOfEvent() {
        try {
            testResult = Reporter.getCurrentTestResult(); // Récupérer le résultat du test
            String testName = testResult.getMethod().getMethodName();
            System.out.println("Début du test : " + testName);            
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
    public void photographerMissToFillTheTitleOfEvent() {
        try {
            testResult = Reporter.getCurrentTestResult(); // Récupérer le résultat du test
            String testName = testResult.getMethod().getMethodName();
            System.out.println("Début du test : " + testName);            
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
    public void photographerMissToFillTofillTheTitleAndTheLocationOfevent() {
        try {
            testResult = Reporter.getCurrentTestResult(); // Récupérer le résultat du test
            String testName = testResult.getMethod().getMethodName();
            System.out.println("Début du test : " + testName);            
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
    public void photographerMissToFillTofillTheTitleAndTheDateOfevent() {
        try {
            testResult = Reporter.getCurrentTestResult(); // Récupérer le résultat du test
            String testName = testResult.getMethod().getMethodName();
            System.out.println("Début du test : " + testName);            
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
    public void photographerMissToFillTofillTheTitleAndTheLocationAndTheDateOfevent() {
        try {
            testResult = Reporter.getCurrentTestResult(); // Récupérer le résultat du test
            String testName = testResult.getMethod().getMethodName();
            System.out.println("Début du test : " + testName);            
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

}