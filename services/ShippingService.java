package services;
import cart.CartItem;
import interfaces.Shippable;
import java.util.ArrayList;

public class ShippingService implements Shippable {
  ArrayList<CartItem> cartItems;

  public ShippingService(ArrayList<CartItem> cartItems) {
      this.cartItems = cartItems;
  }

    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(ArrayList<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getWeight() {
      double totalWeight=0;
        for(CartItem item : cartItems) {
            totalWeight+= ((Shippable)item.getProduct()).getWeight()* item.getQuantity();
        }
        return totalWeight;
    }

    public String printShippingInfo() {
        StringBuilder shippingInfo = new StringBuilder("** Shipment Notice **\n");

        for (CartItem item : cartItems) {
            if (item.getProduct() instanceof Shippable) {
                double weight = ((Shippable) item.getProduct()).getWeight();
                String line = String.format("x%-2d %-10s %8.1fg\n",
                        item.getQuantity(),
                        item.getProduct().getName(),
                        weight * item.getQuantity()
                );
                shippingInfo.append(line);
            }
        }

        shippingInfo.append(String.format("Total package weight %,.1fkg\n", getWeight() / 1000.0));
        return shippingInfo.toString();
    }


    public double getShippableItemFee() {
      double shippableItemFee=0;
      for(CartItem item : cartItems) {
          double weight=((Shippable)item.getProduct()).getWeight();
          shippableItemFee+=weight*item.getQuantity()*0.5;
      }
      return shippableItemFee;
     }


}
