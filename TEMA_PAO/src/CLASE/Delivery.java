package CLASE;

import java.util.Scanner;

public class Delivery {
    private String deliveryPerson;
    private String deliveryMethod;
    private float KM;

    public Delivery() {
    }
    public Delivery(String deliveryPerson, String deliveryMethod, float KM) {
        this.deliveryPerson = deliveryPerson;
        this.deliveryMethod = deliveryMethod;
        this.KM = KM;
    }
    //@Override
    public void scan() {
        Scanner var = new Scanner(System.in);

        System.out.println("Delivery person name: ");
        String deliveryPerson = var.nextLine();

        System.out.println("Delivery method ");
        String deliveryMethod = var.nextLine();

        System.out.println("Distance to address: ");
        float KM = var.nextFloat();

    }
    @Override
    public String toString() {
        return "Delivery{" +
                "deliveryPerson='" + deliveryPerson + '\'' +
                ", deliveryMethod='" + deliveryMethod + '\'' +
                ", KM=" + KM +
                '}';
    }
    public String getDeliveryPerson() {
        return deliveryPerson;
    }
    public void setDeliveryPerson(String deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }
    public String getDeliveryMethod() {
        return deliveryMethod;
    }
    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }
    public float getKM() {
        return KM;
    }
    public void setKM(float KM) {
        this.KM = KM;
    }
}
