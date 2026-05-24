package pageObjects.orangeHRM.editNavigation;

import org.openqa.selenium.WebDriver;
import pageUIs.orangeHRM.editNavigation.EmegencyDetailPageUI;
import pageUIs.orangeHRM.editNavigation.PersonalDetailPageUI;

public class EmergencyDetailPageObject extends EditNavigatorPageObject {
    private WebDriver driver;

    public EmergencyDetailPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    public Object fileNameInTable(WebDriver driver, String fileName) {
        waitElementVisible(driver, EmegencyDetailPageUI.FILE_NAME,fileName);
        return getElementDOMProperty(driver,EmegencyDetailPageUI.FILE_NAME,"value",fileName);

    }
}