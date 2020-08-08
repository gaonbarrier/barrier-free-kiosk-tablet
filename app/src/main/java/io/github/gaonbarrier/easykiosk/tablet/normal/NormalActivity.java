package io.github.gaonbarrier.easykiosk.tablet.normal;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import io.github.gaonbarrier.easykiosk.tablet.HELPER.FullSize;
import io.github.gaonbarrier.easykiosk.tablet.R;
import io.github.gaonbarrier.easykiosk.tablet.menu.Category;
import io.github.gaonbarrier.easykiosk.tablet.menu.MenuLayout;

public class NormalActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private FragTest bestFrag;
    private FragTest coffeeFrag;
    private FragTest smoothieFrag;
    private FragTest adeFrag;
    private FragTest teaFrag; // 이상 Back

    private Context mContext;
    public static Activity _NormalActivity;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ContentsPagerAdapter mContentPagerAdapter;

    private  MenuLayout menuLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nor);
        //front
        _NormalActivity = NormalActivity.this;
        mContext = getApplicationContext();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //최초 실행 시 가로화면 설정

        Point size = FullSize.FullSize(_NormalActivity);

        mTabLayout = findViewById(R.id.main_tab);
        mViewPager = findViewById(R.id.main_viewpager);

        menuLayout = new MenuLayout();


        for(Category category : menuLayout.getCategory()){
            mTabLayout.addTab(mTabLayout.newTab().setText(category.getCategoryName()));
        }



        mContentPagerAdapter = new ContentsPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(mContentPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //front

        /*// 플래그먼트 매니저 선언
        fragmentManager = getSupportFragmentManager();

        // 각각의 버튼에 맞는 Frag들 선언
        bestFrag = new FragTest("하나");
        coffeeFrag = new FragTest("둘");
        smoothieFrag = new FragTest("셋");
        adeFrag = new FragTest("넷");
        teaFrag = new FragTest("다섯");*/
        /*
        // 대충 이렇게 트랜잭션 생성하고 바꿔주는듯 (기본인 bestFrag 지정)
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragTest, bestFrag).commitAllowingStateLoss();*/
    }

    // 버튼 누르면 생기는 이벤트들
    // 버튼 확인하고 각각에 맞는 Frag들 지정
    /*public void clickHandler(View view)
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
    }*/ //어차피 fragment 설정들은 모두 ContentPagerAdapter에서 해결해놨기 때문에 여기에 있는 것들은 그냥 다 지워버리겠음.
}
