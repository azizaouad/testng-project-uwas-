package stepDefinitions;

import Base.Base;
import POM.AccountPage;
import POM.LoginPage;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class succestochangepassword extends Base {

    @Test(dataProvider = "Passwords")
    @And("user click on change password {string}")
    public void user_click_on_change_password(String actual_password,String New_password) {
        try {
            launch_browser();
            waitForVisibilityOfElement(By.id("email"));
            LoginPage.enterEmail(props.getProperty("emailphotographe"));
            LoginPage.enterPassword(actual_password);
            LoginPage.clickOnTheLoginBTN();
            AccountPage.navigateToAccount();
            AccountPage.ClickOnChangePasswordLink();
            waitForVisibilityOfElement(By.id("oldPwd"));
            AccountPage.enterOldPassword(actual_password);
            AccountPage.enterNewPassword(New_password);
            AccountPage.enterconfirmPassword(New_password);
            AccountPage.ClickOnTheEditPWDBTN();
            Thread.sleep(4000);
            waitForElementToBeClickable(By.id("user-dropdown"));
            AccountPage.UserLogout();
            waitForVisibilityOfElement(By.id("email"));
            LoginPage.enterEmail(props.getProperty("emailphotographe"));
            LoginPage.enterPassword(New_password);
            LoginPage.clickOnTheLoginBTN();
            LoginPage.SuccesToLogin();
            closeBrowser();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    @DataProvider(name = "Passwords")
    public Object[][] invalidLoginData() {
        return new Object[][]{
                {"Admin123!", "Admin123!!"},
                {"Admin123!!", "Admin123!"},
        };
    }

}
