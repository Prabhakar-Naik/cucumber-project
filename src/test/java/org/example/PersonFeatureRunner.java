package org.example;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author prabhakar, @Date 18-07-2024
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/org/example",
        glue = "org.example")
@ContextConfiguration(classes = PersonFeatureSteps.class)
public class PersonFeatureRunner {

        // the class running from here for the testing.
}
