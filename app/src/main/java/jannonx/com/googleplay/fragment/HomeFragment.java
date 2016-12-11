package jannonx.com.googleplay.fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.List;

import jannonx.com.googleplay.base.BaseFragment;
import jannonx.com.googleplay.base.BaseHolder;
import jannonx.com.googleplay.base.LoadingPager;
import jannonx.com.googleplay.base.SuperBaseAdapter;
import jannonx.com.googleplay.bean.AppInfoBean;
import jannonx.com.googleplay.bean.HomeBean;
import jannonx.com.googleplay.conf.Constants;
import jannonx.com.googleplay.factory.ListViewFactory;
import jannonx.com.googleplay.holder.HomeHolder;
import jannonx.com.googleplay.holder.HomePictureHolder;
import jannonx.com.googleplay.protocol.HomeProtocol;
import jannonx.com.googleplay.utils.OkUtils;
import jannonx.com.googleplay.utils.UIUtils;


/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/1-下午7:11
 * @描述信息 desc
 */

public class HomeFragment extends BaseFragment {

    private List<AppInfoBean> mAppInfoBeens;
    private List<String> mPictures;
    private HomeProtocol mProtocol;


    /**
     * @desc 正在开始加载数据，在子线程中执行
     * @call 外界调用LoadingPager的triggerLoadDate()的时候
     */

    @Override
    protected View initSuccessView() {
        ListView listView = ListViewFactory.create();

        /**设置listview的head部分*/
        HomePictureHolder homePictureHolder = new HomePictureHolder();
        listView.addHeaderView(homePictureHolder.mHodlerView);
        homePictureHolder.setDataAndBindView(mPictures);



        listView.setAdapter(new HomeAdapter(listView, mAppInfoBeens));

        return listView;
    }

    /**
     * @desc 正在开始加载数据，在子线程中执行
     * @call 外界调用LoadingPager的triggerLoadDate()的时候
     */

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected LoadingPager.LoadedResult initData() {

        try {
            /**------------------------- okhttp  begin -------------------------*/

//            String url = Constants.URLS.BASEURL + "home?index="+0;
//            String stringFromServer = OkUtils.getStringFromServer(url);
//            Gson gson = new Gson();
//            HomeBean homeBean = gson.fromJson(stringFromServer, HomeBean.class);


            /**------------------------- HttpUtils begin  -------------------------*/

//            HttpUtils httpUtils = new HttpUtils();
//            String url = Constants.URLS.BASEURL + "home";
//            RequestParams params = new RequestParams();
//            params.addQueryStringParameter("index", "0");
//            ResponseStream responseStream = httpUtils.sendSync(HttpRequest.HttpMethod.GET, url, params);
//            String s = responseStream.readString();
//            Gson gson = new Gson();
//            HomeBean homeBean = gson.fromJson(s, HomeBean.class);


            /**------------------------- HomeProtocoll begin  -------------------------*/

            mProtocol = new HomeProtocol();
            HomeBean homeBean = mProtocol.getLoadData(0);


            //处理json数据
            LoadingPager.LoadedResult state = checkState(homeBean);


            if (state != LoadingPager.LoadedResult.SUCCESS) {//有问题  homeBean==null
                return state;
            }

            state = checkState(homeBean.list);


            if (state != LoadingPager.LoadedResult.SUCCESS) {//  homeBean.list.size()==0
                return state;
            }

            //赋值
            mAppInfoBeens = homeBean.list;
            mPictures = homeBean.picture;

            return state;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return LoadingPager.LoadedResult.SUCCESS;

    }


    class HomeAdapter extends SuperBaseAdapter<AppInfoBean> {

        public HomeAdapter(AbsListView absListView, List<AppInfoBean> data) {
            super(absListView, data);
        }

        @Override
        protected BaseHolder<AppInfoBean> getSpecialHolder() {
            return new HomeHolder();
        }

        @Override
        protected List<AppInfoBean> onLoadMore() throws Exception {
            return performLoadMore();
        }

        @Override
        protected void onNormalItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(UIUtils.getContext(), mAppInfoBeens.get(position).packageName, Toast.LENGTH_SHORT).show();
        }

        private List<AppInfoBean> performLoadMore() throws Exception {

//            String url = Constants.URLS.BASEURL + "home?index=" + mAppInfoBeens.size();
//            String stringFromServer = OkUtils.getStringFromServer(url);
//            Gson gson = new Gson();
//            HomeBean homeBean = gson.fromJson(stringFromServer, HomeBean.class);


            /**------------------------- HomeProtocoll begin  -------------------------*/

            HomeBean homeBean = mProtocol.getLoadData(mAppInfoBeens.size());

            /**-------------------------  HomeProtocoll end -------------------------*/
            if (homeBean != null) {
                return homeBean.list;
            }

            return super.onLoadMore();
        }
    }
}
