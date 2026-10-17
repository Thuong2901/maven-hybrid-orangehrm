
package pageObjects.saucelab;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.saucelab.ProductPageUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductPO extends BasePage {

    private WebDriver driver;

    public ProductPO(WebDriver driver) {
        this.driver = driver;
    }

    public void sortBy(String sortCriteria) {
        waitElementClickable(driver, ProductPageUI.SORT_DROPDOWN);
        selectItemInDropDown(
                driver,
                ProductPageUI.SORT_DROPDOWN,
                sortCriteria
        );
    }

    public String getSortItemSelected() {
        waitElementVisible(driver, ProductPageUI.SORT_DROPDOWN);
        return getSelectedItemInDropdown(
                driver,
                ProductPageUI.SORT_DROPDOWN
        );
    }

    public boolean isProductNameSortAscending() {
        List<WebElement> productName =
                getListElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);

        List<String> productList = new ArrayList<>();

        for (WebElement product : productName) {
            productList.add(product.getText());
        }

        List<String> sortedList = new ArrayList<>(productList);
        Collections.sort(sortedList);

        return productList.equals(sortedList);
    }

    public boolean isProductNameSortDescending() {
        List<WebElement> productName =
                getListElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);

        List<String> productList = new ArrayList<>();

        for (WebElement product : productName) {
            productList.add(product.getText());
        }

        List<String> sortedList = new ArrayList<>(productList);
        Collections.sort(sortedList);
        Collections.reverse(sortedList);

        return productList.equals(sortedList);
    }

    public boolean isProductPriceSortAscending() {
        List<WebElement> productPrice =
                getListElement(driver, ProductPageUI.PRODUCT_PRICE_TEXT);

        List<Float> productList = new ArrayList<>();

        for (WebElement product : productPrice) {
            String price = product.getText()
                    .replace("$", "")
                    .trim();

            productList.add(Float.parseFloat(price));
        }

        List<Float> sortedList = new ArrayList<>(productList);
        Collections.sort(sortedList);

        return productList.equals(sortedList);
    }

    public boolean isProductPriceSortDescending() {
        List<WebElement> productPrice =
                getListElement(driver, ProductPageUI.PRODUCT_PRICE_TEXT);

        List<Float> productList = new ArrayList<>();

        for (WebElement product : productPrice) {
            String price = product.getText()
                    .replace("$", "")
                    .trim();

            productList.add(Float.parseFloat(price));
        }

        List<Float> sortedList = new ArrayList<>(productList);
        Collections.sort(sortedList);
        Collections.reverse(sortedList);

        return productList.equals(sortedList);
    }
}
