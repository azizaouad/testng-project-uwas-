package POM;

import Base.Base;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class AccountPage extends Base {
    public static String strFirstName = "firstName";
    public static String strChangePasswordLink = "changePwLink";
    public static String strLastName = "lastName";
    public static String strUserDropDown ="user-dropdown";
    public static String strAccount = "account-nav";
    public static String editBTN = "edit-btn";
    public static String strOldPassword = "oldPwd";
    public static String strNewPassword = "newPwd";
    public static String strConfirmPassword = "confirmPwd";
    public static String editPasswordBTN = "change-pw-btn";
    public static String logout = "testLogout";

    public static void FailToChangePWD() {
        Assert.assertTrue(driver.findElement(By.id(strOldPassword)).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id(strNewPassword)).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id(strConfirmPassword)).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id(editPasswordBTN)).isDisplayed());
    }
    public static void UserLogout(){
        driver.findElement(By.id(strUserDropDown)).click();
        driver.findElement(By.id(logout)).click();
    }

    public static void ClickOnTheEditPWDBTN(){
        driver.findElement(By.id(editPasswordBTN)).click();
    }
    public static void enterconfirmPassword(String confirmPWD){
        driver.findElement(By.id(strConfirmPassword)).sendKeys(confirmPWD);
    }
    public static void enterNewPassword(String newpwd){
        driver.findElement(By.id(strNewPassword)).sendKeys(newpwd);
    }
    public static void enterOldPassword(String oldPWD){
        driver.findElement(By.id(strOldPassword)).sendKeys(oldPWD);
    }
    public static void ClickOnChangePasswordLink(){
        waitForVisibilityOfElement(By.id(strChangePasswordLink));

        driver.findElement(By.id(strChangePasswordLink)).click();
    }

    public static void navigateToAccount(){
        waitForVisibilityOfElement(By.id(strUserDropDown));

        driver.findElement(By.id(strUserDropDown)).click();

        driver.findElement(By.id(strAccount)).click();
    }
    public static void ChangeFirstName(String firstName){

        waitForVisibilityOfElement(By.id(strFirstName));
        WebElement First_name = driver.findElement(By.id(strFirstName));
        First_name.sendKeys(Keys.CONTROL, "a");
        First_name.sendKeys(Keys.DELETE);
        First_name.sendKeys(firstName);
    }

    public static void ChangeLastName(String lastName){

        waitForVisibilityOfElement(By.id(strLastName));
        WebElement First_name = driver.findElement(By.id(strLastName));
        First_name.sendKeys(Keys.CONTROL, "a");
        First_name.sendKeys(Keys.DELETE);
        First_name.sendKeys(lastName);
    }
    public static void ClickOnEditBTN(){
        driver.findElement(By.id(editBTN)).click();
    }

    public static void AssertionOfEditFirstName(String first_name) {
        driver.navigate().refresh();
        waitForVisibilityOfElement(By.id(strFirstName));

        String newfirstname = driver.findElement(By.id(strFirstName)).getAttribute("value");

        boolean change = (first_name.toUpperCase()).contentEquals(newfirstname.toUpperCase());
        if (change) {
            Assert.assertTrue(change);
        } else {
            Assert.assertFalse(change);
        }
    }
    public static void AssertionOfEditLastName(String last_name){
        driver.navigate().refresh();
        waitForVisibilityOfElement(By.id(strLastName));

        String newfirstname = driver.findElement(By.id(strLastName)).getAttribute("value");

        boolean change = (last_name.toUpperCase()).contentEquals(newfirstname.toUpperCase());
        if (change) {
            Assert.assertTrue(change);
        } else {
            Assert.assertFalse(change);
        }
    }
}
