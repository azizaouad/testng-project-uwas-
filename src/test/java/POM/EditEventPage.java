package POM;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import Base.Base;

public class EditEventPage extends Base {
    public static void ClickOnEditBTN() {
        waitForVisibilityOfElement(By.id("testEdit"));

        driver.findElement(By.id("testEdit")).click();
    }

    public static void enterTheNewLocationOfEvent(String locationofevent) {
        waitForVisibilityOfElement(By.id("location"));
        WebElement Location = driver.findElement(By.id("location"));
        Location.sendKeys(Keys.CONTROL + "a");
        Location.sendKeys(Keys.DELETE);

        Location.sendKeys(locationofevent);
    }

    public static void enterTheNewTitleOfEvent(String nameofevent) {
        waitForVisibilityOfElement(By.id("event-title"));
        WebElement title = driver.findElement(By.id("event-title"));
        title.sendKeys(Keys.CONTROL + "a");
        title.sendKeys(Keys.DELETE);

        title.sendKeys(nameofevent);
    }

    public static void ClickOnTheThreePoints(String nameofevent, String locationofevent) {
        List<WebElement> points = driver.findElements(By.id("event-edit-dropdown"));
        List<WebElement> eventNames = driver.findElements(By.id("event-name"));
        List<WebElement> eventDate = driver.findElements(By.id("event-date"));
        List<WebElement> eventLocation = driver.findElements(By.id("event-location"));
        LocalDate localDate = LocalDate.now();
        for (int i = 0; i < eventNames.size(); i++) {
            String name_string = eventNames.get(i).getText();
            String date_string = eventDate.get(i).getText();
            String Location_string = eventLocation.get(i).getText();
            Boolean location = Location_string.equals(locationofevent);
            boolean name = name_string.toUpperCase().equals(nameofevent.toUpperCase());
            boolean date = date_string.equals(localDate.toString());
            if ((date) && (name) && (location)) {
                points.get(i).click();

            }
        }
    }

    public static void TheDetailOfEventIsUpdated(String newnameofevent, String locationofevent, String Date)
            throws InterruptedException {
        Thread.sleep(3000);

        int obtainedresult = 0;
        int expectedresult = 1;
        List<WebElement> eventNames = driver.findElements(By.id("event-name"));
        List<WebElement> eventLocation = driver.findElements(By.id("event-location"));

        List<WebElement> eventDate = driver.findElements(By.id("event-date"));

        for (int i = 0; i < eventNames.size(); i++) {
            String name_string = eventNames.get(i).getText();

            boolean name = name_string.toUpperCase().equals(newnameofevent.toUpperCase());
            boolean location = (eventLocation.get(i).getText()).equals(locationofevent);
            boolean date = eventDate.get(i).getText().equals(Date);

            if ((name) && (date) && (location)) {
                obtainedresult = 1;
                break;
            }
        }

        Assert.assertEquals(expectedresult, obtainedresult);
    }

}
