package io.github.gaonbarrier.easykiosk.tablet.Cart;

import java.util.ArrayList;

public class Cart {
    private ArrayList<Select> cartList;
    private int Amount;

    public Cart(){
        cartList = new ArrayList<Select>();
    }

    public ArrayList<Select> getCartList() { return cartList; }
    public void setCartList(ArrayList<Select> cartList){ this.cartList = cartList; }
}