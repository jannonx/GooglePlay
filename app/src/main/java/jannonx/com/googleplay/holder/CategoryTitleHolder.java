package jannonx.com.googleplay.holder;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import jannonx.com.googleplay.base.BaseHolder;
import jannonx.com.googleplay.bean.CategoryBean;
import jannonx.com.googleplay.utils.UIUtils;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/12-下午5:09
 * @描述信息 desc
 */
public class CategoryTitleHolder extends BaseHolder<CategoryBean> {

    private TextView mTextView;

    @Override
    protected View initView() {
        mTextView = new TextView(UIUtils.getContext());
        mTextView.setBackgroundColor(Color.WHITE);
        mTextView.setPadding(10, 0, 0, 0);
        return mTextView;
    }

    @Override
    protected void bindDateAndView(CategoryBean data) {
        mTextView.setText(data.title);
    }
}
