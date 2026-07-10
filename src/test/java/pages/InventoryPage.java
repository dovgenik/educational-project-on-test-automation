package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Импорт класса Select. Используется для работы с выпадающим списком сортировки.
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

// Импорт ожиданий.
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * Page Object страницы Products (Inventory).
 *
 * Здесь находятся все элементы страницы
 * и методы для работы с ними.
 */

public class InventoryPage {

    private WebDriver driver; // Сохраняем браузер.
    private WebDriverWait wait; // Сохраняем объект ожидания.

    // ЛОКАТОРЫ


    private By backpackButton = By.cssSelector("[id*='sauce-labs-backpack']");
    private By bikeLightButton = By.cssSelector("[id*='sauce-labs-bike-light']");
    private By backpack = By.id("add-to-cart-sauce-labs-backpack"); // Кнопка добавления рюкзака.
    private By backpackDel = By.id("remove-sauce-labs-backpack"); // Кнопка удаления рюкзака.
    private By bikeLight = By.id("add-to-cart-sauce-labs-bike-light");
    private By bikeLightDel = By.id("remove-sauce-labs-bike-light");
    private By cartButton = By.className("shopping_cart_link"); // Значок корзины.
    //private By cartBadge = By.className("shopping-cart-badge"); // Счетчик товаров в корзине.
    private By cartBadge = By.cssSelector("[data-test='shopping-cart-badge']");
    private By menuButton = By.id("react-burger-menu-btn"); // Кнопка открытия бокового меню.
    private By logout = By.id("logout_sidebar_link"); // Кнопка Logout.
    private By title = By.className("title"); // Заголовок страницы Products.
    private By sortDropdown = By.className("product_sort_container"); // Выпадающий список сортировки.

    public InventoryPage(WebDriver driver, WebDriverWait wait){ // Конструктор класса.

        this.driver = driver; // Сохраняем браузер.
        this.wait = wait; // Сохраняем ожидание.
    }

    /*
     * Якщо потрібно перевірити стан товару (в корзині чи ні) по кнопкі.
     * Перевірка, чи містить ID кнопки слово "remove".
     */
    private boolean isProductInCart(By productLocator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(productLocator));
        WebElement button = driver.findElement(productLocator);
        String currentId = button.getAttribute("id");
        return currentId.startsWith("remove");
    }

    /*
     * Інверсія кнопки рюкзака, якщо він в корзині.
     */
    public void invBackpackToCart(){
        Allure.step("Проверка состояния кнопки рюкзака и если 'Remove' то в 'Add to Cart'");

        // Перевіряємо, якщо товар в кошику, клікаєм на Remove.
        if (isProductInCart(backpackButton)) {
            driver.findElement(backpackButton).click();
        }
    }


    /*
     * Добавление рюкзака в корзину.
     */

    public void addBackpackToCart(){

        Allure.step("Добавление Sauce Labs Backpack в корзину");

        wait.until(ExpectedConditions.elementToBeClickable(backpack)); // Ждем появления кнопки.
        driver.findElement(backpack).click(); // Нажимаем кнопку Add to cart.
    }


    /*
     * Активна ли кнопка удаление рюкзака из корзины.
     */
    public boolean backpackDelIsDisplayed(){

        Allure.step("Проверка активности кнопки удаления из корзины");

        return isProductInCart(backpackButton);
    }

    /*
     * Удаление рюкзака из корзины.
     */

    public void delBackpackFromCart(){

        Allure.step("Удаление Sauce Labs Backpack из корзины");

        wait.until(ExpectedConditions.elementToBeClickable(backpackDel)); // Ждем появления кнопки.
        driver.findElement(backpackDel).click(); // Нажимаем кнопку Remove.
    }

    /*
     * Інверсія кнопки фонаря, якщо він в корзині.
     */
    public void invBikeLightToCart(){
        Allure.step("Проверка состояния кнопки фонаря и если 'Remove' то в 'Add to Cart'");

        // Перевіряємо, якщо товар в кошику, клікаєм на Remove.
        if (isProductInCart(bikeLightButton)) {
            driver.findElement(bikeLightButton).click();
        }
    }


    /*
     * Добавление Bike Light в корзину.
     */

    public void addBikeLightToCart(){

        Allure.step("Добавление фонаря в корзину"); // Добавляем шаг в отчет Allure.
        wait.until(ExpectedConditions.elementToBeClickable(bikeLight)); // Ждем появления кнопки.
        driver.findElement(bikeLight).click(); // Нажимаем кнопку
    }

    /*
     * Активна ли кнопка удаления фонаря из корзины.
     */
    public boolean bikeLightDelIsDisplayed(){

        Allure.step("Проверка активности кнопки удаления из корзины");

        return isProductInCart(bikeLightButton);
    }

    /*
     * Удаление рюкзака из корзины.
     */

    public void delBikeLightFromCart(){

        Allure.step("Удаление Bike Light из корзины");

        wait.until(ExpectedConditions.elementToBeClickable(bikeLightDel)); // Ждем появления кнопки.
        driver.findElement(bikeLightDel).click(); // Нажимаем кнопку Add to cart.
    }

    /*
     * Открытие корзины.
     */

    public void openCart(){

        Allure.step("Открытие корзины");

        wait.until(ExpectedConditions.elementToBeClickable(cartButton));
        driver.findElement(cartButton).click();
    }

    /*
     * Получение количества товаров в корзине.
     */

    public String getCartBadge(){

        Allure.step("Получение количества товаров в корзине по значку корзины");
        java.util.List<org.openqa.selenium.WebElement> badge = driver.findElements(cartBadge);

        if (badge.isEmpty()) {
            return "0"; // Якщо значка немає, значить у кошику 0 товарів
        } else {
            return badge.get(0).getText(); // Якщо є — беремо його текст
        }

         }

    /*
     * Выход из аккаунта.
     */

    public void logout(){

        Allure.step("Выход из аккаунта");

        driver.findElement(menuButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(logout)); // Ждем появления кнопки.
        driver.findElement(logout).click();
    }

    /*
     * Получение названия страницы.
     */

    public String getTitle(){
        return driver.findElement(title).getText();
    }

    /*
     * Проверка Url, что пользователь находится на странице Products (inventory).
     */

    public boolean isInventoryPageDisplayed(){
        return driver.getCurrentUrl().contains("inventory");
    }

}