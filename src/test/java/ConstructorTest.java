import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import PageObject.MainPage;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class ConstructorTest {

    private WebDriver driver;
    private static final String BASE_URL = "https://qa-stellarburgers.education-services.ru/";

    private WebDriverWait wait;
    private MainPage mainPage;

    @BeforeEach
    public void setUp(){
        driver = WebDriverFactory.getDriver();

        driver.get(BASE_URL);
        mainPage = new MainPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Переход к разделу Соусы")
    public void testTransitionToSaucesTab(){
        mainPage.clickSaucesTab();
        assertThat(mainPage.getActiveTabText()).isEqualTo("Соусы");
    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    public void testTransitionToFillingsTab(){
        mainPage.clickFillingsTab();
        assertThat(mainPage.getActiveTabText()).isEqualTo("Начинки");
    }

    @Test
    @DisplayName("Переход к разделу Булки")
    public void testTransitionToBunsTab(){
        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();
        assertThat(mainPage.getActiveTabText()).isEqualTo("Булки");
    }
}
