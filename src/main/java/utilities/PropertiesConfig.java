
package utilities;

import core.GlobalConstants;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesConfig {

    private Properties properties;

    private final String propertyFilePath =
            GlobalConstants.ENVIRONMENT_CONFIG_PATH + "env-%s.properties";

    // Ngăn việc khởi tạo trực tiếp từ bên ngoài class
    private PropertiesConfig() {
    }

    public static PropertiesConfig getProperties(String serverName) {
        return new PropertiesConfig(serverName);
    }

    public PropertiesConfig(String serverName) {

        properties = new Properties();

        String filePath = String.format(propertyFilePath, serverName);

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(filePath))) {

            properties.load(reader);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(
                    "Configuration properties not found at " + filePath, e);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Cannot read configuration properties at " + filePath, e);
        }
    }

    public String getApplicationUrl() {
        String url = properties.getProperty("App.Url");

        if (url != null && !url.trim().isEmpty()) {
            return url;
        }

        throw new RuntimeException(
                "URL not specified in the properties file.");
    }

    public String getApplicationUserName() {
        String username = properties.getProperty("App.User");

        if (username != null && !username.trim().isEmpty()) {
            return username;
        }

        throw new RuntimeException(
                "Username not specified in the properties file.");
    }

    public String getApplicationPassword() {
        String password = properties.getProperty("App.Pass");

        if (password != null && !password.trim().isEmpty()) {
            return password;
        }

        throw new RuntimeException(
                "Password not specified in the properties file.");
    }
}
