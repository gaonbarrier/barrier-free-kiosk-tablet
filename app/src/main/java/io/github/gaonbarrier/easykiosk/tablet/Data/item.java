package io.github.gaonbarrier.easykiosk.tablet.Data;

import java.util.ArrayList;

public class item {
    private String name;
    private String Category;
    private int PriceHot;
    private int PriceCold;
    private ArrayList<Element> elements;
    public item(String name, String Category, int PriceHot, int PriceCold){
        this.name = name;
        this.Category = Category;
        this.PriceHot = PriceHot;
        this.PriceCold = PriceCold;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return Category; }
    public void setCategory(String category) { Category = category; }
    public int getHotPrice() { return PriceHot; }
    public void setHotPrice(int hotPrice) { PriceHot = hotPrice; }
    public int getColdPrice() { return PriceCold; }
    public void setColdPrice(int coldPrice) { PriceCold = coldPrice; }
    public ArrayList<Element> getElements() { return elements; }
    public void setElements(ArrayList<Element> elements) { this.elements = elements; }

}
