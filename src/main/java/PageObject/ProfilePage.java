package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BasePage {

    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");

    private final By logo = By.xpath(".//div[contains(@class, 'AppHeader_header__logo')]/a");

    private final By logoutButton = By.xpath(".//button[text()='Выход']");

    private final By profileLink = By.xpath(".//a[contains(@href, '/account/profile')]");

    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Кликнуть 'Конструктор' в шапке")
    public void clickConstructorButton() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorButton)).click();
    }

    @Step("Кликнуть на логотип Stellar Burgers")
    public void clickLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(logo)).click();
    }

    @Step("Кликнуть кнопку 'Выйти'")
    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    @Step("Проверить отображение страницы профиля")
    public boolean isProfilePageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(profileLink)).isDisplayed();
    }
}