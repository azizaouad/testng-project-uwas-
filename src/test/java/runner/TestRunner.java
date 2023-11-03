package runner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/functionalFeatures/login.feature", glue = "stepDefinitions", plugin = {
        "pretty",
        "html:target/reports/report.html", "json:target/reports/report.json" }
)

public class TestRunner extends AbstractTestNGCucumberTests {

}
