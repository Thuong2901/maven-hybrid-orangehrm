
package javaOOP;

public class User {
    public static void main(String[] args) {
        //khởi tạo
        //Object/Instance
        Car car = new Car();
        car.setFullName();
        car.fullName = "Honda Civic";

        Car secondCar = new Car();

        // khởi tạo
        //Animal animal = new Animal();

        //Khởi tạo
        Computer computer = new Computer();
        computer.setRAM();

        //Person
        Person firstEmp = new Person("Nguyen Van An","23","Hung Yen");
        Person secondEmp = new Person("Nguyen Van Binh","25","Ha Noi");
    }

}