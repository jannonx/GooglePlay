package jannonx.com.googleplay.base;

import android.view.View;
import android.widget.TextView;

import jannonx.com.googleplay.R;
import jannonx.com.googleplay.utils.UIUtils;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/3-下午7:27
 * @描述信息 desc
 */

public abstract class BaseHolder<HOLDEEBEANTYPE> {
    public View mHodlerView;
    private HOLDEEBEANTYPE data;

    public BaseHolder() {
        mHodlerView = initView();
        //设置标记
        mHodlerView.setTag(this);
    }

    /**
     * @return
     * @desc 1.初始化根布局
     * @desc 2.初始化个布局的孩子
     * @desc 3.必须实现，但是不知道具体的实现，定义成为抽象方法，交给子类集体实现
     */
    protected abstract View initView();

    /**
     * 传入数据
     */
    public void setDataAndBindView(HOLDEEBEANTYPE data) {
        this.data = data;
        bindDateAndView(data);

    }

    /**
     * 视图和数据的绑定
     *
     * @desc 3.必须实现，但是不知道具体的实现，定义成为抽象方法，交给子类集体实现
     */
    protected abstract void bindDateAndView(HOLDEEBEANTYPE data);
}
