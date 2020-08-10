package io.github.gaonbarrier.easykiosk.tablet.normal;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.tabs.TabLayout;
import io.github.gaonbarrier.easykiosk.tablet.R;
import io.github.gaonbarrier.easykiosk.tablet.menu.Category;
import io.github.gaonbarrier.easykiosk.tablet.menu.getData;

public class NormalActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private io.github.gaonbarrier.easykiosk.tablet.menu.getData getData;

/*
    private FragTest bestFrag;
    private FragTest coffeeFrag;
    private FragTest smoothieFrag;
    private FragTest adeFrag;
    private FragTest teaFrag;
 */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nor);
        getData = new getData();
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_lay);
        for(Category c : getData.getCategory()) {
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setText(c.getCategoryName());
            tabLayout.addTab(tab);
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) { //상태가 선택할 때

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) { //탭을 선택하지 않은거

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) { //선택한 탭을 다시 선택

                }
            });
        }

        //new tabmenu(this);

        /*
        // 플래그먼트 매니저 선언
        fragmentManager = getSupportFragmentManager();
        
        // 각각의 버튼에 맞는 Frag들 선언
        bestFrag = new FragTest("하나");
        coffeeFrag = new FragTest("둘");
        smoothieFrag = new FragTest("셋");
        adeFrag = new FragTest("넷");
        teaFrag = new FragTest("다섯");
        
        // 대충 이렇게 트랜잭션 생성하고 바꿔주는듯 (기본인 bestFrag 지정)
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragTest, bestFrag).commitAllowingStateLoss();
         */
    }

    // 버튼 누르면 생기는 이벤트들
    // 버튼 확인하고 각각에 맞는 Frag들 지정
/*    public void clickHandler(View view)
    {
        transaction = fragmentManager.beginTransaction();

        switch(view.getId())
        {
            case R.id.btn_fragment1:
                transaction.replace(R.id.fragTest, bestFrag).commitAllowingStateLoss();
                break;
            case R.id.btn_fragment2:
                transaction.replace(R.id.fragTest, coffeeFrag).commitAllowingStateLoss();
                break;
            case R.id.btn_fragment3:
                transaction.replace(R.id.fragTest, smoothieFrag).commitAllowingStateLoss();
                break;
            case R.id.btn_fragment4:
                transaction.replace(R.id.fragTest, adeFrag).commitAllowingStateLoss();
                break;
            case R.id.btn_fragment5:
                transaction.replace(R.id.fragTest, teaFrag).commitAllowingStateLoss();
                break;
        }
    }
 */
}
