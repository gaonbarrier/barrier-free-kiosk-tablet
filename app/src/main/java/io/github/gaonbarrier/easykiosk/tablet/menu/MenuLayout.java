package io.github.gaonbarrier.easykiosk.tablet.menu;

import android.database.Cursor;
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
    public MenuLayout(){
        Category = new ArrayList<Category>();
        ArrayList<String> CategoryName = new ArrayList<>();
        Cursor c = MainActivity.Receiver.getItemDBOpenHelper().selectColumns();
        while(c.moveToNext()){
             if(!CategoryName.contains(c.getString(2))) CategoryName.add(c.getString(2));
        }
        //O(n*K), k = 카테고리 갯수 -> 상수취급
        c.moveToFirst();
        Iterator iterator = CategoryName.iterator();
        //어떤 병신들이 있는지 찾았으니 이제 짱박아둬야지?
        while(iterator.hasNext()) {
            Category temp = new Category((String) iterator.next(), MainActivity.Receiver.getItemDBOpenHelper().selectCategory((String) iterator.next()));
        }
        //O(n*k)
        //효율 지리구요
    }

}
