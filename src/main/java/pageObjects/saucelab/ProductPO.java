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
    public ProductPO(WebDriver driver){
        this.driver =driver;
    }

    public void sortBy(String sortCriteria) {
        waitElementClickable(driver, ProductPageUI.SORT_DROPDOWN);
        selectItemInDropDown(driver,ProductPageUI.SORT_DROPDOWN,sortCriteria);
    }

    public String getSortItemSelected(){
        waitElementVisible(driver,ProductPageUI.SORT_DROPDOWN);
        return getSelectedItemInDropdown(driver,ProductPageUI.SORT_DROPDOWN);
    }

    public boolean isProductNameSortAscending() {
        //Lấy ra hết các element chứa prodcutName
        List<WebElement> productName = getListElement(driver,ProductPageUI.PRODUCT_NAME_TEXT);

        //Khai báo 1 mảng ds A
        ArrayList<String> productList = new ArrayList<String>();

        //Dùng vòng lăp lấy productName text lưu vào ds A
        for (WebElement product : productName){
            productList.add(product.getText());
        }

        //Khai báo 1 mảng ds B lấy dữ liệu từ A qua
        ArrayList<String> sortedList = new ArrayList<String>();
        for (String product: productList){
            sortedList.add(product);
        }

        //Sort ascending ds B
        Collections.sort(sortedList);


        return  productList.equals(sortedList);
    }

    public boolean isProductNameSortDescending() {
        //Lấy ra hết các element chứa prodcutName
        List<WebElement> productName = getListElement(driver,ProductPageUI.PRODUCT_NAME_TEXT);

        //Khai báo 1 mảng ds A
        ArrayList<String> productList = new ArrayList<String>();

        //Dùng vòng lăp lấy productName text lưu vào ds A
        for (WebElement product : productName){
            productList.add(product.getText());
        }

        //Khai báo 1 mảng ds B lấy dữ liệu từ A qua
        ArrayList<String> sortedList = new ArrayList<String>();
        for (String product: productList){
            sortedList.add(product);
        }

        //Sort ascending ds B
        Collections.sort(sortedList);

        //Sort lai B thanh Descending
        Collections.reverse(sortedList);

        return  productList.equals(sortedList);
    }

    public boolean isProductPriceSortAscending() {
        //Lấy ra hết các element chứa prodcutPrice
        List<WebElement> productPrice = getListElement(driver,ProductPageUI.PRODUCT_PRICE_TEXT);

        //Khai báo 1 mảng ds A
        ArrayList<Float> productList = new ArrayList<Float>();

        //Dùng vòng lăp lấy productName text lưu vào ds A
        for (WebElement product : productPrice){
            productList.add(Float.parseFloat(product.getText().replace("$","")));
        }

        //Khai báo 1 mảng ds B lấy dữ liệu từ A qua
        ArrayList<Float> sortedList = new ArrayList<Float>();
        for (Float product: productList){
            sortedList.add(product);
        }

        //Sort ascending ds B
        Collections.sort(sortedList);


        return  productList.equals(sortedList);
    }

    public boolean isProductPriceSortDescending() {
        //Lấy ra hết các element chứa prodcutPrice
        List<WebElement> productPrice = getListElement(driver,ProductPageUI.PRODUCT_PRICE_TEXT);

        //Khai báo 1 mảng ds A
        ArrayList<Float> productList = new ArrayList<Float>();

        //Dùng vòng lăp lấy productName text lưu vào ds A
        for (WebElement product : productPrice){
            productList.add(Float.parseFloat(product.getText().replace("$","")));
        }

        //Khai báo 1 mảng ds B lấy dữ liệu từ A qua
        ArrayList<Float> sortedList = new ArrayList<Float>();
        for (Float product: productList){
            sortedList.add(product);
        }

        //Sort ascending ds B
        Collections.sort(sortedList);

        //Sort lai B thanh Descending
        Collections.reverse(sortedList);


        return  productList.equals(sortedList);
    }
}
