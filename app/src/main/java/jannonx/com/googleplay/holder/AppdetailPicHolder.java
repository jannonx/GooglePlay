package jannonx.com.googleplay.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;

import butterknife.BindView;
import jannonx.com.googleplay.R;
import jannonx.com.googleplay.base.BaseHolder;
import jannonx.com.googleplay.bean.AppInfoBean;
import jannonx.com.googleplay.conf.Constants;
import jannonx.com.googleplay.utils.BitmapUtilsHelper;
import jannonx.com.googleplay.utils.UIUtils;
import jannonx.com.googleplay.view.RatioLayout;
import jannonx.com.googleplay.view.flyinout.RandomLayout;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/12-下午11:33
 * @描述信息 desc
 */
public class AppdetailPicHolder extends BaseHolder<AppInfoBean> {
    @ViewInject(R.id.app_detail_pic_iv_container)
    LinearLayout mAppDetailPicIvContainer;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_detail_pic, null);
        ViewUtils.inject(this, view);
        return view;
    }


    @Override
    protected void bindDateAndView(AppInfoBean data) {
        List<String> screen = data.screen;
        for (int i = 0; i < screen.size(); i++) {
            //根据分辨率，计算宽高比
            RatioLayout ratioLayout = new RatioLayout(UIUtils.getContext());
            ratioLayout.setRatio((float) 150 / 250);
            /**根据宽度动态计算高度*/
            ratioLayout.setRelative(RatioLayout.RELATIVE_WIDTH);


            ImageView imageView = new ImageView(UIUtils.getContext());
            BitmapUtilsHelper.display(imageView, Constants.URLS.IMAGEURL + screen.get(i));

            /**得到屏幕的宽度*/
            int screenWidthPixels = UIUtils.getResources().getDisplayMetrics().widthPixels;
            /**每张图片是屏幕的三分之一*/
            int width = (screenWidthPixels - UIUtils.dip2Px(5) * 4) / 3;
            int height = RandomLayout.LayoutParams.WRAP_CONTENT;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
            if (i != 0) {
                params.leftMargin = UIUtils.dip2Px(5);
            }

            ratioLayout.addView(imageView);

            mAppDetailPicIvContainer.addView(ratioLayout, params);

        }

    }
}
