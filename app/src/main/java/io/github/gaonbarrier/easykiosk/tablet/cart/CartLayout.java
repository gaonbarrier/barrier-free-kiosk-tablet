package io.github.gaonbarrier.easykiosk.tablet.cart;

import java.util.ArrayList;

public class CartLayout {
    private ArrayList<Select> cartList;
    private int Amount;

    public CartLayout(){
        cartList = new ArrayList<Select>();
    }

    public ArrayList<Select> getCartList() { return cartList; }
    public void setCartList(ArrayList<Select> cartList){ this.cartList = cartList; }
}