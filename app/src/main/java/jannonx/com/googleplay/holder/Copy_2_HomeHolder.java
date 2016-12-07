package jannonx.com.googleplay.holder;

import android.view.View;
import android.widget.TextView;

import jannonx.com.googleplay.R;
import jannonx.com.googleplay.base.BaseHolder;
import jannonx.com.googleplay.utils.UIUtils;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/3-下午6:37
 * @描述信息 1.提供视图 2.接收数据 3. 数据和视图的绑定
 */

public class Copy_2_HomeHolder extends BaseHolder<String> {

    private TextView mTv1;
    private TextView mTv2;

    @Override
    protected View initView() {
        // 1.加载根布局
        View rootView = View.inflate(UIUtils.getContext(), R.layout.item_temp, null);
        // 2.记载个布局的孩子
        mTv1 = (TextView) rootView.findViewById(R.id.tmp_tv1);
        mTv2 = (TextView) rootView.findViewById(R.id.tmp_tv2);
        return rootView;
    }

    @Override
    protected void bindDateAndView(String data) {
        mTv1.setText("final文本头部" + data);
        mTv2.setText("final文本头部" + data);
    }

}
