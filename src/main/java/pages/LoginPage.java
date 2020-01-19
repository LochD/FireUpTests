package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    private By loginButtonXpath = By.xpath(".//input[@type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login() {
        WebElement loginButton = driver.findElement((loginButtonXpath));
        loginButton.click();
    }
}
