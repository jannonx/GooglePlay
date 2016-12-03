package jannonx.com.googleplay.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import jannonx.com.googleplay.holder.HomeHolder;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/3-下午6:11
 * @描述信息 desc
 */

public abstract class SuperBaseAdapter<ITEMBEANTYPE> extends BaseAdapter {

    private List<ITEMBEANTYPE> mData = new ArrayList<>();

    public SuperBaseAdapter(List<ITEMBEANTYPE> data) {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            /*------------------------- 决定根部局  -------------------------*/
        BaseHolder<ITEMBEANTYPE> homeHolder = null;
        if (convertView == null) {
            homeHolder = getSpecialHolder();
        } else {
            homeHolder = (BaseHolder) convertView.getTag();
        }

            /*------------------------- 得到数据  -------------------------*/
        //赋值
        ITEMBEANTYPE data = mData.get(position);

            /*------------------------- 视图和数据的绑定  -------------------------*/
        homeHolder.setDataAndBindView(data);

        return homeHolder.mHodlerView;
    }

    /**
     * @return BaseHolder的具体子类对象
     * @desc 必须实现，但是不知道具体的实现，定义成为抽象方法，交给子类集体实现
     */
    protected abstract BaseHolder<ITEMBEANTYPE> getSpecialHolder();


}
