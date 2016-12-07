package jannonx.com.googleplay.utils;

import android.view.View;

import com.lidroid.xutils.BitmapUtils;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/4-下午9:28
 * @描述信息 desc
 */

public class BitmapUtilsHelper {

    static BitmapUtils bitmapUtils;

    //只加载一次
    static {
        bitmapUtils = new BitmapUtils(UIUtils.getContext());

    }

    public static <T extends View> void display(T container, String uri) {
        bitmapUtils.display(container, uri);
    }
}
