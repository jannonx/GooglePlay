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
 * @创建时间 2016/12/5-下午5:02
 * @描述信息 desc
 */

public class LoadMoreHolder extends BaseHolder<Integer> {

    /**
     * loadmore视图有几种显示情况
     * 1.显示加载
     * 2.显示重试
     * 3.都不显示(没有更多)
     */
    public static final int LOADMORE_LOADING = 0;
    public static final int LOADMORE_RETRY = 1;
    public static final int LOADMORE_NONE = 2;
    @ViewInject(R.id.item_loadmore_container_loading)
    LinearLayout mContainerLoading;
    @ViewInject(R.id.item_loadmore_container_retry)
    LinearLayout mContainerRetry;

    @Override
    protected View initView() {
        View rootView = View.inflate(UIUtils.getContext(), R.layout.item_load_more, null);
        ViewUtils.inject(this, rootView);
        return rootView;
    }

    @Override
    protected void bindDateAndView(Integer curState) {
        // 隐藏所有
        mContainerLoading.setVisibility(View.GONE);
        mContainerRetry.setVisibility(View.GONE);

        switch (curState) {
            case LOADMORE_LOADING:// 显示加载视图
                mContainerLoading.setVisibility(View.VISIBLE);
                break;
            case LOADMORE_RETRY:// 显示重试视图
                mContainerRetry.setVisibility(View.VISIBLE);
                break;
            case LOADMORE_NONE:// 啥也不显示

                break;

            default:
                break;
        }
    }


}
