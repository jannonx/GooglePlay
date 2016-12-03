package jannonx.com.googleplay.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jannonx.com.googleplay.R;
import jannonx.com.googleplay.fragment.HomeFragment;
import jannonx.com.googleplay.utils.UIUtils;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/3-下午6:11
 * @描述信息 desc
 */

public abstract class MyBaseAdapter<ITEMBEANTYPE> extends BaseAdapter {

    private List<ITEMBEANTYPE> mData = new ArrayList<>();

    public MyBaseAdapter(List<ITEMBEANTYPE> data) {
        mData = data;
    }

    @Override
    public int getCount() {
        if (mData != null) {
            return mData.size();
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


}
