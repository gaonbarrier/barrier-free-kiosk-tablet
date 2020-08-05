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
        //커피, 차, 아이스크림 같은 카테고리
        ArrayList<String> CategoryName = new ArrayList<>();
        //기계 입장에서는 데베 테이블 던져줘봐야 이게 뭐지? 먹는건가 소리도 못한다. 즉 데베 테이블안에 어떤 카테고리가 있는지
        //알아 볼 필요가 있기 때문에 그 정보들을 저장하는 ArrayList를 만듬
        Cursor c = MainActivity.Receiver.getItemDBOpenHelper().selectColumns();
        while(c.moveToNext()){
             if(!CategoryName.contains(c.getString(2))) CategoryName.add(c.getString(2));
        }
        //커서안에 짱박혀있는 Item 데이터베이스 아재들을 다 가져와서 카테고리 이름이 어떤 것이 있고 몇개가 있는지 알아내고 뽑아낸다
        //O(n*K), k = 카테고리 갯수 -> 상수취급

        Iterator iterator = CategoryName.iterator();
        //어떤 아재들이 있는지 알았으니 그 아재들마다 각각 item들 정보, 각 item마다 element 정보를 받아야한다.
        c = MainActivity.Receiver.getIngredientDBOpenHelper().selectColumns();
        //커서에 이제 ingredient 아재들을 짱박아둔다.
        while(iterator.hasNext()) {
            Category temp = new Category((String) iterator.next(), MainActivity.Receiver.getItemDBOpenHelper().selectCategory((String) iterator.next()));
            //Category라는 한 아재를 만든다. 그 아재에게는 카테고리 이름과 그 카테고리에 있는 item들 아재들이 다 들어간다.
            Iterator iterator1 = temp.getItems().iterator();
            //item 아재들 다 불러온다. 이 아재들 각각에게 Element 정보를 줘야한다.
            while(iterator1.hasNext()) {
                while (c.moveToNext()) {
                    //일단 생각ㄷ은 item 아재 하나가 .....
                }
            }
        }
        //O(n*k)
        //효율 지리구요
    }

}
