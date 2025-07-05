package examples;

import cart.Cart;
import cart.CartItem;
import customer.Customer;

import java.time.LocalDate;

public class Example {


    public static void main(String[] args) {

        Customer customer = new Customer("Mariam",20000);
        Cheese cheese= new Cheese("cheese",100,7,200,  LocalDate.of(2025,7,5));
        TV tv= new TV("LG",10000,2,500);
        CartItem cartItem= new CartItem(cheese,2);
        CartItem cartItem1= new CartItem(tv,1);
        CartItem cartItem2= new CartItem(cheese,2);
        Cart cart= new Cart();
        cart.addToCart(cartItem);
        cart.addToCart(cartItem2);
        cart.addToCart(cartItem1);

        System.out.println("Example 1:");
        System.out.println();
        cart.checkout(customer);

        System.out.println();
        System.out.println("Example 2:");
        System.out.println();

        Cheese roomy= new Cheese("roomy",100,7,200,  LocalDate.of(2025,1,5));
        CartItem cartItem3= new CartItem(roomy,2);
        Cart order2= new Cart();
        order2.addToCart(cartItem3);
        order2.checkout(customer);

        System.out.println();
        System.out.println("Example 3:");
        System.out.println();

        Cheese gouda= new Cheese("gouda",100,7,150,  LocalDate.of(2025,8,5));
        CartItem cartItem4= new CartItem(gouda,12);
        Cart order3= new Cart();
        order3.addToCart(cartItem4);
        order3.checkout(customer);

        System.out.println();
        System.out.println("Example 4:");
        System.out.println();

        CartItem cartItem5= new CartItem(cheese,4);
        Cheese kiri= new Cheese("kiri",100,7,150,  LocalDate.of(2025,8,5));
        CartItem cartItem6= new CartItem(kiri,2);
        Cart order4= new Cart();
        order4.addToCart(cartItem5);
        order4.addToCart(cartItem6);
        order4.checkout(customer);

        System.out.println();
        System.out.println("Example 5:");
        System.out.println();


        CartItem cartItem7= new CartItem(tv,1);
        Cart order5= new Cart();
        order5.addToCart(cartItem7);
        order5.checkout(customer);


    }
}
