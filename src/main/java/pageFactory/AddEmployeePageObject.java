package pageFactory;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.PersonalDetailPageObject;

import java.util.List;

public class AddEmployeePageObject extends BasePageFactory {
    private WebDriver driver;

    @FindBy(name = "firstName")
    private WebElement firstNameTextbox;

    @FindBy(name = "lastName")
    private WebElement lastNameTextbox;

    @FindBy(xpath = "//label[text()='Employee Id']/parent::div/following-sibling::div/input")
    private WebElement employeeIDTextbox;

    @FindBy(xpath = "//button[contains(string(),'Save')]")
    private WebElement saveButton;

    private List<WebElement> loadingSpinner;


    public AddEmployeePageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitElementVisible(driver,firstNameTextbox);
        sendkeyToElement(firstNameTextbox,firstName);

    }

    public void enterToLastNameTextbox(String lastName) {
        waitElementVisible(driver,lastNameTextbox);
        sendkeyToElement(lastNameTextbox,lastName);

    }

    public String getEmployeeID() {

        waitElementVisible(driver,employeeIDTextbox);
        return  getElementDOMProperty(employeeIDTextbox,"value");
    }

    public PersonalDetailPageObject clickToSaveButton() {
        waitElementClickable(driver, saveButton);
        clickToElement(saveButton);
        return null;
    }

    public boolean isLoadingSpinnerDisappear(WebDriver driver){
        return waitListElementInvisible(driver,loadingSpinner);
    }
}