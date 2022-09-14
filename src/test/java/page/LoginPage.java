package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    protected static final Logger userLogger = LogManager.getLogger(LoginPage.class);

    public void login(WebDriver webDriver, WebDriverWait webDriverWait) {
        userLogger.info("Login in lk");
        var login = System.getProperty("login");
        var password = System.getProperty("password");
        open(webDriver);
        webDriver.findElement(By.cssSelector("button.header2__auth")).click();

        var form = webDriverWait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@action = '/login/']")));
        var element = form.findElement(By.xpath(".//input[@name = 'email']"));
        element.clear();
        element.sendKeys(login);
        element = form.findElement(By.xpath(".//input[@name = 'password']"));
        element.clear();
        element.sendKeys(password);
        form.findElement(By.xpath(".//button[@type = 'submit']")).submit();
    }
}
