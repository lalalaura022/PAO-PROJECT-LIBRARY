package CLASE.PRODUSE.Carti;

import CLASE.PERSOANE.Author;
import java.util.Scanner;

public class Reviews {
    private String author;
    private String text;
    private float rate;

    public Reviews() {}

    public Reviews(String author, String text, float rate) {
        this.author = author;
        this.text = text;
        this.rate = rate;
    }
    //@Override
    public void scan() {
        Scanner var = new Scanner(System.in);

        System.out.println("Author's name: ");
        String author = var.nextLine();

        System.out.println("text: ");
        String text = var.nextLine();

        System.out.println("Rate: ");
        float rate = var.nextFloat();

    }
    @Override
    public String toString() {
        return "Reviews{" +
                "author='" + author + '\'' +
                ", text='" + text + '\'' +
                ", rate=" + rate +
                '}';
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public float getRate() {
        return rate;
    }
    public void setRate(float rate) {
        this.rate = rate;
    }
}
