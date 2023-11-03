package POM;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import         org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import Base.Base;

public class AddEventPage extends Base {

    public static String strEventDropDown = "dropdown-event-link";
    public static String strAddevent = "event-add";
    public static String strEventTitle = "event-title";
    public static String strEventLocation = "location";
    public static String strEventDate = "testEventDate";
    public static String upload = "upload";
    public static String OkBTN = "test123";

    public static void theevenisCreatedtoday(String nameofevent, String locationofevent) {
        int obtainedresult = 0;
        int expectedresult = 1;
        List<WebElement> eventNames = driver.findElements(By.id("event-name"));
        List<WebElement> eventLocation = driver.findElements(By.id("event-location"));
        List<WebElement> eventDate = driver.findElements(By.id("event-date"));
        LocalDate localDate = LocalDate.now();

        for (int i = 0; i < eventNames.size(); i++) {
            String name_string = eventNames.get(i).getText();
            // System.out.println(name_string);
            String location_string = eventLocation.get(i).getText();
            // System.out.println(location_string);
            String date_string = eventDate.get(i).getText();
            // System.out.println(date_string);
            boolean name = name_string.toUpperCase().equals(nameofevent.toUpperCase());
            boolean location = (location_string.toUpperCase().equals(locationofevent.toUpperCase()));
            boolean date = date_string.equals(localDate.toString());
            System.out.println(localDate.toString());
            // System.out.println("name : " + name);
            // System.out.println("location : " + location);
            // System.out.println("date : " + date);

            if ((name) && (date) && (location)) {
                obtainedresult = 1;
                break;
            }
        }
        Assert.assertEquals(obtainedresult,expectedresult);
    }

    public static void anErrorMsgAppear() {
        Assert.assertTrue(driver.findElement(By.id(strEventTitle)).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id(strEventLocation)).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id(strEventDate)).isDisplayed());
    }

    public static void theEvenisCreated(String nameofevent, String locationofevent, String dateofevent) {
        int obtainedresult = 0;
        int expectedresult = 1;
        List<WebElement> eventNames = driver.findElements(By.id("event-name"));
        List<WebElement> eventLocation = driver.findElements(By.id("event-location"));
        List<WebElement> eventDate = driver.findElements(By.id("event-date"));

        for (int i = 0; i < eventNames.size(); i++) {
            String name_string = eventNames.get(i).getText();
            // System.out.println(name_string);
            String location_string = eventLocation.get(i).getText();
            System.out.println(location_string);
            String date_string = eventDate.get(i).getText();
            // System.out.println(date_string);
            boolean name = name_string.toUpperCase().equals(nameofevent.toUpperCase());
            boolean location = (location_string.toUpperCase().equals(locationofevent.toUpperCase()));
            boolean date = date_string.equals(dateofevent);
            // System.out.println("name : " + name);
            // System.out.println("location : " + location);
            // System.out.println("date : " + date);

            if ((name) && (date) && (location)) {
                obtainedresult = 1;
                break;
            }
        }

       Assert.assertEquals(obtainedresult,expectedresult);
    }

    public static void ClickOnOKBTN() {
        driver.findElement(By.id(OkBTN)).click();
    }

    public static void ClickOnAddEvent() {
        waitForVisibilityOfElement(By.id("dropdown-event-link"));
        driver.findElement(By.id("dropdown-event-link")).click();
        waitForElementToBeClickable(By.id("event-add"));
        driver.findElement(By.id("event-add")).click();
    }

    public static void enterTitleOfEvent(String nameofevent) {
        waitForVisibilityOfElement(By.id(strEventTitle));
        driver.findElement(By.id(strEventTitle)).sendKeys(nameofevent);
    }

    public static void enterLocationOfEvent(String locationofevent) {
        waitForVisibilityOfElement(By.id(strEventLocation));
        driver.findElement(By.id(strEventLocation)).sendKeys(locationofevent);
    }

    public static void enterDateOfEvent(String dateofevent) {
        WebElement dateInput = driver.findElement(By.id(strEventDate));
        dateInput.sendKeys(Keys.CONTROL, "a");
        dateInput.sendKeys(Keys.DELETE);
        dateInput.sendKeys(dateofevent);
        dateInput.sendKeys(Keys.ENTER);
    }

    public static void uploadImage(String pathofimage) {
        String relativePath = pathofimage;
        File file = new File(relativePath);
        String absolutePath = file.getAbsolutePath();
        WebElement source = driver.findElement(By.id(upload));
        source.sendKeys(absolutePath);
    }

}
