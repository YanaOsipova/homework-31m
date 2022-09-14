package data;

import exception.BrowserNotSupportedException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import static data.Driver.getBrowserName;
import static io.github.bonigarcia.wdm.WebDriverManager.*;

public class WebDriverFactory {
    private static final Driver BROWSER_NAME = getBrowserName(System.getProperty("browserName"));
    private static WebDriver webDriver;
    public static WebDriver create() {
        switch (BROWSER_NAME) {
            case CHROME -> {
                chromedriver().setup();
                webDriver = new ChromeDriver();
                return webDriver;
            }
            case FIREFOX -> {
                firefoxdriver().setup();
                webDriver = new FirefoxDriver();
                return webDriver;
            }
            case EDGE -> {
                edgedriver().setup();
                webDriver = new EdgeDriver();
                return webDriver;
            }
            default -> throw new BrowserNotSupportedException("Драйвер для " + BROWSER_NAME.getBrowser() + " не найден!");
        }
    }

    public static WebDriver create(MutableCapabilities options) {
        switch (BROWSER_NAME) {
            case CHROME -> {
                if (options instanceof ChromeOptions) {
                    chromedriver().setup();
                    webDriver = new ChromeDriver((ChromeOptions) options);
                    return webDriver;
                }
                throw new BrowserNotSupportedException("Ошибка при создании ChromeDriver: " + options.getClass().getSimpleName());
            }
            case FIREFOX -> {
                if (options instanceof FirefoxOptions) {
                    firefoxdriver().setup();
                    webDriver = new FirefoxDriver((FirefoxOptions) options);
                    return webDriver;
                }
                throw new BrowserNotSupportedException("Ошибка при создании FirefoxDriver: " + options.getClass().getSimpleName());
            }
            case EDGE -> {
                if (options instanceof EdgeOptions) {
                    edgedriver().setup();
                    webDriver = new EdgeDriver((EdgeOptions) options);
                    return webDriver;
                }
                throw new BrowserNotSupportedException("Ошибка при создании InternetExplorerDriver: " + options.getClass().getSimpleName());
            }
            default -> throw new BrowserNotSupportedException("Драйвер для "  + BROWSER_NAME.getBrowser() + " не найден!");
        }
    }
}
