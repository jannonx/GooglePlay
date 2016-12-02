package jannonx.com.googleplay.fragment;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.v4.app.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

import jannonx.com.googleplay.base.BaseFragment;
import jannonx.com.googleplay.base.LoadingPager;
import jannonx.com.googleplay.utils.UIUtils;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/1-下午7:11
 * @描述信息 desc
 */

public class RecommandFragment extends BaseFragment {

    /**
     * @desc 正在开始加载数据，在子线程中执行
     * @call 外界调用LoadingPager的triggerLoadDate()的时候
     */

    @Override
    protected View initSuccessView() {
        TextView textView = new TextView(UIUtils.getContext());
        textView.setText(this.getClass().getSimpleName());
        textView.setGravity(Gravity.CENTER);
        return textView;
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

        LoadingPager.LoadedResult[] loadedResults = {LoadingPager.LoadedResult.EMPTY,
                LoadingPager.LoadedResult.ERROR, LoadingPager.LoadedResult.SUCCESS};

        Random random = new Random();
        int nextInt = random.nextInt(loadedResults.length);
        return loadedResults[nextInt];
    }
}