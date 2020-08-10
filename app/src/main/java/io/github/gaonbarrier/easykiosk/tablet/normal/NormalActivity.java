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

import java.util.ArrayList;

public class NormalActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private FragTest bestFrag;
    private FragTest coffeeFrag;
    private FragTest smoothieFrag;
    private FragTest adeFrag;
    private FragTest teaFrag; // 이상 Back
    //뭐하는 아재들인지 잘은 모르겠음. Fragment를 Extend한걸로 봐서 흠.....글쎄다

    private ArrayList<FragTest> Frags;
    //딱 보니 위에 아재들이 Static이길래 Dynamic으로 한번 바꿔보기로 함.

    private Context mContext;
    public static Activity _NormalActivity;

    private TabLayout mTabLayout;
    //이 아재는 CategoryName 불러오는 아재.
    private ViewPager mViewPager;
    //뷰페이저? 이 아재의 목적은 아직 잘 모르겠음.
    private ContentsPagerAdapter mContentPagerAdapter;
    //이 아재도 잘은 모르겠음.

    private MenuLayout menuLayout;
    //솔직히 맘같에선 밑에 처리들을 전부 menuLayout 아재가 다 처리햇으면 좋겠음. 언제든 바꿀 수 있게 위 아재들을 field에 좀 짱박아둘 생각.

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
            //Frags.add(new FragTest(category.getCategoryName()));
            //밑에 Frag아재들 Static 으로 저지랄하는거 Dynamic으로 바꾼거
        }
        //여기까지가 보면 Tab 아재들의 이름들을 가져오는 방식.

        //자 여기서 만약에 CategoryName 아재들의 순서를 정하고 싶다면? Argument를 저 아재 그대로 쓸 게 아니라
        //CategoryName의 우선순위를 적어 둔 아재를 넣는 게 맞다고 봄. -> 어떻 게 할것이냐?
        //1. 처음에 들어온 순서를 기억하게 한다.
        //2. 카테고리를 먼저 만들고 짱박아두게 만드는 방법. -> 메커니즘을 살짝 고쳐야 할지도 모르지만 불가능한건 아니다.

        mContentPagerAdapter = new ContentsPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount(), menuLayout.getCategory());
        mViewPager.setAdapter(mContentPagerAdapter);
        //뭔 아재들인진 모르겠지만 어댑터 아재를 이걸로 쓰는걸로 보아...뭔 소린지 모르겠음 ㅋㅋㅋㅋ
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //리스너 아재를 집어넣는 것 같음.
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }
            //이 아재는 현재 아이템들을 세팅해주는 아재인거같은데 글쎄 뭐지?

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
    //잘은 모르겠지만 아재들 이해 잘 안됨 ㅇㅇ 설명부탁.
}
