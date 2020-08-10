package io.github.gaonbarrier.easykiosk.tablet.normal;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import io.github.gaonbarrier.easykiosk.tablet.MAKERS.BOARD;
import io.github.gaonbarrier.easykiosk.tablet.menu.Category;

import java.util.ArrayList;


public class ContentsPagerAdapter extends FragmentStatePagerAdapter {
    private int mPageCount;
    private ArrayList<Category> category;

    public ContentsPagerAdapter(@NonNull FragmentManager fm, int mPageCount, ArrayList<Category> category) {
        super(fm);
        this.mPageCount = mPageCount;
        this.category = category;
    }

    @NonNull
    @Override
    public Fragment getItem(Category Category, int Size) {
       BOARD a = new BOARD(Category ,Size);

        // 이 아재들 중에 position의 목적이 도데체 뭘까?
    }
    //패러미터 뭘로할까
    /*@Override
    public Fragment getItem(String CategoryName, int size) {

         String CategoryName
         int size

        BOARD a = new BOARD(CategoryName,size);
        return a;
    }*/

    @Override
    public int getCount() {
        return mPageCount;
    }
}
