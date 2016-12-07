package jannonx.com.googleplay.holder;

import android.view.View;
import android.widget.LinearLayout;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import jannonx.com.googleplay.R;
import jannonx.com.googleplay.base.BaseHolder;
import jannonx.com.googleplay.utils.UIUtils;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/5-下午6:29
 * @描述信息 desc
 */

public class holder extends BaseHolder<Integer> {
    public static final int LOADMORE_LOADING = 0;
    public static final int LOADMORE_RETRY = 1;
    public static final int LOADMORE_NONE = 2;
    @ViewInject(R.id.item_loadmore_container_loading)
    LinearLayout mLinearLayoutLoading;
    @ViewInject(R.id.item_loadmore_container_retry)
    LinearLayout mLinearLayoutRetry;

    @Override
    protected View initView() {
        View rootView = View.inflate(UIUtils.getContext(), R.layout.item_load_more, null);
        ViewUtils.inject(this, rootView);
        return null;
    }

    @Override
    protected void bindDateAndView(Integer data) {
        //隐藏所有
        mLinearLayoutLoading.setVisibility(View.GONE);
        mLinearLayoutRetry.setVisibility(View.GONE);
        switch (data) {
            case LOADMORE_LOADING:
                //显示加载视图
                mLinearLayoutLoading.setVisibility(View.VISIBLE);
                break;
            case LOADMORE_RETRY:
                //实现重新记载视图
                mLinearLayoutRetry.setVisibility(View.VISIBLE);
                break;
            case LOADMORE_NONE:
                //没有视图
                break;

            default:
                break;
        }

    }
}
