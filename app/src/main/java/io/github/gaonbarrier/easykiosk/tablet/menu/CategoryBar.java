package io.github.gaonbarrier.easykiosk.tablet.menu;

import android.content.Context;
import android.util.Log;
import com.google.android.material.tabs.TabLayout;
import io.github.gaonbarrier.easykiosk.tablet.R;
import io.github.gaonbarrier.easykiosk.tablet.normal.NormalActivity;

import java.util.ArrayList;

public class CategoryBar{
    //context는 정보? 출신?같은 느낌
    Context mContext; //mContext라는 Context 객체를 생성.
    private getData getData; //getData라는 클래스를 호출.

    public CategoryBar(Context context) {
        getData = new getData(); //생성자 호출.
        this.mContext = context; //
        TabLayout tabLayout = (TabLayout)((NormalActivity)mContext).findViewById(R.id.tabLay);
        // 탭레이아웃 선언
        for(Category category : getData.getCategory()){  //0~category까지?
            //Log.d("categoty", "Data: " + category.getCategoryName());
            TabLayout.Tab tab = tabLayout.newTab(); //탭 생성
            tab.setText(category.getCategoryName()); //탭 이름을 카테고리 이름으로 설정
            tabLayout.addTab(tab); //생성한 탭을 추가

            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }

            });
        }
    }
}
