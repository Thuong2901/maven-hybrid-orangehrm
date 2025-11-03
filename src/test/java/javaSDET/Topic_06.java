package javaSDET;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_06 {
    //sẽ có cả hàm abstract và non abstract
    //khởi tạo bình thường
    //chỉ cho kế thừa

    //OOP :Abstraction

    //lấy dữ liệu ra khác void
    public String getFullName(){
        WebDriver driver = new FirefoxDriver();
        driver.findElement(By.cssSelector("")).getText();
        return null;
    }

    //Action(void)
    public void setFullName(){}
}
