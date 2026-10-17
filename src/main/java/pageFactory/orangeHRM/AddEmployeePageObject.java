package pageFactory.orangeHRM;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject;

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

    @FindBy(xpath = "//label[text()='Employee Name']/parent::div/following-sibling::div//input")
    private WebElement employeeNameTextbox;

    @FindBy(xpath = "//label[text()='Gender']/parent::div/following-sibling::div//input")
    private List<WebElement> genderRadioButtons;

    @FindBy(xpath = "//button")
    private List<WebElement> buttons;

    private List<WebElement> loadingSpinner;


    public AddEmployeePageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public void enterToTextboxByName(
            WebDriver driver, String textboxName, String value) {
        if (textboxName.equals("firstName")) {
            waitElementVisible(driver, firstNameTextbox);
            sendkeyToElement(firstNameTextbox, value);
        } else if (textboxName.equals("lastName")) {
            waitElementVisible(driver, lastNameTextbox);
            sendkeyToElement(lastNameTextbox, value);
        } else {
            throw new IllegalArgumentException(
                    "Textbox name chưa được hỗ trợ: " + textboxName);
        }
    }

    public String getTextboxValueByLabel(
            WebDriver driver, String label) {
        if (label.equals("Employee Id")) {
            waitElementVisible(driver, employeeIDTextbox);
            return getElementDOMProperty(employeeIDTextbox, "value");
        }
        throw new IllegalArgumentException(
                "Label chưa được hỗ trợ: " + label);
    }

    public void enterToTextboxByLabel(
            WebDriver driver, String label, String value) {
        if (label.equals("First Name")) {
            enterToFirstNameTextbox(value);
        } else if (label.equals("Last Name")) {
            enterToLastNameTextbox(value);
        } else {
            throw new IllegalArgumentException(
                    "Label chưa được hỗ trợ: " + label);
        }
    }

    public void clickToButtonByText(
            WebDriver driver, String buttonText) {
        for (WebElement button : buttons) {
            if (button.getText().trim().equals(buttonText)) {
                waitElementClickable(driver, button);
                clickToElement(button);
                return;
            }
        }
        throw new IllegalArgumentException(
                "Không tìm thấy button: " + buttonText);
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
        return new PersonalDetailPageObject(driver);
    }

    public boolean isLoadingSpinnerDisappear(WebDriver driver){
        return waitListElementInvisible(driver,loadingSpinner);
    }
}