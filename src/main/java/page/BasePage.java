package page;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    public void open(WebDriver webDriver) {
        webDriver.get(System.getProperty("page"));
    }
}
