package TestRunner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
//To run multiple features add path to the features in the CucumberOptions.
//To run specific scenarios use tags
//To ignore scenarios use tags ={"~@sanity"}
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {".\\Features\\customers.feature",".\\Features\\login.feature"},
        glue = "StepDefinition",
        dryRun = false,
        monochrome = false,
        tags = {"@sanity"},
        plugin = {"pretty", "html: test - output"}
)

public class TestRun {
}
