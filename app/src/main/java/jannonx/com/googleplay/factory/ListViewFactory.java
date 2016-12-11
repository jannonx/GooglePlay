package jannonx.com.googleplay.factory;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ListView;

import jannonx.com.googleplay.utils.UIUtils;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/7-下午5:30
 * @描述信息 创建ListView的工厂类
 */

public class ListViewFactory {

    public static ListView create() {
        ListView listView = new ListView(UIUtils.getContext());
        //背景无颜色
        listView.setCacheColorHint(Color.TRANSPARENT);
        //可以快速滑动
//        listView.setFastScrollEnabled(true);
        //选择无颜色
        listView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        return listView;
    }
}
