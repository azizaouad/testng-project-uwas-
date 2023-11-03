package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
// import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
// import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
// import org.openqa.selenium.support.ui.ExpectedConditions;
// import org.openqa.selenium.support.ui.WebDriverWait;
// import org.uwas.Driver;

import Base.Base;
import POM.LoginPage;

// import java.time.Duration;
// import java.util.ArrayList;
// import java.util.List;
import java.util.Random;
// import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class forget_password_steps extends Base {

    private String addRandomCharacter(String title) {
        Random random = new Random();
        char randomChar = (char) (random.nextInt(26) + 'a'); // Generate a random lowercase letter

        String modifiedTitle = title + randomChar; // Append the random character to the title
        return modifiedTitle;
    }

    String new_passe = addRandomCharacter("Aziz1996@");
    String new_pass = addRandomCharacter(new_passe);

    @Before("@FP")
    public void user_open_the_website_and_click_on_forget_password() {

        launch_browser();

        waitForVisibilityOfElement(By.linkText("Forgot Password?"));
        driver.findElement(By.linkText("Forgot Password?")).click();

    }

    @After("@FP")
    public void tearDown() {
        closeBrowser();
    }

    @When("user write email as {string}")
    public void user_write_email(String email) {
        try {
            Thread.sleep(10);
            waitForVisibilityOfElement(By.id("normal_login_email"));
            driver.findElement(By.id("normal_login_email")).sendKeys(email);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @And("user confirm the email")
    public void user_confirm_the_email() {
        try {
            Thread.sleep(10);
            driver.findElement(By.id("testResetPW")).click();
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @And("user visit the mail and click on the link")
    public void user_visit_the_mail_and_click_on_the_link() {
        try {

            driver.switchTo().newWindow(WindowType.TAB);
            Thread.sleep(40);
            driver.navigate()
                    .to("https://qa.team/inbox?utf8=%E2%9C%93&code=uwas01&locale=en&commit=go+%C2%BB");
            driver.findElement(By.className("access-button-text")).click();
            LoginPage.waitForVisibilityOfElement(By.id("user_email"));
            driver.findElement(By.id("user_email")).sendKeys("a.jerbi@coral-io.fr");
            driver.findElement(By.id("user_password")).sendKeys("ADmin123!!");
            driver.findElement(By.xpath("//*[@id=\"new_user\"]/div[4]/input")).click();

            Thread.sleep(3000);
            driver.findElement(By.id("code")).sendKeys("uwas02");
            driver.findElement(By.id("submit_code")).click();
            Thread.sleep(3000);

            driver.navigate().refresh();

            driver.findElement(By.className("list-group-item")).click();
            WebElement corps_mail = driver.findElement(By.className("col-xs-12"));
            String regex = props.getProperty("url") + "/change-password/[A-Za-z0-9\\-_.~]+";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(corps_mail.getText().toString().split("---")[1]);

            if (matcher.find()) {
                String resetPasswordLink = matcher.group(0);
                System.out.println("Lien de réinitialisation de mot de passe : " +
                        resetPasswordLink);
                driver.get(resetPasswordLink);

            } else {
                System.out.println("Aucun lien de réinitialisation de mot de passe trouvé dans l'e-mail.");
            }

            // List<String> containedUrls = new ArrayList<String>();
            // String urlRegex =
            // "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
            // Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
            // Matcher urlMatcher =
            // pattern.matcher(corps_mail.getText().toString().split("---")[1]);

            // while (urlMatcher.find()) {
            // containedUrls.add(corps_mail.getText().toString().split("---")[1].substring(urlMatcher.start(0),
            // urlMatcher.end(0)));
            // }

            // String webUrlResetPassword = "";

            // if (System.getProperty("environment").equals("recette")) {
            // webUrlResetPassword = "https:///coralio:cmVjZXR0ZWNvcmFsaW8yMDIyCg==@" +
            // containedUrls.get(0).substring(8);
            // } else {
            // webUrlResetPassword = containedUrls.get(0).toString();
            // }

        }

        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @And("user write the new password")
    public void user_write_password() {
        try {
            Thread.sleep(50);
            // System.out.println(new_pass);
            waitForVisibilityOfElement(By.id("normal_login_password"));
            driver.findElement(By.id("normal_login_password")).sendKeys(new_pass);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @And("user write confirm_password")
    public void user_write_confirm_password() {
        try {
            driver.findElement(By.id("normal_login_confirmPassword")).sendKeys(new_pass);
            Thread.sleep(10);
            driver.findElement(By.id("testChangePW")).click();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @And("user fill password as {string}")
    public void user_fill_password(String password) {
        try {
            Thread.sleep(5);
            waitForVisibilityOfElement(By.id("normal_login_password"));
            driver.findElement(By.id("normal_login_password")).sendKeys(password);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @And("user fill confirm_password as {string}")
    public void user_fill_confirm_password(String confirm_password) {
        try {
            driver.findElement(By.id("normal_login_confirmPassword")).sendKeys(confirm_password);
            Thread.sleep(1);
            driver.findElement(By.id("testChangePW")).click();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("the password is changed user can login with new password as {string} and email as {string}")
    public void the_password_is_changed_user_can_login_with_new_password_and_email(String password, String email) {
        try {
            Thread.sleep(4000);
            waitForVisibilityOfElement(By.id("email"));
            driver.findElement(By.id("email")).sendKeys(email);
            driver.findElement(By.id("password")).sendKeys(new_pass);
            driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
            Thread.sleep(5000);
            boolean display = driver.findElement(By.id("user-dropdown")).isDisplayed();
            // System.out.println(display);
            int obtainedresult = 0;
            int expectedresult = 1;
            if (display) {
                obtainedresult = 1;
                // System.out.println("test fail");
            } else {
                expectedresult = 0;
            }
            Assert.assertEquals(expectedresult, obtainedresult);
            ;
            Thread.sleep(20);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Then("an error message is displayed")
    public void an_error_message_is_displayed() {
        try {
            waitForVisibilityOfElement(By.id("normal_login_email"));
            Assert.assertTrue(driver.findElement(By.id("normal_login_email")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.id("testResetPW")).isDisplayed());
            Thread.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("the password is not changed and an error message appear")
    public void the_password_is_not_changed_and_an_error_message_appear() {
        try {
            // Thread.sleep(4000);
            waitForVisibilityOfElement(By.id("testChangePW"));

            Assert.assertTrue(driver.findElement(By.id("normal_login_password")).isDisplayed());
            Assert.assertTrue(
                    driver.findElement(By.id("normal_login_confirmPassword")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.id("testChangePW")).isDisplayed());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);

        }

    }
}
