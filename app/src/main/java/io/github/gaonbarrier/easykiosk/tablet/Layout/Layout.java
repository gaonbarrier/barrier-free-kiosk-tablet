package io.github.gaonbarrier.easykiosk.tablet.Layout;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import io.github.gaonbarrier.easykiosk.tablet.Data.Category;
import io.github.gaonbarrier.easykiosk.tablet.voice.ContentsPagerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Layout extends FragmentStatePagerAdapter {
    private FragmentManager f_Mgr;
    private FragmentTransaction f_transaction;

    private Context mContext;
    private Activity mActivity;

    private ContentsPagerAdapter mContentPagerAdapter;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private int mPageCount;
    private ArrayList<Category> categories;

    public Layout(Context context, Activity activity, @NonNull FragmentManager fm, int mPageCount, ArrayList<Category> category){
        super(fm);
        mContext = context;
        mActivity = activity;
        this.mPageCount = mPageCount;
        this.categories = category;
    }
    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        Board board = new Board((Activity) mContext, mActivity, categories.get(position));
        return null;
    }

    @Override
    public int getCount() { return mPageCount; }
}
