package CLASE.PERSOANE;

import java.util.Scanner;

public class Seller extends Person{
    private float salary;

    public Seller() {}
    public Seller(String name, String number, Integer age, float salary) {
        super(name, number, age);
        this.salary = salary;
    }
    @Override
    public void scan() {
        Scanner var = new Scanner(System.in);

        System.out.println("Seller's name: ");
        String Name = var.nextLine();
        this.Name = Name;

        System.out.println("Seller's number: ");
        String Number = var.nextLine();
        this.Number = Number;

        System.out.println("Seller's age: ");
        Integer age = var.nextInt();
        var.nextLine();
        this.age = age;

        System.out.println("Seller's sallary: ");
        float salary = var.nextFloat();
        var.nextLine();
        this.salary = salary;
        this.Name = Name;
        this.Number = Number;
        this.age = age;
    }
    @Override
    public String toString() {
        return "Seller{" +
                ", Name='" + Name + '\'' +
                ", Number='" + Number + '\'' +
                ", salary=" + salary +
                ", Age=" + age +
                '}';
    }
    public float getSalary() {
        return salary;
    }
    public void setSalary(float salary) {
        this.salary = salary;
    }
}
