package CLASE.PERSOANE;

import java.util.Scanner;

public class Author extends Person {

    private String DateBirth;
    private int NumberofBooks;

    public Author() {}
    public Author(String name, String number, Integer age, String dateBirth, int numberofBooks) {
        super(name, number, age);
        this.DateBirth = DateBirth;
        this.NumberofBooks = NumberofBooks;
    }
    @Override
    public void scan() {
        Scanner var = new Scanner(System.in);

        System.out.println("Author's name: ");
        String Name = var.nextLine();
        this.Name = Name;

        System.out.println("Author's number: ");
        String Number = var.nextLine();
        this.Number = Number;

        System.out.println("Author's age: ");
        Integer age = var.nextInt();
        this.age = age;
        var.nextLine();

        System.out.println("Author's Date Birth: ");
        String DateBirth = var.nextLine();

        System.out.println("Number of books: ");
        int NumberofBooks = var.nextInt();
        var.nextLine();

        this.DateBirth = DateBirth;
        this.NumberofBooks = NumberofBooks;
        this.Name = Name;
        this.Number = Number;
        this.age = age;

    }
    @Override
    public String toString() {
        return "Author{" +
                "DateBirth=" + DateBirth +
                ", NumberofBooks=" + NumberofBooks +
                ", Name='" + Name + '\'' +
                ", Number='" + Number + '\'' +
                ", Age='" + age + '\'' +
                '}';
    }
    public String getDateBirth() {
        return DateBirth;
    }
    public void setDateBirth(String dateBirth) {
        DateBirth = dateBirth;
    }
    public int getNumberofBooks() {
        return NumberofBooks;
    }
    public void setNumberofBooks(int numberofBooks) {
        NumberofBooks = numberofBooks;
    }
}
