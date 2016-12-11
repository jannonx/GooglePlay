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
import jannonx.com.googleplay.base.LoadingPager;
import jannonx.com.googleplay.base.SuperBaseAdapter;
import jannonx.com.googleplay.bean.SubjectInfoBean;
import jannonx.com.googleplay.factory.ListViewFactory;
import jannonx.com.googleplay.holder.SubjectHolder;
import jannonx.com.googleplay.protocol.SubjectProtocol;
import jannonx.com.googleplay.utils.UIUtils;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/1-下午7:11
 * @描述信息 desc
 */

public class SubjectFragment extends BaseFragment {

    private SubjectProtocol mProtocol;
    private List<SubjectInfoBean> mData;

    /**
     * @desc 正在开始加载数据，在子线程中执行
     * @call 外界调用LoadingPager的triggerLoadDate()的时候
     */

    @Override
    protected View initSuccessView() {
        ListView listView = ListViewFactory.create();
        listView.setAdapter(new SubjectAdapter(listView, mData));
        return listView;
    }

    /**
     * @desc 正在开始加载数据，在子线程中执行
     * @call 外界调用LoadingPager的triggerLoadDate()的时候
     */

    @Override
    protected LoadingPager.LoadedResult initData() {

        mProtocol = new SubjectProtocol();
        try {
            mData = mProtocol.getLoadData(0);
            return checkState(mData);
        } catch (Exception e) {
            e.printStackTrace();
            return LoadingPager.LoadedResult.ERROR;
        }
    }


    class SubjectAdapter extends SuperBaseAdapter<SubjectInfoBean> {


        public SubjectAdapter(AbsListView absListView, List<SubjectInfoBean> data) {
            super(absListView, data);
        }

        @Override
        protected BaseHolder<SubjectInfoBean> getSpecialHolder() {
            return new SubjectHolder();
        }

        @Override
        protected boolean hasLoadMore() {
            return false;
        }

//        @Override
//        protected void onNormalItemClick(AdapterView<?> parent, View view, int position, long id) {
//            Toast.makeText(UIUtils.getContext(),mData.get(position).des,Toast.LENGTH_SHORT).show();
//        }
    }
}