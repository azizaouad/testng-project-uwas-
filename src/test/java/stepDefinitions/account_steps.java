package stepDefinitions;

import java.util.Random;
import POM.AccountPage;
import POM.LoginPage;
import org.openqa.selenium.By;
import Base.Base;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class account_steps extends Base {

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
    public void user_edit_his_first_name() {
        AccountPage.ChangeFirstName(firstname);
        AccountPage.ClickOnEditBTN();
        AccountPage.AssertionOfEditFirstName(firstname);
    }

    @Test(dataProvider = "invalidNameData", groups = "scenarioAvecAccount")
    public void user_want_to_edit_his_first_name(String first_name) {
        AccountPage.ChangeFirstName(first_name);
        AccountPage.ClickOnEditBTN();
        AccountPage.AssertionOfEditFirstName(first_name);
    }
    @DataProvider(name = "invalidNameData")
    public Object[][] invalidNameData() {
        return new Object[][]{
                {"3li"},
                {"@li"},
//                {"@3la"},
//                {" "},
//                {"li"},
        };
    }

    @Test(groups = "scenarioAvecAccount")
    public void user_edit_his_last_name() {
        AccountPage.ChangeLastName(lastname);
        AccountPage.ClickOnEditBTN();
        AccountPage.AssertionOfEditLastName(lastname);
    }
    @Test(dataProvider = "invalidNameData", groups = "scenarioAvecAccount")
    public void user_want_to_edit_his_last_name(String last_name) {
        AccountPage.ChangeLastName(last_name);
        AccountPage.ClickOnEditBTN();
        AccountPage.AssertionOfEditLastName(last_name);
    }

//    @And("user click on edit button")
//    public void user_click_on_edit() {
//        AccountPage.ClickOnEditBTN();
//    }
//
//    @Then("the first name does not change to {string}")
//    public void the_first_name_does_not_change(String first_name) {
//        AccountPage.AssertionOfEditFirstName(first_name);
//    }
//
//    @Then("the last name does not change to {string}")
//    public void the_last_name_does_not_change(String last_name) {
//        AccountPage.AssertionOfEditLastName(last_name);
//    }
//
//    @Then("first name should be changed")
//    public void first_name_should_be_changed() {
//        AccountPage.AssertionOfEditFirstName(firstname);
//    }
//
//    @Then("last name should be changed")
//    public void last_name_should_be_changed() {
//        AccountPage.AssertionOfEditLastName(lastname);
//    }



@Test(dataProvider = "Passwords")
    @And("user click on change password {string}")
    public void user_click_on_change_password(String actual_password,String New_password,String confirmPassword) {

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
                {"Admin123!", "admin123!","admin123!"},
                {"Admin123!", "ADMIN123!","ADMIN123!"},
                {"Admin123!","Admin!!!!","Admin!!!!"},
                {"Admin123!","Admin1234","Admin1234"},
                {"Admin123!","Admin1!","Admin1!"},
                {"Admin123!","Aziz1996@","Admin123!!!!"},
                {"Admin12345!","Ule2002@","Ule2002@"},
        };
    }

    @And("user click on change password")
    public void user_click_on_change_password() {
        AccountPage.ClickOnChangePasswordLink();
    }

    @And("user fill his actuel password {string}")
    public void user_fill_his_acutuel_password(String actuel_password) {
        waitForVisibilityOfElement(By.id("oldPwd"));
        AccountPage.enterOldPassword(actuel_password);
    }

    @And("user fill his new password {string}")
    public void user_fill_his_new_password(String new_password) {
        AccountPage.enterNewPassword(new_password);
    }

    @And("user confirm password {string}")
    public void user_confirm_password(String confirm_password) {
        AccountPage.enterconfirmPassword(confirm_password);
    }

    @And("user click on confirm button")
    public void user_click_on_confirm_button() {
        AccountPage.ClickOnTheEditPWDBTN();
    }

    @And("user logout")
    public void user_logout() {
        try {
            Thread.sleep(10000);
            waitForElementToBeClickable(By.id("user-dropdown"));
            AccountPage.UserLogout();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("user can connect with new credentials email and password {string}")
    public void user_can_connect_with_new_credentials(String password) {
        waitForVisibilityOfElement(By.id("email"));
        LoginPage.enterEmail(props.getProperty("emailphotographe"));
        LoginPage.enterPassword(password);
        LoginPage.clickOnTheLoginBTN();
        LoginPage.SuccesToLogin();
    }

    @Then("an error message appear")
    public void an_error_message_appear() {
        AccountPage.FailToChangePWD();
    }
}
