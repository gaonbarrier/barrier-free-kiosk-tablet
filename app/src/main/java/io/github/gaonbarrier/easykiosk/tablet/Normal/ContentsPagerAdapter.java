package io.github.gaonbarrier.easykiosk.tablet.Normal;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import io.github.gaonbarrier.easykiosk.tablet.Data.Category;
import org.jetbrains.annotations.NotNull;

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
    @NotNull
    @Override
    public Fragment getItem(int position) {
        BOARD a = new BOARD(category.get(position));
        return a;
    }
    //패러미터 뭘로할까
    /*@Override
    public Frag getItem(String CategoryName, int size) {

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
