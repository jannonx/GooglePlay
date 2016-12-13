package jannonx.com.googleplay.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import jannonx.com.googleplay.base.BaseFragment;
import jannonx.com.googleplay.base.BaseHolder;
import jannonx.com.googleplay.base.LoadingPager;
import jannonx.com.googleplay.base.SuperBaseAdapter;
import jannonx.com.googleplay.bean.CategoryBean;
import jannonx.com.googleplay.factory.ListViewFactory;
import jannonx.com.googleplay.holder.CategoryNormalHolder;
import jannonx.com.googleplay.holder.CategoryTitleHolder;
import jannonx.com.googleplay.protocol.CategoryProtocol;
import jannonx.com.googleplay.utils.LogUtils;
import jannonx.com.googleplay.utils.UIUtils;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/1-下午7:11
 * @描述信息 desc
 */

public class CategoryFragment extends BaseFragment {

    private List<CategoryBean> mData;

    /**
     * @desc 正在开始加载数据，在子线程中执行
     * @call 外界调用LoadingPager的triggerLoadDate()的时候
     */

    @Override
    protected View initSuccessView() {
        ListView listView = ListViewFactory.create();
        listView.setAdapter(new CategoryAdapter(listView, mData));
        return listView;
    }

    /**
     * @desc 正在开始加载数据，在子线程中执行
     * @call 外界调用LoadingPager的triggerLoadDate()的时候
     */

    @Override
    protected LoadingPager.LoadedResult initData() {
        CategoryProtocol protocol = new CategoryProtocol();
        try {
            mData = protocol.getLoadData(0);
            LogUtils.sf(mData.toString());
            return checkState(mData);
        } catch (Exception e) {
            e.printStackTrace();
            return LoadingPager.LoadedResult.ERROR;
        }
    }

    private class CategoryAdapter extends SuperBaseAdapter<CategoryBean> {

        public CategoryAdapter(AbsListView absListView, List<CategoryBean> data) {
            super(absListView, data);
        }

        @Override
        protected BaseHolder<CategoryBean> getSpecialHolder(int position) {
            CategoryBean categoryBean = mData.get(position);
            if (categoryBean.isTitle) {
                LogUtils.sf("CategoryTitleHolderCategoryTitleHolderCategoryTitleHolder....");
                return new CategoryTitleHolder();
            } else {
                return new CategoryNormalHolder();
            }

        }

        @Override
        protected boolean hasLoadMore() {
            return false;
        }

        @Override
        public int getViewTypeCount() {//2+1
            return super.getViewTypeCount() + 1;
        }

        @Override
        protected int getNormalItemViewType(int position) {
            CategoryBean categoryBean = mData.get(position);
            if (categoryBean.isTitle) {
                return 1;
            } else {
                return 2;
            }
        }
    }
}
