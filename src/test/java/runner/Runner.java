package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(


        features = "C:\\CucumberProject\\AmazonFramework\\src\\test\\resources\\features\\API.feature",
        glue={"stepdefinitions"},
        //tags="@Checkout",
        dryRun = false,
        plugin= {"pretty", "html:C:/CucumberProject/AmazonFramework/reports/test-output.html"},
        monochrome = true
        //headed = true
)
public class Runner {
}
