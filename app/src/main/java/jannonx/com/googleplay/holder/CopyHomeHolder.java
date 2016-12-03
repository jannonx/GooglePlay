package jannonx.com.googleplay.holder;

import android.view.View;
import android.widget.TextView;

import jannonx.com.googleplay.R;
import jannonx.com.googleplay.utils.UIUtils;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/3-下午6:37
 * @描述信息 1.提供视图 2.接收数据 3. 数据和视图的绑定
 */

public class CopyHomeHolder {
    public View mHodlerView;
    private TextView mTv1;
    private TextView mTv2;
    private String data;

    public CopyHomeHolder() {
        mHodlerView = initView();
        //设置标记
        mHodlerView.setTag(this);
    }

    private View initView() {
        // 1.加载根布局
        View rootView = View.inflate(UIUtils.getContext(), R.layout.item_temp, null);
        // 2.记载个布局的孩子
        mTv1 = (TextView) rootView.findViewById(R.id.tmp_tv1);
        mTv2 = (TextView) rootView.findViewById(R.id.tmp_tv2);
        return rootView;
    }

    /**
     * 传入数据
     */
    public void setDataAndBindView(String data) {
        this.data = data;
        bindDateAndView(data);

    }

    /**
     * 视图和数据的绑定
     */
    private void bindDateAndView(String data) {
        mTv1.setText("文本头部" + data);
        mTv2.setText("文本头部" + data);
    }
}
