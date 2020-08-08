package io.github.gaonbarrier.easykiosk.tablet.menu;

import android.database.Cursor;
import android.util.Log;
import io.github.gaonbarrier.easykiosk.tablet.db.itemDBOpenHelper;
import io.github.gaonbarrier.easykiosk.tablet.MainActivity;
import io.github.gaonbarrier.easykiosk.tablet.network.Receiver;

import java.util.ArrayList;
import java.util.Iterator;


public class MenuLayout {//얘의 목적?
    //귀찮은 작업을 하기 싫어서?
    //Menu 레이아웃을 여기다 구현해서 갓다 붙히는게 좋지 않을까???
    //모듈화 ㄱ
    /*
     * 어떻게 해 볼까
     *
     * ArrayList Category 내에 coffee, Tea 등등의 카테고리가 있고
     * coffee, Tea 각각이 각 Menu ArrayList 를 만드는게 좋을까?
     * */
    private ArrayList<Category> Category;
    //
    public static String Cate_name[];

    public MenuLayout(){
        Category = new ArrayList<Category>();
        //커피, 차, 아이스크림 같은 카테고리\
        createCategory(); //전체 DB 생성. 시간 좀 걸릴거임.


        /*for(int i = 0; i<Category.size(); i++){
            Cate_name[i] = Category.get(i).getCategoryName();
        }
        for(Category category : Category){
            category.getCategoryName();
        }*/

    }
    public void createCategory(){
        ArrayList<String> CategoryName = new ArrayList<>();
        //기계 입장에서는 데베 테이블 던져줘봐야 이게 뭐지? 먹는건가 소리도 못한다. 즉 데베 테이블안에 어떤 카테고리가 있는지
        //알아 볼 필요가 있기 때문에 그 정보들을 저장하는 ArrayList를 만듬
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

    public static int getCount() { return count; }

    public static void setCount(int count) { MenuLayout.count = count; }

    public static String[] getCate_name() { return Cate_name; }

    public static void setCate_name(String[] cate_name) { Cate_name = cate_name; }
}
