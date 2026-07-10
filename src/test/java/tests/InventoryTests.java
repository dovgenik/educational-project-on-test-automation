package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

import java.util.Objects;

//ТЕСТЫ СТРАНИЦЫ PRODUCTS (INVENTORY)

public class InventoryTests extends BaseTest {

   // @Test(description = "Проверка заголовка страницы Products")

    public void inventoryTitleTest() {

        // Сначала нужно авторизоваться, потому что Inventory доступна только после логина
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");

        // Создаем страницу товаров
        InventoryPage inventory = new InventoryPage(driver, wait);

        // Проверяем текст заголовка страницы
        Assert.assertEquals(inventory.getTitle(), "Products");
    }

 //   @Test(description = "Проверка выхода из системы")

    public void logoutTest() {

        // Логинимся
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");

        // Создаем страницу товаров
        InventoryPage inventory = new InventoryPage(driver, wait);

        // Нажимаем logout (через меню)
        inventory.logout();

        // Проверяем, что нас вернуло на стартовый сайт
        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo"));
    }

  //  @Test(description = "Проверка, что после Logout нельзя попасть на Inventory")

    public void inventoryUnavailableAfterLogoutTest() {

        // Логинимся
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");

        // Создаем страницу товаров
        InventoryPage inventory = new InventoryPage(driver, wait);

        // Нажимаем logout (через меню)
        inventory.logout();

        // Пытаемся попасть на страницу с товарами
        driver.get("https://www.saucedemo.com/inventory.html");

        // Проверяем, что после Logout нельзя попасть на Inventory
        Assert.assertFalse(Objects.requireNonNull(driver.getCurrentUrl()).contains("inventory"));
    }

 //   @Test(description = "Проверка счетчика корзины после открытия корзины")

    public void cartBadgeAfterOpeningCartTest() {

        // Логинимся
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");

        // Создаем страницу товаров
        InventoryPage inventory = new InventoryPage(driver, wait);

        // Добавляем товар в корзину
        inventory.addBackpackToCart();

        // Открываем корзину
        inventory.openCart();

        // Проверяем счетчик корзины после открытия корзины
        Assert.assertEquals(inventory.getCartBadge(), "1");

    }

// 10)Прямой переход на Cart без авторизации
@Test(description = "Проверка, что без Logout нельзя попасть в корзину")

public void withoutLoginCartTest() {

    // Пытаемся попасть на страницу корзины
    driver.get("https://www.saucedemo.com/cart.html");

    // Проверяем, что нельзя попасть на страницу корзины
    Assert.assertFalse(Objects.requireNonNull(driver.getCurrentUrl()).contains("cart"));
}


    // 12  Проверка работы кнопки Back в браузере после выхода из системы

    @Test(description = "Проверка, что после Logout, используя работы кнопку Back, нельзя попасть на Inventory")

    public void inventoryUnavailableAfterLogoutAndBackTest() {

        // Логинимся
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");
        LoginPage loginPage = new LoginPage(driver, wait);

        // Создаем страницу товаров
        InventoryPage inventory = new InventoryPage(driver, wait);

        // Нажимаем logout (через меню)
        inventory.logout();

        // Пытаемся вернуться на страницу с товарами
        driver.navigate().back();

        // Проверяем, наличие сообщение, что без авторизации не доступна Inventory
        Assert.assertTrue(loginPage.getErrorText().contains("logged in"));

        // Проверяем, что после Logout нельзя попасть на Inventory
        Assert.assertFalse(Objects.requireNonNull(driver.getCurrentUrl()).contains("inventory"));
    }

}