package stepDefinitions;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Random;

import POM.FilterPage;
import POM.LoginPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Base.Base;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class filterPhotographer_steps extends Base {

    // choisir la période à filtrer
    String startdate = generateRandomDate(2023, 2023);
    String enddate = generateRandomDate(2024, 2024);

    // choisir la recherche par nom

    String FilterText = addRandomCharacter("event");

    // choisir à filtrer par location
    String FilterLocation = addRandomCharacter("Location");

    private String addRandomCharacter(String title) {
        Random random = new Random();
        char randomChar = (char) (random.nextInt(26) + 'a'); // Generate a random lowercase letter

        String modifiedTitle = title + randomChar; // Append the random character to the title
        return modifiedTitle;
    }

    @Before("@filter")
    public void user_should_navigate_to_the_website() {
        launch_browser();
        waitForVisibilityOfElement(By.id("email"));

        LoginPage.enterEmail(props.getProperty("emailphotographe"));
        LoginPage.enterPassword(props.getProperty("password"));
        LoginPage.clickOnTheLoginBTN();
        FilterPage.navigateToFilter();

    }

    @After("@filter")
    public void tearDown() {
        closeBrowser();
    }

    @And("user click on all filters and should choose the start date and the end date")
    public void user_click_on_all_filters_and_should_choose_the_start_date_and_the_finish_date() {
        FilterPage.ChooseTwoDates(startdate, enddate);
    }

    @Then("the user must find the events in the period from start date to finish date")
    public void the_user_must_find_the_events_in_the_period_from_start_date_to_finish_date()
            throws InterruptedException {
        FilterPage.UserFindAllEventsInThisPeriod(startdate, enddate);
    }

    @And("user click on all filters and should write the name of event as {string}")
    public void user_click_on_all_filters_and_should_write_the_name_of_event(String name_of_event) {
        FilterPage.UserEnterNameOfEventTofilter(name_of_event);
    }

    @Then("The user must find the events whose name of event as {string}")
    public void The_user_must_find_the_events_whose_name_of_event(String name_of_event) throws InterruptedException {
        FilterPage.UserShouldFindAllEventsContainsThisNameOfEvent(name_of_event);
    }

    @And("user click on all filters and should write the location of event as {string}")
    public void user_click_on_all_filters_and_should_write_the_location_of_event(String location_of_event) {
        FilterPage.UserEnterLocationOfEventTofilter(location_of_event);
    }

    @Then("The user must find the events whose location of event as {string}")
    public void The_user_must_find_the_events_whose_location_of_event(String location_of_event)
            throws InterruptedException {
        FilterPage.UserShouldFindAllEventsContainsThisLocationOfEvent(location_of_event);
    }

    @And("user click on all filters and should write the status of event")
    public void user_click_on_all_filters_and_should_write_the_status_of_event() {
        try {
            Thread.sleep(1000);

            driver.findElement(By.id("eventFilterInProgress")).click();

            driver.findElement(By.id("eventFilterEventsBTN")).click();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("The user must find the events whose status of event as {string}")
    public void The_user_must_find_the_events_whose_status_of_event(String status_of_event) {
        try {
            Thread.sleep(2000);
            int expectedresult = 1;
            int obtainedresult = 0;
            List<WebElement> eventNames = driver.findElements(By.id("event-name"));
            List<WebElement> eventstatus = driver.findElements(By.className("ant-tag-gold"));
            // boolean find = false;
            int no_event = eventNames.size();
            // System.out.println("number of filtred events " + no_event);
            if (no_event == 0) {
                obtainedresult = 1;
                System.out.println("no event with this status");
            } else {
                for (int i = 0; i < eventNames.size(); i++) {
                    // String names = eventNames.get(i).getText();
                    String status = eventstatus.get(i).getText();
                    boolean find = status.toUpperCase().contentEquals(status_of_event.toUpperCase());
                    // System.out.println(names);
                    // System.out.println(status);
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
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @And("user click on all filters and should write the status of the event")
    public void user_click_on_all_filters_and_should_write_the_status_of_the_event() {
        try {

            Thread.sleep(1000);

            driver.findElement(By.id("eventFilterCompleted")).click();

            driver.findElement(By.id("eventFilterEventsBTN")).click();
            Thread.sleep(10);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("The user must find the events whose have the status of event as {string}")
    public void The_user_must_find_the_events_whose_have_the_status_of_event(String status_of_event) {
        try {
            Thread.sleep(2000);
            int expectedresult = 1;
            int obtainedresult = 0;
            List<WebElement> eventNames = driver.findElements(By.id("event-name"));
            List<WebElement> eventstatus = driver.findElements(By.className("ant-tag-green"));
            // boolean find = false;

            int no_event = eventNames.size();
            System.out.println("number of filtred events " + no_event);
            if (no_event == 0) {
                obtainedresult = 1;
                System.out.println("no event with this status");
            } else {
                for (int i = 0; i < eventNames.size(); i++) {
                    // String names = eventNames.get(i).getText();
                    String status = eventstatus.get(i).getText();
                    boolean find = status.toUpperCase().contentEquals(status_of_event.toUpperCase());
                    // System.out.println(names);
                    // System.out.println(status);
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

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @And("user click on all filters and should click on today")
    public void user_click_on_all_filters_and_should_click_on_today() {

        waitForVisibilityOfElement(By.id("eventDateFilter"));
        driver.findElement(By.id("eventDateFilter")).click();

        waitForVisibilityOfElement(By.id("test_Today"));
        driver.findElement(By.id("test_Today")).click();

        waitForVisibilityOfElement(By.id("eventDateFilterOK"));
        driver.findElement(By.id("eventDateFilterOK")).click();

        waitForVisibilityOfElement(By.id("eventFilterEventsBTN"));
        driver.findElement(By.id("eventFilterEventsBTN")).click();

    }

    @Then("The user must find the events of today")
    public void The_user_must_find_the_events_of_today() {
        try {
            Thread.sleep(2000);
            int expectedresult = 1;
            int obtainedresult = 0;
            List<WebElement> eventNames = driver.findElements(By.id("event-name"));
            List<WebElement> eventDate = driver.findElements(By.id("event-date"));
            LocalDate localDate = LocalDate.now();
            // boolean find = false;
            int no_event = eventNames.size();
            System.out.println("number of filtred events " + no_event);
            if (no_event == 0) {
                obtainedresult = 1;
                System.out.println("no event today");
            } else {
                for (int i = 0; i < eventNames.size(); i++) {
                    // String names =eventNames.get(i).getText();
                    String date = eventDate.get(i).getText();
                    boolean found = date.contentEquals(localDate.toString());
                    // System.out.println(names);
                    // System.out.println(date);
                    if (found) {
                        obtainedresult = 1;
                    } else {
                        // System.out.println("test fail");
                        obtainedresult = 0;
                        break;
                    }
                }
            }
            Assert.assertEquals(expectedresult, obtainedresult);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @And("user click on all filters and should click on this week")
    public void user_click_on_all_filters_and_should_click_on_this_week() {

        waitForVisibilityOfElement(By.id("eventDateFilter"));
        driver.findElement(By.id("eventDateFilter")).click();

        driver.findElement(By.id("test_This Week")).click();

        driver.findElement(By.id("eventDateFilterOK")).click();
        waitForVisibilityOfElement(By.id("eventFilterEventsBTN"));
        driver.findElement(By.id("eventFilterEventsBTN")).click();

    }

    @Then("The user must find the events of this week")
    public void The_user_must_find_the_events_of_this_week() {
        try {
            Thread.sleep(2000);
            int expectedresult = 1;
            int obtainedresult = 0;
            List<WebElement> eventNames = driver.findElements(By.id("event-name"));
            List<WebElement> eventDate = driver.findElements(By.id("event-date"));
            LocalDate date = LocalDate.now();
            LocalDate startweek = date;
            while (startweek.getDayOfWeek() != DayOfWeek.MONDAY) {
                startweek = startweek.minusDays(1);

            }
            // System.out.println("Start of the Week = " + startweek);
            LocalDate endweek = date;
            while (endweek.getDayOfWeek() != DayOfWeek.SUNDAY) {
                endweek = endweek.plusDays(1);

            }
            // System.out.println("End of the Week = " + endweek);

            // boolean find = false;
            int no_event = eventNames.size();
            System.out.println("number of filtred events " + no_event);
            if (no_event == 0) {
                obtainedresult = 1;
                System.out.println("no event this week");
            } else {
                for (int i = 0; i < eventNames.size(); i++) {
                    String dateevent = eventDate.get(i).getText();
                    // String names = eventNames.get(i).getText();
                    // System.out.println(names);
                    // System.out.println(dateevent);
                    LocalDate today = LocalDate.parse(dateevent);

                    if ((today.isBefore(startweek)) || (today.isAfter(endweek))) {
                        System.out.println("test fail");
                        obtainedresult = 0;
                        break;
                    } else {
                        obtainedresult = 1;
                    }
                }
            }

            Assert.assertEquals(expectedresult, obtainedresult);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @And("user click on all filters and should click on this month")
    public void user_click_on_all_filters_and_should_click_on_this_month() {

        waitForVisibilityOfElement(By.id("eventDateFilter"));
        driver.findElement(By.id("eventDateFilter")).click();

        waitForVisibilityOfElement(By.id("test_This Month"));
        driver.findElement(By.id("test_This Month")).click();

        driver.findElement(By.id("eventDateFilterOK")).click();

        driver.findElement(By.id("eventFilterEventsBTN")).click();

    }

    @Then("The user must find the events of this month")
    public void The_user_must_find_the_events_of_this_month() {
        try {
            Thread.sleep(2000);
            int expectedresult = 1;
            int obtainedresult = 0;
            List<WebElement> eventNames = driver.findElements(By.id("event-name"));
            List<WebElement> eventDate = driver.findElements(By.id("event-date"));
            LocalDate date = LocalDate.now();
            // boolean find = false;
            int no_event = eventNames.size();
            System.out.println("number of filtred events " + no_event);
            if (no_event == 0) {
                obtainedresult = 1;
                System.out.println("no event this month");
            } else {
                for (int i = 0; i < eventNames.size(); i++) {
                    String dateevent = eventDate.get(i).getText();
                    // String names = eventNames.get(i).getText();
                    // System.out.println(names);
                    // System.out.println(dateevent);
                    LocalDate today = LocalDate.parse(dateevent);
                    LocalDate firstday = LocalDate.parse(date.withDayOfMonth(1).toString());
                    LocalDate lastday = LocalDate.parse(date.withDayOfMonth(date.lengthOfMonth()).toString());
                    if (today.isBefore(firstday) || today.isAfter(lastday)) {
                        // System.out.println("test fail");
                        obtainedresult = 0;
                        break;
                    } else {
                        obtainedresult = 1;
                    }
                }
            }
            Assert.assertEquals(expectedresult, obtainedresult);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @And("user click on all filters and should click on this year")
    public void user_click_on_all_filters_and_should_click_on_this_year() {

        waitForVisibilityOfElement(By.id("eventDateFilter"));
        driver.findElement(By.id("eventDateFilter")).click();
        waitForVisibilityOfElement(By.id("test_This Year"));
        driver.findElement(By.id("test_This Year")).click();

        driver.findElement(By.id("eventDateFilterOK")).click();

        driver.findElement(By.id("eventFilterEventsBTN")).click();

    }

    @Then("The user must find the events of this year")
    public void The_user_must_find_the_events_of_this_year() {
        try {
            Thread.sleep(2000);
            int expectedresult = 1;
            int obtainedresult = 0;
            List<WebElement> eventNames = driver.findElements(By.id("event-name"));
            List<WebElement> eventDate = driver.findElements(By.id("event-date"));
            LocalDate date = LocalDate.now();
            // boolean find = false;
            int no_event = eventNames.size();
            System.out.println("number of filtred events " + no_event);
            if (no_event == 0) {
                obtainedresult = 1;
                System.out.println("no event this year");
            } else {
                for (int i = 0; i < eventNames.size(); i++) {
                    String dateevent = eventDate.get(i).getText();

                    LocalDate today = LocalDate.parse(dateevent);
                    LocalDate firstDayOfYear = date.with(TemporalAdjusters.firstDayOfYear());
                    LocalDate lastDayOfYear = date.with(TemporalAdjusters.lastDayOfYear());

                    if (today.isBefore(firstDayOfYear) || today.isAfter(lastDayOfYear)) {
                        obtainedresult = 0;
                        break;
                    } else {
                        obtainedresult = 1;
                    }
                }
            }
            Assert.assertEquals(expectedresult, obtainedresult);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @And("user click on all filters and fill the name of event as {string} , the location of event as {string}, and the status of event as {string} and the date of event today")
    public void user_click_on_all_filters_and_fill_the_name_of_event_the_location_of_event_and_the_status_of_event_and_the_date_of_event_today(
            String name_of_event, String location_of_event, String status_of_event) {

        waitForVisibilityOfElement(By.id("eventName"));
        driver.findElement(By.id("eventName")).sendKeys(name_of_event);

        driver.findElement(By.id("eventLocation")).sendKeys(location_of_event);

        driver.findElement(By.id("eventFilterInProgress")).click();

        driver.findElement(By.id("eventDateFilter")).click();

        waitForVisibilityOfElement(By.id("test_Today"));
        driver.findElement(By.id("test_Today")).click();

        driver.findElement(By.id("eventDateFilterOK")).click();

        driver.findElement(By.id("eventFilterEventsBTN")).click();

    }

    @Then("The user must find the events whoose name of event as {string} , location of event as {string}, status of event as {string} and the date of event today")
    public void The_user_must_find_the_events_whoose_name_of_event_location_of_event_status_of_event_and_the_date_of_event_today(
            String name_of_event, String location_of_event, String status_of_event) {
        try {
            Thread.sleep(2000);
            int expectedresult = 1;
            int obtainedresult = 0;
            List<WebElement> eventNames = driver.findElements(By.id("event-name"));
            List<WebElement> eventDate = driver.findElements(By.id("event-date"));
            List<WebElement> eventstatus = driver.findElements(By.className("ant-tag-gold"));
            List<WebElement> eventlocation = driver.findElements(By.id("event-location"));
            LocalDate date = LocalDate.now();
            // boolean find = false;
            int no_event = eventNames.size();
            System.out.println("number of filtred events " + no_event);
            if (no_event == 0) {
                obtainedresult = 1;
                System.out.println("no event with this filter");
            } else {
                for (int i = 0; i < eventNames.size(); i++) {
                    String dateevent = eventDate.get(i).getText();
                    String names = eventNames.get(i).getText();
                    String status = eventstatus.get(i).getText();
                    String location = eventlocation.get(i).getText();
                    // System.out.println(names);
                    // System.out.println(location);
                    // System.out.println(dateevent);
                    // System.out.println(status);
                    boolean name_boolean = names.toUpperCase().contains(name_of_event.toUpperCase());
                    boolean date_boolean = dateevent.contentEquals(date.toString());
                    boolean status_boolean = status.contentEquals(status_of_event);
                    boolean location_boolean = location.contains(location_of_event);
                    if ((name_boolean) && (date_boolean) && (status_boolean) && (location_boolean)) {
                        // System.out.println("filter is okay");
                        obtainedresult = 1;
                    } else {
                        // System.out.println("test fail");
                        obtainedresult = 0;
                        break;
                    }
                }
            }
            Assert.assertEquals(expectedresult, obtainedresult);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);

        }
    }

}
