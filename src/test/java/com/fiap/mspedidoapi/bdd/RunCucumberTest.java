package com.fiap.mspedidoapi.bdd;

import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.ConfigurationParameter;
import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.fiap.mspedidoapi.bdd.steps")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty,html:target/cucumber-reports.html,json:target/cucumber-reports/CucumberTestReport.json")
public class RunCucumberTest {
}
