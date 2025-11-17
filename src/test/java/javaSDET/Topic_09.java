package javaSDET;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_09 {

    String name ;

    //Constructor
    public Topic_09(String name){
        this.name = name;
    }

    //khi chạy đa luôn và gọi đến hàm này
    //Phải chạy theo thứ tự
    public synchronized WebDriver getDriver(){
        return new FirefoxDriver();
    }
}
