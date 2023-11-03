package POM;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Base.Base;

public class ArchiveEventPage extends Base {
    public static String strEventDropDown = "dropdown-event-link";
    public static String ChoiceOfEvent = "event-edit-dropdown";
    public static String namesofevents = "event-name";
    public static String datesofevents = "event-date";
    public static String locationsofevents = "event-location";

    public static String archiveOption = "testArchive";
    public static String okarchiveOption = "testOKArchive";
    public static String archivedevents = "event-archive";
    public static String Restore = "testRestore";

    public static void ClickOnTheThreePoint(String nameofevent) {
        List<WebElement> points = driver.findElements(By.id(ChoiceOfEvent));
        List<WebElement> eventNames = driver.findElements(By.id(namesofevents));
        List<WebElement> eventDate = driver.findElements(By.id(datesofevents));
        List<WebElement> eventLocation = driver.findElements(By.id(locationsofevents));
        LocalDate localDate = LocalDate.now();
        for (int i = 0; i < eventNames.size(); i++) {
            String name_string = eventNames.get(i).getText();
            String date_string = eventDate.get(i).getText();
            String Location_string = eventLocation.get(i).getText();
            Boolean location = Location_string.equals("Not defined");
            boolean name = name_string.toUpperCase().equals(nameofevent.toUpperCase());
            boolean date = date_string.equals(localDate.toString());
            if ((date) && (name) && (location)) {
                points.get(i).click();

            }
        }
    }

    public static void ChooseArchive() {
        waitForVisibilityOfElement(By.id(archiveOption));
        driver.findElement(By.id(archiveOption)).click();
        waitForVisibilityOfElement(By.id(okarchiveOption));
        driver.findElement(By.id(okarchiveOption)).click();
    }

    public static void CheckingIfTheEventIsArchived(String nameofevent) throws InterruptedException {
        waitForElementToBeClickable(By.id(strEventDropDown));
        driver.findElement(By.id(strEventDropDown)).click();
        waitForVisibilityOfElement(By.id(archivedevents));
        driver.findElement(By.id(archivedevents)).click();
        int obtainedresult = 0;
        int expectedresult = 1;
        Thread.sleep(2000);
        List<WebElement> eventNames = driver.findElements(By.id(namesofevents));
        List<WebElement> eventDate = driver.findElements(By.id(datesofevents));
        LocalDate localDate = LocalDate.now();
        for (int i = 0; i < eventNames.size(); i++) {
            String name_string = eventNames.get(i).getText();
            String date_string = eventDate.get(i).getText();
            boolean name = name_string.toUpperCase().equals(nameofevent.toUpperCase());
            boolean date = date_string.equals(localDate.toString());
            if ((name) && (date)) {
                obtainedresult = 1;
                // System.out.println((nameofevent + " is archived"));
                break;
            }
        }
        Assert.assertEquals(expectedresult, obtainedresult);
    }

    public static void NavigateToArchivedEvents() {
        waitForElementToBeClickable(By.id(strEventDropDown));
        driver.findElement(By.id(strEventDropDown)).click();
        waitForVisibilityOfElement(By.id(archivedevents));
        driver.findElement(By.id(archivedevents)).click();
    }

    public static void ChooseTheEvenToRestore(String nameofevent) throws InterruptedException {
        Thread.sleep(3000);
        List<WebElement> points = driver.findElements(By.id(ChoiceOfEvent));
        List<WebElement> eventNames = driver.findElements(By.id(namesofevents));
        List<WebElement> eventDate = driver.findElements(By.id(datesofevents));
        LocalDate localDate = LocalDate.now();
        for (int i = 0; i < eventNames.size(); i++) {
            String name_string = eventNames.get(i).getText();
            String date_string = eventDate.get(i).getText();

            boolean name = name_string.toUpperCase().equals(nameofevent.toUpperCase());
            boolean date = date_string.equals(localDate.toString());
            if ((date) && (name)) {
                points.get(i).click();
                waitForVisibilityOfElement(By.id(Restore));
                driver.findElement(By.id(Restore)).click();
                // System.out.println(nameofevent + " is restored");
                break;

            }
        }
    }

    public static void CheckingIfTheEventIsRestored(String nameofevent) throws InterruptedException {
        Thread.sleep(3000);

        int obtainedresult = 0;
        int expectedresult = 1;

        List<WebElement> eventNames = driver.findElements(By.id("event-name"));

        List<WebElement> eventDate = driver.findElements(By.id("event-date"));

        LocalDate localDate = LocalDate.now();
        if (eventNames.size() == 0) {
            // System.out.println("no event for restore ");

            obtainedresult = 1;
        } else {
            for (int i = 0; i < eventNames.size(); i++) {
                String name_string = eventNames.get(i).getText();
                String date_string = eventDate.get(i).getText();
                boolean name = name_string.toUpperCase().equals(nameofevent.toUpperCase());
                boolean date = date_string.equals(localDate.toString());
                if ((name) && (date)) {
                    obtainedresult = 0;

                    break;
                } else {
                    obtainedresult = 1;
                    ;

                }
            }
        }

        Assert.assertEquals(expectedresult, obtainedresult);
    }

}
