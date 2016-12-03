package jannonx.com.googleplay.base;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import jannonx.com.googleplay.R;
import jannonx.com.googleplay.factory.ThreadPoolExecutorProxyFactory;
import jannonx.com.googleplay.utils.UIUtils;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/2-下午4:22
 * @描述信息 1.放置常见的四个页面, 加载页面，错误页面，空页面，成功页面
 */

public abstract class LoadingPager extends FrameLayout {

    public static final int STATE_LOADINGVIEW = 0;//加载
    public static final int STATE_ERRORVIEW = 1;//失败
    public static final int STATE_EMPTYVIEW = 2;//空
    public static final int STATE_SUCCESSVIEW = 3;//成功
    public static final int STATE_NONE = 4;//成功
    private View mLodingView;
    private View mErrorView;
    private View mSuccessView;
    private View mEmptyView;
    private int mCurrentPage = STATE_NONE;

    public LoadingPager(Context context) {
        super(context);
        initCommonViews();
    }

    /**
     * @desc 初始化常规视图(加载页面，错误页面，空页面)
     * @call LoadingPager初始化
     */
    private void initCommonViews() {
        //加载页面，
        mLodingView = View.inflate(UIUtils.getContext(), R.layout.pager_loading, null);
        this.addView(mLodingView);
        // 错误页面，
        mErrorView = View.inflate(UIUtils.getContext(), R.layout.pager_error, null);
        this.addView(mErrorView);
        // 空页面
        mEmptyView = View.inflate(UIUtils.getContext(), R.layout.pager_empty, null);
        this.addView(mEmptyView);


        refreshUIByState();
    }

    /**
     * @desc 根据当前状态，显示不同的ui
     * @call 1. LoadingPager初始化的时候
     * @call 2.
     */

    private void refreshUIByState() {
        //控制加载视图的显示  0  显示  8 隐藏
        mLodingView.setVisibility((mCurrentPage == STATE_LOADINGVIEW) || (mCurrentPage == STATE_NONE) ? View.VISIBLE : View.GONE);
        //控制错误视图的显示  0  显示  8 隐藏
        mErrorView.setVisibility((mCurrentPage == STATE_ERRORVIEW) ? View.VISIBLE : View.GONE);

        //重新加载数据的点击事件
        mErrorView.findViewById(R.id.error_btn_refresh).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //重新加载数据
                triggerLoadDate();
            }
        });
        //控制空视图的显示  0  显示  8 隐藏
        mEmptyView.setVisibility((mCurrentPage == STATE_EMPTYVIEW) ? View.VISIBLE : View.GONE);

        //数据加载成功了，而且成功视图没有
        if (mCurrentPage == STATE_SUCCESSVIEW && mSuccessView == null) {
            mSuccessView = initSuccessView();
            this.addView(mSuccessView);
        }

        if (mSuccessView != null) {
            mSuccessView.setVisibility((mCurrentPage == STATE_SUCCESSVIEW) ? View.VISIBLE : View.GONE);
        }

    }

    /**
     * @desc 创建视图成功
     * @desc BaseFragment必须实现，但是不知道具体实现，定义成为抽象方法，交给BaseFragmen子类具体实现
     * @desc 它是和Loading中定义的方法是同名
     * @call 外界调用triggerLoadDate的时候
     */
    protected abstract View initSuccessView();

    /**
     * @desc 触发异步加载数据
     * @call 外界需要触发加载数据的时候调用该方法
     */
    public void triggerLoadDate() {
        //如果状态不处在加载成功的状态&&不处于正在加载中，就可以重新加载
        if (mCurrentPage != STATE_SUCCESSVIEW && mCurrentPage != STATE_LOADINGVIEW) {
            //重置当前的状态
            mCurrentPage = STATE_LOADINGVIEW;
            refreshUIByState();
            //异步加载数据
//            new Thread(new LoadDataTask()).start();
            //交给工厂处理
            ThreadPoolExecutorProxyFactory.getNormalThreadPoolExecutorProxy().execute(new LoadDataTask());
        }
    }

    /**
     * @desc 正在开始加载数据，在子线程中执行
     * @desc BaseFragment必须实现，但是不知道具体实现，定义成为抽象方法，交给BaseFragmen子类具体实现
     * @desc 它是和Loading中定义的方法是同名
     * @call 外界调用triggerLoadDate的时候
     */
    protected abstract LoadedResult initData();


    public enum LoadedResult {
        SUCCESS(STATE_SUCCESSVIEW), EMPTY(STATE_EMPTYVIEW), ERROR(STATE_ERRORVIEW);
        int state;

        LoadedResult(int state) {
            this.state = state;
        }
    }

    private class LoadDataTask implements Runnable {
        @Override
        public void run() {
            //真正开始记载数据
            LoadedResult temState = initData();
            //赋值临时状态给当前的状态
            mCurrentPage = temState.state;

            UIUtils.postTaskSafely(new Runnable() {
                @Override
                public void run() {
                    //刷新ui
                    refreshUIByState();
                }
            });
        }
    }
}
