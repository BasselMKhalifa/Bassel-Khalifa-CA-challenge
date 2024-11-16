package org.example.Part1_UI;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class registrationTest {

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
    @Test
    public void testRegistration() {
        setup();
        // Get the current timestamp
        LocalDateTime now = LocalDateTime.now();

        // Format the timestamp (e.g., "yyyy-MM-dd HH:mm:ss")
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String timestamp = now.format(formatter);
        driver.get("https://myprofile.purina.com/sign-up?property=petfinder&origin=https%3A%2F%2Fwww.petfinder.com%2F&client=6gmsl23kdd0607g6qdbgknqsd1&aid=undefined&method=undefined&sign-up-source=PFPetfinderSiteRegistration");
        WebElement emailTxt = driver.findElement(By.cssSelector("input[name='emailAddress']"));
        emailTxt.sendKeys("user"+timestamp+"@gmail.com");
        WebElement submitBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        submitBtn.click();
        WebElement firstNameTxt = driver.findElement(By.cssSelector("input[name='firstName']"));
        firstNameTxt.sendKeys("John");
        WebElement lastNameTxt = driver.findElement(By.cssSelector("input[name='lastName']"));
        lastNameTxt.sendKeys("Doe");
        WebElement zipCodeTxt = driver.findElement(By.cssSelector("input[name='zipCode']"));
        zipCodeTxt.sendKeys("12345");
        WebElement numberofDogsSelect = driver.findElement(By.cssSelector("select[name='dogCount']"));
        numberofDogsSelect.click();
        Select select =  new Select(numberofDogsSelect);
        select.selectByValue("1");
        WebElement numberofCatsSelect = driver.findElement(By.cssSelector("select[name='catCount']"));
        numberofCatsSelect.click();
        Select select2 =  new Select(numberofCatsSelect);
        select2.selectByValue("1");

    }
}
