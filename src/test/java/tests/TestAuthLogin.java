package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.BaseTest;
import utils.LoggerUtil;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class TestAuthLogin extends BaseTest {

    @Test
    private void firstTest() {
        String baseUrl = System.getenv("BASE_URL");
        WebDriver driver;

        System.out.println(baseUrl);

        if (baseUrl != null) {
            LoggerUtil.info(String.format("BASE_URL = %s", baseUrl));

            ChromeOptions chromeOptions = new ChromeOptions();
            Allure.addAttachment("RemoteUrl", baseUrl);
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--disable-dev-shm-usage");
            chromeOptions.addArguments("--window-size=1440,1080");
            chromeOptions.setCapability("goog:loggingPrefs", Map.of("browser", "ALL"));
            try {
                driver = new RemoteWebDriver(new URL(baseUrl), chromeOptions);
            } catch (MalformedURLException e) {
                throw new RuntimeException("Malformed URL for Selenium Remote WebDriver", e);
            }
        } else {
            driver = new ChromeDriver();
            baseUrl = "https://www.google.com/";
        }
        driver.get(baseUrl);
        driver.quit();
    }

//    @Test
//    @Epic("Авторизация и аутентификация")
//    @Feature("Вход с валидными логином и паролем")
//    @Severity(SeverityLevel.NORMAL)
//    @Link("https://team-b9fb.testit.software/projects/1/tests/8")
//    public void testLoginWithValidUsernameAndPassword() throws InterruptedException {
//
//        String login = getConfig().getUserName();
//        String password = getConfig().getPassword();
//
//        DashboardPage dashboardPage = new LoginPage(getDriver())
//                .addValueToFieldLogin(login)
//                .addValueToFieldPassword(password)
//                .clickButtonLogin();
//
//        Allure.step("Загрузилась сатраница Дашборд");
//        Thread.sleep(1000);
//        Assert.assertTrue(dashboardPage.getCurrentUrl().contains(String.format("%s/dashboard", getConfig().getBaseUrl())));
//    }
//
//    @Test
//    @Epic("Авторизация и аутентификация")
//    @Feature("Введенные данные сохраняются в значение поля")
//    @Severity(SeverityLevel.NORMAL)
//    @Link("https://team-b9fb.testit.software/projects/1/tests/8")
//    public void testEnteredDataSavedFieldValue() {
//
//        String login = "test login";
//        String password = "test password";
//
//        LoginPage loginPage = new LoginPage(getDriver())
//                .addValueToFieldLogin(login)
//                .addValueToFieldPassword(password);
//
//        Assert.assertEquals(loginPage.getValueToFieldLogin(), login);
//        Assert.assertEquals(loginPage.getValueToFieldPassword(), password);
//    }
//
//    @Test
//    @Epic("Авторизация и аутентификация")
//    @Feature("Вход с пустыми полями логина и пароля")
//    @Severity(SeverityLevel.BLOCKER)
//    @Link("https://team-b9fb.testit.software/projects/1/tests/9")
//    public void testLoginWithEmptyUsernameAndPassword() {
//
//        LoginPage loginPage = new LoginPage(getDriver())
//                .clickToFieldLogin()
//                .clickToFieldPassword()
//                .clickButtonLoginWithHelper();
//
//        Allure.step("Страница не обновилась, на полях появились подсказки");
//        Assert.assertEquals(loginPage.getCurrentUrl(), String.format("%s/login", getConfig().getBaseUrl()));
//        Assert.assertEquals(loginPage.getHelperTextLogin(), "Поле Логин обязательно для заполнения");
//        Assert.assertEquals(loginPage.getHelperTextPassword(), "Поле Пароль обязательно для заполнения");
//    }
}
