package io.github.gaonbarrier.easykiosk.tablet.item;

import android.media.Image;

import java.util.ArrayList;

public class item {
    private String name;
    private String Category;
    private int HotPrice;
    private int ColdPrice;
    private ArrayList<Element> item;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCategory() { return Category; }

    public void setCategory(String category) { Category = category; }

    public int getHotPrice() { return HotPrice; }

    public void setHotPrice(int hotPrice) { HotPrice = hotPrice; }

    public int getColdPrice() { return ColdPrice; }

    public void setColdPrice(int coldPrice) { ColdPrice = coldPrice; }

}
