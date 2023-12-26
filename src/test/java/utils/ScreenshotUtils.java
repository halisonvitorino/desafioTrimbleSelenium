package utils;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    public static void captureScreenshot(WebDriver driver, Scenario scenario) {
        try {
                // Converte o WebDriver para TakesScreenshot
                TakesScreenshot ts = (TakesScreenshot) driver;

                // Captura a imagem da tela como um arquivo
                byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);

                // Adiciona a imagem ao relatório do Cucumber
                scenario.attach(screenshot, "image/png", "ExecutionScreenshot");
            } catch (Exception e) {
                System.out.println("Erro ao capturar screenshot: " + e.getMessage());
            }

    }
}

