package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {

    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");

    private final By bunsTab = By.xpath(".//span[text()='Булки']/parent::div");
    private final By saucesTab = By.xpath(".//span[text()='Соусы']/parent::div");
    private final By fillingsTab = By.xpath(".//span[text()='Начинки']/parent::div");

    private final By activeTab = By.xpath("//div[contains(@class, 'tab_tab_type_current')]/span");

    private final By createOrderButton = By.xpath(".//button[text()='Оформить заказ']");

    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Кликнуть по кнопке 'Войти в аккаунт'")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Кликнуть по кнопке 'Личный Кабинет'")
    public void clickPersonalAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton)).click();
    }

    @Step("Перейти на вкладку 'Булки'")
    public void clickBunsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsTab)).click();
    }

    @Step("Перейти на вкладку 'Соусы'")
    public void clickSaucesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesTab)).click();
    }

    @Step("Перейти на вкладку 'Начинки'")
    public void clickFillingsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsTab)).click();
    }

    @Step("Получить название текущей активной вкладки")
    public String getActiveTabText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(activeTab)).getText();
    }

    @Step("Проверить отображение кнопки 'Оформить заказ'")
    public boolean isCreateOrderButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(createOrderButton)).isDisplayed();
    }
}