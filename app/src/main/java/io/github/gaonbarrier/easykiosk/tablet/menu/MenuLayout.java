package io.github.gaonbarrier.easykiosk.tablet.menu;

import java.util.ArrayList;

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

    }

}
