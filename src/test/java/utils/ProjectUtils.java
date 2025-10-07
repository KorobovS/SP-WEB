package utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class ProjectUtils {

    public static WebDriver createDriver() {

        TestConfig config = new TestConfig();
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriver driver;
        String remoteUrl = System.getenv("SELENIUM_REMOTE_URL");
        String url = System.getenv("BASE_URL");
        System.out.println(url);

        if (remoteUrl != null) {
            LoggerUtil.info(String.format("SELENIUM_REMOTE_URL = %s", remoteUrl));
            Allure.addAttachment("RemoteUrl", remoteUrl);
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--disable-dev-shm-usage");
            chromeOptions.addArguments("--window-size=1440,1080");
            chromeOptions.setCapability("goog:loggingPrefs", Map.of("browser", "ALL"));
            try {
                driver = new RemoteWebDriver(new URL(remoteUrl), chromeOptions);
            } catch (MalformedURLException e) {
                throw new RuntimeException("Malformed URL for Selenium Remote WebDriver", e);
            }
            driver.get(url);
        } else {
            LoggerUtil.info("Local run");
            Allure.addAttachment("Local run", "No remote driver");
            chromeOptions.addArguments("--window-size=1920,1080");
            driver = new ChromeDriver(chromeOptions);
            driver.get(config.getBaseUrl());
        }

        return driver;
    }
}
