package stepDefinitions;

import POM.LoginPage;
import io.cucumber.java.en.Then;

import static org.junit.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Base.Base;

public class login_steps extends Base {
    ITestResult testResult;

    @BeforeMethod(groups = "login")
    public void userShouldNavigateToTheWebsite() {
        launch_browser();
    }

    @Test(groups = "login")
    @Then("user to login with valid email and valid password")
    public void userLoginWithValidEmailAndPassword() {
        testResult = Reporter.getCurrentTestResult(); // Récupérer le résultat du test
        String testName = testResult.getMethod().getMethodName();
        System.out.println("Début du test : " + testName);
            waitForVisibilityOfElement(By.id("email"));

            LoginPage.enterEmail(props.getProperty("emailphotographe"));
            LoginPage.enterPassword(props.getProperty("password"));
            LoginPage.clickOnTheLoginBTN();
            LoginPage.SuccesToLogin();
    }

    @Test(dataProvider= "invalidLoginData", groups = "login")
    @Then("login with invalid credentials {string} and {string}")
    public void userLoginWithInvalidCredentials(String email, String password) {
        testResult = Reporter.getCurrentTestResult(); // Récupérer le résultat du test
        String testName = testResult.getMethod().getMethodName();
        System.out.println("Début du test : " + testName);
        waitForVisibilityOfElement(By.id("email"));
        LoginPage.enterEmail(email);
        LoginPage.enterPassword(password);

        LoginPage.clickOnTheLoginBTN();
        LoginPage.FailToLogin();
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][]{
                {"a.aouadi@coral-io.fr", "Admin12223!"},
                {"aaaa@outlook.fr", "Aziz1996@"},
                {"lllll@outlook.fr", "Mo123!!"},
                {"", "Aziz1996@"},
                {"ph@gmail.com", ""},
                {"",""},
        };
    }



    @AfterMethod(groups = "login")
    public void tearDown() {
        closeBrowser();
    }
}

