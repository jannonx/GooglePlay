package jannonx.com.googleplay.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import com.astuetz.PagerSlidingTabStrip;
import com.astuetz.PagerSlidingTabStripExtends;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import jannonx.com.googleplay.R;
import jannonx.com.googleplay.base.BaseFragment;
import jannonx.com.googleplay.base.LoadingPager;
import jannonx.com.googleplay.factory.FragmentFactory;
import jannonx.com.googleplay.utils.UIUtils;

import static jannonx.com.googleplay.R.id.main_tabs;

public class MainActivity extends FragmentActivity {


    @BindView(main_tabs)
    PagerSlidingTabStripExtends mMainTabs;
    @BindView(R.id.main_vp)
    ViewPager mMainVp;
    private String[] mStringArr;
    private MyOnPageChangeListener mMyOnPageChangeListener = new MyOnPageChangeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
        initData();
        initEvent();
        initListener();

    }

    private void initListener() {
        mMainTabs.setOnPageChangeListener(mMyOnPageChangeListener);

        //默认选中第一个页面home
        mMainVp.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //手动触发onPageSelected方法
                mMyOnPageChangeListener.onPageSelected(0);
                //仅仅手动触发一次
                mMainVp.getViewTreeObserver().removeGlobalOnLayoutListener(this);

            }
        });
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

    //LoadingPager的triggerLoadDate()
    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //开始加载的时候，触发LoadingPager的triggerLoadDate()
            //此时的Fragment肯定是已经创建过得fragment了
            BaseFragment baseFragment = FragmentFactory.createFragemnt(position);
            if (baseFragment != null) {
                LoadingPager loadingPager = baseFragment.getLoadingPager();
                if (loadingPager != null) {
                    loadingPager.triggerLoadDate();
                }
            }


        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

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
