package jannonx.com.googleplay.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import jannonx.com.googleplay.R;
import jannonx.com.googleplay.base.BaseHolder;
import jannonx.com.googleplay.bean.SubjectInfoBean;
import jannonx.com.googleplay.conf.Constants;
import jannonx.com.googleplay.utils.BitmapUtilsHelper;
import jannonx.com.googleplay.utils.UIUtils;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/8-下午2:46
 * @描述信息 desc
 */

public class SubjectHolder extends BaseHolder<SubjectInfoBean> {
    @ViewInject(R.id.item_subject_iv_icon)
    ImageView mIvIcon;
    @ViewInject(R.id.item_subject_tv_title)
    TextView mTvTitle;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_subject, null);
        ViewUtils.inject(this, view);

        return view;
    }

    @Override
    protected void bindDateAndView(SubjectInfoBean data) {
        BitmapUtilsHelper.display(mIvIcon, Constants.URLS.IMAGEURL + data.url);
        mTvTitle.setText(data.des);

    }
}
