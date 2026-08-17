import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

    public static WebDriver getDriver() {
        String browser = System.getProperty("browser", "chrome");
        return getDriver(browser);
    }

    public static WebDriver getDriver(String browserName) {
        WebDriver driver;

        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().clearDriverCache().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "yandex":
                WebDriverManager.chromedriver().clearDriverCache().setup();
                ChromeOptions yandexOptions = new ChromeOptions();
                // путь к browser.exe Яндекс.Браузера
                yandexOptions.setBinary("C:/Users/Иван/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
                yandexOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage");
                driver = new ChromeDriver(yandexOptions);
                break;

            default:
                throw new IllegalArgumentException("Неподдерживаемый браузер: " + browserName);
        }

        return driver;
    }
}
