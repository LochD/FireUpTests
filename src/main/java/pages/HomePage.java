package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    By orderButtonXpath = By.xpath(".//span[@class='v-menubar-menuitem v-menubar-menuitem-mainMenu-carrierOrders']");
    By allOrdersScrollBarButtonXpath = By.xpath(".//span[@class='v-menubar-menuitem v-menubar-menuitem-mainMenu-carrierOrders-allCarrierOrders']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openFilterPage() {
        waiter.until(ExpectedConditions.elementToBeClickable(orderButtonXpath));
        openOrderButton();
        openAllOrdersScrollBarButton();
    }

    private void openOrderButton() {
        WebElement orderButton = driver.findElement(orderButtonXpath);
        orderButton.click();
    }

    private void openAllOrdersScrollBarButton() {
        WebElement allOrdersScrollBarButton = driver.findElement(allOrdersScrollBarButtonXpath);
        allOrdersScrollBarButton.click();
    }
}
