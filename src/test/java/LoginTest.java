import PageObject.RecoveryPasswordPage;
import PageObject.LoginPage;
import PageObject.MainPage;
import PageObject.RegisterPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;


import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest {

    private WebDriver driver;
    private static final String BASE_URL = "https://qa-stellarburgers.education-services.ru/";

    private String name;
    private String email;
    private String password;
    private String accessToken;

    @BeforeEach
    public void setUp() {
        driver = WebDriverFactory.getDriver();
        driver.get(BASE_URL);

        Random random = new Random();
        this.name = "user_" + random.nextInt(10000);
        this.email = "user_" + System.currentTimeMillis() + "@yandex.ru";
        this.password = "Strong_password";

        this.accessToken = UserApiClient.createUser(this.name, this.email, this.password);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

        if (this.accessToken != null) {
            UserApiClient.deleteUser(this.accessToken);
        } else {
            String token = UserApiClient.loginUser(this.email, this.password);
            UserApiClient.deleteUser(token);
        }
    }

    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт' на главной")
    public void testLoginFromMainPageLoginButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(this.email, this.password);

        assertThat(mainPage.isCreateOrderButtonDisplayed()).isTrue();
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    public void testLoginFromPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(this.email, this.password);

        assertThat(mainPage.isCreateOrderButtonDisplayed()).isTrue();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void testLoginFromRegistrationForm() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();

        loginPage.login(this.email, this.password);

        assertThat(mainPage.isCreateOrderButtonDisplayed()).isTrue();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void testLoginFromForgotPasswordForm() {
        driver.get(BASE_URL + "forgot-password");

        RecoveryPasswordPage recoveryPasswordPage = new RecoveryPasswordPage(driver);
        recoveryPasswordPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(this.email, this.password);

        MainPage mainPage = new MainPage(driver);
        assertThat(mainPage.isCreateOrderButtonDisplayed()).isTrue();
    }
}
