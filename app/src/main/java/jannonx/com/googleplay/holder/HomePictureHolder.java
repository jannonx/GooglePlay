package jannonx.com.googleplay.holder;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;

import jannonx.com.googleplay.R;
import jannonx.com.googleplay.base.BaseHolder;
import jannonx.com.googleplay.conf.Constants;
import jannonx.com.googleplay.utils.BitmapUtilsHelper;
import jannonx.com.googleplay.utils.UIUtils;
import jannonx.com.googleplay.view.InnerViewPager;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/7-下午8:37
 * @描述信息 desc
 */

public class HomePictureHolder extends BaseHolder<List<String>> {
    @ViewInject(R.id.item_home_picture_pager)
    InnerViewPager mViewPager;
    @ViewInject(R.id.item_home_picture_container_indicator)
    LinearLayout mLinearLayout;
    private List<String> mData;


    @Override
    protected View initView() {
        View rootView = View.inflate(UIUtils.getContext(), R.layout.item_home_pictures, null);
        ViewUtils.inject(this, rootView);

        return rootView;
    }

    @Override
    protected void bindDateAndView(List<String> data) {
        mData = data;

        mViewPager.setAdapter(new PicAdapter());

        //现有mData数据
        for (int i = 0; i < mData.size(); i++) {
            ImageView iv = new ImageView(UIUtils.getContext());

            int dec = UIUtils.dip2Px(5);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dec, dec);
            lp.leftMargin = dec;
            lp.bottomMargin = dec;
            iv.setLayoutParams(lp);
            iv.setImageResource(R.drawable.indicator_normal);
            //添加圆点
            mLinearLayout.addView(iv);
        }


        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                position = position % mData.size();

                //还原效果
                for (int i = 0; i < mData.size(); i++) {
                    ImageView childAt = (ImageView) mLinearLayout.getChildAt(i);
                    childAt.setImageResource(R.drawable.indicator_normal);
                }
                //选中状态
                ImageView childAt = (ImageView) mLinearLayout.getChildAt(position);
                childAt.setImageResource(R.drawable.indicator_selected);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //设置当前选中的Index
        int diff = Integer.MAX_VALUE / 2 % mData.size();
        mViewPager.setCurrentItem(Integer.MAX_VALUE / 2 - diff);

        final ScrollRunnable runnable = new ScrollRunnable();
        runnable.start();

        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        runnable.stop();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        runnable.start();
                        break;

                }
                return false;
            }
        });
    }


    class ScrollRunnable implements Runnable {
        public void start() {
            UIUtils.getHandler().postDelayed(this, 2000);
        }

        public void stop() {
            UIUtils.getHandler().removeCallbacks(this);
        }

        @Override
        public void run() {
            int currentItem = mViewPager.getCurrentItem();
            currentItem++;
            mViewPager.setCurrentItem(currentItem);
            start();
        }
    }


    class PicAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            if (mData != null) {
                return Integer.MAX_VALUE;//无限轮播
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            position = position % mData.size();

            ImageView imageView = new ImageView(UIUtils.getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            Glide.with(UIUtils.getContext()).load(mData.get(position)).into(imageView);
            BitmapUtilsHelper.display(imageView, Constants.URLS.BASEURL + mData.get(position));

            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
