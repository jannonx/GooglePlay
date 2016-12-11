package jannonx.com.googleplay.fragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;


import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import jannonx.com.googleplay.R;
import jannonx.com.googleplay.base.BaseFragment;
import jannonx.com.googleplay.base.LoadingPager;
import jannonx.com.googleplay.protocol.HotProtocol;
import jannonx.com.googleplay.utils.UIUtils;
import jannonx.com.googleplay.view.FlowLayout;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/1-下午7:11
 * @描述信息 desc
 */

public class HotFragment extends BaseFragment {

    private List<String> mData;

    /**
     * @desc 正在开始加载数据，在子线程中执行
     * @call 外界调用LoadingPager的triggerLoadDate()的时候
     */

    @Override
    protected View initSuccessView() {
        ScrollView scrollingView = new ScrollView(UIUtils.getContext());
        FlowLayout flowLayout = new FlowLayout(UIUtils.getContext());
        flowLayout.setPadding(5, 5, 5, 0);
        for (String info : mData) {
            TextView tv = new TextView(UIUtils.getContext());
            tv.setGravity(Gravity.CENTER);//居中
            tv.setTextColor(Color.WHITE);//文字白色
            int padding = UIUtils.dip2Px(5);
            tv.setPadding(padding, padding, padding, padding);//内边距
            tv.setText(info);//文字内容

            //设置背景
            tv.setBackgroundResource(R.drawable.shape_hot_bg);

            GradientDrawable gradientDrawabel = new GradientDrawable();
            gradientDrawabel.setCornerRadius(10);//圆角
            int alpha = 255;//不透明
            Random rand = new Random();

            int red = rand.nextInt(170) + 30;//30~200
            int green = rand.nextInt(170) + 30;//30~200
            int blue = rand.nextInt(170) + 30;//30~200
            int argb = Color.argb(alpha, red, green, blue);
            gradientDrawabel.setColor(argb);//背景颜色
            tv.setBackground(gradientDrawabel);

            final String infos = info;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(UIUtils.getContext(), infos, Toast.LENGTH_SHORT).show();
                }
            });

            flowLayout.addView(tv);
        }

        scrollingView.addView(flowLayout);


        return scrollingView;
    }

    /**
     * @desc 正在开始加载数据，在子线程中执行
     * @call 外界调用LoadingPager的triggerLoadDate()的时候
     */

    @Override
    protected LoadingPager.LoadedResult initData() {
        HotProtocol protocol = new HotProtocol();
        try {
            mData = protocol.getLoadData(0);
            return checkState(mData);
        } catch (Exception e) {
            e.printStackTrace();
            return LoadingPager.LoadedResult.ERROR;
        }
    }
}