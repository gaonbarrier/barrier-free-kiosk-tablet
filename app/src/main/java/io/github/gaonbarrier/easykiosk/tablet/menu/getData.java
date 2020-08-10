package io.github.gaonbarrier.easykiosk.tablet.menu;

import android.database.Cursor;
import io.github.gaonbarrier.easykiosk.tablet.db.itemDBOpenHelper;
import io.github.gaonbarrier.easykiosk.tablet.MainActivity;
import io.github.gaonbarrier.easykiosk.tablet.network.Receiver;

import java.util.ArrayList;
import java.util.Iterator;


public class getData {//얘의 목적?

    private ArrayList<Category> Category;
    public getData(){
        Category = new ArrayList<Category>();
        //커피, 차, 아이스크림 같은 카테고리
        createCategory();

    }

    public ArrayList<io.github.gaonbarrier.easykiosk.tablet.menu.Category> getCategory() {
        return Category;
    }

    public void setCategory(ArrayList<io.github.gaonbarrier.easykiosk.tablet.menu.Category> category) {
        Category = category;
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
        //커서안에 짱박혀있는 Item 데이터베이스 아재들을 다 가져와서 카테고리 이름이 어떤 것이 있고 몇개가 있는지 알아내고 뽑아낸다
        //O(n*K), k = 카테고리 갯수 -> 상수취급

        //어떤 아재들이 있는지 알았으니 그 아재들마다 각각 item들 정보, 각 item마다 element 정보를 받아야한다.

        //커서에 이제 ingredient 아재들을 짱박아둔다.

        for(String data : CategoryName){
            Category temp = new Category(data, MainActivity.Receiver.getItemDBOpenHelper().selectCategory(data));
            System.out.println("temp 아재 카테고리 : " + temp.getCategoryName());
            System.out.println("temp 아재 안에 있는 거 : ");

            for(item item : temp.getItems()){
                System.out.println(item.getName() + " , " + item.getCategory() + " , " + item.getHotPrice() + " , " + item.getColdPrice());
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
                for(Element Element : item.getElements()){
                    System.out.println(item.getName() + "의 Element 정보 ");
                    System.out.println(Element.getName() + " , " + Element.getImage());
                }
            }
            Category.add(temp);
        }

    }
    public void insert(){

    }

}
