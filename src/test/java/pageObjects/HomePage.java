package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class HomePage {

    private final WebDriver driver;

    public HomePage (WebDriver driver){
        this.driver = driver;
    }

    By todoInputField       = By.className("new-todo");
    By todoCheckBox         = By.xpath("/html/body/section/div/section/ul/li[1]/div/input");
    By todoCheckBoxChecked  = By.xpath("//a[contains(text(),'Completed')]");
    By todoInputFilled      = By.cssSelector("li:nth-child(1) label");
    By todoListCompleted    = By.linkText("Completed");
    By todoEditInputField   = By.xpath("/html/body/section/div/section/ul/li/div/label");
    By todoInputForDelete   = By.xpath("//label[contains(text(),'New todo')]");
    By todoDeleteButton     = By.xpath("//body/section[1]/div[1]/section[1]/ul[1]/li[1]/div[1]/button[1]");

    public void inputTodo(String todo){
        WebElement textBox = driver.findElement(todoInputField);
        Assert.assertTrue(textBox.isDisplayed());
        textBox.sendKeys(todo);
    }

    public void editTodo(String todo){
        WebElement todoToBeEdited = driver.findElement(todoEditInputField);
        Actions actions = new Actions(driver);
        actions.moveToElement(todoToBeEdited).perform();
        todoToBeEdited.click();
        actions.doubleClick(todoToBeEdited).perform();
        WebElement todoToBeEditedHidden = driver.findElement(By.xpath("/html/body/section/div/section/ul/li/input"));
        todoToBeEditedHidden.sendKeys(Keys.CONTROL + "a");
        todoToBeEditedHidden.sendKeys(Keys.DELETE);
        todoToBeEditedHidden.sendKeys(todo);
    }

    public void markChecked(){
        driver.findElement(todoCheckBox).click();
    }

    public void pressEnter(){
        driver.findElement(todoInputField).sendKeys(Keys.ENTER);
    }

    public void readCreatedTodo(String todo){
        WebElement textBox = driver.findElement(todoInputFilled);
        Assert.assertTrue(textBox.getText().contentEquals(todo));
    }

    public void verifyMarkChecked(){
        WebElement checkBox = driver.findElement(todoCheckBoxChecked);
        Assert.assertTrue(checkBox.isDisplayed());
    }

    public void listCompletedTodo(String todo){
        WebElement textBox = driver.findElement(todoInputFilled);
        driver.findElement(todoListCompleted).click();
        Assert.assertTrue(textBox.getText().contentEquals(todo));
    }

    public void deleteTodo(){
        WebElement element = driver.findElement(todoInputForDelete);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        WebElement hiddenElement = driver.findElement(todoDeleteButton);
        hiddenElement.click();
    }

    public void verifyDeletedTodo(){
        WebElement textBox = driver.findElement(todoInputField);
        Assert.assertTrue(textBox.isDisplayed());
        Assert.assertTrue(textBox.getText().isEmpty());
    }

    public void readEditedTodo(String todo){
        WebElement textBox = driver.findElement(todoInputFilled);
        Assert.assertTrue(textBox.getText().contentEquals(todo));
    }
}
