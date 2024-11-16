package org.example.Part1_UI;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class loginTest {
    private WebDriver driver;

    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
    public void login() {
        setup();
        driver.get("https://login.purina.com/login?redirect_uri=https%3A%2F%2Fwww.petfinder.com%2Fsso-signin%2F&response_type=code&client_id=6gmsl23kdd0607g6qdbgknqsd1&identity_provider=COGNITO&scope=phone%20email%20profile%20openid%20https%3A%2F%2Fapi.services.purina.com%2Fesp%3Aapi%3A%3Aaccess%20aws.cognito.signin.user.admin&state=8jUvPnNojNigimJiLpepH7gtG5hePoGy-7b227072657648726566223a2268747470733a2f2f7777772e70657466696e6465722e636f6d2f227d&code_challenge=bWkJa9lWW0HNNai19Q0FbfjhkUhNUWB0nhRTdYK9fLo&code_challenge_method=S256");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        WebElement signInButton = driver.findElement(By.cssSelector("input[name='signInSubmitButton']"));
//        js.executeScript("arguments[0].click();", signInButton);
//        signInButton.click();
        WebElement email = driver.findElement(By.cssSelector("input[id='signInFormUsername']"));
        js.executeScript("arguments[0].value='YourTextHere';", email);
        email.sendKeys("baselkhalifa92@gmail.com");
        WebElement password = driver.findElement(By.id("signInFormPassword"));
        //email.click();
        //email.sendKeys("baselkhalifa92@gmail.com");
        password.sendKeys("P@ssw0rd");

    }
}
