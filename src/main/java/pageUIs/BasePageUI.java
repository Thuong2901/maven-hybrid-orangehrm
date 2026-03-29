package pageUIs;

public class BasePageUI {
    //orangeHRM
    public static final String SPINNER_ICON="Css=div.oxd-loading-spinner";
    public static final String TEXTBOX_BY_LABEL ="XPath=//label[text()\"%s\"]/parent::div/following-sibling::div//input";
    public static final String TEXTBOX_BY_NAME="XPath=//input[@name='%s']";
    public static final String BUTTON_BY_TEXT="XPath=//button[contains(string(),'%s')]";
    public static final String BUTTON_BY_TEXT_IN_MAIN_TITLE="XPath=//h6[text()='%s']/following-sibling::form//button[contains(string(),'%s')]";
    public static final String MODULE_BY_TEXT_IN_MENU_ITEM="XPath=//span[text()='%s']//parent::a[contains(@class,'oxd-main-menu-item')]";
    public static final String PARENT_DROPDOWN_BY_LABEL="XPath=//label[text()='%s']/parent::div/following-sibling::div//i";
    public static final String CHILD_DROPDOWN_BY_LABEL="XPath=//label[text()='%s']/parent::div/following-sibling::div//" +
            "div[@class='oxd-select-option']/span";
    public static final String TOAST_MESSAGE_BY_TEXT ="XPath=//p[contains(@class,'oxd-text--toast-message')and text()='%s']";
    public static final String RADIO_BUTTON_BY_LABEL ="XPath=//label[text()='%s']/input";
    public static final String CHECKBOX_BUTTON_BY_LABEL ="XPath=//p[text()='%s']/following-sibling::div//span";
    public static final String USER_DROPDOWN ="Css= p.oxd-userdropdown-name";
    public static final String LOGIN_LINK ="XPath=//a[@class='oxd-userdropdown-link' and text()='Logout']";




    //opencart

    public static final String USER_MY_ACCOUNT_HEADER ="XPath=//nav[@id='top']//span[text()='My Account']";
    public static final String USER_LOGOUT_LINK_ITEM ="XPath=//a[@class='dropdown_item' and text()='Logout']";
    public static final String ADMIN_LOGOUT_LINK_ITEM ="XPath=//li[@id='nav_logout]//span[text()='Logout']";
    public static final String USER_HOME_LOGO ="Css=div#logo>a";

    //JQuery
    public static final String UPLOAD_FILE_TYPE="css=input[type='file']";
}