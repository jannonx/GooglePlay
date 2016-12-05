package jannonx.com.googleplay.holder;

import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import jannonx.com.googleplay.R;
import jannonx.com.googleplay.base.BaseHolder;
import jannonx.com.googleplay.bean.AppInfoBean;
import jannonx.com.googleplay.conf.Constants;
import jannonx.com.googleplay.utils.BitmapUtilsHelper;
import jannonx.com.googleplay.utils.StringUtils;
import jannonx.com.googleplay.utils.UIUtils;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/3-下午6:37
 * @描述信息 1.提供视图 2.接收数据 3. 数据和视图的绑定
 */

public class HomeHolder extends BaseHolder<AppInfoBean> {
    @ViewInject(R.id.item_appinfo_tv_title)//应用名称
            TextView mTitle;
    @ViewInject(R.id.item_appinfo_iv_icon)//应用图标
            ImageView mIcon;
    @ViewInject(R.id.item_appinfo_tv_des)//应用描述
            TextView mDes;
    @ViewInject(R.id.item_appinfo_tv_size)//应用大小
            TextView mSize;
    @ViewInject(R.id.item_appinfo_rb_stars)//应用评分
            RatingBar mStars;

    @Override
    protected View initView() {
        // 1.加载根布局
        View rootView = View.inflate(UIUtils.getContext(), R.layout.item_home_info, null);
        // 2.记载个布局的孩子
        ViewUtils.inject(this, rootView);

        return rootView;
    }


    /**
     * 数据和视图的绑定
     */
    @Override
    protected void bindDateAndView(AppInfoBean data) {
        mTitle.setText(data.name);

        //防止多次创建，耗费资源
//        BitmapUtils bitmapUtils=new BitmapUtils(UIUtils.getContext());
//        bitmapUtils.display(mIcon, Constants.URLS.IMAGEURL+data.iconUrl);
//        BitmapUtilsHelper.display(mIcon, Constants.URLS.IMAGEURL + data.iconUrl);

        Glide.with(UIUtils.getContext()).load(Constants.URLS.IMAGEURL + data.iconUrl).centerCrop().into(mIcon);

        mDes.setText(data.des);
        //long---mb
        mSize.setText(StringUtils.formatFileSize(data.size));
        mStars.setRating(data.stars);


    }
}
