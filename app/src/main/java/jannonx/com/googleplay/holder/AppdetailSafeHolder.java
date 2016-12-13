package jannonx.com.googleplay.holder;


import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;

import java.util.List;

import jannonx.com.googleplay.R;
import jannonx.com.googleplay.base.BaseHolder;
import jannonx.com.googleplay.bean.AppInfoBean;
import jannonx.com.googleplay.conf.Constants;
import jannonx.com.googleplay.utils.BitmapUtilsHelper;
import jannonx.com.googleplay.utils.UIUtils;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/12-下午11:33
 * @描述信息 desc
 */
public class AppdetailSafeHolder extends BaseHolder<AppInfoBean> implements View.OnClickListener {
    @ViewInject(R.id.app_detail_safe_iv_arrow)
    ImageView mAppDetailSafeIvArrow;
    @ViewInject(R.id.app_detail_safe_pic_container)
    LinearLayout mAppDetailSafePicContainer;//安全，无广告
    @ViewInject(R.id.app_detail_safe_des_container)
    LinearLayout mAppDetailSafeDesContainer;//描述信息

    private boolean isDesOpen = true;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_detail_safe, null);
        ViewUtils.inject(this, view);
        view.setOnClickListener(this);
        return view;
    }

    @Override
    protected void bindDateAndView(AppInfoBean data) {
        List<AppInfoBean.AppSafeInfo> safeInfos = data.safe;
        for (int i = 0; i < safeInfos.size(); i++) {
            AppInfoBean.AppSafeInfo appSafeInfo = safeInfos.get(i);

            //上面部分，一个图标
            ImageView safeIcon = new ImageView(UIUtils.getContext());
            BitmapUtilsHelper.display(safeIcon, Constants.URLS.IMAGEURL + appSafeInfo.safeUrl);
            mAppDetailSafePicContainer.addView(safeIcon);

            //下面部分，一个图标，一段文字
            LinearLayout linearLayout = new LinearLayout(UIUtils.getContext());
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            int padding = UIUtils.dip2Px(5);
            linearLayout.setPadding(padding, 0, 0, 0);
            ImageView safeDes = new ImageView(UIUtils.getContext());
            TextView textView = new TextView(UIUtils.getContext());

            BitmapUtilsHelper.display(safeDes, Constants.URLS.IMAGEURL + appSafeInfo.safeDesUrl);
            textView.setText(appSafeInfo.safeDes);
            //文字颜色
            String safeDesColor = appSafeInfo.safeDesColor;
            if (Integer.parseInt(safeDesColor) == 0) {
                textView.setTextColor(Color.GREEN);
            } else {
                textView.setTextColor(Color.YELLOW);
            }
            linearLayout.addView(safeDes);
            linearLayout.addView(textView);
            mAppDetailSafeDesContainer.addView(linearLayout);
        }

        //初始化动画，mAppDetailSafeDesContainer隐藏
        doAnimation(false);

    }

    @Override
    public void onClick(View v) {
        doAnimation(true);

    }

    private void doAnimation(boolean isAnimation) {
        if (isDesOpen) {

            mAppDetailSafeDesContainer.measure(0, 0);
            int measuredHeight = mAppDetailSafeDesContainer.getMeasuredHeight();

            int start = measuredHeight;
            int end = 0;

            if (isAnimation) {
                startAnimation(start, end);
            } else {
                startAnimation(0, 0);
            }

        } else {
            mAppDetailSafeDesContainer.measure(0, 0);
            int measuredHeight = mAppDetailSafeDesContainer.getMeasuredHeight();

            int start = 0;
            int end = measuredHeight;

//            valueAnimator.setDuration(300); //默认时长的300ms
            if (isAnimation) {
                startAnimation(start, end);
            } else {
                startAnimation(0, 0);
            }
        }

        if (isAnimation) {
            if (isDesOpen) {
                ObjectAnimator.ofFloat(mAppDetailSafeIvArrow, "rotation", 180, 0).start();
            } else {
                ObjectAnimator.ofFloat(mAppDetailSafeIvArrow, "rotation", 0, 180).start();
            }
        }


        isDesOpen = !isDesOpen;
    }

    /**
     * 开始动画
     *
     * @param start
     * @param end
     */
    private void startAnimation(int start, int end) {
        //描述部分
//        ValueAnimator valueAnimator = new ValueAnimator();
        ValueAnimator valueAnimator = ValueAnimator.ofInt(start, end);

        valueAnimator.start();

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //得到渐变的值
                int height = (int) valueAnimator.getAnimatedValue();
                //得到params
                ViewGroup.LayoutParams params = mAppDetailSafeDesContainer.getLayoutParams();
                //赋值params
                params.height = height;
                //设置params
                mAppDetailSafeDesContainer.setLayoutParams(params);
            }
        });

    }


}