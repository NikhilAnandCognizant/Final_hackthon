package com.tests.stepDef;


import io.cucumber.junit.CucumberOptions;

@CucumberOptions(
        features = "src/main/resources/features",
        glue = "com.tests.stepDef",
        tags = "@Faq" // Only runs scenarios marked with @Smoke
)
public class FaqTestRunner {
}
