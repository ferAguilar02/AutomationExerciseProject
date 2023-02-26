package com.jalasoft.automation.hooks;

import framework.selenium.DriverManager;
import io.cucumber.java.AfterAll;

public class ScenarioHooks {
    @AfterAll
    public static void afterAll() {
        DriverManager.getInstance().quitWebDriver();
    }
}
