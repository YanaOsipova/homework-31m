package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static data.Communication.getCommunication;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static data.Value.*;

public class LKPage extends LoginPage{
    private static final String COUNTRY = System.getProperty("country");
    private static final String CITY = System.getProperty("city");
    public static final String TYPE_COMM_1 = System.getProperty("typeCommunication1");
    public static final String COMMUNICATION_1 = getCommunication(TYPE_COMM_1);
    public static final String TYPE_COMM_2 = System.getProperty("typeCommunication2");
    public static final String COMMUNICATION_2 = getCommunication(TYPE_COMM_2);
    public void learningPageWithBiography(WebDriver webDriver, WebDriverWait webDriverWait) {
        userLogger.info("Вход на страничку о себе");
        var action = new Actions(webDriver);
        var form = webDriverWait
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.cssSelector("div.header2-menu__item.header2__right__menu__item.header2-menu__item_small.header2-menu__item_dropdown.header2-menu__item_dropdown_no-border")));
        action.moveToElement(form).perform();
        webDriverWait
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.cssSelector("a[title='Личный кабинет']")))
                .click();
        webDriverWait
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.cssSelector("a[title='О себе']"))).click();
    }

    public void setPersonalData(WebDriver webDriver, WebDriverWait webDriverWait) {
        userLogger.info("Заполнение данных о себе");
        var element = webDriver.findElement(By.id("id_fname"));
        element.clear();
        element.sendKeys(NAME.getValue());

        element = webDriver.findElement(By.id("id_fname_latin"));
        element.clear();
        element.sendKeys(NAME_LATIN.getValue());

        element = webDriver.findElement(By.id("id_lname"));
        element.clear();
        element.sendKeys(LNAME.getValue());

        element = webDriver.findElement(By.id("id_lname_latin"));
        element.clear();
        element.sendKeys(LNAME_LATIN.getValue());

        element = webDriver.findElement(By.id("id_blog_name"));
        element.clear();
        element.sendKeys(BLOG_NAME.getValue());

        element = webDriver.findElement( By.xpath("//input[@name='date_of_birth']"));
        element.clear();
        element.sendKeys(BIRTHDAY.getValue());

        webDriver.findElement(By.xpath("//div[@data-slave-selector='.js-lk-cv-dependent-slave-city']"))
                .click();
        webDriverWait
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath(format("//button[@title='%s']", COUNTRY))))
                .click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='placeholder']")))
                .click();
        webDriverWait.until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath(format("//button[@title='%s']", CITY))))
                .click();
        webDriver.findElements(By.xpath("//div[@data-selected-option-class='lk-cv-block__select-option_selected']")).get(2).click();
        webDriver.findElement(By.xpath("//button[@title='Начальный уровень (Beginner)']")).click();

        webDriver.findElements(By.xpath("//span[@class='radio__label']"))
                .get(1)
                .click();

        if (!webDriver.findElement(By.xpath("//input[@title='Полный день' and @checked]")).getAttribute("checked").equalsIgnoreCase("true")) {
            webDriver.findElement(By.xpath("//span[contains(text(), 'Полный день')]"))
                    .click();
        }
        if (!webDriver.findElement(By.xpath("//*[@id='id_is_email_preferable']"))
                .getAttribute("checked").equalsIgnoreCase("true")) {
            webDriver.findElements(By.xpath("//span[contains(text(), 'Предпочтительный способ связи')]"))
                    .get(0)
                    .click();
        }
        var elements = webDriver.findElements(By.xpath("//button[contains(text(), 'Удалить')]"));
        for (int i = 0; i < elements.size(); i += 2) {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(elements.get(i)))
                    .click();
        }
        webDriver.findElement(By.xpath("//button[contains(text(), 'Добавить')]"))
                .click();
        webDriver.findElement(By.xpath("//span[contains(text(), 'Способ связи')]"))
                .click();
        webDriver.findElements(By.xpath(format("//button[contains(text(), '%s')]", TYPE_COMM_1))).get(2).click();
        element = webDriver.findElement(By.xpath("//*[@id='id_contact-2-value']"));
        element.sendKeys(COMMUNICATION_1);

        webDriver.findElement(By.xpath("//button[contains(text(), 'Добавить')]"))
                .click();
        webDriver.findElement(By.xpath("//span[contains(text(), 'Способ связи')]"))
                .click();
        webDriver.findElements(By.xpath(format("//button[contains(text(), '%s')]", TYPE_COMM_2))).get(3).click();
        element = webDriver.findElement(By.xpath("//*[@id='id_contact-3-value']"));
        element.sendKeys(COMMUNICATION_2);

        webDriver.findElement(By.xpath("//div[@class='select select_full']"))
                .click();
        webDriver.findElement(By.xpath("//*[@id='id_gender']/option[@value='f']"))
                .click();

        element = webDriver.findElement(By.xpath("//*[@id='id_company']"));
        element.clear();
        element.sendKeys(COMPANY.getValue());

        element = webDriver.findElement(By.xpath("//*[@id='id_work']"));
        element.clear();
        element.sendKeys(JOB_TITLE.getValue());

        webDriver.findElement(By.xpath("//button[@title='Сохранить и продолжить']"))
                .click();
    }

    public void checkPersonalData(WebDriver webDriver) {
        var text = webDriver.findElement(By.id("id_fname")).getAttribute("value");
        assertThat(text).as("Name").isEqualTo(NAME.getValue());

        text = webDriver.findElement(By.id("id_fname_latin")).getAttribute("value");
        assertThat(text).as("Name(Latin)").isEqualTo(NAME_LATIN.getValue());

        text = webDriver.findElement(By.id("id_lname")).getAttribute("value");
        assertThat(text).as("Last Name").isEqualTo(LNAME.getValue());

        text = webDriver.findElement(By.id("id_lname_latin")).getAttribute("value");
        assertThat(text).as("Last Name(Latin)").isEqualTo(LNAME_LATIN.getValue());

        text = webDriver.findElement(By.id("id_blog_name")).getAttribute("value");
        assertThat(text).as("Blog name").isEqualTo(BLOG_NAME.getValue());
        text = webDriver.findElement(By.xpath("//input[@name='date_of_birth']"))
                .getAttribute("value");
        assertThat(text).as("Date").isEqualTo(BIRTHDAY.getValue());

        text = webDriver.findElement(By.xpath(format("//div[contains(text(), '%s')]", COUNTRY)))
                .getText();
        assertThat(text).as("Country").isEqualTo(COUNTRY);

        text = webDriver.findElement(By.xpath(format("//div[contains(text(), '%s')]", CITY)))
                .getText();
        assertThat(text).as("City").isEqualTo(CITY);

        text = webDriver.findElement(By.xpath("//input[@data-title='Уровень знания английского языка']"))
                .getAttribute("value");
        assertThat(text).as("Уровень знания английского языка").isEqualTo("1");
        text = webDriver.findElement(By.xpath("//*[@id='id_ready_to_relocate_1']"))
                .getAttribute("value");

        assertThat(text).as("Checkbox").isEqualTo("True");
        text = webDriver.findElement(By.xpath("//input[@title='Полный день']"))
                .getAttribute("checked");
        assertThat(text).as("Checkbox").isEqualTo("true");
        text = webDriver.findElement(By.xpath("//*[@id='id_email']"))
                .getAttribute("value");
        assertThat(text).as("email").isEqualTo(System.getProperty("login"));
        text = webDriver.findElement(By.xpath("//*[@id='id_is_email_preferable']"))
                .getAttribute("checked");
        assertThat(text).as("Checkbox").isEqualTo("true");

        text = webDriver.findElement(By.xpath("//*[@id='id_contact-0-value']"))
                .getAttribute("value");
        assertThat(text).as("First Communication").isEqualTo(COMMUNICATION_1);
        text = webDriver.findElement(By.xpath(format("//input[@value='%s']", TYPE_COMM_1.toLowerCase())))
                .getAttribute("value");
        assertThat(text).as("First Communication").isEqualTo(TYPE_COMM_1.toLowerCase());

        text = webDriver.findElement(By.xpath("//*[@id='id_contact-1-value']"))
                .getAttribute("value");
        assertThat(text).as("Second Communication").isEqualTo(COMMUNICATION_2);
        text = webDriver.findElement(By.xpath(format("//input[@value='%s']", TYPE_COMM_2.toLowerCase())))
                .getAttribute("value");
        assertThat(text).as("Second Communication").isEqualTo(TYPE_COMM_2.toLowerCase());

        text = webDriver.findElement(By.xpath("//*[@id='id_gender']/option[@value='f']"))
                .getAttribute("selected");
        assertThat(text).as("checkbox").isEqualTo("true");
        text = webDriver.findElement(By.xpath("//*[@id='id_company']"))
                .getAttribute("value");
        assertThat(text).as("Company").isEqualTo(COMPANY.getValue());
        text = webDriver.findElement(By.xpath("//*[@id='id_work']"))
                .getAttribute("value");
        assertThat(text).as("Work").isEqualTo(JOB_TITLE.getValue());
        userLogger.info("Конец теста 2");
    }
}
