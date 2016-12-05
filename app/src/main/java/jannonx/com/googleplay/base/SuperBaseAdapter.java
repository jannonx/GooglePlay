package jannonx.com.googleplay.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import jannonx.com.googleplay.factory.ThreadPoolExecutorProxyFactory;
import jannonx.com.googleplay.holder.LoadMoreHolder;
import jannonx.com.googleplay.utils.UIUtils;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/3-下午6:11
 * @描述信息 desc
 */

public abstract class SuperBaseAdapter<ITEMBEANTYPE> extends BaseAdapter implements AdapterView.OnItemClickListener {

    private static final int VIEWTYPE_LOADMORE = 0;
    private static final int VIEWTYPE_NORMAL = 1;
    private static final int PAGERSIZE = 20;
    private List<ITEMBEANTYPE> mData = new ArrayList<>();
    private LoadMoreHolder mLoadMoreHolder;
    private LoadMoreTask mTask;
    private AbsListView mAbsListView;

    public SuperBaseAdapter(AbsListView absListView, List<ITEMBEANTYPE> data) {
        super();
        mLoadMoreHolder = new LoadMoreHolder();
        mData = data;
        mAbsListView = absListView;
        mAbsListView.setOnItemClickListener(this);
    }

    @Override
    public int getCount() {
        if (mData != null) {
            return mData.size() + 1;
        }
        return 0;
    }

    @Override

    public Object getItem(int position) {
        if (mData != null) {
            return mData.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /**
         * 系统返回converView之前，首先会判断即将显示的ItemViewType是否有缓存
         */
            /*------------------------- 决定根部局  -------------------------*/
        BaseHolder<ITEMBEANTYPE> homeHolder = null;
        if (convertView == null) {
            if (getItemViewType(position) == VIEWTYPE_LOADMORE) {
                //加载更多
                homeHolder = (BaseHolder<ITEMBEANTYPE>) getLoadMoreHolder();
            } else {
                //普通视图
                homeHolder = getSpecialHolder();
            }
        } else {
            homeHolder = (BaseHolder) convertView.getTag();
        }

            /*------------------------- 视图和数据的绑定  -------------------------*/
        if (getItemViewType(position) == VIEWTYPE_LOADMORE) {//加载更多视图
            if (hasLoadMore()) {
                //记载更多数据
                triggerLoadMore();
            } else {
                //没有更多数据了
                mLoadMoreHolder.setDataAndBindView(LoadMoreHolder.LOADMORE_NONE);
            }
        } else {//普通视图
            //赋值
            ITEMBEANTYPE data = mData.get(position);
            homeHolder.setDataAndBindView(data);
        }

        return homeHolder.mHodlerView;
    }

    /*------------------------- 加载更多相关逻辑 begin -------------------------*/

    /**
     * @desc 触发加载更多的数据
     * @call 滑到底部，而且有记载更多的情况
     */

    private void triggerLoadMore() {
        //加载没有完成，禁止再次加载
        if (mTask == null) {
            int state = LoadMoreHolder.LOADMORE_LOADING;
            mLoadMoreHolder.setDataAndBindView(state);
            //异步加载数据
            mTask = new LoadMoreTask();
            ThreadPoolExecutorProxyFactory.getNormalThreadPoolExecutorProxy().execute(mTask);
        }
    }

    /**
     * @return
     * @des 真正在子线程中加载更多的数据
     * @des 默认返回null，但是子类可以选择性的复写该方法，返回具体的记载更多之后的数据
     */

    protected List<ITEMBEANTYPE> onLoadMore() throws Exception {
        return null;//默认返回为空
    }

    /**
     * @return
     * @des 决定是否有更多，子类可以覆写该方法，修改返回值
     */

    protected boolean hasLoadMore() {
        return true;
    }

    /**
     * 获得LoadMoreHolder对象引用
     *
     * @return
     */
    private LoadMoreHolder getLoadMoreHolder() {
        if (mLoadMoreHolder == null) {
            mLoadMoreHolder = new LoadMoreHolder();
        }
        return mLoadMoreHolder;
    }

    /*------------------------- 加载更多相关逻辑 end -------------------------*/

    @Override
    public int getItemViewType(int position) {
        if (position == getCount() - 1) {
            return VIEWTYPE_LOADMORE;
        } else {
            return VIEWTYPE_NORMAL;
        }
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount() + 1;
    }

    /**
     * @return BaseHolder的具体子类对象
     * @desc 必须实现，但是不知道具体的实现，定义成为抽象方法，交给子类集体实现
     */
    protected abstract BaseHolder<ITEMBEANTYPE> getSpecialHolder();

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //判断item的类型
        if (getItemViewType(position) == VIEWTYPE_LOADMORE) {
            triggerLoadMore();
        } else {
            //普通类item点击
            onNormalItemClick(parent, view, position, id);
        }
    }

    /**
     * @des 子类可以选择性覆写的方法，普通类的item的点击事件
     */

    protected void onNormalItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private class LoadMoreTask implements Runnable {
        @Override
        public void run() {
            int state;
            List<ITEMBEANTYPE> loadMoreData = null;
            //正在的在子线程中加载更多
            try {

                loadMoreData = onLoadMore();
                if (loadMoreData.size() < PAGERSIZE) {
                    state = LoadMoreHolder.LOADMORE_NONE;//没有加载更多
                } else {
                    state = LoadMoreHolder.LOADMORE_LOADING;//加载更多

                }
            } catch (Exception e) {
                e.printStackTrace();
                state = LoadMoreHolder.LOADMORE_RETRY;
            }

            //临时变量
            final int tempState = state;
            final List<ITEMBEANTYPE> tempLoadMoreData = loadMoreData;


            //刷下ui
            //1.listView---更新数据集  --notifa
            UIUtils.postTaskSafely(new Runnable() {
                @Override
                public void run() {
                    if (tempLoadMoreData != null) {
                        mData.addAll(tempLoadMoreData);
                        notifyDataSetChanged();//adapter的操作
                    }

                    //更新LoadMoreHolder的界面
                    mLoadMoreHolder.setDataAndBindView(tempState);
                }
            });
            mTask = null;
        }


    }
}
