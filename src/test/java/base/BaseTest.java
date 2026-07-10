package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.support.ui.WebDriverWait;


public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();

        // Передаю налаштування браузера щоб позбавитись вікон з попередженнями (т.ч. пароль опубліковано в інтернеті)
        Map<String, Object> prefs = new HashMap<>();

        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);

        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);


        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");

    }

    @AfterMethod
    public  void  tearDown() {
if (driver != null) {
    driver.quit();
}
    }

}