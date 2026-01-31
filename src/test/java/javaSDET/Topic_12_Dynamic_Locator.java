package javaSDET;

import org.testng.annotations.Test;

public class Topic_12_Dynamic_Locator {
    @Test
    public void testDynamicLocator(){
        String personalDetailPage ="//a[text()='Personal Details']";
        String dependentPage ="//a[text()='Dependent']";
        String jobPage ="//a[text()='Jobs']";

        String dynamicPageOneParam = "//a[text()='%s']";
        String dynamicPageTwoParam = "//div[@class='orangehrm-%s-employee-navigation']//a[text()='%s']";
        String dynamicPageThreeParam = "//td[@data-key='females' and text()='%s']/following-sibling::td"+
                "[@data-key='country' and text()='%s']//following-sibling::td[@data-key='males' and text()='%s']";


        openPageByName(personalDetailPage);
        openPageByName(dependentPage);
        openPageByName(jobPage);

        openPageByName(dynamicPageOneParam,"Personal Details");
        openPageByName(dynamicPageOneParam,"Dependent");
        openPageByName(dynamicPageOneParam,"Jobs");

        openPageByName(dynamicPageTwoParam,"edit","Jobs");
        openPageByName(dynamicPageTwoParam,"new","Employee");
        openPageByName(dynamicPageTwoParam,"view","Offer");

        openPageByName(dynamicPageOneParam,"384187","Afghanistan","407124");
        openPageByName(dynamicPageOneParam,"12253515","AFRICA","12599691");
        openPageByName(dynamicPageOneParam,"338282","Argentina","349238");

    }

    public void openPageByName(String pageLocator){
        System.out.println("Click to Page:" + pageLocator);

    }

//    public void openPageByName(String pageLocator, String pageName){
//        System.out.println("Click To Page :" + String.format(pageLocator,pageName));
//    }
//
//    public void openPageByName(String pageLocator,String pageFuntcion, String pageName){
//        System.out.println("Click To Page :" + String.format(pageLocator,pageFuntcion,pageName));
//    }
//
//    public void openPageByName(String pageLocator,String firstParam, String secondParam,String thirdParam){
//        System.out.println("Click To Page :" + String.format(pageLocator,firstParam,secondParam,thirdParam));
//    }

    public void openPageByName(String pageLocator,String... restParams){
        System.out.println("Click To Page :" + String.format(pageLocator,(Object[]) restParams));
    }

}
