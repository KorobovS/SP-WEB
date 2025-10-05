package utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;

public abstract class BaseTest {

    private WebDriver driver;
    private final TestConfig config = new TestConfig();

    protected WebDriver getDriver() {

        LoggerUtil.info("Driver received");

        return driver;
    }

    protected TestConfig getConfig() {

        LoggerUtil.info("Configuration received");

        return config;
    }

    private void closeDriver() {

        if (driver != null) {
            driver.quit();
            driver = null;
            LoggerUtil.info("Driver quit");
        }

        LoggerUtil.info("Driver NULL");
    }

    @Parameters("browser")
    @BeforeMethod
    protected void beforeMethod(Method method, @Optional("yandex") String browser) {

        String baseUrl = System.getenv("BASE_URL");

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
            LoggerUtil.info("Local run");

            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "yandex":
                    System.setProperty("webdriver.chrome.driver", "driver/yandexdriver-25.8.0.1872-win64/yandexdriver.exe");
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--disable-notifications");
                    options.addArguments("--remote-allow-origins=*");
                    driver = new ChromeDriver(options);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().setSize(new Dimension(1440, 1080));
        LoggerUtil.info(String.format("Open browser: %s", browser));

        driver.get(config.getBaseUrl());

        LoggerUtil.info(String.format("Run %s.%s", this.getClass().getName(), method.getName()));
    }

    @AfterMethod
    protected void afterMethod(Method method, ITestResult testResult) {
        if (!testResult.isSuccess()) {
            Allure.addAttachment(
                    "screenshot.png",
                    "image/png",
                    new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)),
                    "png");

            Allure.addAttachment(
                    "Page HTML",
                    "text/html",
                    Objects.requireNonNull(driver.getPageSource()),
                    ".html");

            LoggerUtil.error(String.format("Crashed with an error %s.%s", this.getClass().getName(), method.getName()));
        }

        closeDriver();

        LoggerUtil.info(String.format("Execution time is %.3f sec%n", (testResult.getEndMillis() - testResult.getStartMillis()) / 1000.0));
    }
}
