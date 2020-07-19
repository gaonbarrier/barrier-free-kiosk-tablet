package io.github.gaonbarrier.easykiosk.tablet.item;
import android.media.Image;

import java.util.*;

public class Element {
    private ArrayList<ingredient> combi = new ArrayList<ingredient>();
    private Iterator<ingredient> itr = combi.iterator();

    public Element(String itemName, int hotPrice, int coldPrice){
        item Item = new item();
        //ingredient Ingredient = new ingredient();
        Item.setItemName(itemName);
        Item.setHotPrice(hotPrice);
        Item.setColdPrice(coldPrice);
    }
    public ArrayList<ingredient> getCombi(){
        return combi;
    }
    public void setCombi(ArrayList<ingredient> combi) {
        this.combi = combi;
    }
    public void inputIngredient(ArrayList<ingredient> combi, ingredient element){
        combi.add(element);
    }
}
