package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait waiter;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waiter = new WebDriverWait(driver, 10);
    }
}
