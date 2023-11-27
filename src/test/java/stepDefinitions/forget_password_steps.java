package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import Base.Base;
import POM.LoginPage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class forget_password_steps extends Base {

    ITestResult testResult ;
    private String addRandomCharacter(String title) {
        Random random = new Random();
        char randomChar = (char) (random.nextInt(26) + 'a'); // Generate a random lowercase letter

        String modifiedTitle = title + randomChar; // Append the random character to the title
        return modifiedTitle;
    }

    String new_passe = addRandomCharacter("Aziz1996@");
    String new_pass = addRandomCharacter(new_passe);

    @BeforeMethod(groups = "@FP")
    public void user_open_the_website_and_click_on_forget_password() {

        launch_browser();

        waitForVisibilityOfElement(By.linkText("Forgot Password?"));
        driver.findElement(By.linkText("Forgot Password?")).click();

    }

    @AfterMethod(groups = "@FP")
    public void tearDown() {
        closeBrowser();
    }

    @Test(groups = "@FP")
    public void user_write_email() {
        try {
            testResult = Reporter.getCurrentTestResult(); // Récupérer le résultat du test
            String testName = testResult.getMethod().getMethodName();
            System.out.println("Début du test : " + testName);
            Thread.sleep(10);
            waitForVisibilityOfElement(By.id("normal_login_email"));
            driver.findElement(By.id("normal_login_email")).sendKeys(props.getProperty("notexistingmail"));
            driver.findElement(By.id("testResetPW")).click();
//            Thread.sleep(4000);
            waitForVisibilityOfElement(By.id("normal_login_email"));
            Assert.assertTrue(driver.findElement(By.id("normal_login_email")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.id("testResetPW")).isDisplayed());
            String CurrentUrl = driver.getCurrentUrl();
            Assert.assertTrue(CurrentUrl.contains("/reset-password"));

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @DataProvider(name = "Passwords")
    public Object[][] invalidLoginData() {
        return new Object[][]{
                { "admin123!","admin123!"},
                { "ADMIN123!","ADMIN123!"},
                {"Admin!!!!","Admin!!!!"},
                {"Admin1234","Admin1234"},
                {"Admin1!","Admin1!"},
                {"Aziz1996@","Admin123!!!!"},
                {"Ule2002@",""},
                {"","Ule2002@"},
                {"",""},
        };
    }
    @Test(groups = "@FP")
    public void userchangePAssword( ){
        try {
            testResult = Reporter.getCurrentTestResult(); // Récupérer le résultat du test
            String testName = testResult.getMethod().getMethodName();
            System.out.println("Début du test : " + testName);
            Thread.sleep(10);
            waitForVisibilityOfElement(By.id("normal_login_email"));
            driver.findElement(By.id("normal_login_email")).sendKeys(props.getProperty("MailForResetPassword"));
            driver.findElement(By.id("testResetPW")).click();
            Thread.sleep(4000);

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
            String env = props.getProperty("env");
            if ("recette".equalsIgnoreCase(env)) {
                driver.findElement(By.className("list-group-item")).click();
                WebElement corps_mail = driver.findElement(By.className("col-xs-12"));
                String regex = props.getProperty("urlRecette") + "/change-password/[A-Za-z0-9\\-_.~]+";
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
            } else {
                if ("stage".equalsIgnoreCase(env)) {
                    driver.findElement(By.className("list-group-item")).click();
                    WebElement corps_mail = driver.findElement(By.className("col-xs-12"));
                    String regex = props.getProperty("urlStage") + "/change-password/[A-Za-z0-9\\-_.~]+";
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

                } else {
                    System.out.println("error");

                }

            }
            waitForVisibilityOfElement(By.id("normal_login_password"));
            driver.findElement(By.id("normal_login_password")).sendKeys(new_pass);
            driver.findElement(By.id("normal_login_confirmPassword")).sendKeys(new_pass);
            driver.findElement(By.id("testChangePW")).click();
            waitForVisibilityOfElement(By.id("email"));
            driver.findElement(By.id("email")).sendKeys(props.getProperty("MailForResetPassword"));
            driver.findElement(By.id("password")).sendKeys(new_pass);
            driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
            waitForVisibilityOfElement(By.id("user-dropdown"));

            boolean display = driver.findElement(By.id("user-dropdown")).isDisplayed();
            // System.out.println(display);
            int obtainedresult = 0;
            int expectedresult = 1;
            if (display) {
                obtainedresult = 1;
            } else {
                expectedresult = 0;
            }
            Assert.assertEquals(obtainedresult, expectedresult);






        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test(dataProvider = "Passwords",groups = "@FP")

    public void userFailTochangePAssword(String pass , String ConfirmPass) {
        try {
            testResult = Reporter.getCurrentTestResult(); // Récupérer le résultat du test
            String testName = testResult.getMethod().getMethodName();
            System.out.println("Début du test : " + testName);
            Thread.sleep(10);
            waitForVisibilityOfElement(By.id("normal_login_email"));
            driver.findElement(By.id("normal_login_email")).sendKeys(props.getProperty("MailForResetPassword"));
            driver.findElement(By.id("testResetPW")).click();
            Thread.sleep(4000);

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
            String env = props.getProperty("env");
            if ("recette".equalsIgnoreCase(env)) {
                driver.findElement(By.className("list-group-item")).click();
                WebElement corps_mail = driver.findElement(By.className("col-xs-12"));
                String regex = props.getProperty("urlRecette") + "/change-password/[A-Za-z0-9\\-_.~]+";
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
            } else {
                if ("stage".equalsIgnoreCase(env)) {
                    driver.findElement(By.className("list-group-item")).click();
                    WebElement corps_mail = driver.findElement(By.className("col-xs-12"));
                    String regex = props.getProperty("urlStage") + "/change-password/[A-Za-z0-9\\-_.~]+";
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

                } else {
                    System.out.println("error");

                }

            }
            waitForVisibilityOfElement(By.id("normal_login_password"));
            driver.findElement(By.id("normal_login_password")).sendKeys(pass);
            driver.findElement(By.id("normal_login_confirmPassword")).sendKeys(ConfirmPass);
            driver.findElement(By.id("testChangePW")).click();


            Assert.assertTrue(driver.findElement(By.id("normal_login_password")).isDisplayed());
            Assert.assertTrue(
                    driver.findElement(By.id("normal_login_confirmPassword")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.id("testChangePW")).isDisplayed());

            String CurrentUrl = driver.getCurrentUrl();
            Assert.assertTrue(CurrentUrl.contains("/change-password"));

//            boolean password = driver.findElement(By.id("normal_login_password")).isDisplayed();
//            System.out.println("password" + password);
//
//            boolean confirmpassword =driver.findElement(By.id("normal_login_confirmPassword")).isDisplayed() ;
//            System.out.println("confirmpassword" + confirmpassword);
//            boolean button = driver.findElement(By.id("testChangePW")).isDisplayed();
//            System.out.println(" bouton " + button);
//            boolean URl = CurrentUrl.contains("/change-password");
//            System.out.println("url "+ URl);



        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



}
