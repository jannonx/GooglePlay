package jannonx.com.googleplay.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;

import com.astuetz.PagerSlidingTabStripExtends;

import butterknife.BindView;
import butterknife.ButterKnife;
import jannonx.com.googleplay.R;
import jannonx.com.googleplay.factory.FragmentFactory;
import jannonx.com.googleplay.utils.UIUtils;

import static jannonx.com.googleplay.R.id.main_tabs;

public class MainActivity extends FragmentActivity {


    @BindView(main_tabs)
    PagerSlidingTabStripExtends mMainTabs;
    @BindView(R.id.main_vp)
    ViewPager mMainVp;
    @BindView(R.id.activity_main)
    RelativeLayout mActivityMain;
    private String[] mStringArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
        initData();
        initEvent();

    }


    private void initEvent() {


    }

    private void initData() {
        //数据
        mStringArr = UIUtils.getStringArr(R.array.main_titls);

        // Initialize the ViewPager and set an adapter
        mMainVp.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager()));

        // Bind the tabs to the ViewPager
        mMainTabs.setViewPager(mMainVp);


    }

    private void initView() {


    }

    private class MainFragmentPagerAdapter extends FragmentPagerAdapter {

        public MainFragmentPagerAdapter(FragmentManager fm) {

            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return FragmentFactory.createFragemnt(position);
        }

        @Override
        public int getCount() {
            if (mStringArr != null) {
                return mStringArr.length;
            }
            return 0;
        }

        //这个要复写
        @Override
        public CharSequence getPageTitle(int position) {
            return mStringArr[position].toString();
        }
    }
}
