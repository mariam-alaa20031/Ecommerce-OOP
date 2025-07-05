package examples;

import interfaces.Expirable;
import interfaces.Shippable;
import product.Product;

import java.time.LocalDate;

public class Cheese extends Product implements Shippable, Expirable {
    private double weight;
    private LocalDate expiryDate;

    public Cheese(String name, double price, int quantity, double weight, LocalDate expiryDate) {
        super(name, price, quantity);
        this.weight = weight;
        this.expiryDate = expiryDate;
    }

    @Override
    public double getWeight() {
        return weight;
    }


    @Override
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}