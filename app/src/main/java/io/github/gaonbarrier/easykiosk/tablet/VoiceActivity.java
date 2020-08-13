package io.github.gaonbarrier.easykiosk.tablet;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import io.github.gaonbarrier.easykiosk.tablet.cart.Select;
import io.github.gaonbarrier.easykiosk.tablet.menu.Category;
import io.github.gaonbarrier.easykiosk.tablet.menu.MenuLayout;
import io.github.gaonbarrier.easykiosk.tablet.menu.item;
import io.github.gaonbarrier.easykiosk.tablet.network.Sender;
import io.github.gaonbarrier.easykiosk.tablet.voice.*;
import java.util.ArrayList;

public class VoiceActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private ArrayList<FragTest> Frags;
    private Context mContext;
    public static Activity _VoiceActivity;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ContentsPagerAdapter mContentPagerAdapter;

    private Sender sender;

    private MenuLayout menuLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);

        _VoiceActivity = VoiceActivity.this;
        mContext = getApplicationContext();

        setRequestedOrientation((ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE));

        mTabLayout = findViewById(R.id.voice_tab);
        mViewPager = findViewById(R.id.voice_viewpager);

        menuLayout = new MenuLayout();

        sender = new Sender();
        for(Category category : menuLayout.getCategory()){
           mTabLayout.addTab(mTabLayout.newTab().setText(category.getCategoryName()));
            System.out.println(category.getCategoryName());
            for(item item : category.getItems()){
                System.out.println(item.getName());
            }
        }
        mContentPagerAdapter = new ContentsPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount(), menuLayout.getCategory());
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

        Button cartButton = (Button)findViewById((R.id.voice_cartButton));
        Button paymentButton = (Button)findViewById(R.id.voice_resultButton);
        Button cancelButton = (Button)findViewById(R.id.voice_cancelButton);

        cartButton.setText("장바구니");
        paymentButton.setText("주문완료");
        cancelButton.setText("주문취소");


        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(Select temp : MainActivity.CartLayout.getCartList()){
                    System.out.println(temp.getName() + " , " + temp.getPrice() + " , " + temp.getAmount());
                }
            }
        });

        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String json = sender.orderToJSON(MainActivity.CartLayout.getCartList());
                System.out.println(json);
                sender.sendData(json);
                MainActivity.CartLayout.getCartList().clear();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("주문취소됬음 아재");
                MainActivity.CartLayout.getCartList().clear();
            }
        });

    }
}