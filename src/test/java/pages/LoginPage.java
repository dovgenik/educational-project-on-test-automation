package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
private WebDriver driver;

private By userName = By.id("user-name");
private By password = By.cssSelector("[data-test='password']");
private By loginButton = By.xpath("//input[@id='login-button']");

private By errorMessage = By.cssSelector("[data-test='error']");

public LoginPage(WebDriver driver) {
this.driver = driver;
}

public void login(String user, String pass) {
    Allure.step("Авторизація користувача");
    driver.findElement(userName).sendKeys(user);
    driver.findElement(password).sendKeys(pass);
    driver.findElement(loginButton).click();
}

public String getErrorText() {
    Allure.step("Отримання тексту помилки");

    return driver.findElement(errorMessage).getText();


}



}