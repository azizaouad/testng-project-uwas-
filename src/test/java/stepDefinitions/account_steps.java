package stepDefinitions;

import java.util.Random;
import POM.AccountPage;
import POM.LoginPage;
import org.openqa.selenium.By;
import Base.Base;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class account_steps extends Base {
        ITestResult testResult;


    private String addRandomCharacter(String title) {
        Random random = new Random();
        char randomChar = (char) (random.nextInt(26) + 'a'); // Generate a random lowercase letter

        String modifiedTitle = title + randomChar; // Append the random character to the title
        return modifiedTitle;
    }

    String firstname = addRandomCharacter("first");
    String lastname = addRandomCharacter("last");

    @BeforeMethod(groups = "scenarioAvecAccount")
    public void goToAccount() {
        launch_browser();
        waitForVisibilityOfElement(By.id("email"));
        LoginPage.enterEmail(props.getProperty("emailphotographe"));
        LoginPage.enterPassword(props.getProperty("password"));
        LoginPage.clickOnTheLoginBTN();
        AccountPage.navigateToAccount();
    }

    @AfterMethod(groups = "scenarioAvecAccount")
    public void tearDown() {
        closeBrowser();
    }

    @Test(groups = "scenarioAvecAccount")
    public void userChangeHisfirstName() {
        testResult = Reporter.getCurrentTestResult(); // Récupérer le résultat du test
        String testName = testResult.getMethod().getMethodName();
        System.out.println("Début du test : " + testName);
        AccountPage.ChangeFirstName(firstname);
        AccountPage.ClickOnEditBTN();
        AccountPage.AssertionOfEditFirstName(firstname);
    }

    @Test(dataProvider = "invalidNameData", groups = "scenarioAvecAccount")
    public void UserFailToChangeHisFirstName(String first_name) {
        testResult = Reporter.getCurrentTestResult(); // Récupérer le résultat du test
        String testName = testResult.getMethod().getMethodName();
        System.out.println("Début du test : " + testName);     
        AccountPage.ChangeFirstName(first_name);
        AccountPage.ClickOnEditBTN();
        AccountPage.AssertionOfEditFirstName(first_name);
    }

    @DataProvider(name = "invalidNameData")
    public Object[][] invalidNameData() {
        return new Object[][]{
                {"3li"},
                {"@li"},
                {"@3la"},
                {" "},
                {"li"},
        };
    }

    @Test(groups = "scenarioAvecAccount")
    public void UserChangeHisLastName() {
        testResult = Reporter.getCurrentTestResult(); // Récupérer le résultat du test
        String testName = testResult.getMethod().getMethodName();
        System.out.println("Début du test : " + testName);
        AccountPage.ChangeLastName(lastname);
        AccountPage.ClickOnEditBTN();
        AccountPage.AssertionOfEditLastName(lastname);
    }

    @Test(dataProvider = "invalidNameData", groups = "scenarioAvecAccount")
    public void UserFailToChangeHisLastName(String last_name) {
        testResult = Reporter.getCurrentTestResult(); // Récupérer le résultat du test
        String testName = testResult.getMethod().getMethodName();
        System.out.println("Début du test : " + testName);
        AccountPage.ChangeLastName(last_name);
        AccountPage.ClickOnEditBTN();
        AccountPage.AssertionOfEditLastName(last_name);
    }


    @Test(dataProvider = "Passwords")
    public void UserFailToChangeHisPassword(String actual_password, String New_password, String confirmPassword) {
        testResult = Reporter.getCurrentTestResult(); // Récupérer le résultat du test
        String testName = testResult.getMethod().getMethodName();
        System.out.println("Début du test : " + testName);

        AccountPage.ClickOnChangePasswordLink();
        waitForVisibilityOfElement(By.id("oldPwd"));
        AccountPage.enterOldPassword(actual_password);
        AccountPage.enterNewPassword(New_password);
        AccountPage.enterconfirmPassword(confirmPassword);
        AccountPage.ClickOnTheEditPWDBTN();
        AccountPage.FailToChangePWD();


    }

    @DataProvider(name = "Passwords")
    public Object[][] invalidLoginData() {
        return new Object[][]{
                {"Admin123!", "admin123!", "admin123!"},
                {"Admin123!", "ADMIN123!", "ADMIN123!"},
                {"Admin123!", "Admin!!!!", "Admin!!!!"},
                {"Admin123!", "Admin1234", "Admin1234"},
                {"Admin123!", "Admin1!", "Admin1!"},
                {"Admin123!", "Aziz1996@", "Admin123!!!!"},
                {"Admin12345!", "Ule2002@", "Ule2002@"},
        };
    }
}



