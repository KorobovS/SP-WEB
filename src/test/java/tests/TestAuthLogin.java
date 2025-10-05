package tests;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.BaseTest;

public class TestAuthLogin extends BaseTest {

    @Test
    void testSuccessfulLogin() {
        driver.get(baseUrl + "/login");

        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-btn"));

        username.sendKeys("testuser");
        password.sendKeys("password");
        loginButton.click();

        WebElement welcomeMessage = driver.findElement(By.id("welcome"));
        Assert.assertTrue(welcomeMessage.isDisplayed());
    }

    @Test
    void testLoginFailure() {
        driver.get(baseUrl + "/login");

        // ... test code

        if (driver.findElements(By.className("error")).size() > 0) {
            takeScreenshot("testLoginFailure");
        }
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
