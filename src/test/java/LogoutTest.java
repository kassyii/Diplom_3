import PageObject.LoginPage;
import PageObject.MainPage;
import PageObject.ProfilePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;


import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class LogoutTest {

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
        this.email = "user_" + random.nextInt(10000) + "@yandex.ru";
        this.password = "Strong_password";

        this.accessToken = UserApiClient.createUser(this.name, this.email, this.password);

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(this.email, this.password);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

        if (this.accessToken != null) {
            UserApiClient.deleteUser(this.accessToken);
        }
    }

    @Test
    @DisplayName("Выход из аккаунта по кнопке 'Выйти' в личном кабинете")
    public void testLogoutFromProfilePage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickLogoutButton();

        LoginPage loginPage = new LoginPage(driver);
        assertThat(loginPage.isLoginHeaderDisplayed()).isTrue();
    }
}
