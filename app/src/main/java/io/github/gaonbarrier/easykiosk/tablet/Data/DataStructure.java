package io.github.gaonbarrier.easykiosk.tablet.Data;

import android.content.Context;
import android.database.Cursor;
import io.github.gaonbarrier.easykiosk.tablet.MainActivity;

import java.util.ArrayList;


public class DataStructure {

    private ArrayList<Category> Category;
    public DataStructure(){
        Category = new ArrayList<Category>();
        createCategory();
    }
    public void createCategory(){
        ArrayList<String> CategoryName = new ArrayList<>();
        Cursor c = MainActivity.Receiver.getItemDBOpenHelper().selectColumns();
        while(c.moveToNext()){
            if(!CategoryName.contains(c.getString(2))) {
                CategoryName.add(c.getString(2));
                for(String data : CategoryName){
                    System.out.println(data);
                }
            }
        }
        for(String data : CategoryName){
            Category temp = new Category(data, MainActivity.Receiver.getItemDBOpenHelper().selectCategory(data));
            for(item item : temp.getItems()){
                ArrayList<Element> tmp = new ArrayList<>();
                tmp.add(new Element(item.getName(),"123"));
                c = MainActivity.Receiver.getIngredientDBOpenHelper().selectColumns();
                while(c.moveToNext()){
                    if(item.getName().equals(c.getString(1))){
                        Element element = new Element(c.getString(2),c.getString(3));
                        tmp.add(element);
                    }
                }
                item.setElements(tmp);
            }
            Category.add(temp);
        }
    }
    public void insert(){

    }
    public ArrayList<Category> getCategory() { return Category; }
    public void setCategory(ArrayList<Category> category) { Category = category; }
}
