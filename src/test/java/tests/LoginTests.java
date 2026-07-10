package tests;

import base.BaseTest;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

/*
 * TEST SUITE: LOGIN
 * Проверка авторизации пользователя:
 * - успешный логин
 * - негативные сценарии
 */

public class LoginTests extends BaseTest {

  //  @Test(description = "Успешный вход в систему")

    public void validLoginTest(){

        Allure.step("Тест на успешную Авторизацию пользователя");

        // Создаем объект страницы Login
        // driver и wait берем из BaseTest
        LoginPage loginPage = new LoginPage(driver, wait);

        // Вызываем метод login: внутри него происходит ввод логина, пароля и нажатие кнопки
        loginPage.login("standard_user", "secret_sauce");

        // Проверяем результат: если логин успешный - URL должен содержать "inventory"
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }



   // @Test(description = "Неверный логин")

    public void invalidLoginTest(){

        // Создаем страницу логина
        LoginPage loginPage = new LoginPage(driver, wait);

        // Вводим неправильный логин
        loginPage.login("wrong_user", "secret_sauce");

        // Проверяем текст ошибки, contains() значит "содержит ли строка текст"
        Assert.assertTrue(loginPage.getErrorText().contains("Username"));
    }


   // @Test (description = "Login с пустыми полями")

    public void emptyLoginTest(){

        // Создаем страницу логина
        LoginPage loginPage = new LoginPage(driver, wait);

        // Передаем пустые строки
        loginPage.login("", "");

        // Проверяем текст ошибки, contains() значит "содержит ли строка текст"
        Assert.assertTrue(loginPage.getErrorText().contains("Username"));
    }

  //  @Test (description = "Login без пароля")

    public void emptyPasswordTest(){

        // Создаем страницу логина
        LoginPage loginPage = new LoginPage(driver, wait);

        // Логин есть, пароль пустой
        loginPage.login("standard_user", "");

        // Проверяем, что система ругается на пароль
        Assert.assertTrue(loginPage.getErrorText().contains("Password"));

    }

}


