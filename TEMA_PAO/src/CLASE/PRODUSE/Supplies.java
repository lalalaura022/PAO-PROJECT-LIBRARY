package CLASE.PRODUSE;

import java.util.Scanner;

public class Supplies {
    private String type;
    private String color;
    private float price;

    public Supplies() {
    }

    public Supplies(String type, String color, float price) {
        this.type = type;
        this.color = color;
        this.price = price;
    }
    //@Override
    public void scan() {
        Scanner var = new Scanner(System.in);

        System.out.println("Supply's type: ");
        String type = var.nextLine();

        System.out.println("Supply's color");
        String color = var.nextLine();

        System.out.println("Supply's price");
        float price = var.nextFloat();
    }
    @Override
    public String toString() {
        return "Supplies{" +
                "type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                '}';
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
}
