package stepDefinitions;

import java.util.Random;
import org.openqa.selenium.By;
import Base.Base;
import POM.AddEventPage;
import POM.ArchiveEventPage;
import POM.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class archive_event_steps extends Base {

    private static final String RANDOM_NAME;
    static {
        String[] names = { "restore", "tester", "test", "archive", "qa", "quality", "testeur",
                "automation", "code", "testrestore", "testarchive" };
        Random random = new Random();
        int index = random.nextInt(names.length);
        RANDOM_NAME = names[index];
    }

    public static String getConstantRandomName() {
        return RANDOM_NAME;
    }

    String nameofevent = getConstantRandomName();

    @Before("@archive")
    public void goToAccount() {
        launch_browser();
        waitForVisibilityOfElement(By.id("email"));
        LoginPage.enterEmail(props.getProperty("emailphotographe"));
        LoginPage.enterPassword(props.getProperty("password"));
        LoginPage.clickOnTheLoginBTN();

    }

    @After("@archive")
    public void tearDown() {
        closeBrowser();
    }

    @And("photographer add event")
    public void add_event() {
        AddEventPage.ClickOnAddEvent();
        AddEventPage.enterTitleOfEvent(nameofevent);
        AddEventPage.ClickOnOKBTN();
    }

    @When("photographer click on the three buttouns")
    public void three_buttons() {
        try {
            Thread.sleep(3000);
            ArchiveEventPage.ClickOnTheThreePoint(nameofevent);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @And("Choose archive the event")
    public void Choose_archive_the_event() {
        ArchiveEventPage.ChooseArchive();

    }

    @Then("the event is archived")
    public void the_event_is_archived() throws InterruptedException {
        ArchiveEventPage.CheckingIfTheEventIsArchived(nameofevent);
    }

    @When("photographer should go to the archive event")
    public void photographer_should_go_to_the_archive_event() {
        ArchiveEventPage.NavigateToArchivedEvents();
    }

    @And("Choose restore the event")
    public void Choose_restore_the_event() throws InterruptedException {
        ArchiveEventPage.ChooseTheEvenToRestore(nameofevent);
    }

    @Then("the event is restored")
    public void the_event_is_restored() throws InterruptedException {
        ArchiveEventPage.CheckingIfTheEventIsRestored(nameofevent);
    }
}
