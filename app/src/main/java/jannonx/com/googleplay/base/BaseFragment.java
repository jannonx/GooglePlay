package jannonx.com.googleplay.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.List;
import java.util.Map;

import jannonx.com.googleplay.utils.LogUtils;
import jannonx.com.googleplay.utils.UIUtils;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/2-下午4:11
 * @描述信息 desc
 */

public abstract class BaseFragment extends Fragment {

    private LoadingPager mLoadingPager;

    public LoadingPager getLoadingPager() {
        return mLoadingPager;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**
         * @desc 真正开始加载数据，在子线程中执行
         * @desc 必须实现，但是不知道具体实现，定义成为抽象方法，交给子类具体实现
         * @call 外界调用triggerLoadDate的时候
         *
         */
        if (mLoadingPager == null) {//第一次走

            mLoadingPager = new LoadingPager(UIUtils.getContext()) {
                @Override
                protected View initSuccessView() {
                    return BaseFragment.this.initSuccessView();
                }

                @Override
                protected LoadedResult initData() {
                    return BaseFragment.this.initData();
                }
            };
        } else {//第二次进来
            ViewParent parent = mLoadingPager.getParent();
            if (parent != null && parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(mLoadingPager);
            }
        }

        //刷新
//        loadingPager.triggerLoadDate();

        return mLoadingPager;//---ViewGroup
    }

    protected abstract View initSuccessView();

    /**
     * @desc 真正开始加载数据，在子线程中执行
     * @desc 必须实现，但是不知道具体实现，定义成为抽象方法，交给子类具体实现
     * @call 外界调用triggerLoadDate的时候
     */

    protected abstract LoadingPager.LoadedResult initData();


    public LoadingPager.LoadedResult checkState(Object resResult) {

        if (resResult == null) {
            return LoadingPager.LoadedResult.EMPTY;
        }

        if (resResult instanceof List) {
            if (((List) resResult).size() == 0) {
                return LoadingPager.LoadedResult.EMPTY;
            }
        }

        if (resResult instanceof Map) {
            if (((Map) resResult).size() == 0) {
                return LoadingPager.LoadedResult.EMPTY;
            }
        }


        return LoadingPager.LoadedResult.SUCCESS;


    }
}
