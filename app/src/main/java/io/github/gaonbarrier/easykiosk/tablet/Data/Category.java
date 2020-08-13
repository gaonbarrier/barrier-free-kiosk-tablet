package io.github.gaonbarrier.easykiosk.tablet.Data;

import java.util.ArrayList;

public class Category{
    private String CategoryName;
    private ArrayList<item> items;

    public Category(String CategoryName, ArrayList<item> items){
        this.CategoryName = CategoryName;
        this.items = items;
    }

    public String getCategoryName() { return CategoryName; }
    public void setCategoryName(String categoryName) { CategoryName = categoryName; }
    public ArrayList<item> getItems() { return items; }
    public void setItems(ArrayList<item> items) { this.items = items; }
}