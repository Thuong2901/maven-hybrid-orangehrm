package javaFaker;

import com.github.javafaker.Faker;

import java.util.Locale;

public class Topic_02_Faker {
    public static void main(String[] args) {
        Faker faker = new Faker(new Locale("vi"));
        System.out.println(faker.internet().emailAddress());
        System.out.println("FirstName :" + faker.address().firstName());
        System.out.println("LastName :" + faker.address().lastName());
        System.out.println(faker.internet().privateIpV4Address());
        System.out.println(faker.internet().ipV4Address());
        System.out.println(faker.internet().uuid());

        System.out.println("FirstName :" + faker.address().firstName());
        System.out.println("LastName :" + faker.address().lastName());
        System.out.println("Password :" + faker.internet().password(8,12,true,true,true));

        System.out.println(faker.business().creditCardExpiry());
        System.out.println(faker.business().creditCardType());
        System.out.println(faker.business().creditCardNumber());



    }
}
