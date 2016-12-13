package jannonx.com.googleplay.fragment;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.v4.app.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import jannonx.com.googleplay.base.BaseFragment;
import jannonx.com.googleplay.base.LoadingPager;
import jannonx.com.googleplay.protocol.RecommendProtocol;
import jannonx.com.googleplay.utils.UIUtils;
import jannonx.com.googleplay.view.flyinout.ShakeListener;
import jannonx.com.googleplay.view.flyinout.StellarMap;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/1-下午7:11
 * @描述信息 推荐页面
 */

public class RecommandFragment extends BaseFragment {

    private static final int PAGERCOUNT = 15;
    private List<String> mData;
    private ShakeListener mShakeListener;

    /**
     * @desc 正在开始加载数据，在子线程中执行
     * @call 外界调用LoadingPager的triggerLoadDate()的时候
     */

    @Override
    protected View initSuccessView() {
        final StellarMap stellarMap = new StellarMap(UIUtils.getContext());
        final RecommandAdapter adapter = new RecommandAdapter();
        stellarMap.setAdapter(adapter);

        //设置拆分规则
        stellarMap.setRegularity(15, 20);
        //设置默认的页面
        stellarMap.setGroup(0, true);

        //设置摇一摇监听
        mShakeListener = new ShakeListener(UIUtils.getContext());
        mShakeListener.setOnShakeListener(new ShakeListener.OnShakeListener() {
            @Override
            public void onShake() {
                int currentGroup = stellarMap.getCurrentGroup();
                if (currentGroup == adapter.getGroupCount() - 1) {
                    currentGroup = 0;
                } else {
                    currentGroup++;
                }

                stellarMap.setGroup(currentGroup, true);
            }
        });
        return stellarMap;
    }


    @Override
    public void onPause() {
        if (mShakeListener != null) {
            mShakeListener.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        if (mShakeListener != null) {
            mShakeListener.pause();
        }
        super.onResume();
    }

    /**
     * @desc 正在开始加载数据，在子线程中执行
     * @call 外界调用LoadingPager的triggerLoadDate()的时候
     */

    @Override
    protected LoadingPager.LoadedResult initData() {
        RecommendProtocol protocol = new RecommendProtocol();
        try {
            mData = protocol.getLoadData(0);
            return checkState(mData);
        } catch (Exception e) {
            e.printStackTrace();
            return LoadingPager.LoadedResult.ERROR;
        }
    }

    private class RecommandAdapter implements StellarMap.Adapter {
        @Override
        public int getGroupCount() {

            if (mData.size() % PAGERCOUNT != 0) {//是否有余
                return mData.size() % PAGERCOUNT + 1;
            }

            return mData.size() % PAGERCOUNT;
        }

        @Override
        public int getCount(int group) {
            //最后一页
            if (group == getGroupCount() - 1) {
                if (mData.size() % PAGERCOUNT != 0) {//是否有余
                    return mData.size() % PAGERCOUNT;
                }
            }
            return PAGERCOUNT;
        }

        @Override
        public View getView(int group, int position, View convertView) {
            TextView textView = new TextView(UIUtils.getContext());
            //获取文字所在的位置
            int location = group * PAGERCOUNT + position;
            final String text = mData.get(location);
            textView.setText(text);

            //设置内间距
            int padding = UIUtils.dip2Px(4);
            textView.setPadding(padding, padding, padding, padding);

            Random random = new Random();
            //字体颜色
            int alpha = 255;
            int blue = random.nextInt(190) + 30;//30~220
            int red = random.nextInt(190) + 30;//30~220
            int green = random.nextInt(190) + 30;//30~220
            int color = Color.argb(alpha, red, green, blue);
            textView.setTextColor(color);

            //字体大小
            float textSize = random.nextInt(17) + 12;//12~30
            textView.setTextSize(textSize);

            //设置点击事件
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(UIUtils.getContext(), text, Toast.LENGTH_SHORT).show();
                }
            });

            return textView;
        }

        @Override
        public int getNextGroupOnPan(int group, float degree) {
            return 0;
        }

        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            return 0;
        }
    }
}