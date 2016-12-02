package jannonx.com.googleplay.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/2-下午3:54
 * @描述信息 Fragment的基类
 */

public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        init();
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initData();
        initListener();
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * @desc 初始化
     * @call 子类可以选择性复写的方法
     */
    protected void init() {
    }

    /**
     * @desc 初始化视图
     * @call 子类必须复写的方法
     */
    protected abstract View initView();

    /**
     * @desc 初始化数据
     * @call 子类可以选择性复写的方法
     */
    protected void initData() {

    }

    /**
     * @desc 初始化监听
     * @call 子类可以选择性复写的方法
     */

    private void initListener() {
    }
}
