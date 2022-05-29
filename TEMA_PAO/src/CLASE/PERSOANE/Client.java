package CLASE.PERSOANE;

import CLASE.PRODUSE.Carti.Books;
import CLASE.PRODUSE.Supplies;
import java.util.*;
import java.util.Scanner;

public class Client extends Person{

    private List<Books> purchasedBooks;
    private List<Supplies> purchasedSupplies;

    public Client() {
        this.purchasedBooks = new ArrayList<Books>();
        this.purchasedSupplies = new ArrayList<Supplies>();
    }
    public Client(String name, String number, Integer age){
        super(name, number, age);
        this.purchasedBooks = purchasedBooks;
        this.purchasedSupplies = purchasedSupplies;
    }
    public Client(String name, String number, Integer age, List<Books> purchasedBooks, List<Supplies> purchasedSupplies) {
        super(name, number, age);
        this.purchasedBooks = purchasedBooks;
        this.purchasedSupplies = purchasedSupplies;
    }
   // @Override
    public void scan2(){
        Scanner var = new Scanner(System.in);

        System.out.println("Client's name: ");
        String Name = var.nextLine();
        this.Name = Name;

        System.out.println("Client's number: ");
        String Number = var.nextLine();
        this.Number = Number;

        System.out.println("Client's age: ");
        Integer age = var.nextInt();
        this.age = age;
    }
    @Override
    public void scan() {
        Scanner var = new Scanner(System.in);

        System.out.println("Client's name: ");
        String Name = var.nextLine();
        this.Name = Name;

        System.out.println("Client's number: ");
        String Number = var.nextLine();
        this.Number = Number;

        System.out.println("Client's age: ");
        Integer age = var.nextInt();
        var.nextLine();
        this.age = age;

        System.out.println("Introduce number of purchased boooks: ");
        int bNumber = var.nextInt();
        var.nextLine();

        for(int i = 0; i < bNumber; i++){
            System.out.println("Introduce book number: " + i);
            Books book = new Books();
            book.scan();
            this.purchasedBooks.add(book);
        }

        System.out.println("Introduce number of purchased supplies: ");
        int sNumber = var.nextInt();

        for(int i = 0; i < sNumber; i++){
            System.out.println("Introduce supply number: " + i);
            Supplies supply = new Supplies();
            supply.scan();
            this.purchasedSupplies.add(supply);
        }

        this.purchasedBooks = purchasedBooks;
        this.purchasedSupplies = purchasedSupplies;
        this.Name = Name;
        this.Number = Number;
        this.age = age;

    }
    @Override
    public String toString() {
        return "Client{" +
                "purchasedBooks=" + purchasedBooks +
                ", purchasedSupplies=" + purchasedSupplies +
                ", Name='" + Name + '\'' +
                ", Number='" + Number + '\'' +
                ", Age='" + age + '\'' +
                '}';
    }
    public List<Books> getPurchasedBooks() {
        return purchasedBooks;
    }
    public void setPurchasedBooks(List<Books> purchasedBooks) {
        this.purchasedBooks = purchasedBooks;
    }
    public List<Supplies> getPurchasedSupplies() {
        return purchasedSupplies;
    }
    public void setPurchasedSupplies(List<Supplies> purchasedSupplies) {
        this.purchasedSupplies = purchasedSupplies;
    }
    public void addPurchasedBooks(Books book){
        purchasedBooks.add(book);
    }
    public void addPurchasedSupplies(Supplies supply){
        purchasedSupplies.add(supply);
    }
    public void removePurchasedBooks(Books book) {
        purchasedBooks.remove(book);
    }
    public void removePurchasedSupplies(Supplies supply) {
        purchasedSupplies.remove(supply);
    }
}
