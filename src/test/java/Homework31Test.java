import data.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.LKPage;

import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Homework31Test extends LKPage {
    static final Logger userLogger = LogManager.getLogger(Homework31Test.class);
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    @Test
    @Order(1)
    public void testFieldFilling() {
        userLogger.info("Начало теста 1");
        login(webDriver, webDriverWait);
        learningPageWithBiography(webDriver, webDriverWait);
        setPersonalData(webDriver, webDriverWait);
        userLogger.info("Конец теста 1");
    }

    @Test
    @Order(2)
    public void testCheckingFields() {
        userLogger.info("Начало теста 2");
        login(webDriver, webDriverWait);
        learningPageWithBiography(webDriver, webDriverWait);
        checkPersonalData(webDriver);
        userLogger.info("Конец теста 2");
    }

    @BeforeEach
    public void configDriver() {
        userLogger.info("Создание драйвера");
        webDriver = WebDriverFactory.create();
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(3L));
    }

    @AfterEach
    public void close() {
        userLogger.info("Закрытие драйвера");
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
