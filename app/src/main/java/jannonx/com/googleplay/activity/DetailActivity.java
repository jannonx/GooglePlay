package jannonx.com.googleplay.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import butterknife.BindView;
import jannonx.com.googleplay.R;
import jannonx.com.googleplay.base.LoadingPager;
import jannonx.com.googleplay.bean.AppInfoBean;
import jannonx.com.googleplay.holder.AppdetailDesHolder;
import jannonx.com.googleplay.holder.AppdetailDownloadHolder;
import jannonx.com.googleplay.holder.AppdetailInfoHolder;
import jannonx.com.googleplay.holder.AppdetailPicHolder;
import jannonx.com.googleplay.holder.AppdetailSafeHolder;
import jannonx.com.googleplay.protocol.DetailAppProtocol;
import jannonx.com.googleplay.utils.UIUtils;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/12-下午8:19
 * @描述信息 desc
 */
public class DetailActivity extends Activity {

    @ViewInject(R.id.fl_appdetail_download)
    FrameLayout mFlAppdetailDownload;
    @ViewInject(R.id.fl_appdetail_info)
    FrameLayout mFlAppdetailInfo;
    @ViewInject(R.id.fl_appdetail_safe)
    FrameLayout mFlAppdetailSafe;
    @ViewInject(R.id.fl_appdetail_pic)
    FrameLayout mFlAppdetailPic;
    @ViewInject(R.id.fl_appdetail_des)
    FrameLayout mFlAppdetailDes;

    private String mPackageName;
    private LoadingPager mLoadingPager;
    private AppInfoBean mAppInfoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        initView();
        initData();
        initListener();
    }

    private void initListener() {

    }

    private void initData() {
        //触发加载
        mLoadingPager.triggerLoadDate();

    }

    private void initView() {
        //四个页面
        mLoadingPager = new LoadingPager(UIUtils.getContext()) {
            @Override
            protected View initSuccessView() {
                return DetailActivity.this.initSuccessView();
            }

            @Override
            protected LoadedResult initData() {
                return DetailActivity.this.loadData();
            }
        };
        //设置页面
        setContentView(mLoadingPager);


    }

    private LoadingPager.LoadedResult loadData() {
        DetailAppProtocol protocol = new DetailAppProtocol(mPackageName);
        try {
            mAppInfoBean = protocol.getLoadData(0);
            if (mAppInfoBean != null) {
                return LoadingPager.LoadedResult.SUCCESS;
            } else {
                return LoadingPager.LoadedResult.EMPTY;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return LoadingPager.LoadedResult.ERROR;
        }
    }

    private View initSuccessView() {
        View rootView = View.inflate(UIUtils.getContext(), R.layout.item_detail, null);
        ViewUtils.inject(this, rootView);


        //<!--应用的信息部分-->
        AppdetailInfoHolder appdetailInfoHolder = new AppdetailInfoHolder();
        mFlAppdetailInfo.addView(appdetailInfoHolder.mHodlerView);
        appdetailInfoHolder.setDataAndBindView(mAppInfoBean);

        //<!--应用的安全部分-->
        AppdetailSafeHolder appdetailSafeHolder = new AppdetailSafeHolder();
        mFlAppdetailSafe.addView(appdetailSafeHolder.mHodlerView);
        appdetailSafeHolder.setDataAndBindView(mAppInfoBean);

        //<!--应用的截图部分-->
        AppdetailPicHolder appdetailPicHolder = new AppdetailPicHolder();
        mFlAppdetailPic.addView(appdetailPicHolder.mHodlerView);
        appdetailPicHolder.setDataAndBindView(mAppInfoBean);

        //<!--应用的描述部分-->
        AppdetailDesHolder appdetailDesHolder = new AppdetailDesHolder();
        mFlAppdetailDes.addView(appdetailDesHolder.mHodlerView);
        appdetailDesHolder.setDataAndBindView(mAppInfoBean);


        //<!--应用的下载部分-->
        AppdetailDownloadHolder appdetailDownloadHolder = new AppdetailDownloadHolder();
        mFlAppdetailDownload.addView(appdetailDownloadHolder.mHodlerView);
        appdetailDownloadHolder.setDataAndBindView(mAppInfoBean);


        return rootView;
    }

    private void init() {
        //包名
        mPackageName = getIntent().getStringExtra("packageName");
    }
}
