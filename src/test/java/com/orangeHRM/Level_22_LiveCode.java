package com.orangeHRM;

//import từ thư viện

import core.BaseTest;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageFactory.orangeHRM.AddEmployeePageObject;
import pageFactory.orangeHRM.DashboardPageObject;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.EmployeeListPageObject;
import pageObjects.orangeHRM.LoginPageObject;
import pageObjects.orangeHRM.editNavigation.*;


public class Level_22_LiveCode extends BaseTest {



    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appURL, String browserName) {
        driver = getBrowserDriver(appURL, browserName);

        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);

        adminUserName = "automation";
        adminPassword = "Auto123$$##";

        employeeFirstName= "John";
        employeeLastName= "A";
        employeeID = String.valueOf(getRandomNumber());
        employeeUsername =employeeFirstName + getRandomNumber();
        employeePassword ="Automation12@";

        street ="HN";
        city="HN";
        state ="Nguyen Du";
        zip="88765677";
        country="Algeria";
        mobile="0876544556";
        email ="Peter" + getRandomNumber() + "@gmail.com";
        telephone ="0999889900";
        relationship ="";
        company="SSI";
        job="IT";
        from = "2025-01-11";
        to="2026-01-11";
        level ="College Undergraduate";
        year="2026";
        institute="";
        fluecy="Writing";
        language="French";
        compatency="Poor";
        employeeStatus="Full-Time Contract";
        fileName="file12.xlsx";
        date="2026-05-23";
        jobTile="Account Assistant";
        jobCategory="Craft Workers";
        subUnit="Administration";
        location="HQ - CA, USA";
        employmentStatus="Full-Time Contract";
        salaryComponent="12222";
        payGrade="Grade 2";
        payFrequency="Hourly";
        currency="United States Dollar";
        amount="40000";

        loginPage.enterToTextboxByLabel(driver,"Username",adminUserName);
        loginPage.enterToTextboxByLabel(driver,"Password",adminPassword);

        loginPage.clickToButtonByText(driver,"Login");
        dashboardPage=PageGenerator.getPage(DashboardPageObject.class,driver);

        Assert.assertFalse(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        verifyFalse(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Admin"));
    }

    @Test
    public void Employee_01_NewEmployee() {

        dashboardPage.clickToModuleByTextInMenuItem(driver,"PIM");
        employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class,driver);
        verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));


        employeeListPage.clickToButtonByText(driver,"Add");
        addEmployeePage = PageGenerator.getPage(AddEmployeePageObject.class,driver);
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToTextboxByName(driver,"firstName",employeeFirstName);
        addEmployeePage.enterToTextboxByName(driver,"lastName",employeeLastName);

        //addEmployeePage.clearToTextboxByLabel(driver,"Employee Id");
        addEmployeePage.enterToTextboxByName(driver,"Employee Id",employeeID);

        addEmployeePage.clickToButtonByText(driver,"Create login details");

        addEmployeePage.enterToTextboxByLabel(driver,"Username",employeeUsername);
        addEmployeePage.enterToTextboxByLabel(driver,"Password",employeePassword);
        addEmployeePage.enterToTextboxByLabel(driver,"Confirm Password",employeePassword);

        employeeListPage.clickToButtonByText(driver,"Save");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class,driver);

        verifyTrue(personalDetailPage.isToastMessageDisplay(driver,"Successfully Saved"));

        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(10);


        verifyEquals(personalDetailPage.getTextboxValueByName(driver,"lastName"), employeeLastName);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver,"firstName"), employeeFirstName );
        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver,"Employee Id"), employeeID);

        loginPage =personalDetailPage.clickLogoutOnTopMenu(driver);

        //Login bang quyen employee vua tao
        loginPage.enterToTextboxByLabel(driver,"Username",employeeUsername);
        loginPage.enterToTextboxByLabel(driver,"Password",employeePassword);

        loginPage.clickToButtonByText(driver,"Login");
        dashboardPage=PageGenerator.getPage(DashboardPageObject.class,driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        //Den man hinh Dashboard
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"My info"));

        dashboardPage.clickToModuleByTextInMenuItem(driver,"My info");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class,driver);

        verifyEquals(personalDetailPage.getTextboxValueByName(driver,"firstName"),employeeFirstName);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver,"lastName"),employeeLastName);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver,"Employee Id"),employeeID);

    }

    @Test
    public void Employee_02_UploadAvatar() {
        personalDetailPage.clickToProfileImage();
        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));

        Dimension oldProfileImageSize = personalDetailPage.getProfileNaturalImageSize();

        //File type
        personalDetailPage.uploadMultipleFiles(driver,"R.pdf");
        verifyEquals(personalDetailPage.getErrorMessageProfileImage(),"File type not allowed");

        //maximum size
        personalDetailPage.uploadMultipleFiles(driver,"1.5MB.jpg");
        verifyEquals(personalDetailPage.getErrorMessageProfileImage(),"Attachment Size Exceeded");

        //Maximum Dimension
        personalDetailPage.uploadMultipleFiles(driver,"Dimension.jpg");

        //Valid File type
        personalDetailPage.uploadMultipleFiles(driver,"anh_dong.gif");
        personalDetailPage.uploadMultipleFiles(driver,"anh_dong.gif");

        personalDetailPage.clickToButtonByText(driver,"Save");

        verifyTrue(personalDetailPage.isToastMessageDisplay(driver,"Successfully Updated"));

        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(10);

        Dimension newProfileImageSize = personalDetailPage.getProfileNaturalImageSize();

        verifyNotEquals(oldProfileImageSize,newProfileImageSize);

    }

    @Test
    public void Employee_03_EditPersonalDetail() {
        dashboardPage.clickToModuleByTextInMenuItem(driver,"PIM");
        employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class,driver);
        verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));


        employeeListPage.clickToEditButton();
        personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class,driver);
        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));

        verifyEquals(personalDetailPage.getTextboxValueByName(driver,"lastName"), employeeLastName);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver,"firstName"), employeeFirstName );
        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver,"Employee Id"), employeeID);

        personalDetailPage.enterToTextboxByName(driver,"firstName",employeeFirstName);
        personalDetailPage.enterToTextboxByName(driver,"lastName",employeeLastName);

        personalDetailPage.clickToButtonByText(driver,"Save");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class,driver);

        verifyTrue(personalDetailPage.isToastMessageDisplay(driver,"Successfully Updated"));

        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(10);

        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver, "Employee Id"), employeeID);


    }

    @Test
    public void Employee_04_ContactDetail() {
        personalDetailPage.clickToMenuMyInfo(driver, "Contact Details");
        contacDetailsPage = PageGenerator.getPage(ContactDetailPageObject.class,driver);
        verifyTrue(contacDetailsPage.isLoadingSpinnerDisappear(driver));

        contacDetailsPage.enterToTextboxByLabel(driver,"Street 1",street);
        contacDetailsPage.enterToTextboxByLabel(driver,"Street 2",street);
        contacDetailsPage.enterToTextboxByLabel(driver,"City",city);
        contacDetailsPage.enterToTextboxByLabel(driver,"State/Province",state);
        contacDetailsPage.enterToTextboxByLabel(driver,"Zip/Postal Code",zip);
        contacDetailsPage.selectDropdownByLabel(driver,"Country",country);
        contacDetailsPage.enterToTextboxByLabel(driver,"Mobile",mobile);
        contacDetailsPage.enterToTextboxByLabel(driver,"Work Email",email);

        contacDetailsPage.clickToButtonByText(driver, "Save");
        contacDetailsPage = PageGenerator.getPage(ContactDetailPageObject.class, driver);

        verifyTrue(contacDetailsPage.isToastMessageDisplay(driver, "Successfully Updated"));

        verifyTrue(contacDetailsPage.isLoadingSpinnerDisappear(driver));
        contacDetailsPage.sleepInSecond(5);

        verifyEquals(contacDetailsPage.getTextboxValueByLabel(driver, "Street 1"), street);
        verifyEquals(contacDetailsPage.getTextboxValueByLabel(driver, "Street 2"), street);
        verifyEquals(contacDetailsPage.getTextboxValueByLabel(driver, "City"), city);
        verifyEquals(contacDetailsPage.getTextboxValueByLabel(driver, "State/Province"), state);
        verifyEquals(contacDetailsPage.getTextboxValueByLabel(driver, "Zip/Postal Code"), zip);
        verifyEquals(contacDetailsPage.getTextboxValueByLabel(driver, "Country"), country);
        verifyEquals(contacDetailsPage.getTextboxValueByLabel(driver, "Mobile"), mobile);
        verifyEquals(contacDetailsPage.getTextboxValueByLabel(driver, "Work Email"), email);

    }

    @Test
    public void Employee_05_EmergencyDetail() {
        contacDetailsPage.clickToMenuMyInfo(driver, "Emergency Contacts");
        emergencyDetailPage = PageGenerator.getPage(EmergencyDetailPageObject.class,driver);
        verifyTrue(emergencyDetailPage.isLoadingSpinnerDisappear(driver));

        emergencyDetailPage.clickToAddButton(driver,"Assigned Emergency Contacts");
        emergencyDetailPage = PageGenerator.getPage(EmergencyDetailPageObject.class,driver);
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        emergencyDetailPage.enterToTextboxByLabel(driver,"Name",name);
        emergencyDetailPage.enterToTextboxByLabel(driver,"Relationship",relationship);
        emergencyDetailPage.enterToTextboxByLabel(driver,"Home Telephone",telephone);
        emergencyDetailPage.enterToTextboxByLabel(driver,"Mobile",mobile);

        emergencyDetailPage.clickToButtonByText(driver, "Save");
        emergencyDetailPage = PageGenerator.getPage(EmergencyDetailPageObject.class, driver);

        verifyTrue(contacDetailsPage.isToastMessageDisplay(driver, "Successfully Saved"));
        verifyTrue(contacDetailsPage.isLoadingSpinnerDisappear(driver));


        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver,"Name"),name);
        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver,"Relationship"),relationship);
        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver,"Home Telephone"),telephone);

        //Attach file
        emergencyDetailPage.clickToAddButton(driver,"Attachments");

        emergencyDetailPage.uploadMultipleFiles(driver,"Dimension.jpg");
        verifyEquals(emergencyDetailPage.getErrorMessageAttachFile(driver,""),"File type not allowed");

        emergencyDetailPage.uploadMultipleFiles(driver,"file1.xlxs");

        emergencyDetailPage.clickToButtonByText(driver,"Save");

        verifyTrue(emergencyDetailPage.isToastMessageDisplay(driver,"Successfully Saved"));

        verifyTrue(emergencyDetailPage.isLoadingSpinnerDisappear(driver));
        emergencyDetailPage.sleepInSecond(10);

        verifyEquals(emergencyDetailPage.getTextboxValueByLabel(driver,"file12.xlsx"),fileName);


    }

    @Test
    public void Employee_06_Dependents() {

        emergencyDetailPage.clickToMenuMyInfo(driver, "Emergency Contacts");
        dependentsPage = PageGenerator.getPage(DependentsPageObject.class,driver);
        verifyTrue(dependentsPage.isLoadingSpinnerDisappear(driver));

        dependentsPage.clickToAddButton(driver,"Assigned Dependents");
        dependentsPage = PageGenerator.getPage(DependentsPageObject.class,driver);
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        dependentsPage.enterToTextboxByLabel(driver,"Name",name);
        dependentsPage.selectDropdownByLabel(driver,"Child",relationship);

        dependentsPage.clickToButtonByText(driver, "Save");
        dependentsPage = PageGenerator.getPage(DependentsPageObject.class, driver);

        verifyTrue(dependentsPage.isToastMessageDisplay(driver, "Successfully Saved"));
        verifyTrue(dependentsPage.isLoadingSpinnerDisappear(driver));


        verifyEquals(dependentsPage.getTextboxValueByLabel(driver,"Name"),name);
        verifyEquals(dependentsPage.getTextboxValueByLabel(driver,"Relationship"),relationship);
        verifyEquals(dependentsPage.getTextboxValueByLabel(driver,"Home Telephone"),telephone);

        //Attach file
        dependentsPage.clickToAddButton(driver,"Attachments");

        dependentsPage.uploadMultipleFiles(driver,"Dimension.jpg");
        verifyEquals(dependentsPage.getErrorMessageAttachFile(driver,"File type not allowed"),"File type not allowed");

        dependentsPage.uploadMultipleFiles(driver,"file1.xlxs");

        dependentsPage.clickToButtonByText(driver,"Save");

        verifyTrue(dependentsPage.isToastMessageDisplay(driver,"Successfully Saved"));

        verifyTrue(dependentsPage.isLoadingSpinnerDisappear(driver));
        dependentsPage.sleepInSecond(10);



    }

    @Test
    public void Employee_07_Jobs() {
        dependentsPage.clickToMenuMyInfo(driver, "Job");
        jobPage = PageGenerator.getPage(JobPageObject.class,driver);
        verifyTrue(jobPage.isLoadingSpinnerDisappear(driver));

        jobPage.enterToTextboxByLabel(driver,"Joined Date",date);
        jobPage.selectDropdownByLabel(driver,"Job Title",jobTile);
        jobPage.selectDropdownByLabel(driver,"Job Category",jobCategory);
        jobPage.selectDropdownByLabel(driver,"Sub Unit",subUnit);
        jobPage.selectDropdownByLabel(driver,"Location",location);
        jobPage.selectDropdownByLabel(driver,"Employment Status",employmentStatus);

        jobPage.clickToButtonByText(driver, "Save");
        jobPage = PageGenerator.getPage(JobPageObject.class, driver);

        verifyTrue(jobPage.isToastMessageDisplay(driver, "Successfully Saved"));
        verifyTrue(jobPage.isLoadingSpinnerDisappear(driver));

    }

    @Test
    public void Employee_08_Salary() {
        jobPage.clickToMenuMyInfo(driver, "Salary");
        salaryPage = PageGenerator.getPage(SalaryPageObject.class,driver);
        verifyTrue(salaryPage.isLoadingSpinnerDisappear(driver));

        salaryPage.clickToAddButton(driver,"Add Salary Component");
        salaryPage = PageGenerator.getPage(SalaryPageObject.class,driver);
        verifyTrue(salaryPage.isLoadingSpinnerDisappear(driver));

        salaryPage.enterToTextboxByLabel(driver,"Salary Component",salaryComponent);
        salaryPage.selectDropdownByLabel(driver,"Pay Grade",payGrade);
        salaryPage.selectDropdownByLabel(driver,"Pay Frequency",payFrequency);
        salaryPage.selectDropdownByLabel(driver,"Currency",currency);
        salaryPage.enterToTextboxByLabel(driver,"Amount",amount);


        salaryPage.clickToButtonByText(driver, "Save");
        salaryPage = PageGenerator.getPage(SalaryPageObject.class, driver);

        verifyTrue(salaryPage.isToastMessageDisplay(driver, "Successfully Saved"));
        verifyTrue(salaryPage.isLoadingSpinnerDisappear(driver));

        verifyEquals(salaryPage.InfoInTheTable(driver,"Salary Component"),name);
        verifyEquals(salaryPage.InfoInTheTable(driver,"Amount"),amount);
        verifyEquals(salaryPage.InfoInTheTable(driver,"Currency"),currency);
        verifyEquals(salaryPage.InfoInTheTable(driver,"Pay Frequency"),payFrequency);

    }

    @Test
    public void Employee_09_Tax() {
      //k tìm thấy menu tax
    }


    @Test
    public void Employee_10_Qualification() {
        dependentsPage.clickToMenuMyInfo(driver, "Qualifications");
        qualificationPage = PageGenerator.getPage(QualificationPageObject.class,driver);
        verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

        qualificationPage.clickToAddButton(driver,"Work Experience");
        qualificationPage = PageGenerator.getPage(QualificationPageObject.class,driver);
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        qualificationPage.enterToTextboxByLabel(driver,"Company",company);
        qualificationPage.enterToTextboxByLabel(driver,"Job Title",job);
        qualificationPage.enterToTextboxByLabel(driver,"From",from);
        qualificationPage.enterToTextboxByLabel(driver,"To",to);

        qualificationPage.clickToButtonByText(driver, "Save");
        qualificationPage = PageGenerator.getPage(QualificationPageObject.class, driver);

        verifyTrue(qualificationPage.isToastMessageDisplay(driver, "Successfully Saved"));
        verifyTrue(qualificationPage.isLoadingSpinnerDisappear(driver));


        verifyEquals(qualificationPage.getTextboxValueByLabel(driver,"Company"),company);
        verifyEquals(qualificationPage.getTextboxValueByLabel(driver,"Job Title"),job);
        verifyEquals(qualificationPage.getTextboxValueByLabel(driver,"From"),from);
        verifyEquals(qualificationPage.getTextboxValueByLabel(driver,"From"),to);


        qualificationPage.clickToAddButton(driver,"Languages");
        qualificationPage = PageGenerator.getPage(QualificationPageObject.class,driver);
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        qualificationPage.selectDropdownByLabel(driver,"Language",language);
        qualificationPage.selectDropdownByLabel(driver,"Fluency",fluecy);
        qualificationPage.selectDropdownByLabel(driver,"Competency",compatency);

        qualificationPage.clickToButtonByText(driver, "Save");
        qualificationPage = PageGenerator.getPage(QualificationPageObject.class, driver);

        verifyTrue(qualificationPage.isToastMessageDisplay(driver, "Successfully Saved"));
        verifyTrue(qualificationPage.isLoadingSpinnerDisappear(driver));

        verifyEquals(qualificationPage.getTextboxValueByLabel(driver,"Language"),language);
        verifyEquals(qualificationPage.getTextboxValueByLabel(driver,"Fluency"),fluecy);
        verifyEquals(qualificationPage.getTextboxValueByLabel(driver,"Competency"),compatency);


        qualificationPage.clickToAddButton(driver,"Education");
        qualificationPage = PageGenerator.getPage(QualificationPageObject.class,driver);
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        qualificationPage.selectDropdownByLabel(driver,"Level",level);
        qualificationPage.enterToTextboxByLabel(driver,"Institute",job);
        qualificationPage.enterToTextboxByLabel(driver,"Year",year);
        qualificationPage.enterToTextboxByLabel(driver,"Start Date",from);
        qualificationPage.enterToTextboxByLabel(driver,"End Date",to);

        qualificationPage.clickToButtonByText(driver, "Save");
        qualificationPage = PageGenerator.getPage(QualificationPageObject.class, driver);

        verifyTrue(qualificationPage.isToastMessageDisplay(driver, "Successfully Saved"));
        verifyTrue(qualificationPage.isLoadingSpinnerDisappear(driver));

        verifyEquals(qualificationPage.getTextboxValueByLabel(driver,"Level"),level);
        verifyEquals(qualificationPage.getTextboxValueByLabel(driver,"Year"),year);



    }

    @Test
    public void Employee_11_Search() {
        qualificationPage.clickToMenuHeader(driver,"Employee List");
        employeeInformationPage = PageGenerator.getPage(EmployeeInformationPageObject.class,driver);
        verifyTrue(employeeInformationPage.isLoadingSpinnerDisappear(driver));

        employeeInformationPage.enterToTextboxByLabel(driver,"Employee Name",employeeUsername);
        employeeInformationPage.enterToTextboxByLabel(driver,"Employment Status",employeeStatus);

        employeeInformationPage.clickToButtonByText(driver, "Search");
        employeeInformationPage = PageGenerator.getPage(EmployeeInformationPageObject.class, driver);

        verifyTrue(employeeInformationPage.isToastMessageDisplay(driver, "No Records Found"));
        verifyTrue(employeeInformationPage.isLoadingSpinnerDisappear(driver));

        employeeInformationPage.enterToTextboxByLabel(driver,"Employment Status",employeeStatus);
        employeeInformationPage.clickToButtonByText(driver, "Search");
        employeeInformationPage = PageGenerator.getPage(EmployeeInformationPageObject.class, driver);
        verifyTrue(employeeInformationPage.isLoadingSpinnerDisappear(driver));

        Assert.assertTrue(InfoInTheTable.contains(employeeStatus));

    }


    @AfterClass
    public void afterClass() {
        closeBrowser();
    }

    private WebDriver driver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private AddEmployeePageObject addEmployeePage;
    private PersonalDetailPageObject personalDetailPage;
    private ContactDetailPageObject contacDetailsPage;
    private EmergencyDetailPageObject emergencyDetailPage;
    private DependentsPageObject dependentsPage;
    private QualificationPageObject qualificationPage;
    private EmployeeInformationPageObject employeeInformationPage;
    private JobPageObject jobPage;
    private SalaryPageObject salaryPage;
    private String employeeID, adminUserName, adminPassword, employeeFirstName, employeeLastName;
    private String employeeUsername,employeePassword;
    private String street,city,state,zip,country,mobile,email;
    private String name,relationship,telephone;
    private String company,job,from,to,level,institute,year,fluecy,language,compatency,employeeStatus,fileName;
    private String date,jobTile,jobCategory,subUnit,location,employmentStatus;
    private String salaryComponent,payGrade,payFrequency,currency,amount;
}