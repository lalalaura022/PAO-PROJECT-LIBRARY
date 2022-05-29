package CLASE.PRODUSE.Carti;

import CLASE.PERSOANE.Author;
import java.util.Scanner;
import java.util.Vector;

public class Books {

    private Author author;
    private String name;
    private String section;
    private int releaseDate;
    private int price;
    private Vector<Reviews> review;

    public Books(String name, Author author, int releaseDate){
        this.name = name;
        this.author = author;
        this.releaseDate = releaseDate;
    }

    public Books() {}

    public Books(Author author, String name, String section, int releaseDate, int price, Vector<Reviews> review) {
        this.author = author;
        this.name = name;
        this.section = section;
        this.releaseDate = releaseDate;
        this.price = price;
        this.review = review;
    }
    //@Override
    public void scan(){
        Scanner var = new Scanner(System.in);

        Author author = new Author();
        author.scan();
        this.author = author;

        System.out.println("Book's name: ");
        String name = var.nextLine();

        System.out.println("Section: ");
        String section = var.nextLine();

        System.out.println("Year of release: ");
        int releaseDate = var.nextInt();

        System.out.println("Price: ");
        int price = var.nextInt();

        System.out.println("Introduce number of reviews: ");
        int rNumber = var.nextInt();
/*
        for(int i = 0; i < rNumber; i++){
            System.out.println("Introduce supply number: " + i);
            Reviews reviewObj = new Reviews();
            reviewObj.scan();
            this.review.add(reviewObj);
        }
*/
    }
    @Override
    public String toString() {
        return "Books{" +
                "author=" + author +
                ", name='" + name + '\'' +
                ", section='" + section + '\'' +
                ", releaseDate=" + releaseDate +
                ", price=" + price +
                ", review=" + review +
                '}';
    }
    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSection() {
        return section;
    }
    public void setSection(String section) {
        this.section = section;
    }
    public int getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public Vector<Reviews> getReview() {
        return review;
    }
    public void setReview(Vector<Reviews> review) {
        this.review = review;
    }
    public void addReview(Reviews rev){
        review.add(rev);
    }
    public int biggerThan(int year){
        int ok = 0;
        if(this.releaseDate > year)
            ok = 1;
        return ok;
    }
    public float averageBookRate(){
        float s = 0;
        for(int i = 0; i < this.review.size(); i++){
            s += review.get(i).getRate();
        }
        return s/this.review.size();
    }
}
