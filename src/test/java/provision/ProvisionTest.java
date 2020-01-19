package provision;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.HomePage;
import pages.LoginPage;
import pages.OrdersPage;

public class ProvisionTest {

    private WebDriver driver;

    @Before
    public void startUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.navigate().to("https://speditionpartner.tryfiretms.com/app/");
    }

    @After
    public void endSession() {
        driver.close();
        driver.quit();
    }

    @Test
    public void filtersWithOtherCurrencyExecuteOtherOrder() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();
        HomePage homepage = new HomePage(driver);
        homepage.openFilterPage();
        OrdersPage ordersPage = new OrdersPage(driver);
        ordersPage.setFilterParams("92,42", "92,42", "PLN");
        ordersPage.assertOrderIsNotDisplayed("92,42 EUR");
    }

    @Test
    public void filtersCommissionWithPositiveNumberAndNegativeNumber() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();
        HomePage homepage = new HomePage(driver);
        homepage.openFilterPage();
        OrdersPage ordersPage = new OrdersPage(driver);
        ordersPage.setFilterParams("-10,79", "2,11", "EUR");
        ordersPage.assertOrderIsDisplayed("-10,79 EUR");
        ordersPage.assertOrderIsDisplayed("2,11 EUR");
    }

    @Test
    public void functionResponsibleForCorrectRemovalFilter() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();
        HomePage homepage = new HomePage(driver);
        homepage.openFilterPage();
        OrdersPage ordersPage = new OrdersPage(driver);
        ordersPage.setFilterParams("-199,19", "-186,6", "EUR");
        ordersPage.deleteFilterButtonAssertion();
    }
}
