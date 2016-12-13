package jannonx.com.googleplay.fragment;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.v4.app.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import jannonx.com.googleplay.base.BaseFragment;
import jannonx.com.googleplay.base.BaseHolder;
import jannonx.com.googleplay.base.BaseItemAdapter;
import jannonx.com.googleplay.base.LoadingPager;
import jannonx.com.googleplay.base.SuperBaseAdapter;
import jannonx.com.googleplay.bean.AppInfoBean;
import jannonx.com.googleplay.factory.ListViewFactory;
import jannonx.com.googleplay.holder.AppHolder;
import jannonx.com.googleplay.protocol.AppProtocol;
import jannonx.com.googleplay.utils.UIUtils;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/1-下午7:11
 * @描述信息 desc
 */

public class AppFragment extends BaseFragment {


    private AppProtocol mProtocol;
    private List<AppInfoBean> mDatas;

    /**
     * @desc 正在开始加载数据，在子线程中执行
     * @call 外界调用LoadingPager的triggerLoadDate()的时候
     */

    @Override
    protected View initSuccessView() {
        ListView listView = ListViewFactory.create();
        listView.setAdapter(new AppAdapter(listView, mDatas));
        return listView;
    }

    /**
     * @desc 正在开始加载数据，在子线程中执行
     * @call 外界调用LoadingPager的triggerLoadDate()的时候
     */

    @Override
    protected LoadingPager.LoadedResult initData() {
        SystemClock.sleep(2000);
//        private static final int STATE_ERRORVIEW = 1;//失败
//        private static final int STATE_EMPTYVIEW = 2;//空
//        private static final int STATE_SUCCESSVIEW = 3;//成功
        mProtocol = new AppProtocol();
        try {
            mDatas = mProtocol.getLoadData(0);
            return checkState(mDatas);
        } catch (Exception e) {
            e.printStackTrace();
            return LoadingPager.LoadedResult.ERROR;
        }
    }


    class AppAdapter extends BaseItemAdapter {

        public AppAdapter(AbsListView absListView, List<AppInfoBean> data) {
            super(absListView, data);
        }

        @Override
        protected List<AppInfoBean> onLoadMore() throws Exception {
            List<AppInfoBean> loadData = mProtocol.getLoadData(mDatas.size());
            return loadData;
        }

    }

}
