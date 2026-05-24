package javaFaker;

public class Topic_06_TestClass {
    public static void main(String[] args) {
        Topic_05_Getter_Setter topic = new Topic_05_Getter_Setter();

        //Truy cap doc(get)
        System.out.println(topic.getFirstName());

        //sua du lieu(get)
        topic.setFirstName("Automation Testing");
        System.out.println(topic.getFirstName());
    }
}
