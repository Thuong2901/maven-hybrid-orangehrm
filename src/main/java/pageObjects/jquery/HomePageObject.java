package pageObjects.jquery;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.jquery.HomePageUI;

public class HomePageObject extends BasePage {
    public HomePageObject(WebDriver driver){
        this.driver = driver;
    }
    private WebDriver driver;

    public void openPageByNumber(String pageNumber) {
        waitElementClickable(driver, HomePageUI.DYNAMIC_PAGE_BY_NUMBER,pageNumber);
        clickToElement(driver, HomePageUI.DYNAMIC_PAGE_BY_NUMBER,pageNumber);
        sleepInSecond(2);
    }

    public boolean isPageActiveByNumber(String pageNumber) {
        waitElementVisible(driver,HomePageUI.DYNAMIC_PAGE_ACTIVE_BY_NUMBER,pageNumber);
        return isElementDisplayed(driver,HomePageUI.DYNAMIC_PAGE_ACTIVE_BY_NUMBER,pageNumber);
    }

    public void enterToHeaderTextboxByName(String headerName, String value) {
        waitElementVisible(driver,HomePageUI.DYNAMIC_PAGE_ACTIVE_BY_NUMBER,headerName);
        sendkeyToElement(driver,HomePageUI.DYNAMIC_PAGE_ACTIVE_BY_NUMBER,headerName,value);

    }

    public boolean isPageInfoDisplayed(String female, String country, String male, String total) {
        waitElementVisible(driver,HomePageUI.DYNAMIC_PAGE_INFO,female,country,male,total);
        return isElementDisplayed(driver,HomePageUI.DYNAMIC_PAGE_INFO,female,country,male,total);
    }
}
