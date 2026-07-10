package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.util.Objects;

// ТЕСТЫ ОФОРМЛЕНИЯ ЗАКАЗА

public class CheckoutTests extends BaseTest {

 //   @Test(description = "Успешное оформление заказа")

    public void checkoutTest() {

        // Логин
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");

        // Создаем страницу товаров
        InventoryPage inventory = new InventoryPage(driver, wait);

        // Добавляем товар
        inventory.addBackpackToCart();

        // Переходим в корзину
        inventory.openCart();

        // Создаем страницу корзины
        CartPage cart = new CartPage(driver, wait);

        // Начинаем checkout процесс
        cart.checkout();

        // Создаем страницу checkout
        CheckoutPage checkout = new CheckoutPage(driver, wait);

        // Заполняем форму пользователя
        checkout.fillInformation("John", "Doe", "10001");

        // Завершаем заказ
        checkout.finishOrder();

        // Создаем страницу Checkout Complete Page
        CheckoutCompletePage complete = new CheckoutCompletePage(driver, wait);

        // Проверяем успешное сообщение
        Assert.assertEquals(complete.getSuccessMessage(), "Thank you for your order!");

    }

 //   @Test(description = "Continue shopping после корзины")

    public void continueFromCartTest() {

        // Логин
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");

        // Создаем страницу товаров
        InventoryPage inventory = new InventoryPage(driver, wait);

        // Добавляем товар
        inventory.addBackpackToCart();

        // Переходим в корзину
        inventory.openCart();
        CartPage cart = new CartPage(driver, wait);

        // Возврат назад в магазин
        cart.continueShopping();

        // Проверяем что снова на странице товаров
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

// 3) Проверка, что товар реально отображается в корзине
@Test(description = "Товар реально отображается в корзине")

public void checkProductDisplayTest() {

    // Логин
    new LoginPage(driver, wait).login("standard_user", "secret_sauce");

    // Создаем страницу товаров
    InventoryPage inventory = new InventoryPage(driver, wait);

    // Добавляем товар
    inventory.addBackpackToCart();

    // Переходим в корзину
    inventory.openCart();

    // Создаем страницу корзины
    CartPage cart = new CartPage(driver, wait);


    // Проверяем наличие товара
    Assert.assertTrue(cart.isBackpackDisplayed());

}

    // 4) Проверка, что два товара реально отображается в корзине
    @Test(description = "Два товара реально отображается в корзине")

    public void checkTwoProductDisplayTest() {

        // Логин
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");

        // Создаем страницу товаров
        InventoryPage inventory = new InventoryPage(driver, wait);

        // Добавляем товар
        inventory.addBackpackToCart();
        inventory.addBikeLightToCart();

        // Переходим в корзину
        inventory.openCart();

        // Создаем страницу корзины
        CartPage cart = new CartPage(driver, wait);


        // Проверяем наличие товара
        Assert.assertTrue(cart.isBackpackDisplayed() & cart.isBikeLightDisplayed());

    }
// 5)Удаление товара из корзины

    @Test(description = "Проверка удаления товара из корзины")

    public void delProductFromCartTest() {

        // Логин
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");

        // Создаем страницу товаров
        InventoryPage inventory = new InventoryPage(driver, wait);

        // Добавляем товар
        inventory.addBackpackToCart();

        // Переходим в корзину
        inventory.openCart();

        // Создаем страницу корзины
        CartPage cart = new CartPage(driver, wait);

        // Удаляем товар
        cart.removeBackpack();

        // Проверяем результат удаления товара
        Assert.assertFalse(cart.isBackpackPresent());

    }

// 6) Удаление одного из двух товаров из корзины

    @Test(description = "Проверка удаления двух товаров из корзины")

    public void delTwoProductFromCartTest() {

        // Логин
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");

        // Создаем страницу товаров
        InventoryPage inventory = new InventoryPage(driver, wait);

        // Добавляем товары
        inventory.addBackpackToCart();
        inventory.addBikeLightToCart();

        // Переходим в корзину
        inventory.openCart();

        // Создаем страницу корзины
        CartPage cart = new CartPage(driver, wait);

        // Удаляем товар
        cart.removeBackpack();

        // Проверяем результат удаления товара
        Assert.assertFalse(cart.isBackpackPresent());
        Assert.assertTrue(cart.isBikeLightDisplayed());

    }

    // 7) Проверка сохранения содержимого корзины после обновления страницы
    @Test(description = "Товар сохраняется в корзине после обновления страницы")

    public void checkSafetyProductAfterReloadingTest() {

        // Логин
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");

        // Создаем страницу товаров
        InventoryPage inventory = new InventoryPage(driver, wait);

        // Добавляем товар
        inventory.addBackpackToCart();

        // Переходим в корзину
        inventory.openCart();

        // Создаем страницу корзины
        CartPage cart = new CartPage(driver, wait);
        // перезагрузка страницы
        driver.navigate().refresh();

        // Проверяем наличие товара
        Assert.assertTrue(cart.isBackpackDisplayed());
    }

    // 8)Проверка сохранения содержимого корзины после переходов между страницами
    @Test(description = "Товар сохраняется в корзине после перехода между страницами")

    public void checkSafetyProductAfterPageTransitTest() {

        // Логин
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");

        // Создаем страницу товаров
        InventoryPage inventory = new InventoryPage(driver, wait);

        // Добавляем товар
        inventory.addBackpackToCart();

        // Переходим в корзину
        inventory.openCart();

        // Создаем страницу корзины
        CartPage cart = new CartPage(driver, wait);

        // переход между страницами
        driver.navigate().back();
        driver.navigate().forward();

        // Проверяем наличие товара
        Assert.assertTrue(cart.isBackpackDisplayed());
    }

// 9) Попытка перейти к Checkout с пустой корзиной
@Test(description = "Не возмижно перейти к Checkout с пустой корзиной")

public void checkToCheckoutWithEmptyCartTest() {

    // Логин
    new LoginPage(driver, wait).login("standard_user", "secret_sauce");

    // Создаем страницу товаров
    InventoryPage inventory = new InventoryPage(driver, wait);

    // Переходим в корзину
    inventory.openCart();

    // Создаем страницу корзины
    CartPage cart = new CartPage(driver, wait);

    // Попытка оформить покупку
    cart.checkout();

    // Проверяем переход к началу покупки
    Assert.assertFalse(driver.getCurrentUrl().contains("checkout-step-one"));
}


//  1) Добавление одного товара в корзину

    @Test(description = "Успешное добавление одного товара в корзину")

    public void addProductInCheckoutTest() {

        // Логин
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");

        // Создаем страницу товаров
        InventoryPage inventory = new InventoryPage(driver, wait);

        // Проверяем что товар не в корзине, если в корзине - возврат на "прилавок"
        inventory.invBackpackToCart();

        // запоминаю количество товара в корзине
        int forShoppingCartBadge = Integer.parseInt(inventory.getCartBadge());

        // Добавляем товар
        inventory.addBackpackToCart();

        // Новое количество
        int newShoppingCartBadge = Integer.parseInt(inventory.getCartBadge());

        // Проверяем успешное добавление по счетчику товара в корзине.
        Assert.assertEquals(newShoppingCartBadge - 1, forShoppingCartBadge );

        // Проверяем успешное добавление по кнопке.
        Assert.assertTrue( inventory.backpackDelIsDisplayed());
    }

//  2) Добавление двух товаров в корзину ******************************

    @Test(description = "Успешное добавление двух товаров в корзину")

    public void addTwoProductInCheckoutTest() {

        // Логин
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");

        // Создаем страницу товаров
        InventoryPage inventory = new InventoryPage(driver, wait);

        // Проверяем что рюкзак не в корзине, если в корзине - возврат
        inventory.invBackpackToCart();

        // Проверяем что фонарь не в корзине, если в корзине - возврат
        inventory.invBikeLightToCart();

        // запоминаю количество товара в корзине если > 0, (0 - уже есть)
        int forShoppingCartBadge = Integer.parseInt(inventory.getCartBadge());

        // Добавляем товар
        inventory.addBackpackToCart();
        inventory.addBikeLightToCart();

        // Новое количество
        int newShoppingCartBadge = Integer.parseInt(inventory.getCartBadge());

        // Проверяем успешное добавление по счетчику товара в корзине.
        Assert.assertEquals(newShoppingCartBadge - 2, forShoppingCartBadge );

        // Проверяем успешное добавление по кнопке.
        Assert.assertTrue( inventory.backpackDelIsDisplayed() & inventory.bikeLightDelIsDisplayed());
    }

//  11)  Попытка открыть Cart после Logout

    @Test(description = "Проверка что невозможно открыть корзину после Logout")

    public void cartUnavailableAfterLogoutTest() {

        // Логин
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");
        LoginPage loginPage = new LoginPage(driver, wait);

        // Создаем страницу товаров
        InventoryPage inventory = new InventoryPage(driver, wait);

        // Переходим в корзину
        inventory.openCart();
        CartPage cart = new CartPage(driver, wait);

        // Logout
        inventory.logout();

        // Попытка снова вернуться в корзину
        driver.get("https://www.saucedemo.com/cart.html");

        // Проверяем, наличие сообщение, что без авторизации не доступна Inventory
        Assert.assertTrue(loginPage.getErrorText().contains("logged in"));

        // Проверяем что снова на странице товаров
        Assert.assertFalse(driver.getCurrentUrl().contains("cart"));

    }

}