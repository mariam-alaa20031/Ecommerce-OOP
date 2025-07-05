package customer;

import java.util.UUID;

public class Customer {
    private UUID id;
    private String name;
    private double balance;

    public Customer(){

    }

    public Customer(String name, double balance) {
        this.id=UUID.randomUUID();
        this.name=name;
        this.balance=balance;
    }

    public Customer(UUID id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;

    }
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
