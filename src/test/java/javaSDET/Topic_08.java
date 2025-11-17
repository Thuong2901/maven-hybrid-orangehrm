package javaSDET;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Topic_08 {
    //non-static
    String address = "";
    //phạm vi static là chia sẻ cho toàn bộ system dùng
    static String name ="Automation testing";

    //Hằng số
    static final String AGE = "30";
    @Test
    public void TC_01() throws InterruptedException {

        //Đói tượng là tp
        Topic_08 tp = new Topic_08();
        tp.address="123 MK";

        //Thuộc phạm vi class
        Topic_08.name = "";

        String osName = "Window 11";
        String separator = null;
        WebDriver driver;
        //Condition Statement
        //if - esle
        if (osName.contains("Windows")){
            separator = "\\";
        }
        else {
            separator = "//";
        }

        String browserName = "Chrome";
        //switch-case
        switch (browserName){
            case "Chrome":
                driver = new ChromeDriver();
                break;

            case "FireFox":
                driver = new FirefoxDriver();
                break;

            case "EDGE":
                driver = new EdgeDriver();
                break;

            default:
                throw new RuntimeException("Browser name is not supported");
        }

        //Loop Statement
        int studentNumber = 10;
        //Classic for
        //for
        for (int i =0 ; i < studentNumber; i++){
             System.out.println(i);
        }

        for (int i =0 ; i< studentNumber; i ++){
            if (i==5){
                System.out.println(i);
            }
        }

        List<String> stdName = new ArrayList<String>();
        //For-each
        for (String std : stdName){
            System.out.println(std);
        }

        //while
        int x=0;
        while (x<studentNumber){
            System.out.println(x);
            x ++;
        }

        //do -while
        int z=10;
        do {
            System.out.println(z);
            z++;
        }while (z<studentNumber);

        //Nếu element k được tìm thấy thì cũng k bị lỗi
        try {
            //Happy case
            driver.findElement(By.cssSelector("")).isDisplayed();
        }catch (NoSuchElementException exception){
            //Egde Case
            System.out.println(exception.getMessage());
        }finally {
            //Close Connection : DB//file
        }

        Thread.sleep(1000);
    }
}
