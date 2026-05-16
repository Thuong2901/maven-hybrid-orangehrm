package pageObjects.orangeHRM.editNavigation;

import org.openqa.selenium.WebDriver;

public class EmployeeInformationPageObject extends EditNavigatorPageObject {
    private WebDriver driver;

    public EmployeeInformationPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }



}