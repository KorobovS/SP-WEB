package utils;

import java.io.IOException;
import java.util.Properties;

import static org.testng.Assert.assertNotNull;

public class TestConfig {
    String env;
    Properties properties;

    public TestConfig() {
        env = System.getProperty("env", "demo");
        properties = getPropertiesByEnv(env);

        LoggerUtil.info(String.format("The environment has been launched: %s", env));
    }

    public String getBaseUrl() {
        String baseUrl = properties.getProperty("baseUrl");
        assertNotNull(baseUrl, String.format("BaseUrl is not found in %s.properties", env));

        LoggerUtil.info(String.format("Received baseUrl for the environment: %s", env));

        return baseUrl;
    }

    public String getUserName() {
        String userName = properties.getProperty("userName");
        assertNotNull(userName, String.format("UserName is not found in %s.properties", env));

        LoggerUtil.info(String.format("Received userName for the environment: %s", env));

        return userName;
    }

    public String getPassword() {
        String password = properties.getProperty("password");
        assertNotNull(password, String.format("Password is not found in %s.properties", env));

        LoggerUtil.info(String.format("Received password for the environment: %s", env));

        return password;
    }

    private Properties getPropertiesByEnv(String env) {
        Properties testProperties = new Properties();

        try {
            testProperties.load(getClass().getClassLoader().getResourceAsStream(env + ".properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Cannot open %s.properties", env));
        }

        LoggerUtil.info(String.format("Open %s.properties", env));

        return testProperties;
    }
}