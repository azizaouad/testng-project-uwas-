package stepDefinitions;

import java.util.Random;

import POM.AccountPage;
import POM.AddEventPage;
import POM.LoginPage;
import POM.scenarioPage;
import org.openqa.selenium.By;
import Base.Base;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class scenario_steps extends Base {
    ITestResult testResult ;
    private String addRandomCharacter(String title) {
        Random random = new Random();
        char randomChar = (char) (random.nextInt(26) + 'a'); // Generate a random lowercase letter

        String modifiedTitle = title + randomChar; // Append the random character to the title
        return modifiedTitle;
    }

    String nameofevent = addRandomCharacter("namescenario");
    String locationofevent = addRandomCharacter("locationscenario");
    String newdate = generateRandomDate(2023, 2024);

    @BeforeMethod(groups = "@scenario")
    public void goToAccount() {
        launch_browser();
        waitForVisibilityOfElement(By.id("email"));
        LoginPage.enterEmail(props.getProperty("emailphotographe"));
        LoginPage.enterPassword(props.getProperty("password"));
        LoginPage.clickOnTheLoginBTN();
    }

    @AfterMethod(groups = "@scenario")
    public void tearDown() {
        closeBrowser();
    }

    @Test(groups = "@scenario")
    public void photographer_should_click_on_the_button_of_add_event() throws InterruptedException {
        testResult = Reporter.getCurrentTestResult(); // Récupérer le résultat du test
        String testName = testResult.getMethod().getMethodName();
        System.out.println("Début du test : " + testName);
        AddEventPage.ClickOnAddEvent();
        AddEventPage.enterTitleOfEvent(nameofevent);
        AddEventPage.enterLocationOfEvent(locationofevent);
        AddEventPage.enterDateOfEvent(newdate);
        AddEventPage.uploadImage("src/test/resources/data/traditions-noel-europe-1024x683.jpg");
        AddEventPage.ClickOnOKBTN();
        Thread.sleep(3000);
        AddEventPage.theEvenisCreated(nameofevent, locationofevent, newdate);
        Thread.sleep(4000);
        AccountPage.UserLogout();
    }
    @Test(groups = "@scenario")
    public void user_upload_some_photos() {
        testResult = Reporter.getCurrentTestResult(); // Récupérer le résultat du test
        String testName = testResult.getMethod().getMethodName();
        System.out.println("Début du test : " + testName);
        scenarioPage.uploadImage();
        scenarioPage.CheckIfTheImageUploaded();
        AccountPage.UserLogout();
    }
}
