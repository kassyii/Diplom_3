package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private final By emailInput = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private final By loginButton = By.xpath(".//button[text()='Войти']");

    private final By registerLink = By.xpath(".//a[text()='Зарегистрироваться']");

    private final By loginHeader = By.xpath(".//h2[text()='Вход']");

    private final By createOrderButton = By.xpath(".//button[text()='Оформить заказ']");

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Нажать на ссылку 'Зарегистрироваться'")
    public void clickRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }

    @Step("Ввести Email")
    public void setEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
    }

    @Step("Ввести Пароль")
    public void setPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
    }

    @Step("Кликнуть кнопку 'Войти'")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Заполнить форму входа и нажать 'Войти'")
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loginButton));
        wait.until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));
    }

    @Step("Проверить отображение формы 'Вход'")
    public boolean isLoginHeaderDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeader)).isDisplayed();
    }
}
