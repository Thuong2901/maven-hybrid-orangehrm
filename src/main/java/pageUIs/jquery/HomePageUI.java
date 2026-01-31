package pageUIs.jquery;

public class HomePageUI {
    public static final String DYNAMIC_PAGE_BY_NUMBER = "Xpath = //li[@class='qgrd-pagination-page']/a[text()='%s']";
    public static final String DYNAMIC_PAGE_ACTIVE_BY_NUMBER = "Xpath = //li[@class='qgrd-pagination-page']/a[@class='qgrd-pagination-page-link active' and text()='%s']";
    public static final String DYNAMIC_HEADER_TEXTBOX_BY_NAME = "Xpath =//div[text()='%s']/parent::div/following-sibling::input";
    public static final String DYNAMIC_PAGE_INFO = "Xpath =//td[@data-key='female' and text()='%s']" +
            "/following-sibling::td[@data-key='country' and text()='%s']/following-sibling::td[data-key='males' and text()='%s']/following-sibling::td[@data-key='total' and text()='%s']";



}
