package jannonx.com.googleplay.fragment;

import android.graphics.Color;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jannonx.com.googleplay.base.BaseFragment;
import jannonx.com.googleplay.base.BaseHolder;
import jannonx.com.googleplay.base.LoadingPager;
import jannonx.com.googleplay.base.MyBaseAdapter;
import jannonx.com.googleplay.base.SuperBaseAdapter;
import jannonx.com.googleplay.holder.HomeHolder;
import jannonx.com.googleplay.utils.UIUtils;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/1-下午7:11
 * @描述信息 desc
 */

public class HomeFragment extends BaseFragment {

    private List<String> mData;

    /**
     * @desc 正在开始加载数据，在子线程中执行
     * @call 外界调用LoadingPager的triggerLoadDate()的时候
     */

    @Override
    protected View initSuccessView() {
        ListView listView = new ListView(UIUtils.getContext());
        //常规设置
        listView.setCacheColorHint(Color.TRANSPARENT);
        //可以快速滑动
        listView.setFastScrollEnabled(true);
        listView.setAdapter(new HomeAdapter(mData));
        return listView;
    }

    /**
     * @desc 正在开始加载数据，在子线程中执行
     * @call 外界调用LoadingPager的triggerLoadDate()的时候
     */

    @Override
    protected LoadingPager.LoadedResult initData() {
        SystemClock.sleep(1000);
//        private static final int STATE_ERRORVIEW = 1;//失败
//        private static final int STATE_EMPTYVIEW = 2;//空
//        private static final int STATE_SUCCESSVIEW = 3;//成功

        mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mData.add(i + "");
        }

        LoadingPager.LoadedResult[] loadedResults = {LoadingPager.LoadedResult.EMPTY,
                LoadingPager.LoadedResult.ERROR, LoadingPager.LoadedResult.SUCCESS};

        Random random = new Random();
        int nextInt = random.nextInt(loadedResults.length);
        return loadedResults[2];
    }

    /**
     * HomeAdapter  最终版
     */
    private class HomeAdapter extends SuperBaseAdapter<String> {

        public HomeAdapter(List<String> data) {
            super(data);
        }

        @Override
        protected BaseHolder<String> getSpecialHolder() {
            return new HomeHolder();
        }
    }

    /**HomeAdapter  v2版*/

   /* private class HomeAdapter extends MyBaseAdapter {


        public HomeAdapter(List data) {
            super(data);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            *//*------------------------- 决定根部局  -------------------------*//*
            HomeHolder homeHolder = null;
            if (convertView == null) {
                homeHolder = new HomeHolder();
            } else {
                homeHolder = (HomeHolder) convertView.getTag();
            }

            *//*------------------------- 得到数据  -------------------------*//*
            //赋值
            String data = mData.get(position);

            *//*------------------------- 视图和数据的绑定  -------------------------*//*
            homeHolder.setDataAndBindView(data);

            return  homeHolder.mHodlerView;
        }
    }*/


    /**HomeAdapter  v1版*/

   /* private class HomeAdapter extends MyBaseAdapter {


        public HomeAdapter(List data) {
            super(data);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            *//*------------------------- 决定根部局  -------------------------*//*
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();

                //加载界面
                convertView = View.inflate(UIUtils.getContext(), R.layout.item_temp, null);

                viewHolder.tv_temp_tv1 = (TextView) convertView.findViewById(R.id.tmp_tv1);
                viewHolder.tv_temp_tv2 = (TextView) convertView.findViewById(R.id.tmp_tv2);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            *//*------------------------- 得到数据  -------------------------*//*
            //赋值
            String data = mData.get(position);

            *//*------------------------- 视图和数据的绑定  -------------------------*//*
            viewHolder.tv_temp_tv1.setText("文本头部" + data);
            viewHolder.tv_temp_tv2.setText("文本尾部" + data);
            return convertView;
        }
    }


    private class ViewHolder {
        TextView tv_temp_tv1;
        TextView tv_temp_tv2;
    }*/
}