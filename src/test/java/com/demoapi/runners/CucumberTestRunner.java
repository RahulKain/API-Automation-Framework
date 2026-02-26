package com.demoapi.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * CucumberTestRunner class to run Cucumber features with TestNG
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.demoapi.hooks", "com.demoapi.stepDefinitions"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/index.html",
                "json:target/cucumber-reports/cucumber.json",
                "junit:target/cucumber-reports/cucumber.xml"
        },
        tags = "",
        monochrome = false,
        dryRun = false
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
