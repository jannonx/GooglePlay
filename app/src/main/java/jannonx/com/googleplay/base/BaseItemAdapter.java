package jannonx.com.googleplay.base;

import android.content.Intent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import java.util.List;

import jannonx.com.googleplay.activity.DetailActivity;
import jannonx.com.googleplay.bean.AppInfoBean;
import jannonx.com.googleplay.holder.ItemHolder;
import jannonx.com.googleplay.utils.UIUtils;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/12-下午8:17
 * @描述信息 desc
 */

public class BaseItemAdapter extends SuperBaseAdapter<AppInfoBean> {
    public BaseItemAdapter(AbsListView absListView, List<AppInfoBean> data) {
        super(absListView, data);
    }

    @Override
    protected BaseHolder<AppInfoBean> getSpecialHolder(int position) {
        return new ItemHolder();
    }

    @Override
    protected void onNormalItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(UIUtils.getContext(), DetailActivity.class);
        //packageName packageName
        intent.putExtra("packageName", mData.get(position).packageName);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        UIUtils.getContext().startActivity(intent);
    }
}
