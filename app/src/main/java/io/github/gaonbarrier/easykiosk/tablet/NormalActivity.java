package io.github.gaonbarrier.easykiosk.tablet;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import io.github.gaonbarrier.easykiosk.tablet.HELPER.FullSize;
import io.github.gaonbarrier.easykiosk.tablet.menu.Category;
import io.github.gaonbarrier.easykiosk.tablet.menu.MenuLayout;
import io.github.gaonbarrier.easykiosk.tablet.network.Sender;
import io.github.gaonbarrier.easykiosk.tablet.normal.ContentsPagerAdapter;
import io.github.gaonbarrier.easykiosk.tablet.normal.FragTest;

import java.util.ArrayList;

public class NormalActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

   /* private FragTest bestFrag;
    private FragTest coffeeFrag;
    private FragTest smoothieFrag;
    private FragTest adeFrag;
    private FragTest teaFrag; */// 이상 Back
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

    private Sender sender;
    //Garbage collection이 알아서 처리해줄 것인가? -> 차라리 Main에서 항상 상시 대기치게 만들어야 할까?

    //솔직히 맘같에선 밑에 처리들을 전부 menuLayout 아재가 다 처리햇으면 좋겠음. 언제든 바꿀 수 있게 위 아재들을 field에 좀 짱박아둘 생각.

    private MenuLayout menuLayout;


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

        sender = new Sender();
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

        mContentPagerAdapter = new ContentsPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount(), MainActivity.MenuLayout.getCategory());
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

        Button resultBotton = (Button)findViewById(R.id.main_resultButton);
        resultBotton.setText("ResultTest. 힘내쇼!");
        resultBotton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String json = sender.orderToJSON(MainActivity.CartLayout.getCartList());
                System.out.println(json);
                sender.sendData(json);
                MainActivity.CartLayout.getCartList().clear();
            }
        });
    }
}
