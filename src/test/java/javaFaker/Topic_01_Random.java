package javaFaker;

import java.util.Calendar;
import java.util.Random;

public class Topic_01_Random {
    public static void main(String[] args) {
        Random rand = new Random();

        System.out.println("john" + rand.nextInt(99999) + "@gmail.com");
        System.out.println("john" + rand.nextInt(99999) + "@gmail.com");
        System.out.println("john" + rand.nextInt(99999) + "@gmail.com");
        System.out.println("john" + rand.nextInt(99999) + "@gmail.com");
        System.out.println("john" + rand.nextInt(99999) + "@gmail.com");

        System.out.println(getRandomEmail());
        System.out.println(getRandomEmail());
        System.out.println(getRandomEmail());
        System.out.println(getRandomEmail());
        System.out.println(getRandomNumber());
        System.out.println(getRandomNumber(100,200));


    }
    public static int getRandomNumber(){
        int uLimit = 999;
        int lLimit =100;
        Random rand = new Random();
        return lLimit + rand.nextInt(uLimit - lLimit);
    }

    public static int getRandomNumber(int minium,int maximum){
        Random rand = new Random();
        return minium + rand.nextInt(maximum - minium);
    }
    public static String getRandomEmail(){
        return "automation" + getRandomNumberByDateTime() + "@live.com";
    }

    private static long getRandomNumberByDateTime() {
        return Calendar.getInstance().getTimeInMillis() % 100000;
    }
}
