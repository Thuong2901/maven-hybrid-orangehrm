package pageObjects;

import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;

public class PageGenerator {

    public static <T> T getPage(Class<T> pageClass, WebDriver driver) {
        try {
            Constructor<T> constructor =
                    pageClass.getConstructor(WebDriver.class);

            return constructor.newInstance(driver);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Cannot create page object: " + pageClass.getName(), e);
        }
    }
}