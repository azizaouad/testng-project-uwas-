package POM;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import Base.Base;

public class FilterPage extends Base {

    public static void UserShouldFindAllEventsContainsThisLocationOfEvent(String location_of_event)
            throws InterruptedException {
        Thread.sleep(3000);
        int expectedresult = 1;
        int obtainedresult = 0;

        List<WebElement> eventNames = driver.findElements(By.id("event-name"));
        List<WebElement> eventlocation = driver.findElements(By.id("event-location"));
        // boolean find = false;
        int no_event = eventNames.size();
        System.out.println("number of filtred events " + no_event);
        if (no_event == 0) {
            obtainedresult = 1;
            System.out.println("no event with this location");
        } else {
            for (int i = 0; i < eventNames.size(); i++) {
                // String names = eventNames.get(i).getText();
                String location = eventlocation.get(i).getText();
                boolean find = location.toUpperCase().contains(location_of_event.toUpperCase());
                // System.out.println(names);
                // System.out.println(location);
                if (find) {
                    obtainedresult = 1;
                } else {
                    // System.out.println("test fail");
                    obtainedresult = 0;
                    break;

                }
            }
        }
        Assert.assertEquals(expectedresult, obtainedresult);
    }

    public static void UserEnterLocationOfEventTofilter(String location_of_event) {
        waitForVisibilityOfElement(By.id("eventLocation"));
        driver.findElement(By.id("eventLocation")).sendKeys(location_of_event);
        // Thread.sleep(1000);

        driver.findElement(By.id("eventFilterEventsBTN")).click();
    }

    public static void UserShouldFindAllEventsContainsThisNameOfEvent(String name_of_event)
            throws InterruptedException {
        Thread.sleep(2000);
        int expectedresult = 1;
        int obtainedresult = 0;

        List<WebElement> eventNames = driver.findElements(By.id("event-name"));
        // boolean find = false;
        int no_event = eventNames.size();
        System.out.println("number of filtred events " + no_event);
        if (no_event == 0) {
            obtainedresult = 1;
            System.out.println("no event with this name");
        } else {
            for (int i = 0; i < eventNames.size(); i++) {
                String Name = eventNames.get(i).getText();
                boolean find = Name.toUpperCase().contains(name_of_event.toUpperCase());
                if (find) {
                    obtainedresult = 1;
                    System.out.println(Name);
                } else {
                    obtainedresult = 0;
                    break;

                }
            }
        }

        Assert.assertEquals(expectedresult, obtainedresult);

    }

    public static void UserEnterNameOfEventTofilter(String name_of_event) {
        waitForVisibilityOfElement(By.id("eventName"));
        driver.findElement(By.id("eventName")).sendKeys(name_of_event);

        driver.findElement(By.id("eventFilterEventsBTN")).click();
    }

    public static void UserFindAllEventsInThisPeriod(String startdate, String enddate) throws InterruptedException {
        Thread.sleep(3000);

        int expectedresult = 1;
        int obtainedresult = 0;
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate startInterval = LocalDate.parse(startdate);
        LocalDate endInterval = LocalDate.parse(enddate);
        List<WebElement> eventNames = driver.findElements(By.id("event-name"));
        List<WebElement> eventDate = driver.findElements(By.id("event-date"));
        // boolean find = false;
        int no_event = eventNames.size();
        // System.out.println("number of filtred events " + no_event);
        if (no_event == 0) {
            obtainedresult = 1;
            System.out.println("no event in this period");
        } else {
            for (int i = 0; eventNames.size() > i; i++) {
                LocalDate date = LocalDate.parse(eventDate.get(i).getText());
                // String names = eventNames.get(i).getText();
                // System.out.println(names);
                // System.out.println(date);
                if (date.isBefore(startInterval) || date.isAfter(endInterval)) {
                    obtainedresult = 0;
                    System.out.println(date);
                    break;
                } else {
                    obtainedresult = 1;
                }
            }
        }

        Assert.assertEquals(expectedresult, obtainedresult);
    }

    public static void ChooseTwoDates(String startdate, String enddate) {
        waitForVisibilityOfElement(By.id("eventDateFilter"));

        driver.findElement(By.id("eventDateFilter")).sendKeys(startdate);
        String placeholderText = "End date";
        driver.findElement(By.xpath("//input[@placeholder='" + placeholderText + "']"))
                .sendKeys(enddate);
        driver.findElement(By.id("eventDateFilter")).sendKeys(Keys.ENTER);
        driver.findElement(By.id("eventDateFilterOK")).click();
        driver.findElement(By.id("eventFilterEventsBTN")).click();

    }

    public static void GoTofilter() throws InterruptedException {
        waitForVisibilityOfElement(By.id("dropdown-event-link"));
        driver.findElement(By.id("dropdown-event-link")).click();
        Thread.sleep(1000);
        List<WebElement> List = driver.findElements(By.className("ant-dropdown-menu-title-content"));
        List.get(0).click();
    }

    public static void ChooseViewedEvent() throws InterruptedException {
        Thread.sleep(1000);

        driver.findElement(By.id("eventFilterViewed")).click();
        Thread.sleep(1000);
        List<WebElement> evnetViewed = driver.findElements(By.className("viewed"));
        System.out.println("the events viewed " + evnetViewed.size());

        driver.findElement(By.id("eventFilterEventsBTN")).click();
        Thread.sleep(1000);
    }

    public static void CheckingTheViewedEvents() throws InterruptedException {
        Thread.sleep(2000);

        List<WebElement> eventNames = driver.findElements(By.id("event-name"));
        List<WebElement> evnetViewed = driver.findElements(By.className("viewed"));
        // System.out.println("the events viewed " + evnetViewed.size());
        boolean find = false;
        int no_event = eventNames.size();
        // System.out.println("number of filtred events " + no_event);
        if (no_event == 0) {
            find = true;
            System.out.println("no event consulted");
        } else {
            if (no_event == evnetViewed.size()) {
                find = true;
            }
        }

        Assert.assertTrue(find);

    }

    public static void chooseNotViewedEvent() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.id("eventFilterNotViewed")).click();
        List<WebElement> evnetnotViewed = driver.findElements(By.className("notViewed"));
        System.out.println("the events not viewed " + evnetnotViewed.size());
        driver.findElement(By.id("eventFilterEventsBTN")).click();

    }

    public static void CheckingTheNotViewedEvents() throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> eventNames = driver.findElements(By.id("event-name"));
        List<WebElement> evnetnotViewed = driver.findElements(By.className("notViewed"));
        System.out.println("the events not viewed " + evnetnotViewed.size());
        boolean find = false;
        int no_event = eventNames.size();
        System.out.println("number of filtred events " + no_event);
        if (no_event == 0) {
            find = true;
            System.out.println("no event no consulted");
        } else {
            if (no_event == evnetnotViewed.size()) {
                find = true;
            }
        }

        Assert.assertTrue(find);
    }

    public static void navigateToFilter() {
        waitForVisibilityOfElement(By.id("dropdown-event-link"));
        driver.findElement(By.id("dropdown-event-link")).click();
        waitForElementToBeClickable(By.id("event-filtre"));
        driver.findElement(By.id("event-filtre")).click();

    }

}
