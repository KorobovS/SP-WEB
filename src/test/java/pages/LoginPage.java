package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "login")
    private WebElement fieldLogin;

    @FindBy(name = "password")
    private WebElement fieldPassword;

    @FindBy(xpath = "//button[@data-testid='LoginButton']")
    private WebElement buttonLogin;

    @FindBy(id = ":r1:-helper-text")
    private WebElement helperLogin;

    @FindBy(id = ":r2:-helper-text")
    private WebElement helperPassword;

    @Step("Добавляю значение в поле Логин")
    public LoginPage addValueToFieldLogin(String login) {
        fieldLogin.sendKeys(login);

        return this;
    }

    @Step("Добавляю значение в поле Пароль")
    public LoginPage addValueToFieldPassword(String password) {
        fieldPassword.sendKeys(password);

        return this;
    }

    @Step("Взять значение поля Логин")
    public String getValueToFieldLogin() {

        return fieldLogin.getDomAttribute("value");
    }

    @Step("Взять значение поля  Пароль")
    public String getValueToFieldPassword() {

        return fieldPassword.getDomAttribute("value");
    }

    @Step("Кликаю кнопку Войти")
    public DashboardPage clickButtonLogin() {
        buttonLogin.click();

        return new DashboardPage(driver);
    }

    @Step("Кликнуть в поле Логин")
    public LoginPage clickToFieldLogin() {
        fieldLogin.click();

        return this;
    }

    @Step("Кликнуть в поле Пароль")
    public LoginPage clickToFieldPassword() {
        fieldPassword.click();

        return this;
    }

    @Step("Кликаю кнопку Войти с подсказкой")
    public LoginPage clickButtonLoginWithHelper() {
        buttonLogin.click();

        return this;
    }

    @Step("Проверить текст подсказки для поля Логин")
    public String getHelperTextLogin() {
        return helperLogin.getText();
    }

    @Step("Проверить текст подсказки для поля Пароль")
    public String getHelperTextPassword() {
        return helperPassword.getText();
    }
}
