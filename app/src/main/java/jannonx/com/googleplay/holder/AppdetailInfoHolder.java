package jannonx.com.googleplay.holder;

import android.provider.Contacts;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import butterknife.BindView;
import jannonx.com.googleplay.R;
import jannonx.com.googleplay.base.BaseHolder;
import jannonx.com.googleplay.bean.AppInfoBean;
import jannonx.com.googleplay.conf.Constants;
import jannonx.com.googleplay.utils.BitmapUtilsHelper;
import jannonx.com.googleplay.utils.StringUtils;
import jannonx.com.googleplay.utils.UIUtils;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/12-下午11:33
 * @描述信息 desc
 */
public class AppdetailInfoHolder extends BaseHolder<AppInfoBean> {
    @ViewInject(R.id.app_detail_info_iv_icon)
    ImageView mAppDetailInfoIvIcon;
    @ViewInject(R.id.app_detail_info_tv_name)
    TextView mAppDetailInfoTvName;
    @ViewInject(R.id.app_detail_info_rb_star)
    RatingBar mAppDetailInfoRbStar;
    @ViewInject(R.id.app_detail_info_tv_downloadnum)
    TextView mAppDetailInfoTvDownloadnum;
    @ViewInject(R.id.app_detail_info_tv_version)
    TextView mAppDetailInfoTvVersion;
    @ViewInject(R.id.app_detail_info_tv_time)
    TextView mAppDetailInfoTvTime;
    @ViewInject(R.id.app_detail_info_tv_size)
    TextView mAppDetailInfoTvSize;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_detail_info, null);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    protected void bindDateAndView(AppInfoBean data) {
//        UIUtils.getContext().getResources().getString()

        /**日期：%1$s 占位符的使用*/
        String info_data = UIUtils.getString(R.string.info_date, data.date);
        String info_downloadNum = UIUtils.getString(R.string.info_downloadnum, data.downloadNum);
        String info_size = UIUtils.getString(R.string.info_size, StringUtils.formatFileSize(data.size));
        String info_version = UIUtils.getString(R.string.info_version, data.version);


        //应用图标
        mAppDetailInfoIvIcon.setImageResource(R.drawable.ic_default);
        BitmapUtilsHelper.display(mAppDetailInfoIvIcon, Constants.URLS.IMAGEURL + data.iconUrl);
        //应用名
        mAppDetailInfoTvName.setText(data.name);
        //应用星级
        mAppDetailInfoRbStar.setRating(data.stars);
        //应用下载量
        mAppDetailInfoTvDownloadnum.setText(info_downloadNum);
        //应用版本
        mAppDetailInfoTvVersion.setText(info_version);
        //应用更新日期
        mAppDetailInfoTvTime.setText(info_data);
        //应用大小
        mAppDetailInfoTvSize.setText(info_size);

    }
}
