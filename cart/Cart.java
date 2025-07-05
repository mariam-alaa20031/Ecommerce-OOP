package cart;

import customer.Customer;
import interfaces.Expirable;
import interfaces.Shippable;
import product.Product;
import services.ShippingService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Cart {
    UUID id;
    ArrayList<CartItem> items = new ArrayList<>();

    public Cart() {
    }

    public Cart(ArrayList<CartItem> items) {
        this.id = UUID.randomUUID();
        this.items = items;
    }

    public UUID getId() {
        return id;
    }

    public ArrayList<CartItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<CartItem> items) {
        this.items = items;
    }

    public void addToCart(CartItem item) {
        if ( item.getProduct() == null) {
            return;
        }
        // Check for existing item with the same product
        for (CartItem i : items) {
            Product product = i.getProduct();
            if ( product.equals(item.getProduct())) {
                int oldQuantity = i.getQuantity();
                int newQuantity = item.getQuantity();
                i.setQuantity(oldQuantity + newQuantity);
                return;
            }
        }

        this.items.add(item);
    }


    public boolean canEnterCheckout(Customer customer) {
        if (customer == null) {
            System.out.println("Error: Customer cannot be null!");
            return false;
        } else if (this.items.isEmpty()) {
            System.out.println("Error: Cannot checkout an empty cart!");
            return false;
        }
        else return true;

    }
    public void checkout(Customer customer) {
        if(!canEnterCheckout(customer)){
         return;
        }

        for (CartItem i : this.items) {
            Product product = i.getProduct();
            if (product instanceof Expirable && ((Expirable) product).getExpiryDate().isBefore(LocalDate.now())) {
                System.out.println("Error: " + product.getName() + " expired on " + ((Expirable) product).getExpiryDate());
                return;
            }

        }
        ShippingService shippableItems= getShippableItems();
        double subTotal=getSubTotal();
        double shippingFee=shippableItems.getShippableItemFee();
        double finalAmount = subTotal+ shippingFee;
        if(validateBalance(customer,finalAmount)){
        System.out.print(shippableItems.printShippingInfo());
        printReceipt(this.items,subTotal,shippingFee);
    }
    }
    boolean validateBalance(Customer customer, double finalAmount) {
        if (customer.getBalance() < finalAmount) {
            System.out.println("Error: Customer balance is less than the total balance!");
            return false;
        }
        updateQuantity();
        customer.setBalance(customer.getBalance() - finalAmount);
        return true;
    }
    double getSubTotal(){
        double total = 0;
        for(CartItem j : this.items){
            Product product= j.getProduct();
            total+=j.getQuantity()* product.getPrice();
        }
        return total;

    }
    void updateQuantity(){

        for(CartItem j : this.items){
            Product product= j.getProduct();
            int updatedQuantity = product.getQuantity() - j.getQuantity();
            product.setQuantity(updatedQuantity);
        }


    }


    public void printReceipt(ArrayList<CartItem> items, double subtotal, double shippingFee) {
        double total = subtotal + shippingFee;

        System.out.println("** Checkout receipt **");
        for (CartItem item : items) {
            String line = String.format("%-2dx %-10s %6.0f",
                    item.getQuantity(),
                    item.getProduct().getName(),
                    item.getQuantity() * item.getProduct().getPrice()
            );
            System.out.println(line);
        }

        System.out.println("----------------------");
        System.out.printf("%-15s %6.0f%n", "Subtotal", subtotal);
        System.out.printf("%-15s %6.0f%n", "Shipping", shippingFee);
        System.out.printf("%-15s %6.0f%n", "Amount", total);
        System.out.println();

    }

    ShippingService getShippableItems() {
        ArrayList<CartItem> itemsToBeShipped = new ArrayList<>();
        for (CartItem item : this.items) {
            if (item.getProduct() instanceof Shippable) {
                itemsToBeShipped.add(item);
            }
        }
        return   new ShippingService(itemsToBeShipped);
    }

}
