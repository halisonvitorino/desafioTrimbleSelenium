package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
    By todoEditInputField   = By.xpath("//div/label");
    By todoInputForDelete   = By.xpath("//label[contains(text(),'New todo')]");
    By todoDeleteButton     = By.xpath("//body/section[1]/div[1]/section[1]/ul[1]/li[1]/div[1]/button[1]");

    public void inputTodo(String todo){
        WebElement textBox = driver.findElement(todoInputField);
        Assert.assertTrue(textBox.isDisplayed());
        textBox.sendKeys(todo);
    }

    public void editTodo(String todo){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/label")));
            element.click();
        } catch (TimeoutException e) {
            System.out.println("Elemento não encontrado com o tempo de espera especificado");
        }
        Actions actions = new Actions(driver);
        WebElement textBox = driver.findElement(todoEditInputField);
        //textBox.click();
        //actions.doubleClick(textBox).perform();
        textBox.sendKeys(Keys.CONTROL + "a");
        //textBox.sendKeys(Keys.DELETE);
        //textBox.sendKeys(todo);
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
        System.out.println(todo);
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
}
