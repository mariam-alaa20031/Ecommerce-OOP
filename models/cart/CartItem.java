package models.cart;
import models.product.Product;

public class CartItem  {
    private Product product;
    private int quantity;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public CartItem(Product product, int quantity) {
        if (quantity > product.getQuantity()) {
            System.out.println("Error: Cannot add " + quantity + " of " + product.getName() + "- only " + product.getQuantity() + " available.");
        }
        else {
            this.product = product;
            this.quantity = quantity;
        }    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
