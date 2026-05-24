package javaFaker;

import net.datafaker.Faker;

import java.util.Locale;

public class Topic_03_FakerNET {
    public static void main(String[] args) {
        Faker faker = new Faker(new Locale("vi"));
        System.out.println(faker.internet().emailAddress());
        System.out.println(faker.internet().privateIpV4Address());
        System.out.println(faker.internet().ipV4Address());
        System.out.println(faker.internet().uuid());

        System.out.println("Password :" + faker.credentials().password(8,12,true,true,true));
        System.out.println("Password :" + faker.credentials().password(8,12,true,true,true));
        System.out.println("Password :" + faker.credentials().password(8,12,true,true,true));

        System.out.println("Random:" + faker.number().randomDigit());
        System.out.println("Random:" + faker.number().randomDigits(999999));
        System.out.println("Random:" + faker.number().randomDigits(99));


        System.out.println(faker.business().creditCardExpiry());
        System.out.println(faker.business().creditCardType());
        System.out.println(faker.business().creditCardNumber());



    }
}
