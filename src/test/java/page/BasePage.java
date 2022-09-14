package page;

import org.openqa.selenium.WebDriver;

public class BasePage {
    public void open(WebDriver webDriver) {
        webDriver.get(System.getProperty("page"));
    }
}
