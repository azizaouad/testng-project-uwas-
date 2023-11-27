package Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

    public static WebDriver driver;

    private static Random random = new Random();
    public static Properties props = new Properties();

    public Base() {
        try {
            File fichier = new File("src/main/java/properties/config.properties");
            FileInputStream FIS = new FileInputStream(fichier);
            props.load(FIS);

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String generateRandomDate(int startYear, int endYear) {
        // Generate a random year between startYear and endYear
        int year = random.nextInt(endYear - startYear + 1) + startYear;

        // Generate a random month between 1 and 12
        int month = random.nextInt(12) + 1;

        // Generate a random day between 1 and the maximum number of days in the
        // selected month
        int day = random.nextInt(LocalDate.of(year, month, 1).lengthOfMonth()) + 1;
        LocalDate randomDate = LocalDate.of(year, month, day);
        String randomdateString = randomDate.toString();

        return randomdateString;
    }

    // private String chromeDriverPath = "chromedriver.exe";
    protected static void waitForVisibilityOfElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected static void waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void launch_browser() {
        String browserChoice = props.getProperty("browser");

        if ("chrome".equalsIgnoreCase(browserChoice)) {
            initializeChromeDriver();
        } else if ("firefox".equalsIgnoreCase(browserChoice)) {
            initializeFirefoxDriver();
        } else {
            System.out.println("Invalid browser choice: " + browserChoice);
        }
    }

    public static void initializeChromeDriver() {

        String system = props.getProperty("System");
        // System.out.println(system);
        if ("windows".equalsIgnoreCase(system)) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
//            options.addArguments("headless");
//            options.addArguments("no-sandbox");
//            // options.addArguments("start-maximized");
//            options.addArguments("--window-size=1920,1080");

            options.addArguments("--remote-allow-origins=*");

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();

//            driver.get(props.getProperty("url"));

        } else if ("linux".equalsIgnoreCase(system)) {
            System.setProperty("webdriver.chrome.driver", "/root/chromedriver/chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("no-sandbox");
            // options.addArguments("start-maximized");
            options.addArguments("--window-size=1920,1080");

            options.addArguments("--remote-allow-origins=*");

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();

//            driver.get(props.getProperty("url"));

        } else {
            System.out.println("erreur");
        }
        String env = props.getProperty("env");
        if ("recette".equalsIgnoreCase(env)) {
            driver.get(props.getProperty("urlRecette"));
        } else {
            if ("stage".equalsIgnoreCase(env)) {
                driver.get(props.getProperty("urlStage"));

            } else {
                System.out.println("error");

            }

        }



    }

    private static void initializeFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.get(props.getProperty("url"));
    }

    public static void closeBrowser(){
        if (driver != null) {
            driver.quit();
        }
    }

}