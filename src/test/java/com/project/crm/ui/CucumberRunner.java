package com.project.crm.ui;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(strict = true, plugin = {"pretty"}, features = "src/test/resources/features")
public class CucumberRunner {

}
