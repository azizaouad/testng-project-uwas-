package POM;

import Base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage extends Base {
    public static String strEmail = "email";
    public static String strPassword = "password";
    public static String loginBTN = "testLogin";
    public static String strUserDropDown = "user-dropdown";

    public static void enterEmail(String email) {
        driver.findElement(By.id(strEmail)).sendKeys(email);

    }

    public static void enterPassword(String password) {
        driver.findElement(By.id(strPassword)).sendKeys(password);
    }

    public static void clickOnTheLoginBTN() {
        driver.findElement(By.id(loginBTN)).click();
    }

    public static void SuccesToLogin() {
        waitForVisibilityOfElement(By.id(strUserDropDown));
        WebElement mee = driver.findElement(By.id(strUserDropDown));
        String currenturl = driver.getCurrentUrl();
        Assert.assertTrue(mee.isDisplayed());
//        Assert.assertTrue(mee.isDisplayed());
        Assert.assertFalse(currenturl.contains("/login"));

    }

    public static void FailToLogin() {
        waitForVisibilityOfElement(By.id("email"));
        WebElement emailField = driver.findElement(By.id(strEmail));
        WebElement passwordField = driver.findElement(By.id(strPassword));
        WebElement loginButton = driver.findElement(By.id(loginBTN));

        Assert.assertTrue(emailField.isDisplayed());
        Assert.assertTrue(passwordField.isDisplayed());
        Assert.assertTrue(loginButton.isDisplayed());
    }

}
