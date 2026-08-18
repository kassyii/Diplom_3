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

public class RegisterTest {

    private WebDriver driver;
    private static final String BASE_URL = "https://qa-stellarburgers.education-services.ru/";

    private String name;
    private String email;
    private String password;

    @BeforeEach
    public void setUp() {
        driver = WebDriverFactory.getDriver();
        driver.get(BASE_URL);

        Random random = new Random();
        this.name = "user_" + random.nextInt(10000);
        this.email = "user_" + random.nextInt(1000) + "@yandex.ru";
        this.password = "Strong_password";
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

        String accessToken = UserApiClient.loginUser(this.email, this.password);
        if (accessToken != null) {
            UserApiClient.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void testSuccessfulRegistration(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(this.name, this.email, this.password);

        assertThat(loginPage.isLoginHeaderDisplayed()).isTrue();
    }

    @Test
    @DisplayName("Ошибка регистрации при пароле меньше 6 символов")
    public void testUnSuccessfulRegistrationWithShortPassword(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);

        String shortPassword = "pas";
        registerPage.register(this.name, this.email, shortPassword);

        assertThat(registerPage.isPasswordErrorDisplayed()).isTrue();
    }
}
