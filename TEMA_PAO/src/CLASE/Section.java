package CLASE;

import CLASE.PERSOANE.Author;
import CLASE.PRODUSE.Carti.Books;
import CLASE.PRODUSE.Carti.Reviews;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Section implements Comparable<Section>{
    private String denumire;
    private HashMap<Books, Integer> stock;


    @Override
    public int compareTo(Section o) {
        return denumire.compareTo(o.getDenumire());
    }
    public void scan(){
        Scanner var = new Scanner(System.in);

        System.out.println("Section's name: ");
        String denumire = var.nextLine();
        this.denumire = denumire;
        System.out.println("Introduce some stock for section: ");
        int n;
        System.out.println("How many books do you want to read");
        n = var.nextInt();
        var.nextLine();
        Integer nr;

        for(int i = 0; i < n; i++){
            Books book = new Books();
            book.scan();
            System.out.println("How many books on stock?");
            nr = var.nextInt();
            var.nextLine();
            this.stock.put(book, nr);

        }

        }

    public Section() {}

    @Override
    public String toString() {
        return denumire + "\n";
    }

    public Section(String denumire){
        this.denumire = denumire;
        this.stock = new HashMap<Books, Integer>();
    }
    public Section(String denumire, HashMap<Books, Integer> book){
        this.denumire = denumire;
        this.stock = book;
    }
    public void setStockAll(HashMap<Books, Integer> books){
        this.stock = books;
    }
    public void setStock(Books book, int cantitate) {
        stock.put(book, cantitate);
    }
    public HashMap<Books, Integer> getStock(){
        return stock;
    }
    public Set<Books> getBooks() {
        return stock.keySet();
    }
    public String getDenumire() {
        return denumire;
    }
    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }
    public void lowerStock(Books book) throws Exception{
        if (stock.containsKey(book)) {
            if (stock.get(book) - 1 >= 0) {
                stock.put(book, stock.get(book) - 1);
            } else {
                throw new Exception("The stock is lower than 0!");
            }
        } else {
            throw new Exception("Book not in stock!");
        }
    }
    public void lowerStock(Books book, int cantitate) throws Exception{
        if (stock.containsKey(book)) {
            if (stock.get(book) - cantitate >= 0) {
                stock.put(book, stock.get(book) - cantitate);
            } else {
                throw new Exception("The stock is lower than 0!");
            }
        } else {
            throw new Exception("Book not in stock!");
        }
    }
    public void addStock(Books book) {
        if (stock.containsKey(book)) {
            stock.put(book, stock.get(book) + 1);
        } else {
            stock.put(book, 1);
        }
    }
    public void addStock(Books book, int cantitate) {
        if (stock.containsKey(book)) {
            stock.put(book, stock.get(book) + cantitate);
        } else {
            stock.put(book, cantitate);
        }
    }
    public void removeStock(Books book) throws Exception{
        if (stock.containsKey(book)) {
            stock.remove(book);
        } else {
            throw new Exception("Book not in stock!");
        }
    }



}
