package io.github.gaonbarrier.easykiosk.tablet.normal;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import io.github.gaonbarrier.easykiosk.tablet.MAKERS.BOARD;


public class ContentsPagerAdapter extends FragmentStatePagerAdapter {
    private int mPageCount;

    public ContentsPagerAdapter(@NonNull FragmentManager fm, int mPageCount) {
        super(fm);
        this.mPageCount = mPageCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        //현재 이곳은 분기하는 자리입니다. for같은 반복문이 올 자리가 아닙니다.
        //보드라고 적힌 거 ()안에 제품이 얼마나 들어갈지를 결정함.
        switch (position) {
            case 0:
                BOARD a = new BOARD(1);//이 안에 적히 숫자는 제품의 수량을 나타냄.
                return a;
            case 1:
                BOARD b = new BOARD(4);
                return b;
            case 6:
                BOARD c = new BOARD(3);
                return c;
            default:
                BOARD board = new BOARD(2); //화면은 불러와짐.
                return board;
        }
    }

    @Override
    public int getCount() {
        return mPageCount;
    }
}
