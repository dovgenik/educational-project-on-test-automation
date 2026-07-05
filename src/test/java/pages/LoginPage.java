package pages;

import io.qameta.allure.Allure; // Импорт Allure для красивого отчета.
import org.openqa.selenium.By; // Импорт классов Selenium.
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions; // Импорт ожиданий Selenium.
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * Page Object страницы Login.
 *
 * В этом классе находятся:
 * - локаторы элементов;
 * - методы работы со страницей.
 *
 * Благодаря этому в тестах не будет поиска элементов, а только вызов готовых методов.
 */

public class LoginPage {

    private WebDriver driver; // Переменная для хранения браузера.
    private WebDriverWait wait; // Переменная явного ожидания.

    // ===== ЛОКАТОРЫ =====

    private By username = By.id("user-name");
    private By password = By.cssSelector("[data-test='password']");
    private By loginButton = By.xpath("//input[@id='login-button']");
    private By errorMessage = By.cssSelector("[data-test='error']");

    /*
     * Конструктор класса.
     *
     * Когда создается объект LoginPage, в него передается браузер и ожидание.
     */

    public LoginPage(WebDriver driver, WebDriverWait wait){

        this.driver = driver; // Сохраняем браузер.
        this.wait = wait; // Сохраняем ожидание.
    }

    /*
     * Метод авторизации.
     *
     * Принимает логин и пароль, вводит их в соответствующие поля
     * и нажимает кнопку Login.
     */

    public void login(String user, String pass){

        Allure.step("Авторизация пользователя"); // Добавляем шаг в отчет Allure.
        wait.until(ExpectedConditions.visibilityOfElementLocated(username)); // Ждем пока поле Username загрузится
        driver.findElement(username).sendKeys(user); // Вводим логин.
        driver.findElement(password).sendKeys(pass); // Вводим пароль.
        driver.findElement(loginButton).click(); // Нажимаем Login.
    }

    /*
     * Получить текст ошибки.
     *
     * Используется в негативных тестах.
     */

    public String getErrorText(){
        Allure.step("Получение текста ошибки"); // Добавляем шаг в отчет Allure.
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)); // Ждем появления сообщения об ошибке.
        return driver.findElement(errorMessage).getText(); // Возвращаем текст ошибки.
    }

}