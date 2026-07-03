import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class firstTest {
    @Test
    public static void testMethod() {
        Allure.step( "Авторизація користувача");

        WebDriver driver= new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys("standard_user");

        WebElement userPassInput = driver.findElement(By.cssSelector("[data-test='password']"));
        userPassInput.sendKeys("secret_sauce");

        WebElement userLoginButton = driver.findElement(By.xpath("//input[@id='login-button']"));
        userLoginButton.click();

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));

        System.out.println(driver.getCurrentUrl());
        driver.quit();
    }

}
// WebElement usernameInput = driver.findElement(By.id("user-name"));
// driver.findElement(By.name("user-name"));
// driver.findElement(By.className("submit-button"));
// driver.findElement(By.tagName("input"));
// usernameInput.sendKeys("standard_user");

// Assert.assertEquals(2 + 2, 4);
// Assert.assertTrue()