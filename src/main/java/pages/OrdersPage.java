package pages;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OrdersPage extends BasePage {

    private By filterInputXpath = By.xpath("//div[@id='gwt-uid-5']//input");
    private By provisionDropDownButtonXpath = By.xpath(".//td[@class='gwt-MenuItem gwt-MenuItem-selected']");
    private By fromInputXpath = By.xpath("//input[contains(@id,'MoneyFilter-min')]");
    private By toInputXpath = By.xpath("//input[contains(@id,'MoneyFilter-max')]");
    private By currencyInputXpath = By.xpath(".//input[@id='gwt-uid-17']");
    private By eurDropDownButtonXpath = By.xpath("//span[text()='EUR']");
    private By plnDropDownButtonXpath = By.xpath("//span[text()='PLN']");
    private By submitButtonXpath = By.xpath(".//div[@class='v-button v-widget e-button-blue v-button-e-button-blue']");
    private By deleteFilterButtonXpath = By.xpath(".//div[@class='v-button v-widget link v-button-link']");
    private WebElement deleteFilterButton;

    public OrdersPage(WebDriver driver) {
        super(driver);
    }

    public void setFilterParams(String fromValue, String toValue, String currency) {
        setFilterInput();
        pressProvisionDropDownButton();
        setFromInput(fromValue);
        setToInput(toValue);
        setCurrencyInput(currency);
        if (currency.equals("PLN")) {
            pressCurrencyDropDownButton(plnDropDownButtonXpath);
        } else {
            pressCurrencyDropDownButton(eurDropDownButtonXpath);
        }
        pressSubmitButton();
    }

    public void deleteFilterButtonAssertion() {
        pressDeleteFilterButton();
        Assertions.assertThat(deleteFilterButton.isDisplayed()).isTrue();
    }

    private void setFilterInput() {
        waiter.until(ExpectedConditions.elementToBeClickable(filterInputXpath));
        WebElement filterInput = driver.findElement(filterInputXpath);
        filterInput.click();
        filterInput.sendKeys("Prowizja");
    }

    private void pressProvisionDropDownButton() {
        waiter.until((ExpectedConditions.elementToBeClickable(provisionDropDownButtonXpath)));
        WebElement provisionDropDownButton = driver.findElement(provisionDropDownButtonXpath);
        provisionDropDownButton.click();
    }

    private void setFromInput(String fromValue) {
        waiter.until(ExpectedConditions.elementToBeClickable(fromInputXpath));
        WebElement fromInput = driver.findElement(fromInputXpath);
        fromInput.click();
        fromInput.sendKeys(fromValue);
    }

    private void setToInput(String toValue) {
        WebElement toInput = driver.findElement(toInputXpath);
        toInput.click();
        toInput.sendKeys(toValue);
    }

    private void setCurrencyInput(String currency) {
        WebElement currencyInput = driver.findElement(currencyInputXpath);
        currencyInput.click();
        currencyInput.sendKeys(currency);
    }

    private void pressCurrencyDropDownButton(By currencyXpath) {
        waiter.until(ExpectedConditions.elementToBeClickable(currencyXpath));
        WebElement currencyDropDownButton = driver.findElement(currencyXpath);
        currencyDropDownButton.click();
    }

    private void pressSubmitButton() {
        WebElement submitButton = driver.findElement(submitButtonXpath);
        submitButton.click();
    }

    private void pressDeleteFilterButton() {
        waiter.until(ExpectedConditions.elementToBeClickable(deleteFilterButtonXpath));
        deleteFilterButton = driver.findElement(deleteFilterButtonXpath);
        deleteFilterButton.click();
    }

    public void assertOrderIsDisplayed(String price) {
        waiter.until(ExpectedConditions.elementToBeClickable(By.xpath(".//div[@class='v-table-cell-wrapper' and text()='" + price + "']")));
        Assertions.assertThat(driver.findElement(By.xpath(".//div[@class='v-table-cell-wrapper' and text()='" + price + "']")).isDisplayed()).isTrue();
    }

    public void assertOrderIsNotDisplayed(String price) {
        Assertions.assertThat(driver.findElements(By.xpath(".//div[@class='v-table-cell-wrapper' and text()='" + price + "']")).size() < 1).isTrue();
    }
}
