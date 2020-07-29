package io.github.gaonbarrier.easykiosk.tablet.cart;

import java.util.ArrayList;

public class Cart {
    private ArrayList<Select> cartList = new ArrayList<Select>();
    private int Amount;

    public ArrayList<Select> getCartList() { return cartList; }
    public void setCartList(ArrayList<Select> cartList){ this.cartList = cartList; }
}