package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.HomePage;
import utils.ScreenshotUtils;

import java.io.File;
import java.io.IOException;

public class maintainTodos {

    private static WebDriver driver = null;

    HomePage homePage;

    @Before
    public void setupClass(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
    }

    @After
    public void teardown(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            String testScenario = scenario.getName();
            String error = scenario.getStatus().toString();
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("target/screenshots/" + testScenario + " - " + error + " - " +".png"));
        } else {
            ScreenshotUtils.captureScreenshot(driver, scenario);
        }
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("the user is on the home page")
    public void theUserIsOnTheHomePage(){
        driver.get("https://todomvc.com/examples/react/#/");
        driver.manage().window().maximize();
    }

    @When("the user enters a task")
    public void theUserEntersATask() {
        homePage.inputTodo("New todo");
    }

    @And("press enter")
    public void pressEnter() {
        homePage.pressEnter();
    }

    @Then("a new line with the new task is created")
    public void aNewLineWithTheNewTaskIsCreated() {
        homePage.readCreatedTodo("New todo");
    }

    @When("the user select a task to mark as complete")
    public void theUserSelectATaskToMarkAsComplete() {
        homePage.markChecked();
    }

    @Then("the selected task is marked completed")
    public void theSelectedTaskIsMarkedCompleted() {
        homePage.verifyMarkChecked();
    }

    @And("is listed as completed task")
    public void isListedAsCompletedTask() {
        homePage.listCompletedTodo("New todo");
    }

    @When("the user select a task to edit")
    public void theUserSelectATaskToEdit() {
        homePage.editTodo("Todo edited");
    }

    @When("the user delete a selected task")
    public void theUserDeleteASelectedTask() {
        homePage.deleteTodo();
    }

    @Then("the task is not visible on the list")
    public void theTaskIsNotVisibleOnTheList() {
        homePage.verifyDeletedTodo();
    }

    @Then("a line with the edited task is created")
    public void aLineWithTheEditedTaskIsCreated() {
        homePage.readEditedTodo("Todo edited");
    }
}
