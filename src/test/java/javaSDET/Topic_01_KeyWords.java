package javaSDET;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//Class kế thừa class: extends
//class kế thừa interface :implements
public class Topic_01_KeyWords extends Topic_06 implements Topic_02{
    //chỉ có non-abstract method, k có abstract method
    //khởi tạo bình thường
    //cho kế thừa

    //Data Type
    char c = 'A';
    byte bNumber = 10;
    short sNumber = 1000;
    int iNumber = 1400;
    long lNumber = 1223;
    float fNumber = 12.332f;
    double dNumber = 10.2222d;
    boolean marialStatus = true;

    String fullName = null;
    //Access Modifier

    //Variable
    private String studentName = "";
    String studenAddress = "";
    protected int studentAge = 30;
    public double studentPoint = 9.5;

    //Method
    //không có kiểu trả về(void)
    private void TC_01(){
        WebDriver driver = new FirefoxDriver();
        Topic_01_KeyWords topic = new Topic_01_KeyWords();
        //Topic_06 topic06 = new Topic_06();

    }

    protected void TC_02(){

    }

    public void TC_03(){

    }

    @Override
    public void clearStudent(){
        //Tự implement laại ở đây
    }

    //class/interface/enum/Annotation/Record
    //public
}
