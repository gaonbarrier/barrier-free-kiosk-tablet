package io.github.gaonbarrier.easykiosk.tablet.item;

import java.util.ArrayList;

class Category{
    private String CategoryName;
    private ArrayList<item> items;

    public String getCategoryName() { return CategoryName; }
    public void setCategoryName(String categoryName) { CategoryName = categoryName; }
    public ArrayList<item> getItems() { return items; }
    public void setItems(ArrayList<item> items) { this.items = items; }
}

public class menu {//얘의 목적?
    //귀찮은 작업을 하기 싫어서?
    //Menu 레이아웃을 여기다 구현해서 갓다 붙히는게 좋지 않을까???
    //모듈화 ㄱ
    /*
    * 어떻게 해 볼까
    *
    * ArrayList Category 내에 coffee, Tea 등등의 카테고리가 있고
    * coffee, Tea 각각이 각 Menu ArrayList 를 만드는게 좋을까?
    * 졸라 ㅈ같네...아니야 서버보단 덜 ㅈ같아
    * */
    private ArrayList<Category> Category;
    

}
