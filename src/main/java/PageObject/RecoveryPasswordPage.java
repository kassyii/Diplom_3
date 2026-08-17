package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RecoveryPasswordPage extends BasePage {

    private final By loginLink = By.xpath(".//a[text()='Войти']");

    public RecoveryPasswordPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Кликнуть кнопку 'Войти' на странице восстановления пароля")
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }
}
