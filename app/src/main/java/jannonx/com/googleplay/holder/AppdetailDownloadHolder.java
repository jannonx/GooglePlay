package jannonx.com.googleplay.holder;

import android.view.View;
import android.widget.TextView;

import jannonx.com.googleplay.base.BaseHolder;
import jannonx.com.googleplay.bean.AppInfoBean;
import jannonx.com.googleplay.utils.UIUtils;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/12-下午11:32
 * @描述信息 desc
 */
public class AppdetailDownloadHolder extends BaseHolder<AppInfoBean> {
    @Override
    protected View initView() {
        TextView textView = new TextView(UIUtils.getContext());
        textView.setText(this.getClass().getSimpleName());
        return textView;
    }

    @Override
    protected void bindDateAndView(AppInfoBean data) {

    }
}